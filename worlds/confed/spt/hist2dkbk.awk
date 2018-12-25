# hist2dkbk.awk
# takes a csv file of history and creates a docbook3 files from it.
# 
# 

BEGIN { FS = "," ;

Prolog = "\
<!DOCTYPE BOOK PUBLIC \"-//Davenport//DTD DocBook V3.0//EN\" [ \
<!--ArborText, Inc., 1988-1995, v.4001--> \
<!NOTATION drw SYSTEM \"DRW\"> \
<!ENTITY www \"World Wide Web\"> \
]> \
<book>\
    <chapter>\
    <title>The Confederation Timeline</title>\
        <glosslist>\
";


    print Prolog > "hist.sgml";
}




{

print "<glossentry>"    >> "hist.sgml";
print "    <glossterm>" >> "hist.sgml";
print      $1 "-" $3 >> "hist.sgml";
print "<\/glossterm>" >> "hist.sgml";
print "<glossdef>" >> "hist.sgml";
print "<para>" >> "hist.sgml";
print $4 >> "hist.sgml";
print "<\/para>" >> "hist.sgml";
print "<\/glossdef>" >> "hist.sgml";
print "<\/glossentry>" >> "hist.sgml";

}




END {

Epilog = "\
        <\/glosslist>\
    <\/chapter>\
<\/book>\
";

print Epilog >> "hist.sgml";
}

