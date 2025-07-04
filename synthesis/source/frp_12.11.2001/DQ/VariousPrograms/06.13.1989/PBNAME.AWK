# PBNAME.AWK - Plant By NAME
# Creates a simple list with all names of the plants
# from a standard plant format text file
#
# 06-02-89	Created	JSM
#
BEGIN {	FS = "-" }

/\.NS/,/\.NE/	{
				if ( $1 !~ /\.NS|\.NE/ ) {
					split($1,temp,",")
					printf("%s\n",temp[1]) 
					}
				}		
		
