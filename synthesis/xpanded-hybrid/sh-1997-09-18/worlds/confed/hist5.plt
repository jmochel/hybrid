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

set xrange["2075/1/1":"2099/12/31"]
set label "Landing of the Athens" at "2078/1/1",2 rotate
set label "Laser Rifle" at "2079/9/8",3 rotate
set label "Korea Imperial Throne" at "2081/7/7",1 rotate
set label "Directional Cyberlink" at "2084/5/23",3 rotate
set label "Human Flash Frozen" at "2085/10/1",3 rotate
set label "Artifical Gravity" at "2088/1/1",4 rotate
set label "Anti-laser aerosol" at "2089/1/18",3 rotate
set label "Nano-bots" at "2090/1/1",4 rotate
set label "Conservancy Starts terraforming Mars" at "2091/11/11",2 rotate
set label "Fusion Drive" at "2092/1/1",4 rotate
set label "Graser" at "2096/12/12",4 rotate
set label "Targeting Cyberlinks" at "2097/1/1",3 rotate
set label "Ass'n of Korean Emperor" at "2097/1/1",1 rotate
plot "-" using ($1):($2)
"2075/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist5.ps"
replot
set terminal windows
