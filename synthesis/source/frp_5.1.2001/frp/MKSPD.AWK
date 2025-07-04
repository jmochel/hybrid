BEGIN {
	MinRxnCnt = 1;
}

END {

	for ( Spd = 0; Spd < 21; Spd++ )
	{

		print "Speed : " Spd;

		printf("|%5s|" , "Roll");
		printf("%5s |","Sup");
		printf("%5s |", "Un");
		
		for ( Rank = 0; Rank <= 21; Rank++ )
		{
			printf("%5d|", Rank);
		}

		print;

		for ( Roll = 2; Roll <= 12; Roll++)
		{
			printf("| %2d  |" , Roll);

			# Handle Suprise		

			RxnCnt = int(Roll + 5 - (1/2*Spd) + 6);
			SpdPnts = int((1/2*Spd));
			printf("%2d/%2d |", RxnCnt , SpdPnts);

			# Handle Unranked		

			RxnCnt = int(Roll + 5 - (1/2*Spd) + 2);
			SpdPnts = int((1/2*Spd) - 1);
			printf("%2d/%2d |",RxnCnt , SpdPnts);
		
			for ( Rank = 0; Rank <= 21; Rank++ )
			{
				RxnCnt = int(Roll + 5 - (1/2*Spd) - (1/2*Rank));
				SpdPnts = int((1/2*Spd) + (1/2*Rank));
				printf("%2d\/%2d|", RxnCnt,  SpdPnts);
			}

			print;
		}
	}
}


