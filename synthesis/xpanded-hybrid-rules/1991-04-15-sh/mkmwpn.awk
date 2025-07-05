# Program to take in and print into a file information on various Melee Wpns
# JSM 02-13-90

BEGIN {

	# Here be the choices for different menus

	DamageTypeBloc = "Crushing, Piercing, Slicing";
	SymmetryTypeBloc = "Symmetrical, Asymmetrical, Semi-Symmetrical";

	# Clear the screen

	system("cls")
	
	printf("Please enter the name of the output file:\n")
	getline OutFile

	input = "y"
	
	while ( input != "n" ) {
    	name = GetStr("name of the weapon");
		type = menu(DamageTypeBloc);
        length = GetNum("length of the weapon");
		mass = GetNum("mass of the weapon");
        symmetry = menu(SymmetryTypeBloc);
		accuracy = GetNum("accuracy of the weapon");
		dm  = GetNum("damage multiplier of the weapon");
		ti = GetNum("initial tech index of the weapon");	

		printf("Do you wish to continue (Y/N) ? \n")
		getline input 
		}
}
		
function menu(choices) {

	# Split the character string into an array of choices

	EltCount = split(choices, ChoiceArray, ",");

	# Display that list of choices

	for ( ndx = 1; ndx <= EltCount; ndx++ ) {
    	printf("%d - %s\n", ndx, ChoiceArray[ndx] );
	}

	print "Please enter the number of your choice";
	getline ChoiceNum;

	return(ChoiceArray[ChoiceNum]);
}
	
function GetStr(topic) {

	print "Please enter the " topic ;
	getline name;

	return(name);
}
	
function GetNum(topic) {

	print "Please enter the " topic ;
	getline number;

	return(number);
}
	

