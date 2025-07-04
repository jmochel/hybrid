# SKLL2LTX.AWK
# File to take Skill datafile and tranlate it to LaTeX file
#
#

BEGIN {	
	print "\\documentstyle[manpage,shskill,gnuindex]\{report\}"
    print "\\makeindex"
    print "\\begin\{document\}"
}

/\.name/  { $1 = "";
				print "\\begin{manpage}{SH Skills}{" $0 "}{Version 0.1}"
				print "\\subtitle\{Name\}"
                print "\\ndx\{" $0 "\}";
}

/\.type/	{	$1 = "";
				print "\\subtitle\{Type\}"
				print $0
}

/\.sb/		{	$1 = ""; 
				print "\\subtitle\{Stat Basis\}"
				print $0
}

/\.cost/	{ $1 = "";
				print "\\subtitle\{Experience Point Cost\}"
				print $0
}

/\.BeginDetail/ { 
				print "\\subtitle\{Detail\}"

				while ( $1 != ".EndDetail" ) {
               		getline;
                    if ( $1 == ".EndDetail" ) {
						break;
					}
					else {                    	
	               		print $0;
					}
				}
}

/\.BeginEF/			{ 
				print "\\subtitle\{Ease Factors\}"

				while ( $1 != ".EndEF" ) {
               		getline;
                    if ( $1 == ".EndEF" ) {
						break;
					}
					else {                    	
	               		print $0;
					}
				}

				print "\\end\{manpage\}"
}

END {
	print "\\begin\{theindex\}"
   	print "\\input\{skills.ids\}"
	print "\\end\{theindex\}"
	print "\\end\{document\}"
}

