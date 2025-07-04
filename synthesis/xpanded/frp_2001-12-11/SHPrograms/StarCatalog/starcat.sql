DROP TABLE sc_system;
DROP TABLE sc_system_names;
DROP TABLE sc_system_location;
DROP TABLE sc_system_visuals;
DROP TABLE sc_system_components;

CREATE TABLE sc_system
( 
    hip     int,       -- HIPPARCOS id number
    hd      int,       -- Henry Draper reference number
    hr      int,       -- Harvard Revised/Yale bright Star ID
    gliese  varchar(11)    -- Gliese ID
);

CREATE TABLE sc_system_names
( 
    hip     int,       -- HIPPARCOS id number

    common      varchar(80),    -- Common Terran Name
    designation varchar(80),    -- BayerFlamsteed
    confed      varchar(80),    -- Common Confederation Name
    proximan    varchar(80),    -- Common Proximan Name    
    alphan      varchar(80)     -- Common Alphan Name    
);

CREATE TABLE sc_system_location
( 
    hip     int,       -- HIPPARCOS id number

    prox    char,       -- Proximity (There is a system w/in 10 milliarcsecs.)
    
    -- Location (Terran Polar)

    ra      float,          -- Right Acension (degrees) J1991.25 (alpha)
    de      float,          -- Declination (degrees) J1991.25 (delta)
    plx     float,          -- Parallax
    dist    float,          -- Distance (parsecs)
    
    -- Location (Galatic Cartesian)
    
    x_gal   float,
    y_gal   float,    
    z_gal   float,
    
    -- Key distances

    dist_from_sol       float,  -- Distance from sol in parsecs
    dist_from_proxima   float,  -- Distance from proxima in parsecs
    dist_from_alpha     float  -- Distance from alpha in parsecs
);

CREATE TABLE sc_system_visuals
( 
    hip     int,           -- HIPPARCOS id number

    med_vis_mag float,      -- Terran Visual magnitude 
    
    med_abs_mag float,      -- Absolute magnitude 
    max_abs_mag float,      -- Absolute magnitude 
    min_abs_mag float,      -- Absolute magnitude 

    var_period  float,      -- in days
    var_type    char,       -- type 
   
    bv_ci       float,      -- B-V Color Index (Johnson)
    
    sptype      varchar(12)    -- Spectral Type
);


CREATE TABLE sc_system_components
( 
    hip     int,           -- HIPPARCOS id number

    ccdm    varchar(10),
    
    n_sys   int             -- Number of systems with this CCDM
);

COPY sc_system FROM '/h/SHPrograms/starcat.sc_system.import' USING DELIMITERS '|' ;
COPY sc_system_names FROM '/h/SHPrograms/starcat.sc_system_names.import' USING DELIMITERS '|';
COPY sc_system_location FROM '/h/SHPrograms/starcat.sc_system_location.import' USING DELIMITERS '|';
COPY sc_system_visuals FROM '/h/SHPrograms/starcat.sc_system_visuals.import' USING DELIMITERS '|';
COPY sc_system_components FROM '/h/SHPrograms/starcat.sc_system_components.import' USING DELIMITERS '|';