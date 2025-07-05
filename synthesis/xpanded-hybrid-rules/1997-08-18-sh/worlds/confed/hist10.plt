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

set xrange["2200/1/1":"2224/12/31"]
set label "Organic Metal Weaves" at "2208/1/1",3 rotate
set label "Crysteel" at "2209/10/1",3 rotate
set label "Alphanium" at "2210/1/1",4 rotate
set label "2nd Gen Force Screens" at "2215/1/1",3 rotate
set label "Karban 541 Born" at "2216/1/1",2 rotate
set label "Loss of the Randall" at "2220/1/1",2 rotate
set label "McPherson Relay Theory" at "2221/9/17",4 rotate
set label "Proximan Contact" at "2222/1/1",0 rotate
set label "Alphan-Terran Coalition" at "2223/8/1",0 rotate
set label "Mobile Armoured Marine" at "2222/9/1",2 rotate
plot "-" using ($1):($2)
"2200/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist10.ps"
replot
set terminal windows
