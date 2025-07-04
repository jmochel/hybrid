BEGIN 	{ FS = OFS = "-" 
		avail = avail_menu()
		printf("Listing of Plants by Availability - %s\n\n",avail)
		}

/\.N/ && $3 ~ avail	{ printf("%s\n",$1) }

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

