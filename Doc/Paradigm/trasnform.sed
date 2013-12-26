/\\/s/\\//g
/\t/s/\t//g
/begin{description}/s/begin{description}/begin_variablelist/
/end{description}/s/end{description}/end_variablelist/
/^clearpage/d

/{/s/{/,/g
/}/s/}//g

