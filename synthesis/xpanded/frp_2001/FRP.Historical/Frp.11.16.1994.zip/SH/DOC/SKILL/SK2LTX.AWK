# SK2LTX.AWK
# File to take Skill datafile and tranlate it to LaTeX file
#
#

BEGIN {	
	print "\\documentclass{report}";
	print "\\begin{document}";
	count = 0;

	# Set up some variables and arrays
	# Costs and DFs for each skill type

	TypeCost["TS"] = 150;
	TypeCost["PD"] = 50;
	TypeCost["MD"] = 75;
	TypeCost["ART"] = 200;
	TypeCost["SCI"] = 300;
	TypeCost["ENG"] = 250;
	TypeCost["CFT"] = 150;

	TypeDF["TS"] = 0;
	TypeDF["PD"] = -1;
	TypeDF["MD"] = -2;
	TypeDF["ART"] = -3;
	TypeDF["SCI"] = -1;
	TypeDF["ENG"] = 0;
	TypeDF["CFT"] = -1;

	# Costs and DFs for Skill implementations
	ImplementCost["NT"] = 0;
	ImplementCost["T"] = 25;
	ImplementCost["CT"] = 50;

	ImplementDF["NT"] = 0;
	ImplementDF["T"] = 0;
	ImplementDF["CT"] = -1;

	# Interaction Costs and DFs 
	InteractCost["UN"] = 0;
	InteractCost["AS"] = 25;

	InteractDF["UN"] = 0;
	InteractDF["AS"] = -1;
}

/\.Title/ { $1 = "";
	print "\\title{" $0 "}";
	print "\\date{\\today}";
	print "\\author{J.Mochel}";

	print "\\maketitle";

	print "\\begin{flushleft}";
	print "\\hbadness=10000";
	print "\\hfuzz=\\maxdimen";
	print "\\topmargin=0in";
	print "\\textheight=9in";
	print "\\textwidth=7in";
	print "\\headsep=20pt";
	print "\\marginparsep=0in";
	print "\\marginparwidth=0in";
	print "\\small"
}

/\.BeginSkill/  { $1 = "";
	count++;

	print "\\begin{minipage}[t]{3in}";
	print "\\begin{relate}"
	print "\\item[Name]"
		print "\\ndx{" $0 "}";
}

/\.BaseType/	{	$1 = "";
	BaseType = $2;

	SkillCost = TypeCost[$2];
	SkillDF = TypeDF[$2];

}

/\.Interaction/ {
	SkillCost += InteractCost[$2];
	SkillDF += InteractDF[$2];
}

/\.Implementation/ {
	SkillCost += ImplementCost[$2];
	SkillDF += ImplementDF[$2];
}

/\.StatBasis/		{	$1 = ""; 
				print "\\item[Stat Basis]"
				print $0
				print "\\item[Cost]"
				print SkillCost;
				print "\\item[Use DF]"
				print SkillDF;

}

/\.BeginDesc/ { 
				print "\\item[Description]"

				while ( $1 != ".EndDesc" ) {
				  		getline;
					if ( $1 == ".EndDesc" ) {
						break;
					}
					else {					   	
							print $0;
					}
				}
}

/\.BeginDF/			{ 
				print "\\item[Difficulty Factors]"

				while ( $1 != ".EndDF" ) {
				  		getline;
					if ( $1 == ".EndDF" ) {
						break;
					}
					else {					   	
							print $0;
					}
				}
}

/\.EndSkill/ {
				print "\\end{relate}"

				# Check if we have an even number of minpage environements
				
				printf("\\end{minipage}");

				if ((count % 2) == 0) {
					print "\\\\";
					print "\\vspace{.5in}";
				}
}

END {
	print "\\end{flushleft}"
	print "\\end{document}"
}

