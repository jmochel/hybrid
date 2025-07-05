# BRKNPC.AWK
# File to take NPC datafile and break it into little data files
# 

BEGIN {	
}

/\.Name/  { 
	Name = $2;
    gsub("'","",Name);
    print $0 > Name".n";
}

/\.Title/,/\.EndNPC/ { 
	print $0 >> Name".n"; 
	close(Name".n");
}



