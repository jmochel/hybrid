# NPC2TEX.AWK
# File to take NPC datafile and translate it to TeX file
#
#

BEGIN {	
	print "\\font\\name = cmssdc10 scaled 2488"
	print "\\font\\title = cmssdc10 "
	print "\\font\\tiny = cmtt10 "
	count = 0;
}

/\.Nomen/  { 
	$1 = ""; # Zero Out the first field

	# Print out the Origin Name

        if ( $2 == "Origin" ) {
        	$2 = "";	# Zero out the second field
		print "\\leftline{\\name " $0 "}";
        }

        if ( $2 == "Title" ) {
        	$2 = "";	# Zero out the second field
		print "\\leftline{\\title " $0 "}";
        }

        if ( $2 == "Alias" ) {
        	$2 = "";	# Zero out the second field
		print "\\leftline{\\title Alias: " $0 "}";
        }

}

/\.Species/  { 
	$1 = "";
	print "\\bigskip";
        print "\\leftline{Species:\\ " $0 "}";
}

/\.Gender/  { 
	$1 = "";
        print "\\leftline{Gender:\\ " $0 "}";
}

/\.Culture/ { 
	$1 = "";
        print "\\leftline{Culture:\\ " $0 "}";
        print "\\bigskip";
}

/\.BeginStats/	{; }
	
/\.PST/		{	PST = $2; }

/\.PEN/		{	 PEN = $2; }

/\.DEX/		{	 DEX = $2; }

/\.PAG/		{	 PAG = $2; }

/\.PAW/		{	 PAW = $2; }

/\.MST/		{	 MST = $2; }

/\.MEN/		{	 MEN = $2; }

/\.PSI/		{	 PSI = $2; }

/\.MAG/		{	 MAG = $2; }

/\.MAW/		{	 MAW = $2; }

/\.HT/		{	HT = $2; }

/\.WT/		{	WT = $2; }

/\.AGE/		{	 AGE = $2; }

/\.EndStats/ 	{
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

	print "{";
	print "\\tiny \\leftline{Statistics} \\smallskip";
        print "\\hrule height 2pt \\smallskip";
	printf("\\leftline{PST %d PBD %d ACC %d}\n", PST,PBD,ACC);
	printf("\\leftline{PEN %d PEX %d PFT %d}\n", PEN,PEX,PFT);
	printf("\\leftline{DEX %d PCA %d }\n", DEX,PCA);
	printf("\\leftline{PAG %d PMV %d PDF %d}\n", PAG,PMV,PDF);
	printf("\\leftline{PAW %d }\n", PAW);
	printf("\\leftline{MST %d MBD %d FCS %d }\n", MST,MBD,FCS);
	printf("\\leftline{MEN %d MEX %d MFT %d }\n", MEN,MEX,MFT);
	printf("\\leftline{PSI %d MCA %d }\n", PSI,MCA);
	printf("\\leftline{MAG %d MMV %d MDF %d}\n", MAG,MMV,MDF);
	printf("\\leftline{MAW %d }\n", MAW);
        print "\\smallskip";
        printf("\\leftline{HT %5.2f  WT %5.1f AGE %d}\n",HT,WT,AGE);
        print "\\bigskip";
	print "}";

}

/\.BeginPhysDesc/ { 
	print "{";
	print "\\tiny \\leftline{Physical Description} \\smallskip";
        print "\\hrule height 2pt \\smallskip";
}

/\.BeginFrame/ {
	getline;
	while ( $1 !~ /\.EndFrame/ ) {
		if ($1 ~ /\.Build/ ) {
                	Build = $2;
		}
		if ($1 ~ /\.Bone/ ) {
               		Bone = $2;
		}
		if ($1 ~ /\.Tone/ ) {
               		Tone = $2;
		}
		if ($1 ~ /\.Text/ ) {
                	$1 = "";
               		Text = $0;
		}
                getline;
	}       	
        printf("\\leftline{Build:%s Bone:%s Tone:%s (%s)}\n",Build,Bone,Tone,Text);
}

/\.BeginHair/ {
	getline;
	while ( $1 !~ /\.EndHair/ ) {
		if ($1 ~ /\.Length/ ) {
                	Length = $2;
		}
		if ($1 ~ /\.Color/ ) {
               		Color = $2;
		}
		if ($1 ~ /\.Texture/ ) {
               		Texture = $2;
		}
		if ($1 ~ /\.Style/ ) {
                	Style = $2;
		}
		if ($1 ~ /\.Curl/ ) {
               		Curl = $2;
		}
		if ($1 ~ /\.Ornament/ ) {
               		Ornament = $2;
		}
		if ($1 ~ /\.Text/ ) {
                	$1 = "";
               		Text = $0;
		}
                getline;
	}       	
        print "\\leftline{Hair: " Length "," Color "," Texture "," Curl "(" Text   ")}";
}

/\.BeginSkin/ {
}
/\.EndSkin/ {
}

/\.BeginFacialHair/ {
}
/\.EndFacialHair/ {
}

/\.BeginEars/ {
}
/\.EndEars/ {
}

/\.BeginNose/ {
}
/\.EndNose/ {
}

/\.BeginTeeth/ {
}
/\.EndTeeth/ {
}

/\.BeginBearing/ {
}
/\.EndBearing/ {
}

/\.IDMark/ {
	$1 = "";
        if ( $0 != "") {
	        print "\\leftline{Identifying Mark: " $0 "}";
	}
}

/\.BeginVoice/ {
	getline;
	while ( $1 !~ /\.EndVoice/ ) {
		if ($1 ~ /\.Type/ ) {
                	Type = $2;
		}
		if ($1 ~ /\.Clarity/ ) {
               		Clarity = $2;
		}
		if ($1 ~ /\.Accent/ ) {
               		Accent = $2;
		}
		if ($1 ~ /\.SpeechHabits/ ) {
                	$1 = "";
               		Habits = $0;
		}
                getline;
	}       	
        print "\\leftline{Voice: " Type "," Clarity ", Accent: " Accent "(" Habits ")}";
}


/\.EndVoice/ {
}

/\.BeginClothing/ {
}
/\.EndClothing/ {
}

/\.EndPhysDesc/ {
	print "\\bigskip";
	print "}";
}



/\.BeginSkills/ { 
}

/\.EndSkills/ {
}

/\.BeginHistory/ { 
	printf("\\leftline{History}\n");
}
/^\.Histom/ {  
	$1 = "";
        $3 = "";
        $4 = "";
        $5 = "";
        printf("\\leftline{%s}\n",$0);
}

/\.EndHistory/ { 
	print "\\bigskip";	
}

/\.BeginStatus/ { }

/\.EndStatus/ { ; }

/^\.BeginAssoc/ { }

/^\.EndAssoc/ { ; }

/^\.BeginComment/ { 
	printf("{\\tiny\\leftline{Commentary}\n");
	getline;
        while ( $1 !~ /^\.EndComment/ ) {
        	print $0;
                getline;
	}
	print "}"
}

/\.EndNPC/ {  }

END {
	print "\\bye"
}

