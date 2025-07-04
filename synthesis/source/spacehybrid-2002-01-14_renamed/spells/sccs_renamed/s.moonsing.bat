h12424
s 00013/00000/00000
d D 1.1 99/12/02 15:53:20 jmochel 2 1
cC
cK40338
cO-rwxrwxrwx
e
s 00000/00000/00000
d D 1.0 99/12/02 15:53:17 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/spells/moonsing.bat
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44960
cPspells/moonsing.bat
cReb4786cc5cb68f68
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
rem Action Spells
awk -f spelldes.awk singdown.des > moonsing.txt
awk -f spelldes.awk earthper.des >> moonsing.txt
awk -f spelldes.awk firerock.des >> moonsing.txt
rem Divination Spells
awk -f spelldes.awk augury.des >> moonsing.txt
awk -f spelldes.awk forbear.des >> moonsing.txt
awk -f spelldes.awk noprey.des >> moonsing.txt
awk -f spelldes.awk echos.des >> moonsing.txt
rem Healing Spells
awk -f spelldes.awk rebirth.des >> moonsing.txt


E 2
I 1
E 1
