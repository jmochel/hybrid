# AWK script to process hip_main.dat for impoirt into a star catalog
              
BEGIN { 
    FS = "\|"; 
    
    system("rm starcat.sc_system.import");
    system("rm starcat.sc_system_names.import");
    system("rm starcat.sc_system_location.import");
    system("rm starcat.sc_system_visuals.import");
    system("rm starcat.sc_system_components.import");
}             
              
{             
    Catalog   = $1 ;
    HIP       = $2 ;
    Proxy     = $3 ;
    RAhms     = $4 ;
    DEdms     = $5 ;
    Vmag      = $6 ;
    VarFlag   = $7 ;
    r_Vmag      = $8;
    RAdeg     = $9 ;
    DEdeg     = $10;
    AstroRef  = $11;
    Plx       = $12;
    pmRA      = $13;
    pmDE      = $14;
    e_RAdeg     = $15;
    e_DEdeg     = $16;
    e_Plx       = $17;
    e_pmRA      = $18;
    e_pmDE      = $19;
    DE_RA     = $20;
    Plx_RA    = $21;
    Plx_DE    = $22;
    pmRA_RA   = $23;
    pmRA_DE   = $24;
    pmRA_Plx  = $25;
    pmDE_RA   = $26;
    pmDE_DE   = $27;
    pmDE_Plx  = $28;
    pmDE_pmRA = $29;
    F1        = $30;
    F2        = $31;
    hip       = $32;
    BTmag     = $33;
    e_BTmag     = $34;
    VTmag     = $35;
    e_VTmag     = $36;
    m_BTmag     = $37;
    B_V       = $38;
    e_B_V       = $39;
    r_B_V       = $40;
    V_I       = $41;
    e_V_I       = $42;
    r_V_I       = $43;
    CombMag   = $44;
    Hpmag     = $45;
    e_Hpmag     = $46;
    Hpscat    = $47;
    o_Hpmag     = $48;
    m_Hpmag     = $49;
    Hpmax     = $50;
    HPmin     = $51;
    Period    = $52;
    HvarType  = $53;
    moreVar   = $54;
    morePhoto = $55;
    CCDM      = $56;
    n_CCDM      = $57;
    Nsys      = $58;
    Ncomp     = $59;
    MultFlag  = $60;
    Source    = $61;
    Qual      = $62;
    m_HIP       = $63;
    theta     = $64;
    rho       = $65;
    e_rho     = $66;
    dHp       = $67;
    e_dHp       = $68;
    Survey    = $69;
    Chart     = $70;
    Notes     = $71;
    HD        = $72;
    BD        = $73;
    CoD       = $74;
    CPD       = $75;
    _V_I_red  = $76;
    SpType    = $77;
    r_SpType    = $78;
    
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
        
        print HIP "|||" >> "starcat.sc_system.import";
        print HIP "|||||" >> "starcat.sc_system_names.import";
        print HIP "|" Proxy "|" RAdeg "|" DEdeg "|" Plx "|" rho "|" x_g "|" y_g "|" z_g "|" dist_from_sol "||" >> "starcat.sc_system_location.import";
        print HIP "||||||||" >> "starcat.sc_system_visuals.import";    
        print HIP "||" >> "starcat.sc_system_components.import";
    }        
}
              
