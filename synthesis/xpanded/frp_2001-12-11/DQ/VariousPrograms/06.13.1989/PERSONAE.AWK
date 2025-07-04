# A program to generate the personae and some physical characteristics
# of a human npc
# Jim Mochel
# @1988
# requires the following file 
# $1 = fair || dark
# $1 = go $2 = number of characters to generate.
# 
# ===============User Defined Functions ===============================

function diesim(lo,hi)       
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
     return int( n * rand() ) + 1;
     } 

srand();

/dark/ {
# Read the hair colors from the hair file
for (i = 2; i <= 20; i++) 
     {
     getline hair[i] < "darkhair.dat";
     getline eye[i] < "darkeye.dat" ;
     getline comp[i] < "darkcomp.dat";
     }
}

/fair/ {
# Read the hair colors from the hair file
for (i = 2; i <= 20; i++)
     {
     getline hair[i] < "litehair.dat" ;
     getline eye[i] < "liteeye.dat" ;
     getline comp[i] < "litecomp.dat";
     }
}
                                  
/go/  {           
for (i = 1; i <= $2; i++ )
 {
 # ==================Generate Physical Traits======================
 HAIRCOLOR     =    hair[diesim(2,20)];
 EYECOLOR      =    eye[diesim(2,20)];
 COMPLEXION    =    comp[diesim(2,20)];
 VOICE         =    diesim(2,20);
 ENERGY        =    diesim(2,20);
 
 # ==================Generate Mental Traits======================
 SANITY        =    diesim(2,20);
 INTELLECT     =    diesim(2,20);
 PIETY         =    diesim(2,20);
 MORALS        =    diesim(2,20);
 INDEPENDENCE  =    diesim(2,20);

 # ==================Generate Social Traits======================
 NATURE        =    diesim(2,20);
 DISPOSITION   =    diesim(2,20);
 INTERACTION   =    diesim(2,20);
 MATERIALISM   =    diesim(2,20);
 THRIFT        =    diesim(2,20);
 INTERESTS     =    diesim(2,20);
 NINTERESTS     =    diesim(2,20);

 #===================Print the stats out=========================
 printf("NAME:\n");
 printf("-----------------------------------------------\n");
 printf("Physical Traits - Rated Low to High\n");
 printf("HAIRCOLOR: %s EYECOLOR: %s COMPLEXION : %s\n",HAIRCOLOR,EYECOLOR,COMPLEXION ); 
 printf("VOICE: %d    ENERGY: %d  \n",VOICE,ENERGY);    

 printf("\nMental Traits - Rated Low to High \n");
 printf("SANITY: %d    INTELLECT: %d     PIETY: %d    MORALS: %d \n",SANITY,INTELLECT,PIETY,MORALS);
 printf("INDEPENDENCE: %d \n",INDEPENDENCE);

 printf("\nSocial Traits - Rated low t High\n");
 printf("NATURE: %d    DISPOSITION: %d    INTERACTION: %d \n",NATURE,DISPOSITION,INTERACTION);
 printf("MATERIALISM: %d     THRIFT: %d \n",MATERIALISM,THRIFT);
 printf("INTERESTS (ment->phys): %d    INTERESTS (few->many):  %d \n",INTERESTS,NINTERESTS); 
 printf("\n\n\n\n\n");
 } # End of For                                                        
} # End of pattern
     
