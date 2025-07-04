# Program to take in and print into a file information on various materials
# JSM 12-28-88

BEGIN {
	system("cls")
	
	printf("Please enter the name of the output file:\n")
	getline OUTFILE

	input = "y"
	
	while ( input != "n" ) {
		name = name_menu()
		type = type_menu()
		availability = avail_menu()
		habitat = habitat_menu()
		potloss = potloss_menu()
		usage = usage_menu()
		
		printf("\n.N %s-%s-%s-%s-%s-%s\n",name,type,availability,habitat,potloss,usage) >> OUTFILE

		description_menu()
		comment_menu()
	
		printf("Do you wish to continue (Y/N) ? \n")
		getline input 
		}
}
		
function name_menu() {
	printf("Please enter the name of the item :\n")
	getline name

	return(name)
}

	
function type_menu() {
	printf("Enter the number of the type used \n") 
	printf("1:Animal 2:Vegetable 3:Mineral 4:Other \n")

	getline choice

	if ( choice == 1 )
		type = "A"
	else if ( choice == 2)
		type = "V"
	else if ( choice == 3 )
		type = "M"
	else
		type = "O"
 
	printf("Please enter the subtype if any (no parentheses) :\n")

	getline subtype

	if ( subtype == "") {
		}
	else {
		temp = sprintf("%s(%s)",type,subtype)
		type = temp
	}

	return(type)
}


function avail_menu() {
	printf("Enter the number of the availability \n") 
	printf("1:Very Common 2:Common 3:Uncommon 4:Rare 5:Very Rare 6:Special\n")	

	getline choice

	if ( choice == 1 )
		availability = "VC"
	else if ( choice == 2)
		availability = "C"
	else if ( choice == 3 )
		availability = "UC"
	else if ( choice == 4)
		availability = "R"
	else if ( choice == 5 )
		availability = "VR"
	else 
		availability = "SPC"
 
	return(availability)
}

function habitat_menu() {
	start = 1
	status = 1
	habitat = ""
	
	while ( status > 0 ) {
		printf("Enter the number of the habitat \n") 
		printf("1:Frozen Waste 2:Tundra 3:Waste 4:Rough 5:Plains \n")
		printf("6:Fields 7:Heavy Woods 8:Light Woods 9:Wetlands \n")
		printf("10:Sea Shore 11:Jungle 12:Caverns 13:Special 14:Quit\n" )

		getline choice

		if ( start > 0) {
			start = 0
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
			}
		else {
	 		if ( choice == 	1 )
				habitat = ( habitat "," "FRW")
			else if ( 	choice == 2)
				habitat = ( habitat "," "TUN")
			else if ( choice == 3 )
				habitat = ( habitat "," "WST")
			else if ( choice == 4)
				habitat = ( habitat "," "RGH")
			else if ( choice == 5 )
				habitat = ( habitat "," "PLN")
			else if ( choice == 6 )
				habitat = ( habitat "," "FLD")
			else if ( choice == 7 )
				habitat = ( habitat "," "HWD")
			else if ( choice == 8 )
				habitat = ( habitat "," "LWD")
			else if ( choice == 9 )
				habitat = ( habitat "," "WTL")
			else if ( choice == 10 )
				habitat = ( habitat "," "SSH")
			else if ( choice == 11 )
				habitat = ( habitat "," "JNG")
			else if ( choice == 12 )
				habitat = ( habitat "," "CAV")
			else if ( choice == 13 )
				habitat = ( habitat "," "SPC")
	 		else
 				status = 0
		}
 	}
	return(habitat)
}

function usage_menu() {
	usage = ""
	status = 1
	start = 1

	while ( status > 0 ) { 
		printf("Enter the number of the usage \n") 
		printf("1:Food 2:Poison 3:Healing 4:Magic\n")
		printf("5:Wood 6:Dye 7:Alchemical 8:Special 9:Quit\n")	
	
		getline choice

		if ( start ) {
			start = 0 
			if ( choice == 1 ) 
				usage = ( usage  "FD" )
			else if ( choice == 2)
				usage = ( usage  "PO" )
			else if ( choice == 3 )
				usage = ( usage  "HL" )
			else if ( choice == 4)
				usage = ( usage  "MG" )
			else if ( choice == 5 )
				usage = ( usage  "WD" )
			else if ( choice == 6 )
				usage = ( usage  "DY" )
			else if ( choice == 7)
				usage = ( usage  "AL")
			else if ( choice == 8)
				usage = ( usage  "SPC")
			else
				status = 0
		}	
		else {
			if ( choice == 1 ) 
				usage = ( usage "," "FD" )
			else if ( choice == 2)
				usage = ( usage "," "PO" )
			else if ( choice == 3 )
				usage = ( usage "," "HL" )
			else if ( choice == 4)
				usage = ( usage "," "MG" )
			else if ( choice == 5)
				usage = ( usage "," "WD" )
			else if ( choice == 6 )
				usage = ( usage "," "DY" )
			else if ( choice == 7)
				usage = ( usage "," "AL")
			else if ( choice == 8)
				usage = ( usage  "," "SPC")

			else
				status = 0
		}
    }
	return(usage)
}

	
function potloss_menu() {
	printf("Please enter the percentage of potency loss, if any  :\n")

	getline percent

	if ( percent == 0) {
		potloss = "NONE"
		return(potloss)
		}

	printf("Please enter the time unit for the potloss:\n")
	printf("s:Seconds m:Minutes h:Hours d:days w:Weeks mo:Month y:Year\n")

	getline timeunit

	printf("Please enter the number of time units for the potloss:\n")
	
	getline number

	temp = sprintf("%s%%/%s%s",percent,number,timeunit)
	potloss = temp

	return(potloss)
}

function description_menu() {
	printf("Start Description\n")
	printf(".DS\n") >> OUTFILE

	status = 1
	
	while ( status > 0 ) {
		status = getline
		if (status > 0 )
			printf("%s\n",$0) >> OUTFILE
	}

	printf("Description Ends\n")	
	printf(".DE\n")	>> OUTFILE
}


function comment_menu() {
	printf("Start Comment\n")
	printf(".CS\n") >> OUTFILE

	status = 1
	
	while ( status > 0 ) {
		status = getline
		if (status > 0 )
			printf("%s\n",$0) >> OUTFILE
	}
	printf("End of Comment\n")
	printf(".CE\n")	>> OUTFILE
}

