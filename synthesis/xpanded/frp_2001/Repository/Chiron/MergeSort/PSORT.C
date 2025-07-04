#include <stdio.h>
#include <fcntl.h>
#include <io.h>
#include <sys\types.h>
#include <sys\stat.h>
#include <malloc.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <dos.h>
#include <ctype.h>

#ifdef	WIN32
#include <direct.h>
#include <windows.h>
#endif

typedef unsigned char uchar;
typedef unsigned int uint;
typedef unsigned long ulong;

enum {FALSE, TRUE};

#define DEFAULT_ALPHA 5760
#define MAX_MALLOCS 4096
#define DEFAULT_MAX_MEGS 32
#define SAVE_SIZE 0xf800
#define TEMP_SIZE 0x8000
#define READ_SIZE 0xf800

/* note safety is used as ptrs from above approach data from below */
#define SAFETY READ_SIZE * 2
#define SCAN_SIZE 0xf800

#define MAX_FILE_NAME 128
#define MAX_FIX_SIZE READ_SIZE / 3
#define MAX_MERGE_ORDER 1500
#define MAX_OFFSET_SIZE 256000

#define VERIFY_SORT             0x01
#define VERIFY_WRITE            0x02
#define VERIFY_READ             0x04
#define VERIFY_CHECKSUM_INPUT   0x08
#define VERIFY_CHECKSUM_OUTPUT  0x10
#define VERIFY_CONTENT          0x20

/*
 *  WARNING -- IF YOU ADD FLAGS HERE, MAKE SURE "parameters"
 *        IS INITIALIZED PROPERLY!!!!!!
 *        parameters.alpha MUST BE NON-ZERO!
 */
struct PARAMETERS
{
	short nosort;
	short nomerge;
	short kill;
	short strip;
	short direction;
	/* number of bytes to ignore on end (used as second key in ties) */
	short end_key_bytes;
	/* look at end key first?  Yes, if "unsort" in "unbuild" */
	short end_key_first;
	short fix;
	short start;
	short length;
	int alpha;
	short verify;
	/* holds drive letters allowed to use "cde" */
	uchar use[MAX_FILE_NAME];
	uchar input_file[MAX_FILE_NAME];
	uchar output_file[MAX_FILE_NAME];
	uchar offset_file[MAX_FILE_NAME];
	ulong max_megs;
};

struct STATS
{
	int avail_memory;
	int total_time;
	int sort_time;
	int merge_time;
	int bytes_read;
	int bytes_to_read;
	int bytes_written;
	int records_read;
	int merge_passes;
	int merge_order;
	int bytes_stripped;
	int records_stripped;
	ulong checksum_input1;
	ulong checksum_input4;
	ulong checksum_output1;
	ulong checksum_output4;
};

struct PARAMETERS parameters = {FALSE, FALSE, FALSE, FALSE, 1, 0, 0, 0, 0, 0, DEFAULT_ALPHA, 0, {0}, {0}, {0}, {0}, DEFAULT_MAX_MEGS};
struct STATS stats;
static int (*Gcompare)(uchar *elem1, uchar *elem2);
static uchar tempfile1[MAX_FILE_NAME];
static uchar tempfile2[MAX_FILE_NAME];
static uchar tempfile3[MAX_FILE_NAME];
static uchar tempfile4[MAX_FILE_NAME];

/****************************************************************************
 * jmaxmem - Get the maximum amount of memory available
 *
 * Parameter:
 *        unsigned int MaxMemNeeded
 *
 * Returns:
 *        int Maximum memory
 ****************************************************************************/
int jmaxmem(unsigned int MaxMemNeeded)
{
    unsigned char *Buff ;
	int BuffSize ;
    BuffSize = MaxMemNeeded ;

	fprintf(stderr, "\nAllocating maximum memory...\n");

    while ((Buff = (unsigned char *) malloc (BuffSize *
					 sizeof (unsigned char))) == NULL)
    {
		BuffSize -= 2048 ;
		if (BuffSize <= 0)
		    break ;
    }

	if (BuffSize > 0)
		free(Buff);

	return(BuffSize);
}

uchar *get_all_memory(int *pbuffer_size)
{
	uchar *buffer;

	*pbuffer_size = jmaxmem(parameters.max_megs * 1024 * 1024);

	if ((buffer = malloc(*pbuffer_size)) == NULL)
		*pbuffer_size = 0;

	return(buffer);
}

/* LOW LEVEL I/O ROUTINES */
ulong checksum4(void *buffer, uint size)
{
	ulong *rover;
	ulong counter;
	ulong temp, temp2;

	/* reset rover */
	rover = (ulong *) buffer;
	temp2 = temp = (size / sizeof(ulong));

	counter = 0l;
	while(temp--)
		counter += *rover++;

	temp2 = (sizeof(ulong) - (size - (temp2 * sizeof(ulong)))) * 8;
	if (temp2 < 32)
		counter += ((*rover >> temp2) << temp2);

	return(counter);
}

ulong checksum1(void *buffer, uint size)
{
	uchar *rover;
	ulong counter;

	counter = 0l;
	/* reset rover */
	rover = (uchar *) buffer;
	while(size--)
		counter += *rover++;

	return(counter);
}

void checksum_input(void *buffer, uint size)
{
	ulong *rover;
	ulong temp;
	static uint bytes_needed = sizeof(ulong);
	uchar tempbuf[sizeof(ulong)];

	if (size < bytes_needed)
	{
		*(ulong *)tempbuf = 0;
		memcpy(&tempbuf[sizeof(ulong) - bytes_needed], buffer, size);
		stats.checksum_input4 += *((ulong *)tempbuf);
		bytes_needed -= size;
		return;
	}

	*(ulong *)tempbuf = 0;
	memcpy(&tempbuf[sizeof(ulong) - bytes_needed], buffer, bytes_needed);
	stats.checksum_input4 += *((ulong *)tempbuf);

	rover = (ulong *)((uchar *)buffer + bytes_needed);
	size -= bytes_needed;

	temp = (size / sizeof(ulong));
	while(temp--)
		stats.checksum_input4 += *rover++;

	bytes_needed = sizeof(ulong) - (size % sizeof(ulong));
	*(ulong *)tempbuf = 0;
	memcpy(tempbuf, rover, sizeof(ulong) - bytes_needed);
	stats.checksum_input4 += *((ulong *)tempbuf);
}

void checksum_output(void *buffer, uint size)
{
	ulong *rover;
	ulong temp;
	static uint bytes_needed = sizeof(ulong);
	uchar tempbuf[sizeof(ulong)];

	if (size < bytes_needed)
	{
		*(ulong *)tempbuf = 0;
		memcpy(&tempbuf[sizeof(ulong) - bytes_needed], buffer, size);
		stats.checksum_output4 += *((ulong *)tempbuf);
		bytes_needed -= size;
		return;
	}

	*(ulong *)tempbuf = 0;
	memcpy(&tempbuf[sizeof(ulong) - bytes_needed], buffer, bytes_needed);
	stats.checksum_output4 += *((ulong *)tempbuf);

	rover = (ulong *)((uchar *)buffer + bytes_needed);
	size -= bytes_needed;

	temp = size / sizeof(ulong);
	while(temp--)
		stats.checksum_output4 += *rover++;

	bytes_needed = sizeof(ulong) - (size % sizeof(ulong));
	*(ulong *)tempbuf = 0;
	memcpy(tempbuf, rover, sizeof(ulong) - bytes_needed);
	stats.checksum_output4 += *((ulong *)tempbuf);
}

int jread(int handle, void *buffer, uint count)
{
	uint bytes_read;
	ulong cs, cs2;

	if (!(parameters.verify & VERIFY_READ))
		return(read(handle, buffer, count));

	bytes_read = read(handle, buffer, count);
	cs = checksum4(buffer, bytes_read);
	lseek(handle, -((long)bytes_read), SEEK_CUR);
	bytes_read = read(handle, buffer, count);
	cs2 = checksum4(buffer, bytes_read);

	if (cs != cs2)
	{
		fprintf(stdout, "Read verify failed\n");
		exit(1);
	}

	return(bytes_read);
}

int jwrite(int handle, void *buffer, uint count)
{
	uint bytes_read, bytes_written;
	ulong cs, cs2;

	if (!(parameters.verify & VERIFY_WRITE))
		return(write(handle, buffer, count));

	bytes_written = write(handle, buffer, count);
	cs = checksum4(buffer, bytes_written);

	lseek(handle, -((long)bytes_written), SEEK_CUR);
	bytes_read = read(handle, buffer, count);
	cs2 = checksum4(buffer, bytes_read);

	if (cs != cs2)
	{
		fprintf(stdout, "Write verify failed\n");
		exit(1);
	}

	return(bytes_written);
}

int load_records(int in_handle, uchar *buffer, int buffer_size, uchar ***pptr_table)
{
	static int temp;
	static uchar *buffer_ptr;
	static uchar *walk_ptr;
	static uchar **ptr_table;
	static int next_length;
	static int bytes_wanted;
	static int finished;

	buffer_ptr = buffer;
	walk_ptr = buffer;

	ptr_table = (uchar **)(buffer + buffer_size);

	finished = FALSE;
	while(!finished && ((uchar *)ptr_table - buffer_ptr) > SAFETY)
	{
		if ((temp = jread(in_handle, buffer_ptr, READ_SIZE)) != READ_SIZE)
			finished = TRUE;

		if (parameters.verify & VERIFY_CONTENT)
			stats.checksum_input1 += checksum1(buffer_ptr, temp);
		if (parameters.verify & VERIFY_CHECKSUM_INPUT)
			checksum_input(buffer_ptr, temp);

		stats.bytes_read += temp;
		buffer_ptr += temp;

		next_length = (parameters.fix) ? parameters.fix : (int)*walk_ptr + 1;

		if (next_length <= parameters.end_key_bytes)
		{
			fprintf(stdout, "Error: short record found\n");
			exit(1);
		}

		while (walk_ptr + next_length < buffer_ptr)
		{
			ptr_table--;
			*ptr_table = walk_ptr;
			walk_ptr += next_length;
			next_length = (parameters.fix) ? parameters.fix : (int)*walk_ptr + 1;
			if (next_length <= parameters.end_key_bytes)
			{
				fprintf(stdout, "Error: short record found\n");
				exit(1);
			}
		}
	}

	if (walk_ptr != buffer_ptr)
	{
		next_length = (parameters.fix) ? parameters.fix : (int)*walk_ptr + 1;
		bytes_wanted = next_length - (buffer_ptr - walk_ptr);
		if (bytes_wanted != 0)
		{
			/* if we need some more bytes, and we exited the above */
			/* loop because we were at the end of the file */
			if (finished)
			{
				fprintf(stdout, "Error, short record at end of file\n");
				exit(1);
			}

			if ((temp = jread(in_handle, buffer_ptr, bytes_wanted)) != bytes_wanted)
			{
				fprintf(stdout, "Error reading from input file\n");
				exit(1);
			}

			if (parameters.verify & VERIFY_CONTENT)
				stats.checksum_input1 += checksum1(buffer_ptr, temp);
			if (parameters.verify & VERIFY_CHECKSUM_INPUT)
				checksum_input(buffer_ptr, temp);

			stats.bytes_read += temp;
		}

		ptr_table--;
		*ptr_table = walk_ptr;
		walk_ptr += next_length;
	}

	if ((uchar *)ptr_table < walk_ptr)
	{
		fprintf(stdout, "Error, small records caused overflow of pointer table\n");
		exit(1);
	}

	*pptr_table = ptr_table;
	return((buffer_size - ((uchar *)ptr_table - buffer)) / sizeof(uchar *));
}

void save_records(int out_handle, uchar *save_buffer, uchar **ptr_table, int record_count)
{
	static uchar *save_ptr;
	static uchar **ptr_ptr;
	static int next_length, bytes_left, temp;
	static int i;

	save_ptr = save_buffer;
	ptr_ptr = ptr_table;

	for (i = 0; i < record_count; i++)
	{
		next_length = (parameters.fix) ? parameters.fix : (int)**ptr_ptr + 1;

		if (parameters.strip)
		{
			if ((i != 0) && (Gcompare(*ptr_ptr, *(ptr_ptr - 1)) == 0))
			{
				stats.bytes_stripped += next_length;
				stats.records_stripped++;
				ptr_ptr++;
				continue;
			}
		}

		if (parameters.verify & VERIFY_SORT)
		{
			if ((i != 0) && (Gcompare(*ptr_ptr, *(ptr_ptr - 1)) < 0))
			{
				fprintf(stdout, "Sort order verify failed\n");
				exit(1);
			}
		}

		bytes_left = SAVE_SIZE - (save_ptr - save_buffer);
		if (bytes_left <= next_length)
		{
			memcpy(save_ptr, *ptr_ptr, bytes_left);
			if (jwrite(out_handle, save_buffer, SAVE_SIZE) != SAVE_SIZE)
			{
				fprintf(stdout, "Error writing to output file\n");
				exit(1);
			}

			stats.bytes_written += SAVE_SIZE;
			memcpy(save_buffer, *ptr_ptr + bytes_left, next_length - bytes_left);
			save_ptr = save_buffer + (next_length - bytes_left);
			ptr_ptr++;
		}
		else
		{
			memcpy(save_ptr, *ptr_ptr, next_length);
			ptr_ptr++;
			save_ptr += next_length;
		}
	}

	temp = save_ptr - save_buffer;
    /* if output file needs flushing */
	if (temp > 0)
	{
		if (jwrite(out_handle, save_buffer, temp) != temp)
		{
			fprintf(stdout, "Error writing to output file\n");
			exit(1);
		}
		stats.bytes_written += temp;
	}
}

int jvcompare(uchar *elem1, uchar *elem2)
{
	static int result;
	static uchar l1, l2;

	l1 = *elem1;
	l2 = *elem2;

	result = memcmp(elem1 + 1, elem2 + 1, (l1 <= l2) ? l1 : l2);
    /* if they compare equal let the lengths decide order */
	if (!result)
		result = l1 - l2;
	return(result);
}

int jvekcompare(uchar *elem1, uchar *elem2)
{
	static int result;
	static uchar l1, l2;

    /* if lengths are the same */
	if ((l1 = *elem1) == (l2 = *elem2))
		return(memcmp(elem1 + 1, elem2 + 1, l1));
	else
	{
		result = memcmp(elem1 + 1, elem2 + 1, ((l1 <= l2) ? l1 : l2) - parameters.end_key_bytes);
        /* if they compare equal let the lengths decide order */
		if (!result)
			result = l1 - l2;
		return(result);
	}
}

int jvekfcompare(uchar *elem1, uchar *elem2)
{
	static int result;
	static uchar l1, l2;

	l1 = *elem1;
	l2 = *elem2;
	result = memcmp(elem1 + 1 + l1 - parameters.end_key_bytes,
			elem2 + 1 + l2 - parameters.end_key_bytes,
			parameters.end_key_bytes);

    /* if end keys not equal, we're done */
	if (result)
		return(result);

    /* if lengths are the same */
	if (l1 == l2)
		return(memcmp(elem1 + 1, elem2 + 1, l1));
	else
	{
		result = memcmp(elem1 + 1, elem2 + 1, ((l1 <= l2) ? l1 : l2) - parameters.end_key_bytes);
        /* if they compare equal let the lengths decide order */
		if (!result)
			result = l1 - l2;
		return(result);
	}
}

int jfixcompare(uchar *elem1, uchar *elem2)
{
	return(memcmp(elem1 + parameters.start, elem2 + parameters.start, parameters.length));
}

int jvcomparex(uchar *elem1, uchar *elem2)
{
	static int result;
	static uchar l1, l2;

	l1 = *elem1;
	l2 = *elem2;

	result = memcmp(elem1 + 1, elem2 + 1, (l1 <= l2) ? l1 : l2);
    /* if they compare equal let the lengths decide order */
	if (!result)
		result = l1 - l2;
	return(-result);
}

int jvekcomparex(uchar *elem1, uchar *elem2)
{
	static int result;
	static uchar l1, l2;

    /* if lengths are the same */
	if ((l1 = *elem1) == (l2 = *elem2))
		return(-memcmp(elem1 + 1, elem2 + 1, l1));
	else
	{
		result = memcmp(elem1 + 1, elem2 + 1, ((l1 <= l2) ? l1 : l2) - parameters.end_key_bytes);
        /* if they compare equal let the lengths decide order */
		if (!result)
			result = l1 - l2;
		return(-result);
	}
}

int jvekfcomparex(uchar *elem1, uchar *elem2)
{
	static int result;
	static uchar l1, l2;

	l1 = *elem1;
	l2 = *elem2;
	result = memcmp(elem1 + 1 + l1 - parameters.end_key_bytes, elem2 + 1 + l2 - parameters.end_key_bytes, parameters.end_key_bytes);

    /* if end keys not equal, we're done */
	if (result)
		return(-result);

    /* if lengths are the same */
	if (l1 == l2)
		return(-memcmp(elem1 + 1, elem2 + 1, l1));
	else
	{
		result = memcmp(elem1 + 1, elem2 + 1, ((l1 <= l2) ? l1 : l2) - parameters.end_key_bytes);
        /* if they compare equal let the lengths decide order */
		if (!result)
			result = l1 - l2;
		return(-result);
	}
}

int jfixcomparex(uchar *elem1, uchar *elem2)
{
	return(-memcmp(elem1 + parameters.start, elem2 + parameters.start, parameters.length));
}

void jquicksort(uchar **lo_ptr, uchar **hi_ptr)
{
    /* statics only used temporarily, does not effect recursion */
	static uchar **lo_temp = 0;
	static uchar **hi_temp = 0;
	static uchar *fast_swap = 0;
	static uchar *middle_hold = 0;
	uchar **middle_ptr;

	hi_temp = hi_ptr;

	/* see if already sorted */
	lo_temp = lo_ptr;
	while (lo_temp < hi_temp)
	{
        /* section not sorted, go on to sort it */
		if (Gcompare(*lo_temp, *(lo_temp + 1)) > 0)
			break;
		lo_temp++;
	}

    /* if lo=hi, sorted, so exit */
	if (lo_temp == hi_temp)
		return;

    /* reset lo_temp */
	lo_temp = lo_ptr;

	middle_hold = *(lo_temp + (hi_temp - lo_temp) / 2);

	/* partition the data */
	while (lo_temp < hi_temp)
	{
		while ((lo_temp < hi_temp) && (Gcompare(*lo_temp, middle_hold) < 0))
			lo_temp++;

		while ((hi_temp > lo_temp) && (Gcompare(*hi_temp, middle_hold) > 0))
			hi_temp--;

		if (lo_temp != hi_temp)
		{
			fast_swap = *lo_temp;
            /* move lo_temp forward to stop infinite swapping when both items equal to middle */
			*lo_temp++ = *hi_temp;
            /* move hi_temp back cause we know it belongs on this side */
			*hi_temp-- = fast_swap;
		}
	}

	if ((lo_temp == hi_temp) && (Gcompare(*lo_temp, middle_hold) < 0))
		lo_temp++;

	middle_ptr = lo_temp;

	/* don't say sort 1 item */
	if (lo_ptr != (middle_ptr - 1))
		jquicksort(lo_ptr, middle_ptr - 1);

	if (middle_ptr != hi_ptr)
		jquicksort(middle_ptr, hi_ptr);
}

void jqsort(uchar **base, int count)
{
	if (count < 2)
		return;

	jquicksort(base, base + (count - 1));
}

/* wordsort control file parsing */
void set_ws_parameters(uchar *filename)
{
	FILE *in_stream;
	uchar buffer[500];
	int temp;

	if ((in_stream = fopen(filename, "rb")) == NULL)
	{
		fprintf(stdout, "Cannot open wordsort control file %s\n", filename);
		exit(1);
	}

	temp = 0;
	while(temp < 2)
	{
		if (fgets(buffer, 499, in_stream) != NULL)
		{
			if (strnicmp(buffer, parameters.input_file, 3) == 0)
			{
				if (strnicmp(parameters.input_file, "OFW", 3) == 0)
					parameters.end_key_bytes = 7;
				else if (strnicmp(parameters.input_file, "OLT", 3) == 0)
					parameters.end_key_bytes = 5;
				else if (strnicmp(parameters.input_file, "SFW", 3) == 0)
				{
					parameters.end_key_bytes = 7;
					parameters.end_key_first = ~0;
				}
				else if (strnicmp(parameters.input_file, "SLT", 3) == 0)
				{
					parameters.end_key_bytes = 5;
					parameters.end_key_first = ~0;
				}
				else
					fprintf(stderr, "Unknown wordsort tag, using entire record as key unless specified\n");
				sscanf(buffer+3, "%s", parameters.input_file);
				temp++;
			}
			else if (strnicmp(buffer, parameters.output_file, 3) == 0)
			{
				sscanf(buffer+3, "%s", parameters.output_file);
				temp++;
			}
		}
		else
		{
			fprintf(stdout, "Cannot find requested TAG in wordsort control file\n");
			exit(1);
		}
	}
	fclose(in_stream);
}

void set_parameters(int argc, uchar *argv[])
{
	unsigned int i, j, k;
	char buf[20];
	char *temp_ptr;

	if (argc < 3)
	{
		printf("Syntax:  JSORT386 <input_file> <output_file> [offset_file] [options]\n");
		printf("Options: /NOSORT        Prevents sort step from occuring. (only merges)\n");
		printf("                        (note: creates an offset_file if not specified)\n");
		printf("         /NOMERGE       Prevents merge step from occuring. (only sorts)\n");
		printf("                        (note: offset_file lost if not specified)\n");
		printf("         /STRIP         Strip out duplicate records (according to key)\n");
		printf("         /D             Sort in descending order\n");
		printf("         /K             Kill input file(s)\n");
		printf("         /USE:<drives>  Drive letters legal to use for temporary files.\n");
		printf("         /EK:<bytes>    End Key - uses end bytes as a second key\n");
		printf("                        Ex. /EK:7 uses last 7 bytes as a second key\n");
		printf("         /EKF:<bytes>   End Key First - uses end bytes as a first key\n");
		printf("         /ALPHA:<alpha> Machine ALPHA - to calculate optimum merge order\n");
		printf("                        == transfer_rate (bytes/sec.) * seek_time (sec.)\n");
		printf("                        default ALPHA == 360000 * .016 == %d\n", DEFAULT_ALPHA);
		printf("         /WS:<ctl_file> Wordsort .ctl file parsing (use tags for filenames)\n");
		printf("         /FIX:<reclen>[:start[:len]]  Fixed length records\n");
		printf("                                      reclen = record length\n");
		printf("                                      start  = key offset (default=0)\n");
		printf("                                      len    = key length (default=reclen)\n");
		printf("         /MEM:<megs>    Max memory in megabytes to use (default=%lu)\n", parameters.max_megs);
		printf("         /V:[options]   Do on the fly verifications\n");
		printf("                        R - Verify all reads (read stuff twice)\n");
		printf("                        W - Verify all writes (re-read written stuff)\n");
		printf("                        S - Verify sort order (during sort and merge)\n");
		printf("                        I - Calculate 4 byte checksum for input file\n");
		printf("                        O - Calculate 4 byte checksum for output file\n");
		printf("                        D - Data integrity check - calculates 1 byte\n");
		printf("                            checksum for both input and output files\n");
		printf("                            and compares - these should be equal\n");
		exit(1);
	}

	/* get filename stuff */
	strcpy(parameters.input_file, argv[1]);
	strcpy(parameters.output_file, argv[2]);
	if (*argv[3] != '/')
		strcpy(parameters.offset_file, argv[3]);

	#ifdef WIN32
	i = _getdrive();
	#else
	_dos_getdrive(&i);
	#endif

	k = 0;
	for (j = i; j <= 26; j++)
		parameters.use[k++] = (uchar)(j + 'A' - 1);

    /* do not use drive A and B */
	for (j = i - 1; j > 2; j--)
		parameters.use[k++] = (uchar)(j + 'A' - 1);
	parameters.use[k] = 0;

	/* these should already be zero, but let's be extra safe */
    /* variable length default */
	parameters.fix = 0;
    /* no end key is the default */
	parameters.end_key_bytes = 0;
    /* end key is normally less relevant */
	parameters.end_key_first = 0;

	/* get options */
	for (i = 0; i < argc; i++)
	{
		if (!strnicmp(argv[i], "/NOMERGE", 8))
		{
			parameters.nomerge = TRUE;
		}
		else if (!strnicmp(argv[i], "/USE:", 5))
		{
			strcpy(parameters.use, argv[i] + 5);
			strupr(parameters.use);
		}
		else if (!strnicmp(argv[i], "/NOSORT", 7))
		{
			parameters.nosort = TRUE;
		}
		else if (!strnicmp(argv[i], "/EK:", 4))
		{
			parameters.end_key_bytes = (short)atoi(argv[i] + 4);
			/* end key last */
			parameters.end_key_first = 0;
		}
		else if (!strnicmp(argv[i], "/EKF:", 5))
		{
			parameters.end_key_bytes = (short)atoi(argv[i] + 5);
			/* end key first */
			parameters.end_key_first = ~0;
		}
		else if (!strnicmp(argv[i], "/K", 2))
		{
			parameters.kill = TRUE;
		}
		else if (!strnicmp(argv[i], "/ALPHA:", 7))
		{
			parameters.alpha = atoi(argv[i] + 7);
			if (parameters.alpha < 1)
				parameters.alpha = 1;
		}
		else if (!strnicmp(argv[i], "/WS:", 4))
		{
			set_ws_parameters(argv[i] + 4);
		}
		else if (!strnicmp(argv[i], "/FIX:", 5))
		{
			temp_ptr = argv[i] + 5;
			parameters.fix = atoi(temp_ptr);
			if (parameters.fix > MAX_FIX_SIZE)
			{
				fprintf(stdout, "Fixed length record size too large\n");
				exit(1);
			}
			if ((temp_ptr = strchr(temp_ptr, ':')) == NULL)
			{
				parameters.start = 0;
				parameters.length = parameters.fix;
			}
			else
			{
				parameters.start = atoi(temp_ptr + 1);
				if ((temp_ptr = strchr(temp_ptr + 1, ':')) == NULL)
					parameters.length = parameters.fix - parameters.start;
				else
					parameters.length = atoi(temp_ptr + 1);
			}
			fprintf(stderr, "Fixed Length Records: %d\n", parameters.fix);
		}
		else if (!strnicmp(argv[i], "/D", 2))
		{
			parameters.direction = -1;
		}
		else if (!strnicmp(argv[i], "/STRIP", 6))
		{
			parameters.strip = TRUE;
		}
		else if (!strnicmp(argv[i], "/MEM:", 5))
		{
			parameters.max_megs = (short)atoi(argv[i] + 5);
		}
		else if (!strnicmp(argv[i], "/V:", 3))
		{
			parameters.verify = 0;
			strncpy(buf, argv[i] + 3, sizeof(buf) - 1);
			strupr(buf);

			if (strchr(buf, 'R') != NULL)
				parameters.verify |= VERIFY_READ;
			if (strchr(buf, 'W') != NULL)
				parameters.verify |= VERIFY_WRITE;
			if (strchr(buf, 'S') != NULL)
				parameters.verify |= VERIFY_SORT;
			if (strchr(buf, 'I') != NULL)
				parameters.verify |= VERIFY_CHECKSUM_INPUT;
			if (strchr(buf, 'O') != NULL)
				parameters.verify |= VERIFY_CHECKSUM_OUTPUT;
			if (strchr(buf, 'D') != NULL)
				parameters.verify |= VERIFY_CONTENT;
		}
	}

	/* set compare mode */
	if (parameters.fix > 0)
	{
		if (parameters.direction < 0)
			Gcompare = jfixcomparex;
		else
			Gcompare = jfixcompare;
	}
	else
	{
		if (parameters.end_key_bytes > 0)
		{
			if (parameters.direction < 0)
				if (parameters.end_key_first)
					Gcompare = jvekfcomparex;
				else
					Gcompare = jvekcomparex;
			else
				if (parameters.end_key_first)
					Gcompare = jvekfcompare;
				else
					Gcompare = jvekcompare;
		}
		else
		{
			if (parameters.direction < 0)
				Gcompare = jvcomparex;
			else
				Gcompare = jvcompare;
		}
	}
}

uint get_free_space(unsigned int drive_number)
{
	#ifdef WIN32
    ulong SectorsPerCluster;
	ulong BytesPerSector;
	ulong FreeClusters;
	ulong Clusters;
	ulong FreeDiskSpace;

	uchar RootName[4] = "X:\\";
	
	RootName[0] = (uchar) ( 'A' + drive_number - 1); 

	#pragma message ("Note: Kludge 2:  get_free_space has a fudgy string manip for the root path")

	GetDiskFreeSpace(RootName, &SectorsPerCluster, &BytesPerSector, &FreeClusters, &Clusters);

    	FreeDiskSpace = SectorsPerCluster * BytesPerSector * FreeClusters;

	return((uint)FreeDiskSpace);

	#else
	struct diskfree_t diskspace;
	if (_dos_getdiskfree(drive_number, &diskspace) != 0)
		return(0);
	return((uint)diskspace.bytes_per_sector * (uint)diskspace.sectors_per_cluster * (uint)diskspace.avail_clusters);
	#endif
}

uint get_total_space(unsigned int drive_number)
{
	#ifdef WIN32
	ulong SectorsPerCluster;
	ulong BytesPerSector;
	ulong FreeClusters;
	ulong Clusters;
	ulong FreeDiskSpace;

	uchar RootName[4] = "X:\\";

	RootName[0] = (uchar) ( 'A' + drive_number - 1); 

	#pragma message ("Note: Kludge 2:  get_total_space has a fudgy string manip for the root path")

    	GetDiskFreeSpace(RootName, &SectorsPerCluster, &BytesPerSector, &FreeClusters, &Clusters);
	FreeDiskSpace = SectorsPerCluster * BytesPerSector * Clusters;
	return((uint)FreeDiskSpace);
	#else
	struct diskfree_t diskspace;
	if (_dos_getdiskfree(drive_number, &diskspace) != 0)
		return(0);
	return((uint)diskspace.bytes_per_sector * (uint)diskspace.sectors_per_cluster * (uint)diskspace.total_clusters);
	#endif
}

int tempfile(uchar *filename, uint space_needed)
{
	int i;
	unsigned int temp;
	int temp_handle;
	static int scount = 0x1000;

	i = 0;
	while (parameters.use[i] != 0)
	{
		if (get_free_space(parameters.use[i] - 'A' + 1) > space_needed)
		{
			/* find a temporary filename on this drive */
			do
			{
				sprintf(filename, "%c:\\%x.tmp", parameters.use[i], scount);
				scount++;
			}
			#ifdef WIN32
			while(GetFileAttributes(filename) != 0xFFFFFFFF);
			#else
			while(!_dos_getfileattr((char *)filename, &temp));
			#endif

			if ((temp_handle = open(filename, O_BINARY|O_CREAT|O_RDWR, S_IWRITE|S_IREAD)) == -1)
			{
				fprintf(stdout, "Error opening temporary file %s\n", filename);
				exit(1);
			}
			close(temp_handle);
			return(TRUE);
		}
		i++;
	}
	return(FALSE);
}

void open_sort_files(int *in_handle, int *out_handle, int *offset_handle)
{
	if ((*in_handle = open(parameters.input_file, O_BINARY | O_RDONLY)) == -1)
	{
		fprintf(stdout, "Error opening input file %s\n", parameters.input_file);
		exit(1);
	}
	stats.bytes_to_read = filelength(*in_handle);

	if (!parameters.nomerge)
	{
		if (!tempfile(tempfile1, stats.bytes_to_read))
		{
			fprintf(stdout, "Not enough space for temporary file\n");
			exit(1);
		}
		if (!tempfile(tempfile2, MAX_OFFSET_SIZE))
		{
			fprintf(stdout, "Not enough space for temporary file\n");
			exit(1);
		}
	}
	else
	{
		strcpy(tempfile1, parameters.output_file);
		strcpy(tempfile2, parameters.offset_file);
		if (tempfile2[0] == 0)
			strcpy(tempfile2, "nul");
	}

	if ((*out_handle = open(tempfile1, O_BINARY | O_RDWR | O_CREAT | O_TRUNC, S_IWRITE | S_IREAD)) == -1)
	{
		fprintf(stdout, "Error opening output file %s\n", tempfile1);
		exit(1);
	}

	if ((*offset_handle = open(tempfile2, O_BINARY | O_RDWR | O_CREAT | O_TRUNC, S_IWRITE | S_IREAD)) == -1)
	{
		fprintf(stdout, "Error opening offset file %s\n", tempfile2);
		exit(1);
	}
}

/* removes file */
void jremove(uchar *filename)
{
	if (remove(filename) != 0)
		fprintf(stdout, "Could not remove file %s\n", filename);
}

void create_offset(int in_handle, uchar *offset_file)
{
	static int offset;
	static int temp_handle;
	static int points;
	static uchar *temp_buffer;
	static uchar *data_ptr;
	static uchar *data_ptr2;
	static int bytes_read;
	static int next_length1;
	static int next_length2;
	static int temp;

	/* no offset file, so we will scan input file and create one */
	fprintf(stderr, "Scanning input file to create offset file.\n");
	if (!tempfile(offset_file, MAX_OFFSET_SIZE))
	{
		fprintf(stdout, "Not enough space for temporary file\n");
		exit(1);
	}

	if ((temp_handle = open(offset_file, O_RDONLY | O_BINARY | O_RDWR | O_CREAT | O_TRUNC, S_IWRITE | S_IREAD)) == -1)
	{
		fprintf(stdout, "Cannot open self created offset file %s\n", offset_file);
		exit(1);
	}

	if ((temp_buffer = malloc(SCAN_SIZE)) == NULL)
	{
		fprintf(stdout, "Not enough memory to create offset file\n");
		exit(1);
	}

		/* scan and create offset file */
	points = 1;
	offset = 0;
	if (jwrite(temp_handle, (char *)&offset, sizeof(int)) != sizeof(int))
	{
		printf("\nError writing self created offset file %s\n", offset_file);
		exit(1);
	}

	bytes_read = jread(in_handle, temp_buffer, SCAN_SIZE);
	data_ptr = temp_buffer;
	next_length1 = (parameters.fix) ? parameters.fix : (int)*data_ptr + 1;
	data_ptr2 = data_ptr + next_length1;
	offset = next_length1;

	while (TRUE)
	{
		if (Gcompare(data_ptr, data_ptr2) > 0)
		{
			/* merge point needed here */
			points++;
			if (jwrite(temp_handle, (char *)&offset, sizeof(int)) != sizeof(int))
			{
				fprintf(stdout, "Error writing self created offset file %s\n", offset_file);
				exit(1);
			}
		}
		data_ptr = data_ptr2;
		next_length1 = (parameters.fix) ? parameters.fix : (int)*data_ptr + 1;
		offset += next_length1;
		data_ptr2 += next_length1;

		next_length2 = (parameters.fix) ? parameters.fix : (int)*data_ptr2 + 1;

		if ((bytes_read - (data_ptr2 - temp_buffer)) < next_length2)
		{
			/* rotate the buffer */
			temp = bytes_read - (data_ptr - temp_buffer);
			memmove(temp_buffer, data_ptr, temp);
			bytes_read = jread(in_handle, temp_buffer + temp, SCAN_SIZE - temp);

            /* there are no more records to compare */
			if (bytes_read == 0)
				break;
			bytes_read += temp;
			data_ptr = temp_buffer;
			data_ptr2 = data_ptr + next_length1;
		}
	}
	free(temp_buffer);
	close(temp_handle);
	lseek(in_handle, 0, SEEK_SET);
}

void copyfile(uchar *dest, uchar *source, int final_file)
{
	static int out_handle;
	static int in_handle;
	static int bytes;
	static uchar *buff;

	if ((in_handle = open(source, O_RDONLY | O_BINARY)) == -1)
	{
		fprintf(stdout, "Cannot open input file %s\n", source);
		exit(1);
	}

	if ((out_handle = open(dest, O_RDWR | O_BINARY | O_CREAT | O_TRUNC, S_IREAD | S_IWRITE)) == -1)
	{
		fprintf(stdout, "Cannot open output file %s\n", dest);
		exit(1);
	}

	if ((buff = malloc(0xf800)) == NULL)
	{
		fprintf(stdout, "Error allocating copyfile memory\n");
		exit(1);
	}

	while ((bytes = jread(in_handle, buff, 0xf800)) != 0)
	{
		if (jwrite(out_handle, buff, bytes) != bytes)
		{
			fprintf(stdout, "Error doing file copy to %s\n", dest);
			exit(1);
		}

        /* if this is the final file do checksums */
		if (final_file)
		{
			if (parameters.verify & VERIFY_CONTENT)
				stats.checksum_output1 += checksum1(buff, bytes);
			if (parameters.verify & VERIFY_CHECKSUM_OUTPUT)
				checksum_output(buff, bytes);
		}
	}

	free(buff);
	close(in_handle);
	close(out_handle);
}

/* given the input file, it will do the following */
/* make sure destination drive has space for output file */
/* if not, see if input file is on destination drive */
/* if so, will copy it to a temporary file on another drive */
/* and change input_file appropriately */
void prepare_destination(uchar *input_file)
{
	static unsigned int out_drive_number;
	static uchar tempf[MAX_FILE_NAME];
	static int space_needed;
	static struct stat buf;
	static int i;

	stat(input_file, &buf);

	space_needed = buf.st_size;

	if (parameters.output_file[1] == ':')
		out_drive_number = toupper(parameters.output_file[0]) - 'A' + 1;
	else
	{
		#ifdef WIN32
		out_drive_number = _getdrive();
		#else
		_dos_getdrive(&out_drive_number);
		#endif
	}

	i = 0;
	while (parameters.use[i] != 0)
	{
		if (parameters.use[i] == (uchar)(out_drive_number + 'A' - 1))
		{
			if (get_free_space(parameters.use[i] - 'A' + 1) < space_needed)
			{
				/* copy input_file to another place and exit */
				if (!tempfile(tempf, space_needed))
				{
					fprintf(stdout, "Not enough space for temporary file\n");
					exit(1);
				}
				copyfile(tempf, input_file, FALSE);
				jremove(input_file);
				strcpy(input_file, tempf);
				break;
			}
			else
				break;
		}
		i++;
	}
}

int intpow(int x, int y)
{
	static int temp;

	if (y == 0)
		return(1);

	temp = x;
	y--;
	while(y--)
		temp *= x;

	return(temp);
}

int calc_merge_order(int chunks)
{
	static int merge_order;
	static int temp;
	static int i;
	static int usable_memory;

	usable_memory = stats.avail_memory / 2;

	if (parameters.alpha <= 0)
		parameters.alpha = DEFAULT_ALPHA;

	merge_order = usable_memory / parameters.alpha;

	if (merge_order < 2)
		merge_order = 2;

	/* smallest buffer is 512 */
	if ((usable_memory / merge_order) < 512)
		merge_order = usable_memory / 512;

	i = 1;

	while(intpow(merge_order, i) < chunks)
		i++;

	temp = 2;
	while(intpow(temp, i) < chunks)
		temp++;

	if (temp >= MAX_MERGE_ORDER)
		temp = MAX_MERGE_ORDER - 1;


	return(temp);
}

int jmerge(uchar *input_file, uchar *offset_file)
{
	static int in_handle, in_offset_handle;
	static int out_handle, out_offset_handle;
	static int chunk_count, merge_order, current_chunk;
	static int retval, in_buff_size, chunks_todo;
	static int temp, percent, old_percent, i;
	static int *offset_buffer;
	static int lowest_buffer;
	static short first_record, finished;
	static uchar *savebuff, *savebuff_ptr;
	static int savebuff_size, next_length, oldsave_size;
	static uchar previous_record[MAX_FIX_SIZE];
	static uchar *buffers[MAX_MERGE_ORDER];
	static uchar *ptrs[MAX_MERGE_ORDER];
	static int chunk_offset[MAX_MERGE_ORDER];
	static int input_file_size, chunk_size, total_bytes_written;
	static struct TREENODE
	{
		uchar *data_ptr;
		int chunk_number;
	} tree_node[MAX_MERGE_ORDER * 2];

	if ((in_handle = open(input_file, O_RDONLY | O_BINARY)) == -1)
	{
		fprintf(stdout, "Cannot open input file %s\n", input_file);
		exit(1);
	}

	if (offset_file[0] == 0)
		create_offset(in_handle, offset_file);

	if ((in_offset_handle = open(offset_file, O_RDONLY | O_BINARY)) == -1)
	{
		fprintf(stdout, "Cannot open offset file %s\n", offset_file);
		exit(1);
	}

	chunk_count = filelength(in_offset_handle) / sizeof(int);

    /* if already in one chunk, it's sorted, copy to final file */
	if (chunk_count <= 1)
	{
		/* this will only happen if entire file sorts in memory */
		stats.merge_order = 1;
		close(in_offset_handle);
		close(in_handle);
		prepare_destination(input_file);
		copyfile(parameters.output_file, input_file, TRUE);

		/* delete input temporary files here */
		if (parameters.kill || (strcmp(input_file, parameters.input_file) != 0))
			jremove(input_file);
		if (parameters.kill || (strcmp(offset_file, parameters.offset_file) != 0))
			jremove(offset_file);

        /* only one chunk, so done merging! */
		return(TRUE);
	}


	merge_order = calc_merge_order(chunk_count);
	stats.merge_order = merge_order;

	/* if this will be the final merge pass */
	if (chunk_count <= merge_order)
	{
    	/* merge to the final destination file */

		/* check here for enough destination drive space */
		/* if not enough, copy temp files from dest. drive to */
		/* another drive and proceed as normal */

		close(in_handle);
		prepare_destination(input_file);
		if ((in_handle = open(input_file, O_RDONLY | O_BINARY)) == -1)
		{
			fprintf(stdout, "Cannot open input file %s\n", input_file);
			exit(1);
		}

		strcpy(tempfile3, parameters.output_file);
        /* no need for this file */
		strcpy(tempfile4, "nul");
        /* makes us not get called again */
		retval = TRUE;
	}
	else
	{

		if (!tempfile(tempfile3, filelength(in_handle)))
		{
			fprintf(stdout, "Not enough space for temporary file\n");
			exit(1);
		}
		if (!tempfile(tempfile4, MAX_OFFSET_SIZE))
		{
			fprintf(stdout, "Not enough space for temporary file\n");
			exit(1);
		}
		retval = FALSE;
	}

	if ((out_handle = open(tempfile3, O_RDWR | O_BINARY | O_CREAT | O_TRUNC, S_IREAD | S_IWRITE)) == -1)
	{
		fprintf(stdout, "Cannot open temporary merge file %s\n", tempfile3);
		exit(1);
	}

	if ((out_offset_handle = open(tempfile4, O_RDWR | O_BINARY | O_CREAT | O_TRUNC, S_IREAD | S_IWRITE)) == -1)
	{
		fprintf(stdout, "Cannot Open Temporary Offset File %s\n", tempfile4);
		exit(1);
	}

	temp = stats.avail_memory + SAVE_SIZE;
	if ((offset_buffer = malloc(chunk_count * sizeof(int) + sizeof(int))) == NULL)
	{
		fprintf(stdout, "Not enough memory for offset buffer\n");
		exit(1);
	}
	temp -= chunk_count * sizeof(int) + sizeof(int);

	savebuff_size = (temp / 2048) * 1024;

	temp -= savebuff_size;

	in_buff_size = ((temp / merge_order) / 512) * 512;

	/* need in_buff_size * merge_order bytes, make sure not saving too much!!! */
	fprintf(stderr, "\nOriginal save size: %d bytes\n", savebuff_size);
	oldsave_size = savebuff_size;

	while (savebuff_size > 0 &&
			stats.avail_memory - savebuff_size < in_buff_size * merge_order)
	{
		savebuff_size -= 2048;
    }

	fprintf(stderr, "Saving: %d bytes, (adjusted %d bytes)\n", savebuff_size, oldsave_size - savebuff_size);

	if ((savebuff = malloc(savebuff_size)) == NULL)
	{
		fprintf(stdout, "Not enough memory for save buffer\n");
		exit(1);
	}

	for (i = 0; i < merge_order; i++)
	{
		if ((buffers[i] = malloc(in_buff_size)) == NULL)
		{
			fprintf(stdout, "Error allocating merge buffers...\n(Error allocating merge buffer %d of %u, Size: = %d)\n", i, merge_order, in_buff_size);
			exit(1);
		}
	}


	input_file_size = filelength(in_handle);
	chunk_size = input_file_size / chunk_count;

	if (jread(in_offset_handle, (char *)offset_buffer, chunk_count * sizeof(int)) != chunk_count * sizeof(int))
	{
		fprintf(stdout, "Error reading offset file\n");
		exit(1);
    }

	/* set last pointer to end of file, so it can calculate last chunk size */
	offset_buffer[chunk_count] = input_file_size;

	fprintf(stderr, "Merge Order: %u   Chunk Size: %d\n", merge_order, chunk_size);

	current_chunk = 0;
	savebuff_ptr = savebuff;
	total_bytes_written = 0;

	/* loop while chunks remaining */
	while (current_chunk < chunk_count)
	{

		if (jwrite(out_offset_handle, (char *)&offset_buffer[current_chunk], sizeof(int)) != sizeof(int))
		{
			fprintf(stdout, "Error writing to offset file %s\n", tempfile4);
			exit(1);
		}
		chunks_todo = chunk_count - current_chunk;
		if (chunks_todo > merge_order)
			chunks_todo = merge_order;

		percent = 0;
		old_percent = 0;
		fprintf(stderr, "Merging chunks %d->%d of %d   %d%%   \r", current_chunk + 1, current_chunk + chunks_todo, chunk_count, percent);
		fflush(stderr);

		for (i = 0; i < chunks_todo; i++)
		{
			chunk_offset[i] = offset_buffer[current_chunk + i];
			ptrs[i] = buffers[i];
			temp = offset_buffer[current_chunk + i + 1] - chunk_offset[i];
			temp = (temp < in_buff_size) ? temp : in_buff_size;
			lseek(in_handle, chunk_offset[i], SEEK_SET);
			jread(in_handle, buffers[i], temp);
		}

		/* prepare initial tree structure */
		for (i = chunks_todo; i < 2 * chunks_todo; i++)
		{
			tree_node[i].data_ptr = ptrs[i - chunks_todo];
			tree_node[i].chunk_number = (i - chunks_todo);
		}

		for (i = (2 * chunks_todo) - 1; i > 1; i -= 2)
		{
			if (Gcompare(tree_node[i].data_ptr, tree_node[i - 1].data_ptr) < 0)
			{
				memcpy(&tree_node[i / 2], &tree_node[i], sizeof(struct TREENODE));
			}
			else
			{
				memcpy(&tree_node[i / 2], &tree_node[i - 1], sizeof(struct TREENODE));
			}
		}

		/* the loop starts merging, chunks_todo chunks to merge on this pass. */
		finished = FALSE;
		first_record = TRUE;
        /* while more data left in any of the chunks */
		while(!finished)
		{

			/* lowest buffer == top node of tree */
			lowest_buffer = tree_node[1].chunk_number;

			/* lowest_buffer is out winner */
            /* if a winner exists, process it */
			if (tree_node[1].data_ptr != NULL)
			{
				finished = FALSE;

 				/* write out winning record */
				next_length = (parameters.fix) ? parameters.fix : (int)*ptrs[lowest_buffer] + 1;

				if (parameters.strip && !first_record && (Gcompare(previous_record, ptrs[lowest_buffer]) == 0))
				{
					stats.bytes_stripped += next_length;
					stats.records_stripped++;
				}
				else
				{
					if ((parameters.verify & VERIFY_SORT) && !first_record && (Gcompare(previous_record, ptrs[lowest_buffer]) > 0))
					{
						fprintf(stdout, "Sort order verify failed during merge\n");
						exit(1);
					}

					if ((savebuff_size - (savebuff_ptr - savebuff)) < next_length)
					{
						int temp2;

						temp2 = savebuff_size - (savebuff_ptr - savebuff);
						memcpy(savebuff_ptr, ptrs[lowest_buffer], temp2);
						if (jwrite(out_handle, savebuff, savebuff_size) != savebuff_size)
						{
							fprintf(stdout, "Error writing to data file %s\n", tempfile3);
							exit(1);
						}

						/* if final pass */
						if (retval)
						{
							if (parameters.verify & VERIFY_CONTENT)
								stats.checksum_output1 += checksum1(savebuff, savebuff_size);
							if (parameters.verify & VERIFY_CHECKSUM_OUTPUT)
								checksum_output(savebuff, savebuff_size);
						}

						total_bytes_written += savebuff_size;
						percent = total_bytes_written / ((input_file_size / 100) ? (input_file_size / 100) : 1);
						if (percent != old_percent)
						{
							/* don't print every write */
							old_percent = percent;
							fprintf(stderr, "Merging chunks %d->%d of %d   %d%%   \r", current_chunk + 1, current_chunk + chunks_todo, chunk_count, percent);
							fflush(stderr);
						}

						savebuff_ptr = savebuff;
						memcpy(savebuff, ptrs[lowest_buffer] + temp2, next_length - temp2);
						savebuff_ptr += (next_length - temp2);
					}
					else
					{
						memcpy(savebuff_ptr, ptrs[lowest_buffer], next_length);
						savebuff_ptr += next_length;
					}

					/* update previous buffer since not duplicate */
					if (parameters.strip || (parameters.verify & VERIFY_SORT))
					{
						first_record = FALSE;
						memcpy(previous_record, ptrs[lowest_buffer], next_length);
					}
				}

 				/* advance to next record */
				chunk_offset[lowest_buffer] += next_length;

 				/* if there is more data in chunk */
				if (chunk_offset[lowest_buffer] < offset_buffer[current_chunk + lowest_buffer + 1])
				{
					ptrs[lowest_buffer] += next_length;
					next_length = (parameters.fix) ? parameters.fix : (int)*ptrs[lowest_buffer] + 1;

 					/* if there is not more data in the buffer */
					temp = in_buff_size - (ptrs[lowest_buffer] - buffers[lowest_buffer]);
					if (temp < next_length)
					{
 						/* next record will put us over the edge so rotate */
						memmove(buffers[lowest_buffer], ptrs[lowest_buffer], temp);
						lseek(in_handle, chunk_offset[lowest_buffer] + temp, SEEK_SET);
						/* note: may read into other chunk, but ok */
						jread(in_handle, buffers[lowest_buffer] + temp, in_buff_size - temp);
						ptrs[lowest_buffer] = buffers[lowest_buffer];
					}
				}
				else
				{
					/* stop this buffer from being a factor */
					ptrs[lowest_buffer] = NULL;
				}

				/*  update tree with buffer_ptr[lowest_buffer] */

				temp = (lowest_buffer + chunks_todo);
				tree_node[temp].data_ptr = ptrs[lowest_buffer];
				/* get on even boundary */
				temp = (temp / 2) * 2;
				while (temp > 1)
				{
 					/* if first chunk not empty and (second chunk is empty or first chunk is lowest) */
					if ((tree_node[temp].data_ptr != NULL) && ((tree_node[temp + 1].data_ptr == NULL)
							|| (Gcompare(tree_node[temp].data_ptr, tree_node[temp + 1].data_ptr) < 0)))
					{
 						/* first chunk is winner */
						memcpy(&tree_node[temp / 2], &tree_node[temp], sizeof(struct TREENODE));
					}
					else
					{
 						/* second chunk is winner */
						memcpy(&tree_node[temp / 2], &tree_node[temp + 1], sizeof(struct TREENODE));
					}
					/* go up to parent and get on even boundary */
					temp = (temp / 4) * 2;
				}
			}
			else
			{
				finished = TRUE;
			}
		}
		current_chunk += chunks_todo;
	}

 	/* flush output buffer */
	if (jwrite(out_handle, savebuff, savebuff_ptr - savebuff) != (savebuff_ptr - savebuff))
	{
		fprintf(stdout, "Error writing to data file %s\n", tempfile3);
		exit(1);
	}

 	/* if final pass */
	if (retval)
	{
		if (parameters.verify & VERIFY_CONTENT)
			stats.checksum_output1 += checksum1(savebuff, (savebuff_ptr - savebuff));
		if (parameters.verify & VERIFY_CHECKSUM_OUTPUT)
			checksum_output(savebuff, (savebuff_ptr - savebuff));
	}

	total_bytes_written += (savebuff_ptr - savebuff);

	close(in_handle);
	close(in_offset_handle);

	/* delete input temporary files here */
	if (parameters.kill || (strcmp(input_file, parameters.input_file) != 0))
		jremove(input_file);
	if (parameters.kill || (strcmp(offset_file, parameters.offset_file) != 0))
		jremove(offset_file);

	close(out_handle);
	close(out_offset_handle);

	strcpy(input_file, tempfile3);
	strcpy(offset_file, tempfile4);

	for (i = 0; i < merge_order; i++)
	{
		free(buffers[i]);
	}
	free(savebuff);
	free(offset_buffer);

	fprintf(stderr, "\n");
	return(retval);
}

int main(int argc, char *argv[])
{
	static uchar *buffer;
	static int buffer_size, record_count;
	static uchar **ptr_table;
	static uchar *save_buffer;
	static int in_handle, out_handle, offset_handle;
	static uint free_space, total_space;
	static time_t temp_time;
	static time_t gmtseconds;
	static struct tm *structure_time;
	static char *ascii_time;
	static int i;

	stats.total_time = time(&temp_time);
	stats.sort_time = 0;
	stats.merge_time = 0;

	fprintf(stdout, "------------------------------------------------------------\n");
	fprintf(stdout, "JSORT 386 v1.12 Copyright (C)1993 SilverPlatter International\n");
	fprintf(stdout, "Contact:  SilverPlatter Development - 617-769-2599\n\n");

	set_parameters(argc, argv);

	/* get current time */
	time(&gmtseconds);
	structure_time = localtime(&gmtseconds);
	ascii_time = asctime(structure_time);
	fprintf(stdout, "JSORT386 started: %.8s %.10s %s", ascii_time+11, ascii_time, ascii_time+20);

	/* get free drive space on all used drives */
	fprintf(stderr, "           Drive Stats\n");
	fprintf(stderr, "Letter         Total          Free\n");
	fprintf(stderr, "----------------------------------\n");
	strupr(parameters.use);
	for (i = 0; parameters.use[i] != 0; i++)
	{
		free_space = get_free_space(parameters.use[i] - 'A' + 1);
		total_space = get_total_space(parameters.use[i] - 'A' + 1);
		if (free_space != 0)
			fprintf(stderr, "%c:        %10u    %10u\n", parameters.use[i], total_space, free_space);
	}
	fprintf(stderr, "\n");

	if ((save_buffer = malloc(SAVE_SIZE)) == NULL)
	{
		fprintf(stdout, "Not enough memory to save... (%d bytes)\n", SAVE_SIZE);
		exit(1);
	}

	if ((buffer = get_all_memory(&buffer_size)) == NULL)
	{
		fprintf(stdout, "Not enough memory to sort\n");
		exit(1);
	}

	stats.avail_memory = buffer_size;
	stats.bytes_read = 0;
	stats.bytes_written = 0;
	stats.records_read = 0;
	stats.checksum_input1 = 0;
	stats.checksum_input4 = 0;
	stats.checksum_output1 = 0;
	stats.checksum_output4 = 0;
	fprintf(stderr, "Sort memory available: %d\n", stats.avail_memory);

	if (!parameters.nosort)
	{
		stats.sort_time = time(&temp_time);
		fprintf(stderr, "Beginning sort\n");
		open_sort_files(&in_handle, &out_handle, &offset_handle);

		while(TRUE)
		{
			if ((record_count = load_records(in_handle, buffer, buffer_size, &ptr_table)) == 0)
				break;
			stats.records_read += record_count;
			fprintf(stderr, "Bytes Read:  %d of %d   Records Read: %d   %d%%\r", stats.bytes_read, stats.bytes_to_read, stats.records_read, stats.bytes_read / (stats.bytes_to_read / 100 + 1));
			fflush(stderr);

			jqsort(ptr_table, record_count);

			jwrite(offset_handle, &stats.bytes_written, sizeof(int));
			save_records(out_handle, save_buffer, ptr_table, record_count);
		}
		fprintf(stderr, "Bytes Read:  %d of %d   Records Read: %d   %d%%\n", stats.bytes_read, stats.bytes_to_read, stats.records_read, stats.bytes_read / (stats.bytes_to_read / 100 + 1));
		close(in_handle);
		close(out_handle);
		close(offset_handle);
		fprintf(stderr, "Sort finished\n");
		if (parameters.kill)
		{
			fprintf(stderr, "Killing Input File %s\n", parameters.input_file);
			jremove(parameters.input_file);
		}
		stats.sort_time = time(&temp_time) - stats.sort_time;
	}
	else
	{
		fprintf(stderr, "No sort being performed\n");
		strcpy(tempfile1, parameters.input_file);
		strcpy(tempfile2, parameters.offset_file);
	}

	free(save_buffer);
	free(buffer);

	if (!parameters.nomerge)
	{
		stats.merge_time = time(&temp_time);

		fprintf(stderr, "Beginning merge\n");
		stats.merge_passes = 1;
		/* keep calling until done */
		while (!jmerge(tempfile1, tempfile2))
			stats.merge_passes++;
		fprintf(stderr, "Merge finished\n");

		stats.merge_time = time(&temp_time) - stats.merge_time;
	}
	else
	{
		fprintf(stderr, "No merge being performed\n");
	}

	stats.total_time = time(&temp_time) - stats.total_time;
	if (stats.total_time < 1)
		stats.total_time = 1;

	/* get current time */
	time(&gmtseconds);
	structure_time = localtime(&gmtseconds);
	ascii_time = asctime(structure_time);
	fprintf(stdout, "JSORT386 ended:   %.8s %.10s %s", ascii_time + 11, ascii_time, ascii_time+20);

	fprintf(stdout, "Command Line: ");
	for (i = 0; i < argc; i++)
		fprintf(stdout, "%s ", argv[i]);
	fprintf(stdout, "\n\n");
	fprintf(stdout, "Execution Time %7d seconds\n", stats.total_time);
	fprintf(stdout, "  Sort:        %7d seconds   %3d%%\n", stats.sort_time, (stats.sort_time * 100) / stats.total_time);
	fprintf(stdout, "  Merge:       %7d seconds   %3d%%\n", stats.merge_time, (stats.merge_time * 100) / stats.total_time);
	fprintf(stdout, "\n");
	fprintf(stdout, "Memory Usage:     %10d\n", stats.avail_memory);
	fprintf(stdout, "Merge Passes:     %10d\n", stats.merge_passes);
	fprintf(stdout, "Merge Order:      %10d\n", stats.merge_order);
	fprintf(stdout, "Bytes Processed:  %10d\n", stats.bytes_read);
	fprintf(stdout, "Records Processed:%10d\n", stats.records_read);
	if (parameters.strip)
	{
		fprintf(stdout, "Bytes Stripped:   %10d\n", stats.bytes_stripped);
		fprintf(stdout, "Records Stripped: %10d\n", stats.records_stripped);
	}
	fprintf(stdout, "Performance:\n");
	fprintf(stdout, "  bytes/second:   %10d\n", stats.bytes_read / stats.total_time);
	fprintf(stdout, "  records/second: %10d\n", stats.records_read / stats.total_time);

	if ((parameters.verify & VERIFY_CHECKSUM_INPUT) || (parameters.verify & VERIFY_CONTENT))
	{
		fprintf(stdout, "Input File\n");
		if (parameters.verify & VERIFY_CHECKSUM_INPUT)
			fprintf(stdout, "  Checksum4:   %lx\n", stats.checksum_input4);
		if (parameters.verify & VERIFY_CONTENT)
			fprintf(stdout, "  Checksum1:   %lx\n", stats.checksum_input1);
	}

	if ((parameters.verify & VERIFY_CHECKSUM_OUTPUT) || (parameters.verify & VERIFY_CONTENT))
	{
		fprintf(stdout, "Output File\n");
		if (parameters.verify & VERIFY_CHECKSUM_OUTPUT)
			fprintf(stdout, "  Checksum4:   %lx\n", stats.checksum_output4);
		if (parameters.verify & VERIFY_CONTENT)
			fprintf(stdout, "  Checksum1:   %lx\n", stats.checksum_output1);
	}

	if (parameters.verify & VERIFY_CONTENT)
	{
		if (stats.checksum_input1 != stats.checksum_output1)
		{
			fprintf(stdout, "Error - 1 byte checksum did not match.\n");
			exit(1);
		}
	}

	return(0);
}
