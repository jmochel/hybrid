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

set xrange["2025/1/1":"2049/12/31"]
set label "Contraceptive Implants" at "2025/5/3",4 rotate
set label "Jerusalem II Established" at "2026/8/18",2 rotate
set label "Maxwell Sweeping Field" at "2026/3/4",4 rotate
set label "1st Artifact" at "2027/1/1",4 rotate
set label "1st Gauss Jammer " at "2027/1/1",2 rotate
set label "1st Genetically Engineered Human" at "2034/1/1",4 rotate
set label "1st Commercial Shuttle Craft" at "2030/1/1",2 rotate
set label "Room Temperature Superconductor" at "2031/1/2",4 rotate
set label "Gauss Jammer Athens" at "2031/7/5",2 rotate
set label "Gauss Jammer Einstein" at "2033/7/1",2 rotate
set label "Gauss Jammer Dyson" at "2034/10/17",2 rotate
set label "Artifacts" at "2034/3/2",4 rotate
set label "Gauss Jammer Hawkings" at "2034/7/3",2 rotate
set label "Gauss Jammer Jerusalem" at "2034/8/6",2 rotate
set label "Gauss Jammer Einst Canton" at "2034/9/3",2 rotate
set label "Gauss Jammer New Paradise" at "2035/1/23",2 rotate
set label "Gauss Jammer Camelot" at "2035/10/2",2 rotate
set label "Gauss Jammer Teng" at "2035/5/11",2 rotate
set label "Gauss Jammer Arcadia" at "2035/6/7",2 rotate
set label "Gauss Jammer " at "2036/1/4",2 rotate
set label "Gauss Jammer " at "2036/5/2",2 rotate
set label "Gauss Jammer " at "2037/1/8",2 rotate
set label "Gauss Jammer " at "2037/5/4",2 rotate
set label "Gauss Jammer " at "2037/8/12",2 rotate
set label "Gauss Jammer " at "2037/1/20",2 rotate
set label "Dynamic Fusion Bottle" at "2037/10/20",4 rotate
set label "1st Commercial VR Cyberlink" at "2039/1/1",3 rotate
set label "Muscle Linkage Theory Established" at "2040/1/1",4 rotate
set label "Gauss Jammer " at "2040/8/13",2 rotate
set label "Gauss Jammer " at "2041/9/20",2 rotate
set label "Mars Forerunner Remains" at "2042/1/10",1 rotate
set label "1st Permanent Bionics " at "2044/1/1",3 rotate
set label "Ares City - Mars Colony" at "2040/1/10",1 rotate
set label "Artifact Non-Citizenship Laws" at "2044/2/2",2 rotate
set label "Netrunning comes of age." at "2046/1/1",2 rotate
set label "Lukewarm Fusion Demonstrated" at "2047/1/1",4 rotate
set label "Lunar Forerunner Remains" at "2042/1/10",1 rotate
plot "-" using ($1):($2)
"2025/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist3.ps"
replot
set terminal windows
