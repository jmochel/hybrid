#	SPELLDES.AWK 
#
#	Spell Design Program
#
#	Revision History
#	----------------
#	04/10/91	jsm	Created
#	

# Processes a file of the form:
#
# --------------- Begin File Description ---------------------
#.Name Laser
#.NRG Light
#.ACT Summon
#.ACT Transport
#.ACT Damage
#.CCOMP Material
#.CCOMP Preparation
#.CCOMP Rare
#.CCOMP Expensive
#.CCOMP Somatic
#.CCOMP ToHitRoll
#.TARGET Multiple
#.TCOMP Matter
#.TCOMP Body
#.EC 10
#.CEF 7
#.RES 5
#.RNG 5
#.RNGSCALE 5m
#.CTSCALE Pulse
#.QE 14
#----------------------- End File description ------------------

BEGIN {

	# Initialize the Spell Design Pool

	SDP = 30;

	# Initialize the spells speed
	
	Speed = 0;

	# Open the Various Config Datafiles and read in the data arrays
	
	while ( (getline < "NRGTYPE.CFG") == 1) {
		NrgTypeSDP[$1] = $2;
		NrgTypeSpeed[$1] = $3;
	}

	while ( (getline < "ACTION.CFG") == 1) {
		ActionSDP[$1] = $2;
		ActionSpeed[$1] = $3;
	}

	while ( (getline < "CASTCOMP.CFG") == 1) {
		CastCompSDP[$1] = $2;
	}

	while ( (getline < "TARGET.CFG") == 1) {
		TargetSDP[$1] = $2;
	}

	while ( (getline < "TCOMP.CFG") == 1) {
		TargetCompSDP[$1] = $2;
	}

	while ( (getline < "SCALE.CFG") == 1) {
		GainScaleSDP[$1] = $3;
		LossScaleSDP[$1] = $2;
	}


	while ( (getline < "RNGSCALE.CFG") == 1) {
		RngScaleSDP[$1] = $2;
	}

	while ( (getline < "AOESCALE.CFG") == 1) {
		AoeScaleSDP[$1] = $2;
	}
}

# determine the Name of the spell
/^.Name/ {
	NameFlag = "TRUE";
	Name = $2;
}

# Add in the elements for the Energy Type
/^.NRG/ {
	if ( NRGFlag == "TRUE" ) {
		NRGType = $2;
		SpeedModifier = SpeedModifier + NrgTypeSpeed[$2];
		SDP = SDP + NrgTypeSDP[$2];
	}
	else {
		NRGFlag = "TRUE";
		NRGType = $2;
		SpeedModifier = NrgTypeSpeed[$2];
		SDP = SDP + NrgTypeSDP[$2];
	}
}

# Add in the elements for the Action Type
/^.ACT/ {
	Speed = Speed + SpeedModifier + ActionSpeed[$2];
	SDP = SDP + ActionSDP[$2];
}

# Add in the elements for the Cast Components

/^.CCOMP/ {
	SDP = SDP + CastCompSDP[$2];
}

# Add in components for the target type

/^.TARGET/ {
	SDP = SDP + TargetSDP[$2];
}

# Add in SDP for the target components

/^.TCOMP/ {
	SDP = SDP + TargetCompSDP[$2];
}

# Now subtract out the cost of various desired stats

/^.CDF/ {
	CDFFlag = "TRUE";
	CDF = $2;
	SDP = SDP - CDF;
}

/^.RES/ {
	RESFlag = "TRUE";
	RES = $2;
	SDP = SDP - (2 * RES);
}

/^.LINKRES/ {
	RESFlag = "TRUE";
	RESLINK = $2;
	SDP = SDP - (RESLINK * 4);	
}

/^.QE/ {
	QEFlag = "TRUE";
	QE = $2;
	SDP = SDP - (2*QE);
}

/^.LINKQE/ {
	LINKQEFlag = "TRUE";
	QELINK = $2;
	SDP = SDP - (QELINK * 5);
}

/^.DUR[\t ]/ {
	DURFlag = "TRUE";
	DUR = $2;
	SDP = SDP - DUR;
}

/^.DURSCALE/ {
	DURFlag = "TRUE";
	DURSCALE = $2;
	SDP = SDP + LossScaleSDP[$2];
}

/^.LINKDUR/ {
	LINKDURFlag = "TRUE";
	DURLINK = $2;
	SDP = SDP - (DURLINK * 3);	
}

/^.RNG[\t ]/ {
	RNGFlag = "TRUE";
	RNG = $2;
	SDP = SDP - RNG;
}

/^.RNGSCALE/ {
	RNGFlag = "TRUE";
	RNGSCALE = $2;
	SDP = SDP + RngScaleSDP[$2];
}

/^.LINKRNG/ {
	LINKRNGFlag = "TRUE";
	RNGLINK = $2;
	SDP = SDP - (RNGLINK * 3);	
}

/^.AOE[\t ]/ {
	AOEFlag = "TRUE";
	AOE = $2;
	SDP = SDP - AOE;
}

/^.AOESCALE/ {
	AOEFlag = "TRUE";
	AOESCALE = $2;
	SDP = SDP + AoeScaleSDP[$2];
}

/^.CTSCALE/ {
	CTFlag = "TRUE";
	CTSCALE = $2;
	SDP = SDP + GainScaleSDP[$2];
}

/^.EC/ {
	ECFlag = "TRUE";
	EC = $2;

	if ( EC > 20 ) {
		SDP = SDP + (3 * 20) + (1.5*(EC-20));
	}
	else {
		SDP = SDP + (3*EC);
	} 
}

END {
	print ;
	print "Spell: " Name;
	print "------------------------------------"
	print "Energy Type: " NRGType; 
	print "Energy Cost: " EC " MFT";
	print "Casting Difficulty Factor (CDF): " CDF;
	print "Resistance Difficulty Factor (RDF): " (0 - RES);

	if (RNG) {
		print "Range: " RNG " * " RNGSCALE;
	}

	if (AOE) {
		print "Area of Effect: " AOE " * " AOESCALE;
	}

	if (DUR) {
		print "Duration: " DUR " * " DURSCALE;
	}

	print "Casting Time: " Speed " * " CTSCALE; 

	if ( LINKQEFlag == "TRUE" ) {
		print "Quantitative Effect: " QE "+" QELINK "/rnk"; 
	} 
	else {
		print "Quantitative Effect: " QE;
	}

	print "SDP : " SDP;

	print ;
}
