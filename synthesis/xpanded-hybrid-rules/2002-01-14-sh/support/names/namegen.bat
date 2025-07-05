REM A simple batch file for invoking namegen.awk
REM 02/19/91 jsm Created

awk -f namegen.awk %1 | split -50 
paste xaa xab xac xad > names
erase x??
