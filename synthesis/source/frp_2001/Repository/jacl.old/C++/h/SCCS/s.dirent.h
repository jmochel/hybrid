h04062
s 00081/00000/00000
d D 1.1 99/11/17 08:10:38 jmochel 2 1
cC
cK34294
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/11/17 08:10:34 jmochel 1 0
c BitKeeper file e:/jacl/C++/h/dirent.h
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991117121845|52994|b286d56f5cb6bb79
cHdevilmountain.bedford.foliage.com
cK43655
cPC++/h/dirent.h
cRf1dd8f9f5cb6bb60
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 2
#ifndef _DIRENT_H
#define _DIRENT_H


#include <stdlib.h>
#include <limits.h>

#include <sys/types.h>

#define MAXNAMLEN	255	/* maximum filename length		*/

#ifndef NAME_MAX
#define	NAME_MAX	(MAXNAMLEN - 1)
#endif

struct dirent			/* data from getdents()/readdir()	*/
{
    ino_t	d_ino;		/* inode number of entry		*/
    off_t	d_off;		/* offset of disk directory entry	*/
    wchar_t	d_reclen;	/* length of this record		*/
    char	d_name[MAXNAMLEN + 1];
};

/* 
    The following nonportable ugliness could have been avoided by defining
    DIRENTSIZ and DIRENTBASESIZ to also have (struct dirent *) arguments.
    There shouldn't be any problem if you avoid using the DIRENTSIZ() macro.
*/

#define	DIRENTBASESIZ		(((struct dirent *)0)->d_name \
				- (char *)&((struct dirent *)0)->d_ino)

#define	DIRENTSIZ(namlen)	((DIRENTBASESIZ + sizeof(long) + (namlen)) \
				/ sizeof(long) * sizeof(long))


#  define DIRBUF	8192	/* buffer size for fs-indep. dirs	*/
				/* must in general be larger than the	*/
				/* filesystem buffer size		*/

struct _dircontents 
{
    char*                   _d_entry;
    struct _dircontents*    _d_next;
};

typedef struct _dirdesc 
{
    int			dd_id;	/* uniquely identify each open directory */
    long		dd_loc;	/* where we are in directory entry is this */
    struct _dircontents	*dd_contents;	/* pointer to contents of dir	*/
    struct _dircontents	*dd_cp;		/* pointer to current position	*/

} DIR;


/* Functions */

extern DIR *		    opendir(const char *);
extern struct dirent* 	readdir(DIR *);
extern void		        rewinddir(DIR *);

extern int		closedir(DIR *);
extern void		seekdir(DIR *, off_t);
extern off_t	telldir(DIR *);

extern int		chdir(const char *);
extern char* 	getcwd(char *, size_t);

extern int		mkdir(const char *);

extern int		rmdir(const char *);
extern int		scandir(char *,struct dirent ***,  int (*)(const void *, const void *),int (*)(const void *, const void *));

extern int		_chdrive(int);
extern int		_getdrive(void);
extern char* 	_getdcwd(int, char *, int);

extern int		IsHPFSFileSystem(char *);

#endif
E 2
I 1
E 1
