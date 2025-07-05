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

set xrange["1995/1/1":"1999/12/31"]
set label "Explosion of the Super Pheonix" at "1996/11/2",1 rotate
set label "Cloning of Ceolecanth" at "1996/12/24",4 rotate
set label "Summation Theory of Genetic Drift" at "1997/12/12",4 rotate
set label "Poland joins EEEC" at "1998/11/4",2 rotate
set label "Mohawk Brushfire War" at "1998/10/11",2 rotate
set label "1st Cyber Hookup" at "1996/3/23",4 rotate
set label "Room Temperature Superconductor" at "1997/6/7",4 rotate
set label "Null Clock Logic" at "1997/10/7",4 rotate
set label "Costa Rica preserve" at "1998/11/09",1 rotate
set label "Apartheid Inversion" at "1999/1/10",1 rotate
plot "-" using ($1):($2)
"1995/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist1.ps"
replot
set terminal windows
