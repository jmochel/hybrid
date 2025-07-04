# A DQ Creature Generator
# by J. Mochel

function die_sim(hi,lo)       
   {  
   # Generates a random value for a given range
   # Where hi is the high end of the range
   # Where lo is the low end of the range  
   # diff           The difference between the hi and lo values                                 
   # base           The base of the range
   # die            the type of die 
   diff = (hi-lo+1)   
   base = (hi-diff)                 
   
   if ( diff == 0 )  # If the diff is 0 just return the base numberand
     {               # exit the function
     return base
     break
     }

   if ((int(diff/2) == (diff/2)) && (diff > 2 )) # If value is even and
     {                                           # greater than 2
     diff1 = diff/2                     
     roll = randint(diff1) + randint(diff1)
     return base + roll 
     break
     }    
   else 
     {
     roll = randint(diff)
     return base + roll 
     break
     }                  
   }

function  randint(n)
     {
     return int( n * rand() ) + 1
     }             


BEGIN { srand() }

$1 == "name"   {         # Read in the name of the beastie
               name = $2
               }   

$1 == "intell" {         # Read in the intelligence of the Beastie
               intell = $2
               }

$1 == "armour" {         # Read in the armour of the beastie
               armour = $2
               }

$1 == "ht"     {         # Creatures HT
               loht = $2 
               hiht = $3
               ht = die_sim(hiht,loht)
               }                                  

$1 == "wt"     {         # Creatures WT
               lowt = $2
               hiwt = $3
               wt = die_sim(hiwt,lowt)
               }

$1 == "ps"     {                   # Creatures PS
    lops = $2
    hips = $3      
    ps = die_sim(hips,lops)
    }

$1 == "md"      {                   # Creatures MD
    lomd = $2
    himd = $3
    md = die_sim(himd,lomd)
    }   

$1 == "ag"      {                   # Creatures ag
    loag = $2
    hiag = $3      
    ag = die_sim(hiag,loag)
    }   

$1 == "tmr"     {                    # Creatures tmr
    lotmr = $2
    hitmr = $3
    tmr = die_sim(hitmr,lotmr)
    move = 50 * tmr
    }

$1 == "en"      {                   # Creatures en
    loen = $2
    hien = $3
    en = die_sim(hien,loen)
    }              

$1 == "fat"     {                    # Creatures fat
    lofat = $2
    hifat = $3
    fat = die_sim(hifat,lofat)
    }

$1 == "wp"      {                   # Creatures wp
    lowp = $2
    hiwp = $3
    wp = die_sim(hiwp,lowp)
    mr = wp + 20
    }   

$1 == "ma"      {                   # Creatures ma
    loma = $2
    hima = $3      
    ma = die_sim(hima,loma)
    }           

$1 == "pc"      {                   # Creatures pc
    lopc = $2
    hipc = $3      
    pc = die_sim(hipc,lopc)
    }

$1 == "pb"     {
    lopb = $2
    hipb = $3      
    pb = die_sim(hipb,lopb)
    }
     
$1 == "attack1" {                  # Creatures first attack        
    bc1 = $2 + md
    dam1 = $3
    }

$1 == "attack2"  {                  # Creatures second attack        
    bc2 = $2 + md
    dam2 = $3
    }

END {
   printf " NAME: %s  INTELL: %s  ARMOUR: %d \n",name,intell,armour;        
   printf " HT: %d  WT: %d  \n",ht,wt;        
   printf " PS: %d    MD: %d    AG: %d   EN: %d  PC: %d \n",ps,md,ag,en,pc;
   printf " WP: %d    MR: %d   TMR: %d  FAT: %d \n",wp,mr,tmr,fat;
   printf " MA: %d    PB: %d   \n",ma,pb; 
   printf " MOVEMENT: %d   \n\n",move; 
   printf " ATTACK1  BC: %d  DAM: %s  \n",bc1,dam1;
   printf " ATTACK2  BC: %d  DAM: %s  \n\n\n\n",bc2,dam2;
   }
   
