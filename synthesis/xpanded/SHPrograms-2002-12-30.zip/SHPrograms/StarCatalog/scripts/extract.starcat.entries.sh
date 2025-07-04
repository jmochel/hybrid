#! /bin/sh
#
# This extracts data from the starcat database and 
#
psql -c "select n.hip, l.x_gal, l.y_gal, l.z_gal, n.common, n.designation, v.sptype from sc_system_names n, sc_system_location l, sc_system_visuals v where n.hip = l.hip AND n.hip = v.hip AND l.dist_from_sol < 10 order by l.dist_from_sol" starcat > ./temp/system.extr