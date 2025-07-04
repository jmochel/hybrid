h30350
s 00099/00000/00000
d D 1.1 99/12/02 15:41:54 jmochel 2 1
cC
cK56665
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 15:41:50 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/DataTools/Reaction/rxnspd.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44891
cPDataTools/Reaction/rxnspd.awk
cReb47850d5cb68f68
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
#
#
#

# Generate Reaction Speeds
BEGIN {

printf("\\documentclass{article}\n");
printf("\\title{Character Speed Tables}\n");
printf("\\author{Jim Mochel}\n");

printf("\\setlength{\\hoffset}{-0.8in}\n");
printf("\\setlength{\\oddsidemargin}{0in}\n");
printf("\\setlength{\\textwidth}{7.5in}\n");

printf("\\begin{document}\n\n");

for ( CharSpd = 0; CharSpd <= 10; CharSpd++ )
{

    printf("\\begin{table}\n");
    printf("\\begin{tabular}{l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l}\n");

	# Unranked Action 

	printf(" Roll & Un &");
        
   	for ( Rank = 0; Rank <= 30; Rank++ )
	{
    	printf(" %d ", Rank);

    	if ( Rank < 30 )
        {
            printf(" & ");
		}
    }
        
    printf("\\\\\n");
	printf("\\hline\n");

	for ( Roll = 2; Roll <= 12; Roll++ )
	{
		# Unranked Action 

		if ( Roll == 1)
		{
			Axn = Roll + 8 - int(0.5 * CharSpd);
			SpdPts = int(0.5 * CharSpd);

			if ( Axn < 0 ) 
			{
			  SpdPts += (-1 * Axn);
			  Axn = 0;
			}

			printf("  & %d/%d &", Axn, SpdPts);
            
		}
		else { 		# Ranked Action 

			printf(" %d & ", Roll);	
		
        	for ( Rank = 0; Rank <= 30; Rank++ )
			{
    			Axn = Roll + 8 - int(0.5 * CharSpd) - int(0.5 * Rank);
	    		SpdPts = int(0.5 * CharSpd) + int(0.5 * Rank);

    			if ( Axn < 0 ) 
	    		{
		    	  SpdPts += (-1 * Axn);
			      Axn = 0;
    			}

	    		printf(" %d/%d ", Axn, SpdPts);

				if ( Rank < 30 )
                {
                    printf(" & ");
                }
			}
            
            printf("\\\\\n");

		}


	}

    printf("\\end{tabular}\n");
    printf("\\caption{Reaction and Speed Points for Character Speed %d}\n", CharSpd);
    printf("\\end{table}\n");

	printf("\n");
}


printf("\\end{document}\n");

}
E 2
I 1
E 1
