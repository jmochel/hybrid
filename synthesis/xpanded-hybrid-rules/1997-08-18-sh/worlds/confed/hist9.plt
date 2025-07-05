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

set xrange["2175/1/1":"2199/12/31"]
set label "Ship Mounted Force Screens" at "2177/1/1",2 rotate
set label "Tachyon-Net Sensors" at "2179/1/1",4 rotate
set label "Nano-Bots" at "2199/1/1",4 rotate
plot "-" using ($1):($2)
"2175/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist9.ps"
replot
set terminal windows
