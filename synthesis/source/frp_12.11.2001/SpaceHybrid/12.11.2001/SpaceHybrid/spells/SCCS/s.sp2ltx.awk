h54984
s 00069/00000/00000
d D 1.1 99/12/02 15:54:09 jmochel 2 1
cC
cK18043
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 15:54:06 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/spells/sp2ltx.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44964
cPspells/sp2ltx.awk
cReb4786ff5cb68f68
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

E 2
I 1
E 1
