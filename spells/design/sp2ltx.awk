# SP2LTX.AWK
# Skill Package to LaTeX translator
# Created 03/27/91 JSM
#
# Takes a .dat file of the form
# -------Begin FIle----------------
# .name XXXXXXXX
# .type (1/4, 1/2, 3/4 ) 
# .BeginSkills
# XXXXXXXX 
# XXXXXXXX
# .EndSkills
# --------End File----------------
# and translates it to a latex skill package listing


BEGIN {	
	print "\\documentstyle[shskill,gnuindex]\{report\}"
    print "\\makeindex"
    print "\\begin\{document\}"
}

/\.name/  { $1 = "";
				print "\\fbox{" $0 "}\\\\"
                print "\\ndx\{" $0 "\}";
				print "\\hrule";
}

/\.type/	{	
	if ( $1 == "3/4" ) {
		Multiplier = .75;
		print "Three Quarter Package";
	}

	if ( $1 == "1/2" ) {
		Multiplier = .50;
		print "One Half Package";
	}

	if ( $1 == "1/4" ) {
		Multiplier = .25;
		print "One Quarter Package";
	}
		
}

/\.BeginSkills/ { 
				print "Package Contents"

				while ( $1 != ".EndSkills" ) {
               		getline;
                    if ( $1 == ".EndSkills" ) {
						break;
					}
					else {                    	
						SkillCount++;
	               		print $0;
					}
				}
}


END {
	print "\\begin\{theindex\}"
   	print "\\input\{skills.ids\}"
	print "\\end\{theindex\}"
	print "\\end\{document\}"
}

