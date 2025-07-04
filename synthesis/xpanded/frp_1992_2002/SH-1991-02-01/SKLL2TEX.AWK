BEGIN {	 }

/\.name/  { $1 = "";
						print "\\name\{" $0 "\}"
					}

/\.type/	{	$1 = "";
						print "\\type\{" $0 "\}"
					}

/\.sb/		{	$1 = ""; 
						print "\\sb\{" $0 "\}"
					}

/\.rb/		{	$1 = "";
						print "\\rb\{"  $0 "\}" 
					}


/\.cost/	{ $1 = "";
						print "\\cost\{" $0 "\}"
					}


/\.BeginDetail/ { getline;
									print "\\detail\{";
									while ( $1 != ".EndDetail" ) {
                		getline;
                  	print $0;
									}
                  getline;
                  print "\}" 
                }

/\.BeginEF/			{ getline;
									print "\\ef\{"
									while ( $1 != ".EndEF" ) {
                		getline ;
                  	print $0 ; 
									}
                  getline;
                  print "\}"
                }

