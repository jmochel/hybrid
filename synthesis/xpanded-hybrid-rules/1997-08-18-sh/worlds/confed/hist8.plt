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

set xrange["2150/1/1":"2174/12/31"]
set label "Transom Transform Force Screen " at "2155/1/1",4 rotate
plot "-" using ($1):($2)
"2150/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist8.ps"
replot
set terminal windows
