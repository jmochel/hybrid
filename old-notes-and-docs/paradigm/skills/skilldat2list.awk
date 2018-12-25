# Ignore Comments
#
# Dumps skills to an ASCII List
#

/^.Title/ {
    Temp = $0;
    gsub(".Title","",Temp);
	Title = Temp;
}

/^.BeginSkill/ {
    Temp = $0;
    gsub(".BeginSkill","",Temp);
	Name = Temp;
}

/^.BaseType/ {

	Type = $2;
}

/^.Interaction/ {
	Inter = $2;
}

/^.Implementation/ {
    
	Imp = $2;
}

/.StatBasis/ {
    StatBasis = $2;
}    

/^.EndSkill/ {
	printf("%s,%s,%s,%s,,%s,,\n",Name,Type,Inter,Imp, StatBasis);
}
