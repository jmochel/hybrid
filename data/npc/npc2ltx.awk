# NPC2LTX.AWK
# File to take NPC datafile and tranlate it to LaTeX file
#
#

BEGIN {	
	print "\\documentstyle[sh,gnuindex,relate]{report}"
	print "\\begin{document}"
	print "\\begin{flushleft}"
	print "\\small"

	count = 0;
}

/\.Name/  { $1 = "";
  	print "{\\Large\\bf" $0 "}\\hrulefill \\\\";
    print "\\smallskip";
}

/\.Title/  { $1 = "";
	print "Title:{" $0 "} \\\\";
}

/\.Race/  { $1 = "";
	print "Race:" $0 "\\\\";
}

/\.Culture/ { $1 = "";
	print "Culture:" $0 "\\\\";
	print "\\vspace{2mm}";
}

/\.PST/		{	PST = $2; }

/\.PEN/		{	 PEN = $2; }

/\.DEX/		{	 DEX = $2; }

/\.PAG/		{	 PAG = $2; }

/\.PAW/		{	 PAW = $2; }

/\.MST/		{	 MST = $2; }

/\.MEN/		{	 MEN = $2; }

/\.PSI/		{	 PSI = $2; }

/\.MAG/		{	 MAG = $2; }

/\.MAW/		{	 MAW = $2; 
	# This is the last of the primary stats 
	# Print out all of the calculated stats

	PBD = PST + PEN;
	PFT = 2 * PEN + PAG;
	PEX = 4 * PEN + PAG;
	PMV = (PST + PAG)/5;

	MBD = MST + MEN;
	MFT = 2 * MEN + MAG;
	MEX = 4 * MEN + MAG;
	MMV = (MST + MAG)/5;

	ACC = (PST+DEX)/2;
	PCA = (PST+DEX+PAG)/3;
	PDF = (PAG+DEX)/2;

	FCS = (MST+MAG)/2;
	MCA = (MST+PSI+MAG)/3;
	MDF = (MAG+PSI)/2

	GAW = (MAW+PAW)/2;

	print "\\begin{tabular}{llllllll}";
	
	printf("PST & %d & PEN & %d & DEX & %d & PAG & %d \\\\\n",PST,PEN,DEX,PAG);
	printf("MST & %d & MEN & %d & PSI & %d & MAG & %d \\\\\n",MST,MEN,PSI,MAG);
	printf("PBD & %d & PFT & %d & PEX & %d & PMV & %d \\\\\n",PBD,PFT,PEX,PMV);
	printf("MBD & %d & MFT & %d & MEX & %d & MMV & %d \\\\\n",MBD,MFT,MEX,MMV);
	printf("ACC & %d & PCA & %d & PDF & %d &     & \\\\\n",ACC,PCA,PDF);
	printf("FCS & %d & MCA & %d & MDF & %d &     & \\\\\n",FCS,MCA,MDF);
}

/\.HT/		{	HT = $2; }

/\.WT/		{	WT = $2; }

/\.AGE/		{	 AGE = $2; 
	printf("HT & %d & WT & %d & AGE & %d & & \\\\\n",HT,WT,AGE);
    print "\\end{tabular}";
    print "\\linebreak";
	print "\\vspace{2mm}";
}

/\.BeginDesc/ { 
	IsDesc = 0;
	print "{\\bf Description}\\\\";

	while ( $1 != ".EndDesc" ) {
  		getline;
		if ( $1 == ".EndDesc" ) {
			break;
		}
		else {					   	
        	IsDesc = 1;
			print $0;
		}
	}
}


/\.EndDesc/ {
	if ( IsDesc == 1 ) {
		print "\\linebreak";
	}
}


/\.BeginSkills/ { 
				print "{\\bf Skills}\\\\";
				OldFS = FS;
				FS = ",";
				SkillCt = 0;
				while ( $1 != ".EndSkills" ) {
				  		getline;
					if ( $1 == ".EndSkills" ) {
						break;
					}
					else {					   	
						SkillCt++;
						SkillNames[SkillCt] = $1;
						SkillRanks[SkillCt] = $2;
					}
				}
}

/\.EndSkills/ {

	if ( SkillCt > 0 ) {
		for ( Ct = 1; Ct <= SkillCt; Ct++ ) {
			printf("%s(r%d),\n", SkillNames[Ct], SkillRanks[Ct]);
		}
		print "\\linebreak";
	}

	FS = OldFS;
}

/\.BeginHistory/ { 
		IsHist = 0;
		print "{\\em\\bf History}\\\\";

		while ( $1 != ".EndHistory" ) {
			  		getline;
				if ( $1 == ".EndHistory" ) {
					break;
				}
				else {						  	
                	IsHist = 1;
					print $0;
				}
		}

}

/\.EndHistory/ { 
	if ( IsHist == 1 ) {
		print "\\linebreak";
	}
}

/\.BeginStatus/ { 
		IsStatus = 0;
		print "{\\em\\bf Status}\\\\";
		while ( $1 != ".EndStatus" ) {
			  		getline;
				if ( $1 == ".EndStatus" ) {
					break;
				}
				else {						  	
					print $0;
				}
		}
}

/\.EndStatus/ { 
	if ( IsStatus == 1 ) {
		print "\\linebreak";
	}
}

/^\.BeginKnownAssoc/ { 
		IsKnownAssoc = 0;
		print "{\\bf Known Associations}\\\\";
		while ( $1 != ".EndKnownAssoc" ) {
			  		getline;
				if ( $1 == ".EndKnownAssoc" ) {
					break;
				}
				else {						  	
                	IsKnownAssoc = 1;
					print $0;
				}
		}


}

/^\.EndKnownAssoc/ { 
	if ( IsKnownAssoc == 1 ) {
		print "\\linebreak";
	}
}

/^\.BeginSecretAssoc/ { 
		IsSecretAssoc = 0;
		print "{\\em\\bf Secret Associations}\\\\";
		while ( $1 != ".EndSecretAssoc" ) {
			  		getline;
				if ( $1 == ".EndSecretAssoc" ) {
					break;
				}
				else {						  	
					IsSecretAssoc = 1;
					print $0;
				}
		}
}

/^\.EndSecretAssoc/ { 
	if ( IsSecretAssoc == 1 ) {
		print "\\linebreak";
	}
}

/^\.BeginFriends/ { 
		IsFriends = 0;
		print "{\\bf Friends}\\\\";
		while ( $1 != ".EndFriends" ) {
			  		getline;
				if ( $1 == ".EndFriends" ) {
					break;
				}
				else {						  	
                	IsFriends = 1;
					print $0;
				}
		}
}

/^\.EndFriends/ { 
	if ( IsFriends == 1 ) {
		print "\\linebreak";
	}
}

/^\.BeginComment/ { 
		IsComment = 0;
		print "{\\bf Comment}\\\\";
		while ( $1 != ".EndComment" ) {
			  		getline;
				if ( $1 == ".EndComment" ) {
					break;
				}
				else {						  	
                	IsComment = 1;
					print $0;
				}
		}
}

/^\.EndComment/ { 
	if ( IsComment == 1 ) {
		print "\\linebreak";
	}
}

/\.BeginPossess/ { 
	IsPoss = 0;
	print "{\\bf Possessions}\\\\";

	while ( $1 != ".EndPossess" ) {
  		getline;
		if ( $1 == ".EndPossess" ) {
			break;
		}
		else {					   	
        	IsPoss = 1;
			print $0;
		}
	}
}


/\.EndPossess/ {
	if ( IsPoss == 1 ) {
		print "\\linebreak";
	}
}

/\.BeginSpells/ { 
				print "{\\bf Spells}\\linebreak";
				OldFS = FS;
				FS = ",";
				SpellCt = 0;
				while ( $1 != ".EndSpells" ) {
				  		getline;
					if ( $1 == ".EndSpells" ) {
						break;
					}
					else {					   	
						SpellCt++;
						SpellNames[SpellCt] = $1;
						SpellRanks[SpellCt] = $2;
					}
				}
}

/\.EndSpells/ {

	if ( SpellCt > 0 ) {
		for ( Ct = 1; Ct <= SpellCt; Ct++ ) {
			printf("%s(r%d),\n", SpellNames[Ct], SpellRanks[Ct]);
		}
		print "\\linebreak";
	}

	FS = OldFS;
}

/\.EndNPC/ { print "\\newpage"; }

END {
	print "\\end{flushleft}"
	print "\\end{document}"
}

