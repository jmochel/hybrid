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

set xrange["2100/1/1":"2124/12/31"]
set label "Basic Powered Armor" at "2100/1/1",3 rotate
set label "UN Cyberban Lifted" at "2100/6/1",2 rotate
set label "Interplanetary fusion vessel" at "2101/1/1",3 rotate
set label "Lunar-Terra War" at "2107/11/21",1 rotate
set label "1st Commercial Cyberaugmentation" at "2110/3/1",3 rotate
set label "Lunar Independence" at "2112/1/11",0 rotate
set label "Military Gravity Gen" at "2115/1/1",3 rotate
set label "Empathy Induction" at "2116/10/11",4 rotate
set label "Beef and Bread War" at "2121/1/1",1 rotate
set label "3rd gen Powered Armor" at "2121/1/1",3 rotate
set label "Antigathic Field" at "2122/8/9",4 rotate
set label "Costa Rican Conservancy acquires Panama and Nicaragua. " at "2123/1/1",2 rotate
plot "-" using ($1):($2)
"2100/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist6.ps"
replot
set terminal windows
