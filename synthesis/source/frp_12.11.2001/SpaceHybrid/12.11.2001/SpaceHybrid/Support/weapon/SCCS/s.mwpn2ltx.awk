h63630
s 00099/00000/00000
d D 1.1 99/12/02 15:58:02 jmochel 2 1
cC
cK24670
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 15:57:59 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/Support/weapon/mwpn2ltx.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44988
cPSupport/weapon/mwpn2ltx.awk
cReb47863d5cb68f68
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
/^.Name/ {
	$1 = "";
	Name = $0;
}

/^.Type/ {
	Type = $2;
}

/^.DamageType/ {
	DamageType = $2;
}

/^.Usage/ {
	Usage = $2;
}


/^.Length/ {
	Length = $2;
}

/^.Mass/ {
	Mass = $2;
}

/^.Symmetry/ {
	Symmetry = $2;
}

/^.MDist/ {
	MassDistrib = $2;
}

/^.Hands/ {
	Hands = $2;
}

/^.EndWpn/ {

	# Symmetry

	if ( Symmetry == "NotSymmetrical")
    {
    	Sym = 0;
    }
	else if ( Symmetry == "Symmetrical")
    {
       	Sym = 1;
	}
	else if ( Symmetry == "VerySymmetrical")
    {
       	Sym = 2;
	}
    else {
    	print "ERROR : Bad entry for symmetry ";
        print "NR = " NR;
        exit;
    }

	# Mass Distribution

	if ( MassDistrib == "NotUniform")
    {
    	MassDist = 0;
    }
	else if ( MassDistrib == "Uniform")
    {
       	MassDist = 1;
	}
	else if ( MassDistrib == "VeryUniform")
    {
       	MassDist = 2;
	}
    else {
    	print "ERROR : Bad entry for MassDistrib ";
        print "NR = " NR;
        exit;
    }

	LengthSpdFxr = int ( (4*Length)/(Sym+Hands) );
    MassSpdFxr = int ( (2*Mass)/(MassDist+Hands) );

	SpdFxr = LengthSpdFxr + MassSpdFxr;

    Spd = 2 * SpdFxr + (2 * Hands) + 8;

	# Compensate for special cases

	if ( Usage == "Thrust" )
    {
    	Spd = .80 * Spd;
	}

	printf("%-35s LenFxr:%d MassFxr:%d Spd:%d\n",  Name , LengthSpdFxr, MassSpdFxr, Spd);
}



E 2
I 1
E 1
