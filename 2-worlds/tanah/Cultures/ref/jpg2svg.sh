#!/bin/bash
echo "Converting $1 to an SVG file"

fullname="$1"

fname="${fullname##*/}"
pnmname="/media/jmochel/ramdrive/images/${fname}.pnm"

cat "$1" | jpegtopnm > "/media/jmochel/ramdrive/images/${fname}.pnm"

convert "$1" -dither None -colors 32 "/media/jmochel/ramdrive/images/${fname}.pnm"

cat "${pnmname}" | potrace --svg --opaque > "/media/jmochel/ramdrive/images/${fname}-opaque.svg"
cat "${pnmname}" | potrace --svg > "/media/jmochel/ramdrive/images/${fname}.svg"

autotrace -report-progress -input-format PNM -output-format svg -output-file "/media/jmochel/ramdrive/images/${fname}-color.svg"  "${pnmname}"
autotrace -report-progress -line-threshold 0.1 -line-reversion-threshold 0.1 -despeckle-level 20 -despeckle-tightness 8 -preserve-width -color-count 32 -input-format PNM -output-format svg -output-file "/media/jmochel/ramdrive/images/${fname}-32-color.svg"  "${pnmname}"


