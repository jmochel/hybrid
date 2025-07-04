h55914
s 00092/00000/00000
d D 1.1 99/12/02 15:41:25 jmochel 2 1
cC
cK17273
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 15:41:22 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/DataTools/Names/namegen.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44888
cPDataTools/Names/namegen.awk
cReb4785155cb68f68
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 33
t
T
I 2
# A Name Generating Program 
# Jim Mochel
# 03-07-88
# $1 = primary; $2 = Number of primary syllables
# $1 = secondary; $2 = Number of secondary syllables
# $1 = tertiary; $2 = Number of tertiary syllables
# $1 = go        $2 = number of names to generate
#

function die_sim(hi,lo)   {  
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

#First get the primary names
/primary/ {
          primnum = $2
          for ( i =1; i <= primnum; i++ )
               {
               getline
               syllab1[i] = $1
               }
          }
# Get the secondary names
/secondary/ {
            secnum = $2
            for ( i =1; i <= secnum; i++ )
                 {
                 getline
                 syllab2[i] = $1
                 }
            }                             
# Get the tertiary names
/tertiary/  {
            tertnum = $2
            for ( i =1; i <= tertnum; i++ )
                 {
                 getline
                 syllab3[i] = $1
                 }
            }
# Get the number of names to generate
/go/ {
     num_names = $2
     for ( i = 1; i <= num_names; i++ )
          {
                          
temp1 = die_sim(1,primnum)
temp2 = die_sim(1,secnum)
temp3 = die_sim(1,tertnum)
printf("%s%s%s\n",syllab1[temp1],syllab2[temp2],syllab3[temp3]);

          }
         
      }


          
E 2
I 1
E 1
