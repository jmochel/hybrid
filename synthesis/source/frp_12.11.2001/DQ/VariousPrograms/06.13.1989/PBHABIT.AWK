# PBNABIT.AWK - Plant By HABITitat
# Creates a simple list with all names of the plants
# and their habitats from a standard plant format text file
#
# The output will be of the form:
# HABITAT NAME
#
# 06-02-89	Created	JSM
#

BEGIN {	FS = "-" }

# Find the record
# Advanced beyond the .NS
# Get the first of the names
# Get all the possible habitats
# For each habitat, print out the habitat and the name of the plant

/\.NS/,/\.NE/	{
				if ( $1 !~ /\.NS|\.NE/ ) {
					split($1,name,",")
					split($4,habitat,",")
					for ( cntr in habitat )
						printf("%s %s\n",habitat[cntr],name[1]) 
					}
				}		
		

