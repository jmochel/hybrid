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
    printf("\\begin{tabular}{l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l|l}\n");

		# Unranked Action 

		printf(" Roll & Un &");
            
        	for ( Rank = 0; Rank <= 20; Rank++ )
		{

	    		printf(" %d ", Rank);
				if ( Rank < 20 )
        		        {
		                    printf(" & ");
                		}
		}
            
	        printf("\\\\\n");


	printf("\\hline\n");

	for ( Roll = 1; Roll <= 12; Roll++ )
	{
		# Unranked Action 

		if ( Roll == 1)
		{
			Axn = Roll + 5 - int(0.5 * CharSpd);
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
		
        	for ( Rank = 0; Rank <= 20; Rank++ )
			{
    			Axn = Roll + 5 - int(0.5 * CharSpd) - int(0.5 * Rank);
	    		SpdPts = int(0.5 * CharSpd) + int(0.5 * Rank);

    			if ( Axn < 0 ) 
	    		{
		    	  SpdPts += (-1 * Axn);
			      Axn = 0;
    			}

	    		printf(" %d/%d ", Axn, SpdPts);
				if ( Rank < 20 )
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
