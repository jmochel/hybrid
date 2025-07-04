h51145
s 00006/00000/00000
d D 1.1 99/12/02 15:41:29 jmochel 2 1
cC
cK12308
cO-rwxrwxrwx
e
s 00000/00000/00000
d D 1.0 99/12/02 15:41:25 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/DataTools/Names/namegen.bat
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44888
cPDataTools/Names/namegen.bat
cReb4785145cb68f68
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
REM A simple batch file for invoking namegen.awk
REM 02/19/91 jsm Created

awk -f namegen.awk %1 | split -50 
paste xaa xab xac xad > names
erase x??
E 2
I 1
E 1
