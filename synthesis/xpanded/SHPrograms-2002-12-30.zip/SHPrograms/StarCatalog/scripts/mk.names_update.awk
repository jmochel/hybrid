# AWK script to process Hyg.csv for import into a star catalog
              
BEGIN { 
    FS = ","; 
    
    print "" > "./temp/starcat.names_designation_update.sql";
    print "" > "./temp/starcat.names_hd_update.sql";
    print "" > "./temp/starcat.names_hr_update.sql";
    print "" > "./temp/starcat.names_gliese_update.sql";
    print "" > "./temp/starcat.names_bayerflamsteed_update.sql";    
}             
              
{    
    if (NR != 1)
    {
            hip = trim($2);
            hd = trim($3);
            hr = trim($4);
            gliese = trim($5);
            bayerflamsteed = trim($6);
            common = trim($7);

            if (hip != "" && hip != "0")
            {
                # Print out the various files...
            
                if ( hd != "" )
                {
                    print "UPDATE sc_system SET hd = " hd " WHERE hip = " hip ";" >> "starcat.names_hd_update.sql";
                }
                
                if ( hr != "" )
                {
                    print "UPDATE sc_system SET hr = " hr " WHERE hip = " hip ";" >> "starcat.names_hr_update.sql";
                }            
                
                if ( gliese != "" )
                {
                    print "UPDATE sc_system SET  gliese = '" gliese "' WHERE hip = " hip ";" >> "starcat.names_gliese_update.sql";
                }                            
    
                if ( bayerflamsteed != "" )
                {
                    print "UPDATE sc_system_names SET designation = '" bayerflamsteed "' WHERE hip = " hip ";" >> "starcat.names_bayerflamsteed_update.sql";
                }            
    
                if ( common != "" )
                {
                    print "UPDATE sc_system_names SET common = '" common "' WHERE hip = " hip ";" >> "starcat.names_designation_update.sql";
                }            
            }
    }
}

function trim(stng)
{
  # knockout leading blanks and tabs
  gsub(/^[ \t]+/,"",stng)
  gsub("'","",stng)  
  # knockout trailing blanks and tabs
  gsub(/[ \t]+$/,"",stng)
  return stng
}