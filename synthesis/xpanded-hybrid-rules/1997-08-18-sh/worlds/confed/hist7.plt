reset
clear
set title 'Confederation History'
set data style points
set xlabel 'Date (Terran Gregorian Calendar)'
set timefmt "%Y/%m/%d"
set format x "%Y"
set yrange [0:5]
set grid
set xdata time
set ylabel 'Event'
set ytics 0,1
set xlabel "Years (Terran Gregorian Calendar)"
set ytics ("Science" 4, "Technology" 3, "Minor" 2, "Major" 1, "Turning Points" 0)

set xrange["2125/1/1":"2149/12/31"]
set label "Mexico attempts to invade Gautemala." at "2125/1/1",2 rotate
set label "NorAm Combine leaves UN" at "2125/1/1",2 rotate
set label "Artifacts used in off-planet mining " at "2130/1/1",2 rotate
set label "Creation of the Conservancy" at "2131/1/1",1 rotate
set label "3rd Generation Agrav" at "2133/1/1",3 rotate
set label "Nono-Manufacture " at "2134/1/1",3 rotate
set label "First Contact" at "2136/1/1",0 rotate
set label "Transition Drive " at "2138/2/11",4 rotate
set label "Alphan Return" at "2138/5/1",2 rotate
set label "Terran Return" at "2138/12/1",2 rotate
set label "Transition Drive made Public" at "2139/2/16",2 rotate
set label "Inertial damping Field" at "2139/1/1",4 rotate
set label "FTL Comm" at "2139/12/4",4 rotate
set label "Return of Einstein" at "2139/4/1",2 rotate
set label "Bacterial Augmentation" at "2139/7/1",3 rotate
set label "Diaspora" at "2140/1/1",0 rotate
set label "Military Artifacts" at "2140/8/1",3 rotate
set label " " at "2141/1/1",4 rotate
set label "Liberation and rediscovery of the planet Elysia" at "2143/1/1",2 rotate
set label "Rediscoveryy of Coven" at "2144/7/7",2 rotate
plot "-" using ($1):($2)
"2125/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist7.ps"
replot
set terminal windows
