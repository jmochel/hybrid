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

set xrange["2225/1/1":"2249/12/31"]
set label "Micro-Gravity-Generators" at "2225/1/1",4 rotate
set label "Fractal Growth Organic Metals" at "2226/1/1",3 rotate
set label "Fusion Batteries" at "2227/1/1",3 rotate
set label "Mauler Mech Suit" at "2227/7/1",3 rotate
set label "MI" at "2228/9/1",2 rotate
set label "MI 1st Action" at "2229/2/1",2 rotate
set label "2nd Gen. Bacterial Aug." at "2230/1/1",3 rotate
set label "3rd Gen. Foorce Screens" at "2233/1/1",3 rotate
set label "Elysia rediscovered" at "2233/4/1",2 rotate
set label "Battle of Terragoth" at "2235/1/1",0 rotate
set label "Confederation" at "2235/9/1",0 rotate
set label "Elysia joins Confed" at "2236/1/1",2 rotate
set label "AI Cyberlink" at "2240/1/1",3 rotate
set label "MI Reduction" at "2240/1/1",2 rotate
set label "Marion Sutaken becomes General of the MI" at "2240/2/1",2 rotate
set label "McPherson Relay" at "2235/1/1",3 rotate
set label "Antares Revolt" at "2243/1/1",2 rotate
set label "Neuro-Field BioFeedback Device" at "2245/1/1",3 rotate
set label "" at "2245/3/1",3 rotate
set label "Matter Transmission" at "2245/3/1",4 rotate
set label "Ion Drive Becomes the standard" at "2246/1/1",3 rotate
set label "Marauder Suit" at "2247/1/1",3 rotate
set label "Marion Sutaken becomes Commander General" at "2248/1/1",2 rotate
set label "Stealth Suit" at "2248/1/1",3 rotate
set label "Mora 641 on Confed Council" at "2249/1/1",2 rotate
plot "-" using ($1):($2)
"2225/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist11.ps"
replot
set terminal windows
