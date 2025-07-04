BEGIN {	
	print "\\documentstyle\[twocolumn\]\{report\}"
    print "\\begin\{document\}"
    print "\\small\n"
	print "\\begin\{description\}"
}

/\.name/  { $1 = "";
	name = $0;
}

/\.sb/		{	$1 = ""; 
	printf("\\item[%s] %s\n", name, $0);
}

END {
	print "\\end{description}"
	print "\\end\{document\}"
}

