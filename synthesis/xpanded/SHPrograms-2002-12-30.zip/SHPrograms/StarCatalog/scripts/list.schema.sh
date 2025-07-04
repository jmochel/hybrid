 #! /bin/sh
 psql -c "\d sc_system" starcat > schema.txt
 psql -c "\d sc_system_components" starcat >> schema.txt
 psql -c "\d sc_system_location" starcat >> schema.txt
 psql -c "\d sc_system_names" starcat >> schema.txt
 psql -c "\d sc_system_visuals" starcat >> schema.txt