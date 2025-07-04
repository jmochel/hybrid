BEGIN 	{ FS = OFS = "-" 
		habitat = habitat_menu()
		printf("Listing of Plants by habitat - %s\n\n",habitat)
		}

/\.N/ && $4 ~ habitat	{ printf("%s\n",$1) }

function habitat_menu() {
	habitat = ""
	
	printf("Enter the number of the habitat \n") 
	printf("1:Frozen Waste 2:Tundra 3:Waste 4:Rough 5:Plains \n")
	printf("6:Fields 7:Heavy Woods 8:Light Woods 9:Wetlands \n")
	printf("10:Sea Shore 11:Jungle 12:Caverns 13:Special 14:Quit\n" )

	getline choice

	if ( choice == 	1 )
		habitat = "	FRW"
	else if ( 	choice == 2)
		habitat = "TUN"
	else if ( choice == 3 )
		habitat = "WST"
	else if ( choice == 4)
		habitat = "RGH"
	else if ( choice == 5 )
		habitat = "PLN"
	else if ( choice == 6 )
		habitat = "FLD"
	else if ( choice == 7 )
		habitat = "HWD"
	else if ( choice == 8 )
		habitat = "LWD"
	else if ( choice == 9 )
		habitat = "WTL"
	else if ( choice == 10 )
		habitat = "SSH"
	else if ( choice == 11 )
		habitat = "JNG"
	else if ( choice == 12 )
		habitat = "CAV"
	else if ( choice == 13 )
		habitat = "SPC"
 	else
		status = 0

	return(habitat)
}
