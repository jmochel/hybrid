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

set xrange["2000/1/1":"2024/12/31"]
set label "Assassination of Pres. Schroeder" at "2000/11/7",0 rotate
set label "Start of the Storm Age" at "2000/11/7",0 rotate
set label "Cyber Hookup " at "2000/7/1",4 rotate
set label "Tycho Brachae Base" at "2000/8/15",2 rotate
set label "Somalian Desertification" at "2000/10/16",1 rotate
set label "Nigerian Desertification" at "2000/12/16",1 rotate
set label "Tristamy-13 reconstruction" at "2000/12/12",4 rotate
set label "Aids-2" at "2000/1/11",1 rotate
set label "Andes Plague" at "2000/7/3",1 rotate
set label "1st Coporate Army" at "2000/9/10",3 rotate
set label "1st of the Vatican-gate coverups" at "2001/3/13",3 rotate
set label "Israel Invaded " at "2001/8/9",1 rotate
set label "NorAm Combine Formed" at "2002/1/1",2 rotate
set label "US Goes metric." at "2002/6/7",3 rotate
set label "Quebec Secedes" at "2003/1/1",2 rotate
set label "2nd Corporate Army" at "2003/9/11",2 rotate
set label "California Secedes" at "2003/2/4",2 rotate
set label "Optical Plaque Memory" at "2005/2/1",4 rotate
set label "1st L-5 Station" at "2007/10/12",3 rotate
set label "UN leaves NYC" at "2007/12/30",1 rotate
set label "1st Usable basic cyber hookup" at "2007/2/12",4 rotate
set label "Mirashi SunScreen" at "2008/1/1",4 rotate
set label "UN Establishes Point 7" at "2008/1/12",2 rotate
set label "1st International L-5" at "2009/12/1",2 rotate
set label "Sunscreen Power Plant" at "2011/1/1",3 rotate
set label "Deng Gorge Virus" at "2011/4/21",1 rotate
set label "1st Preconstruction of Human Fetus" at "2012/11/3",4 rotate
set label "California rejoins US" at "2008/11/21",2 rotate
set label "VMOP Demonstrated" at "2013/1/1",3 rotate
set label "Prototype Screen Shuttle" at "2013/10/2",3 rotate
set label "Quebec Food riot" at "2014/5/30",1 rotate
set label "Assassination of Soviet Secretary General" at "2014/3/22",2 rotate
set label "2nd Mohawk Brushfire War" at "2013/1/12",1 rotate
set label "Basic Powered Armour" at "2015/10/16",3 rotate
set label "P.R.China economy fails" at "2015/8/6",2 rotate
set label "Moscow-Antartica Screen Shuttle" at "2016/1/1",2 rotate
set label "Darrel Kaven Research Center" at "2017/1/1",2 rotate
set label "Earth-Moon Shuttle servicve" at "2018/6/1",2 rotate
set label "Sanford Jackl dies" at "2019/4/14",2 rotate
set label "Sunscreen patents deeded to Mohawk Nation" at "2020/4/5",2 rotate
set label "Summation Theory of Genetic Drift becomes accepted model for derivation of populations. " at "2021/11/2",4 rotate
set label "Interstellar Jam Probe" at "2023/1/7",3 rotate
set label "Penal Colony Selene" at "2024/1/1",2 rotate
plot "-" using ($1):($2)
"2000/1/1" 1
EOF

set terminal postscript landscape "Palatino" 6
set output "hist2.ps"
replot
set terminal windows
