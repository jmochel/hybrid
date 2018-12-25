# hist2plot.awk
# takes a csv file of history and creates gnuplot files from it.
# 
# 
BEGIN { FS = "," ;

    DateCount = 12;  # Number of dates spans

    StartDates[1] = "1995/1/1";
    StartDates[2] = "2000/1/1";
    StartDates[3] = "2025/1/1";
    StartDates[4] = "2050/1/1";
    StartDates[5] = "2075/1/1";
    StartDates[6] = "2100/1/1";
    StartDates[7] = "2125/1/1";
    StartDates[8] = "2150/1/1";
    StartDates[9] = "2175/1/1";
    StartDates[10] = "2200/1/1";
    StartDates[11] = "2225/1/1";
    StartDates[12] = "2250/1/1";

    EndDates[1] = "1999/12/31";
    EndDates[2] = "2024/12/31";
    EndDates[3] = "2049/12/31";
    EndDates[4] = "2074/12/31";
    EndDates[5] = "2099/12/31";
    EndDates[6] = "2124/12/31";
    EndDates[7] = "2149/12/31";
    EndDates[8] = "2174/12/31";
    EndDates[9] = "2199/12/31";
    EndDates[10] = "2224/12/31";
    EndDates[11] = "2249/12/31";
    EndDates[12] = "2274/12/31";

Prolog = "\
reset\n\
clear\n\
set title 'Confederation History'\n\
set data style points\n\
set xlabel 'Date (Terran Gregorian Calendar)'\n\
set timefmt \"%Y/%m/%d\"\n\
set format x \"%Y\"\n\
set yrange [0:5]\n\
set grid\n\
set xdata time\n\
set ylabel 'Event'\n\
set ytics 0,1\n\
set xlabel \"Years (Terran Gregorian Calendar)\"\n\
set ytics (\"Science\" 4, \"Technology\" 3, \"Minor\" 2, \"Major\" 1, \"Turning Points\" 0)\n\
";

    # Print headers out to appropriate files

    for ( i = 1; i <= DateCount; i++ )
    {
        print Prolog > "hist" i ".plt";
        print "set xrange"  "[\"" StartDates[i] "\":\"" EndDates[i] "\"]" > "hist" i ".plt";
    }
}

{
    for ( i = 1; i <= DateCount; i++ )
    {
        if ( $1 >= StartDates[i] &&  $1 < EndDates[i] )
        {
            printf("set label \"%s\" at \"%s\",%s rotate\n", $3, $1, $2) >> "hist" i ".plt";
        }
    }
}

END {
    for ( i = 1; i <= DateCount; i++ )
    {
        print "plot \"-\" using ($1):($2)\n\"" StartDates[i] "\" 1\nEOF\n" >> "hist" i ".plt";
        print "set terminal postscript landscape \"Palatino\" 6" >> "hist" i ".plt"
        print "set output \"hist" i ".ps\""  >> "hist" i ".plt"
        print "replot"  >> "hist" i ".plt"
        print "set terminal windows" >> "hist" i ".plt"
    }
}

