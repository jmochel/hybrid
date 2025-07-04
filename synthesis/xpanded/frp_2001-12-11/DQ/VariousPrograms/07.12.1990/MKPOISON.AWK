# Program to take in and print into a file information on various materials
# JSM 12-28-88

BEGIN {
	system("cls")
	
	printf("Please enter the name of the output file:\n")
	getline OUTFILE

	input = "y"
	
	while ( input != "n" ) {
		name = name_menu()
		path = path_menu()
		type = type_menu()
		onset = onset_menu()
		duration = duration_menu()
		damage = damage_menu()
		potloss = potloss_menu()

		printf("\n%s-%s-%s-%s-%s-%s-%s\n",name,path,type,onset,duration,damage,potloss) >> OUTFILE

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
	printf("1:Death Dealing 2:Paralytic 3:Damage 4:Hallucinogenic \n")
	printf("5:Sleeping      6:Special   7:Quit\n")

	getline choice

	if ( choice == 1 )
		type = "DD"
	else if ( choice == 2)
		type = "P"
	else if ( choice == 3 )
		type = "D"
	else if ( choice == 4)
		type = "H"
	else if ( choice == 5 )
		type = "SL"
	else 
		type = "SPC"

	return(type)
}

function path_menu() {
	printf("Enter the number of the path used \n") 
	printf("1:Insinuative 2:Contact 3:Ingestive 4:Airborne Powder \n")
	printf("5:Gas 6:Special 7:Quit\n")

	getline choice

	if ( choice == 1 )
		path = "IN"
	else if ( choice == 2)
		path = "CO"
	else if ( choice == 3 )
		path = "IG"
	else if ( choice == 4)
		path = "AP"
	else if ( choice == 5 )
		path = "GS"
	else 
		path = "SPC"

	return(path)
}

function onset_menu() {
	printf("Please enter the time unit for the onset:\n")
	printf("s:Seconds m:Minutes h:Hours d:days w:Weeks mo:Month y:Year\n")
	getline timeunit

	printf("Please enter the number of time units for the onset:\n")
	getline number

	temp = sprintf("%s%s",number,timeunit)
	onset = temp

	return(onset)
}

function duration_menu() {
	printf("Please enter the time unit for the duration:\n")
	printf("s:Seconds m:Minutes h:Hours d:days w:Weeks mo:Month y:Year\n")
	getline timeunit

	printf("Please enter the number of time units for the duration:\n")
	getline number

	temp = sprintf("%s%s",number,timeunit)
	duration = temp

	return(duration)
}


function damage_menu() {
	printf("Please enter the size of the damage die used:\n")
	getline diesize

	printf("Please enter the number of die used:\n")
	getline number

	printf("Please enter the modifier added to the total:\n")
	getline mod
	if ( mod == "" )
		mod = 0

	temp = sprintf("%sd%s+%s",number,diesize,mod)
	damage  = temp

	return(damage)
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

