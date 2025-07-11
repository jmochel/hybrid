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
	print "{\\Large" $0 "} \\\\";
}

/\.Title/  { $1 = "";
	print "Title:{" $0 "} \\\\";
}

/\.Race/  { $1 = "";
	print "Race:" $0 "\\\\";
}

/\.Culture/ { $1 = "";
	print "Culture:" $0 "\\\\";
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

	printf("PST:%d\\ PEN:%d\\ DEX:%d\\ PAG:%d \\\\\n",PST,PEN,DEX,PAG);
	printf("MST:%d\\ MEN:%d\\ PSI:%d\\ MAG:%d \\\\\n",MST,MEN,PSI,MAG);
	printf("PBD:%d\\ PFT:%d\\ PEX:%d\\ PMV:%d \\\\\n",PBD,PFT,PEX,PMV);
	printf("MBD:%d\\ MFT:%d\\ MEX:%d\\ MMV:%d \\\\\n",MBD,MFT,MEX,MMV);
	printf("ACC:%d\\ PCA:%d\\ PDF:%d \\\\\n",ACC,PCA,PDF);
	printf("FCS:%d\\ MCA:%d\\ MDF:%d \\\\\n",FCS,MCA,MDF);
}

/\.HT/		{	HT = $2; }

/\.WT/		{	WT = $2; }

/\.AGE/		{	 AGE = $2; 
	printf("HT:%d\\ WT:%d\\ AGE:%d\\\\\n",HT,WT,AGE);
	print "\\vspace{.5cm}";
}

/\.BeginDesc/ { 
				print "{\\em Description}\\\\";
				while ( $1 != ".EndDesc" ) {
				  		getline;
					if ( $1 == ".EndDesc" ) {
						print "\\break";
						break;
					}
					else {					   	
						print $0;
					}
				}
}


/\.BeginSkills/ { 
				print "{\\em Skills}\\break";
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
	for ( Ct = 1; Ct <= SkillCt; Ct++ ) {
		printf("%s(r%d),\n", SkillNames[Ct], SkillRanks[Ct]);
	}
	print "\\break";
	FS = OldFS;
}

/\.BeginHistory/ { 
		print "{\\em History}\\\\";
		while ( $1 != ".EndHistory" ) {
			  		getline;
				if ( $1 == ".EndHistory" ) {
					break;
				}
				else {						  	
					print $0;
				}
		}

}

/\.EndHistory/ { ; }

/\.BeginStatus/ { 
		print "{\\em Status}\\\\";
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

/\.EndStatus/ { ; }

/^\.BeginKnownAssoc/ { 
		print "{\\em Known Associations}\\\\";
		while ( $1 != ".EndKnownAssoc" ) {
			  		getline;
				if ( $1 == ".EndKnownAssoc" ) {
					break;
				}
				else {						  	
					print $0;
				}
		}


}

/^\.EndKnownAssoc/ { ; }

/^\.BeginSecretAssoc/ { 
		print "{\\em Secret Associations}\\\\";
		while ( $1 != ".EndSecretAssoc" ) {
			  		getline;
				if ( $1 == ".EndSecretAssoc" ) {
					break;
				}
				else {						  	
					print $0;
				}
		}


}

/^\.EndSecretAssoc/ { ; }

/^\.BeginFriends/ { 
		print "{\\em Friends}\\\\";
		while ( $1 != ".EndFriends" ) {
			  		getline;
				if ( $1 == ".EndFriends" ) {
					break;
				}
				else {						  	
					print $0;
				}
		}


}
/^\.EndFriends/ { ; }

/^\.BeginComment/ { 
		print "{\\em Comment}\\\\";
		while ( $1 != ".EndComment" ) {
			  		getline;
				if ( $1 == ".EndComment" ) {
					break;
				}
				else {						  	
					print $0;
				}
		}
}
/^\.EndComment/ { ; }

/\.EndNPC/ { print "\\newpage"; }

END {
	print "\\end{flushleft}"
	print "\\end{document}"
}

