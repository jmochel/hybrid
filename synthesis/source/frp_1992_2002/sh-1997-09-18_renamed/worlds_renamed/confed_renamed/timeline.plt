reset
clear
set title 'Simple Test of Time'
set data style points
set xlabel 'Date'
set timefmt "%Y/%m/%d"
set format x "%Y"
set yrange [ 0 : 5]
set grid
set xdata time
set xrange [ "1990/1/1":"2008/1/1" ]
set ylabel 'Priority'
set label "Explosion of the Super Pheonix" at "1996/11/2",1 rotate
set label "Cloning of Ceolecanth" at "1996/12/24",4 rotate
plot "-" using ($1):($2)
"2100/11/2"  1
EOF
