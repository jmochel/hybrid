#! /bin/sh
# Make the imports for the starcat.sc_system table
awk -f ./scripts/mk.imports.awk ./rawdata/hip_main.dat
