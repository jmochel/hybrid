BEGIN {	
	count = 0;

	print "\\documentstyle[relate,shskill,gnuindex]\{report\}"
    print "\\makeindex"
    print "\\begin\{document\}"
}

/\.name/  { $1 = "";
				if ((count % 2) == 0) {
					print "\hbox{";
				}
				print "\\begin{parbox}[h]{3in}\n"
				print "\\begin{relate}"
				print "\\item{Name}"
}

/\.type/	{	$1 = "";
				print "\\item{Type}"
				print $0
}

/\.sb/		{	$1 = ""; 
				print "\\item{Stat Basis}"
				print $0
}

/\.cost/	{ $1 = "";
				print "\\item{Exp. Point Cost\}"
				print $0
}

/\.BeginDetail/ { 
				print "\\item\{Detail\}"

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
				print "\\item{Ease Factors}"

				while ( $1 != ".EndEF" ) {
               		getline;
                    if ( $1 == ".EndEF" ) {
						break;
					}
					else {                    	
	               		print $0;
					}
				}

				print "\\end{relate}"
				print "\\end{parbox}"

				if ((count % 2) != 0) {
					print "}";
				}

				count++;
}

END {
	print "\\begin\{theindex\}"
   	print "\\input\{skills.ids\}"
	print "\\end\{theindex\}"
	print "\\end\{document\}"
}

