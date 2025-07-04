# AWK script to process hip_main.dat for impoirt into a star catalog
              
BEGIN { 
    FS = "\|"; 
    
    system("rm ./temp/starcat.sc_system.import");
    system("rm ./temp/starcat.sc_system_names.import");
    system("rm ./temp/starcat.sc_system_location.import");
    system("rm ./temp/starcat.sc_system_visuals.import");
    system("rm ./temp/starcat.sc_system_components.import");
}             
              
{             
    Catalog   = trim($1) ;
    HIP       = trim($2) ;
    Proxy     = trim($3) ;
    RAhms     = trim($4) ;
    DEdms     = trim($5) ;
    Vmag      = trim($6) ;
    VarFlag   = trim($7) ;
    r_Vmag      = trim($8);
    RAdeg     = trim($9) ;
    DEdeg     = trim($10);
    AstroRef  = trim($11);
    Plx       = trim($12);
    pmRA      = trim($13);
    pmDE      = trim($14);
    e_RAdeg     = trim($15);
    e_DEdeg     = trim($16);
    e_Plx       = trim($17);
    e_pmRA      = trim($18);
    e_pmDE      = trim($19);
    DE_RA     = trim($20);
    Plx_RA    = trim($21);
    Plx_DE    = trim($22);
    pmRA_RA   = trim($23);
    pmRA_DE   = trim($24);
    pmRA_Plx  = trim($25);
    pmDE_RA   = trim($26);
    pmDE_DE   = trim($27);
    pmDE_Plx  = trim($28);
    pmDE_pmRA = trim($29);
    F1        = trim($30);
    F2        = trim($31);
    hip       = trim($32);
    BTmag     = trim($33);
    e_BTmag   = trim($34);
    VTmag     = trim($35);
    e_VTmag   = trim($36);
    m_BTmag   = trim($37);
    B_V       = trim($38);
    e_B_V     = trim($39);
    r_B_V     = trim($40);
    V_I       = trim($41);
    e_V_I     = trim($42);
    r_V_I     = trim($43);
    CombMag   = trim($44);
    Hpmag     = trim($45);
    e_Hpmag   = trim($46);
    Hpscat    = trim($47);
    o_Hpmag   = trim($48);
    m_Hpmag   = trim($49);
    Hpmax     = trim($50);
    HPmin     = trim($51);
    Period    = trim($52);
    HvarType  = trim($53);
    moreVar   = trim($54);
    morePhoto = trim($55);
    CCDM      = trim($56);
    n_CCDM    = trim($57);
    Nsys      = trim($58);
    Ncomp     = trim($59);
    MultFlag  = trim($60);
    Source    = trim($61);
    Qual      = trim($62);
    m_HIP     = trim($63);
    theta     = trim($64);
    rho       = trim($65);
    e_rho     = trim($66);
    dHp       = trim($67);
    e_dHp     = trim($68);
    Survey    = trim($69);
    Chart     = trim($70);
    Notes     = trim($71);
    HD        = trim($72);
    BD        = trim($73);
    CoD       = trim($74);
    CPD       = trim($75);
    _V_I_red  = trim($76);
    SpType    = trim($77);
    r_SpType  = trim($78);
    
    # Print out the various files...

    if ( Plx > 0 )
    {
        # Calc out the results...

        phi = RAdeg;
        theta = DEdeg;
        rho = 1000/Plx;

        # ICRS coordinates (J1991.25) 
                
        x = rho * cos(theta) * cos(phi);
        y = rho * cos(theta) * sin(phi);
        z = rho * sin(theta);
        
        # Galatic coords (Treating the J1995.25 coords as J2000)
        
        x_g = -(0.0550 * x) - (0.8734 * y) - (0.4839 * z);
        y_g = (0.4940 * x) - (0.4449 * y) + (0.7470 * z);
        z_g = -(0.8677 * x) - (0.1979 * y) + (0.4560 * z);

        # Distance from Sol
        
        dist_from_sol = sqrt( (0-x_g)^2 + (0-y_g)^2 + (0-z_g)^2);
                                
        # Print to the appropriate files
        
        print HIP "|||" >> "./temp/starcat.sc_system.import";
        print HIP "|||||" >> "./temp/starcat.sc_system_names.import";
        print HIP "|" Proxy "|" RAdeg "|" DEdeg "|" Plx "|" rho "|" x_g "|" y_g "|" z_g "|" dist_from_sol "||" >> "./temp/starcat.sc_system_location.import";
        print HIP "||" Hpmag "|"Hpmax "|" Hpmin "|" Period "|" HvarType "||" SpType >> "./temp/starcat.sc_system_visuals.import";    
        print HIP "|" CCDM "|" Nsys >> "./temp/starcat.sc_system_components.import";
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