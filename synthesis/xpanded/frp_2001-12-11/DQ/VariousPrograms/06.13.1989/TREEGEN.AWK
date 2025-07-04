
# A Tree Characteristic Generator
# by J. Mochel

function die_sim_1(hi,lo)       
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

     roll = randint(diff)
     return base + roll 
     break
	}

function die_sim_2(hi,lo)       
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

	if ( ((diff % 2) == 0 ) && ( diff > 2) ) {
		roll = randint(diff/2) + randint(diff/2)
		return base + roll
		break
	}
	else {
     	roll = randint(diff)
     	return base + roll 
     	break
	}
}

function  randint(n)
     {
     return int( n * rand() ) + 1
     }             


BEGIN { srand() 
		
		}

$1 == "number" {
						# Read in the number of plants to generate
				number = $2
				}

$1 == "name"   {       # Read in the name of plant
               name = $2
               }   

$1 == "type" {         # Read in the type of plant
               type = $2
               }

$1 == "avail" {         # Read in the availability range
               availability = $2
              }

$1 == "habitat" {       # Read in the habitat range
				habitat = $2
               }                                  

$1 == "potloss" {       # Potency loss
				potloss = $2
               }

$1 == "usage"   {       # Usages 
				usage = $2
    		}

# Start of general description of plant

$1 == "height"  {
		height = $2
		}   

$1 == "dia"  	{
		diameter = $2
		}   

#End of description of plant


END {
for ( i = 1; i <= number; i++ ) { 
#	print number
#	print name
#	print type
#	print availability
#	print habitat
#	print potloss
#	print usage
#	print height
#	print diameter

	# Print out the name and type
	printf ".N %s-%s-",name,type

	# Print out the availability
	avail_no = split(availability,avails,",")
	printf "%s-",avails[die_sim_1(avail_no,1)]	

	# Print out the habitats
	scratch = ""				# Init scartch pad

	habitat_no = split(habitat,habitats,",")

	temp = die_sim_2(habitat_no,1)	# Generate a random number of habitats

	for ( counter = 1; counter <= temp; counter++ ) {
		hab = habitats[die_sim_1(habitat_no,1)]
		if ( match(scratch,hab) ) {
			counter--
		}
		else {
			scratch = scratch "," hab
			printf "%s",hab
			if ( counter != temp )
				printf ","
		}
	}	

	# Print the potency loss given
	printf "-%s-",potloss

	# Print out the usages
	scratch = ""				# Init scartch pad

	usage_no = split(usage,usages,",")

	temp = die_sim_2(usage_no,1)	# Generate a random number of usages

	for ( counter = 1; counter <= temp; counter++ ) {
		use = usages[die_sim_1(usage_no,1)]
		if ( match(scratch,use) ) {
			counter--
		}
		else {
			scratch = scratch "," use
			printf "%s",use
			if ( counter != temp )
				printf ","
		}
	}	

	printf "\n"

	# Print the beginning of the description

	printf ".DS\n"

	# Print the ht and diameter
	
	split(height,ht_parm,",")
	ht = die_sim_2(ht_parm[2],ht_parm[1])

	printf "Avg Height = %d feet\n",ht

	split(diameter,dia_parm,",")
	dia = die_sim_2(dia_parm[2],dia_parm[1])

	printf "Avg Diameter = %d feet\n",dia
					
	# Print the description end marker

	printf ".DE\n"

	# Print the Comment start marker

	printf ".CS\n"

	# Print out the comment end marker

	printf ".CE\n\n"
}
}
