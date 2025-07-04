BEGIN 
{
printf("%s %s %s\n", "Name", "Cost","Difficulty Factor");
}

/^.Name/
{
	Name = $1;
    printf("%s ", Name);
}

/^.BasicType/
{
	Type = $1;

	if (Type == "PDISC" )
    {
    	Cost = 2;
        DF = 0;
    }

	if (Type == "MDISC" )
    {
    	Cost = 3;
        DF = -4
    }

	if (Type == "ART" )
    {
    	Cost = 3;
        DF = -2;
    }

	if (Type == "SCI" )
    {
    	Cost = 7;
		DF = 0;
    }

	if (Type == "TECH" )
    {
    	Cost = 4;
        DF = 0;
    }

	if (Type == "ENG" )
    {
    	Cost = 5;
        DF = 0;
    }
	if (Type == "CRAFT" )
    {
    	Cost = 4;
        DF = -1;
    }
	print "Cost " Cost " DF " DF;
}

/^.Interaction/
{

	Inter = $1;

	if (Inter == "SINGLE" ) 
    {
    	Cost += 1;
        DF += -1;
    }

	if (Inter == "MULT" ) 
    {
    	Cost += 1;
        DF += -2;
    }
}

/^.Implementation/
{
	Imp = $1;

	if ( Imp == "SIMPLETOOL")
    {
    	Cost += 1;
        DF += -1;
    }

	if ( Imp == "COMPLEXTOOL")
    {
    	Cost += 2;
        DF += -2;
    }
}

/^.EndSkill/
{
	printf("%s %s %s\n", Name, Cost, DF);
}
