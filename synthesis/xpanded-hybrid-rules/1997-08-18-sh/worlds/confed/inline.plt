

# Test of plotting commands and data in same file
set xlabel "How to determine EOF"
set title "Test of reading data from command file"
set time
set data styl linespoint
plot "-" with linesp,sin(x)
.1
.2
.3
.2
.5
.6

.7
.4
.5
.2
.1
EOF

will result i
