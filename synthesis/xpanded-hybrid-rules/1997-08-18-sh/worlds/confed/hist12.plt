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

set xrange["2250/1/1":"2274/12/31"]
set label "Pulhar Revolt" at "2250/1/1",2 rotate
set label "WARP Torpedo" at "2250/1/1",4 rotate
set label "Rossat Piracy" at "2251/1/1",2 rotate
set label "SS MI fiasco" at "2252/1/1",2 rotate
set label "3rd Gen AI" at "2253/1/1",4 rotate
set label "SCout and Command Suits" at "2253/2/1",3 rotate
set label "Yssdaak First Contact" at "2254/1/1",0 rotate
set label "Yssdaak War" at "2255/1/1",0 rotate
plot "-" using ($1):($2)
"2250/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist12.ps"
replot
set terminal windows
