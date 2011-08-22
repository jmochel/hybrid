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



