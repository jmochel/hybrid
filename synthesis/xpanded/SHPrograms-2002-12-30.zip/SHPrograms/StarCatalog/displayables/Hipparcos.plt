#
# Simple demo to test Hipparcos data conversion to grid data.
#

set title "Hipparcos Systems"
set nohidden3d
set view 60,30
set autoscale
set data style points
splot "hipparcos.dat"