BEGIN 	{ FS = OFS = "-" 
		type = type_menu()
		type = type "\\("
		}

/\.N/ && $2 ~ type	{ printf("Vegetable - %s\n",$1) }

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
 
	return(type)
}

