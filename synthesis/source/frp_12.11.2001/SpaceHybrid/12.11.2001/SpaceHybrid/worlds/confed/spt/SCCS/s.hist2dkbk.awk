h42221
s 00055/00000/00000
d D 1.1 99/12/02 16:01:23 jmochel 2 1
cC
cK02717
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 16:01:20 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/worlds/confed/spt/hist2dkbk.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK45008
cPworlds/confed/spt/hist2dkbk.awk
cReb4786455cb68f68
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

E 2
I 1
E 1
