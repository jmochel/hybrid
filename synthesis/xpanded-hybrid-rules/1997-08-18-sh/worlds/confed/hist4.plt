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

set xrange["2050/1/1":"2074/12/31"]
set label " Chinese Korean War" at "2053/2/1",1 rotate
set label " China surrenders" at "2054/10/11",0 rotate
set label "Clayton Counterterrorism Act" at "2055/2/1",2 rotate
set label "Bureau of Mental Health Established" at "2056/1/1",2 rotate
set label "LA Quake of '56" at "2056/4/16",1 rotate
set label "1st Commercial Organ Bank" at "2057/1/3",2 rotate
set label "Rebirth of the Luddite Movement" at "2058/1/1",2 rotate
set label "ZPG Act" at "2059/1/1",2 rotate
set label "Fusion Torpedo" at "2059/2/3",3 rotate
set label "SERGE Destroys Soccorro NM Bioweapon Facility" at "2060/3/6",1 rotate
set label "Socorro Plague Death Toll reaches 1 Million" at "2060/7/3",2 rotate
set label "Mars Independence" at "2061/1/1",0 rotate
set label "Rail Gun" at "2063/4/13",3 rotate
set label "Socorro Plague Toll : 2 Million" at "2065/1/1",2 rotate
set label " " at "2067/1/1",3 rotate
set label "Socorro Plague Toll : 16 Million" at "2068/1/1",2 rotate
set label "AB Flash Field" at "2069/6/4",4 rotate
set label "Socorro Plague Vaccine" at "2070/1/1",2 rotate
set label "Socorro Plague Toll : 36 Million" at "2072/1/1",2 rotate
set label "Shipwreck of the Athens" at "2073/1/1",2 rotate
plot "-" using ($1):($2)
"2050/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist4.ps"
replot
set terminal windows
