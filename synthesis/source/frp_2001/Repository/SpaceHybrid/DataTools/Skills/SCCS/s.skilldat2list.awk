h64739
s 00166/00000/00000
d D 1.1 99/12/02 15:42:08 jmochel 2 1
cC
cK24611
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 15:42:04 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/DataTools/Skills/skilldat2list.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44892
cPDataTools/Skills/skilldat2list.awk
cReb4785095cb68f68
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
# Ignore Comments
#
# Dumps skills to an ASCII List
#

/^.Title/ {
    Temp = $0;
    gsub(".Title","",Temp);
	Title = Temp;

    print "============================================";
    print Title;
    print "============================================";
    print "";
}

/^.BeginSkill/ {
    Temp = $0;
    gsub(".BeginSkill","",Temp);
	Name = Temp;
}

/^.BaseType/ {

    CorrectBaseType = 0;
    
	Type = $2;

	if (Type == "PDISC" )
    {
    	Cost = 2;
        DF = 0;
        CorrectBaseType = 1;        
    }

	if (Type == "MDISC" )
    {
    	Cost = 3;
        DF = -4;
        CorrectBaseType = 1;                
    }

	if (Type == "ART" )
    {
    	Cost = 3;
        DF = -2;
        CorrectBaseType = 1;                
    }

	if (Type == "SCI" )
    {
    	Cost = 7;
		DF = 0;
        CorrectBaseType = 1;        		
    }

	if (Type == "TECH" )
    {
    	Cost = 4;
        DF = 0;
        CorrectBaseType = 1;                
    }

	if (Type == "ENG" )
    {
    	Cost = 5;
        DF = 0;
        CorrectBaseType = 1;                
    }

	if (Type == "CRAFT" )
    {
    	Cost = 4;
        DF = -1;
        CorrectBaseType = 1;                
    }

    if (CorrectBaseType == 0 )
    {
        print "Error: Incorrect BaseType found for " Name;
        print "Error: Incorrect BaseType was  " Type;
        exit;
    }
}

/^.Interaction/ {
    CorrectInter = 0;

	Inter = $2;

	if (Inter == "U" ) 
    {
    	Cost += 0;
        DF += 0;
        CorrectInter = 1;        
    }

	if (Inter == "S" ) 
    {
    	Cost += 1;
        DF += -1;
        CorrectInter = 1;                
    }

	if (Inter == "M" ) 
    {
    	Cost += 1;
        DF += -2;
        CorrectInter = 1;                
    }

    if (CorrectInter == 0 )
    {
        print "Error: Incorrect Interaction found for " Name;
        exit;        
    }

}

/^.Implementation/ {
    CorrectImp = 0;
    
	Imp = $2;

	if ( Imp == "NT")
    {
    	Cost += 0;
        DF += 0;
        CorrectImp = 1;                        
    }

	if ( Imp == "ST")
    {
    	Cost += 1;
        DF += -1;
        CorrectImp = 1;                                
    }

	if ( Imp == "CT")
    {
    	Cost += 2;
        DF += -2;
        CorrectImp = 1;                                
    }

    if (CorrectImp == 0 )
    {
        print "Error: Incorrect Implementation found for " Name;
        exit;
    }
    
}

/.StatBasis/ {
    StatBasis = $2;

    if ( $1 == "" )
    {
        print "Error: No Stat Basis found for " Name;
        exit;        
    }
}    

/^.EndSkill/ {
	printf("%-50s SB:%-10s Cost:%-4s DF:%-5s\n", Name, StatBasis,Cost, DF);
}
E 2
I 1
E 1
