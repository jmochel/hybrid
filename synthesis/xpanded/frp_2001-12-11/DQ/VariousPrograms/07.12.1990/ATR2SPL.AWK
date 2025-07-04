# DAT2SPL.AWK
# A program that converts a spell description file into a
# spell listing file.

BEGIN {

	while ( (getline < "area.dat" ) != 0) {
		BCLO[$1] = $2; BCHI[$1] = $3; EXPM[$1] = $4;
	}

	while ( (getline < "action.dat" ) != 0) {
		BCLO[$1] = $2; BCHI[$1] = $3; EXPM[$1] = $4;
	}

	while ( (getline < "target.dat" ) != 0) {
		BCLO[$1] = $2; BCHI[$1] = $3; EXPM[$1] = $4;
	}

}

/^@NAME/		{ print $0 }
/^@COLLEGE/		{ print $0 }

/^Light/		{
				BaseChanceLow += BCLO["Light"];
                BaseChanceHigh += BCHI["Light"];
                Experience	+= EXPM["Light"];
                }

/^Dark/			{
				BaseChanceLow += BCLO["Dark"];
                BaseChanceHigh += BCHI["Dark"];
                Experience += EXPM["Dark"];
                }

/^Shadow/		{
				BaseChanceLow += BCLO["Shadow"];
                BaseChanceHigh += BCHI["Shadow"];
                Experience += EXPM["Shadow"];
                }

/^Starlight/			{
				BaseChanceLow += BCLO["Starlight"];
                BaseChanceHigh += BCHI["Starlight"];
                Experience += EXPM["Starlight"];
                }

/^Fire/			{
				BaseChanceLow += BCLO["Fire"];
                BaseChanceHigh += BCHI["Fire"];
                Experience += EXPM["Fire"];
                }

/^Water/			{
				BaseChanceLow += BCLO["Water"];
                BaseChanceHigh += BCHI["Water"];
                Experience += EXPM["Water"];
                }

/^Air/			{
				BaseChanceLow += BCLO["Air"];
                BaseChanceHigh += BCHI["Air"];
                Experience += EXPM["Air"];
                }

/^Earth/			{
				BaseChanceLow += BCLO["Earth"];
                BaseChanceHigh += BCHI["Earth"];
                Experience += EXPM["Earth"];
                }

/^Essence/			{
				BaseChanceLow += BCLO["Essence"];
                BaseChanceHigh += BCHI["Essence"];
                Experience += EXPM["Essence"];
                }

/^Mana/			{
				BaseChanceLow += BCLO["Mana"];
                BaseChanceHigh += BCHI["Mana"];
                Experience += EXPM["Mana"];
                }

/^Matter/			{
				BaseChanceLow += BCLO["Matter"];
                BaseChanceHigh += BCHI["Matter"];
                Experience += EXPM["Matter"];
                }

/^Force/			{
				BaseChanceLow += BCLO["Force"];
                BaseChanceHigh += BCHI["Force"];
                Experience += EXPM["Force"];
                }

/^Mind/			{
				BaseChanceLow += BCLO["Mind"];
                BaseChanceHigh += BCHI["Mind"];
                Experience += EXPM["Mind"];
                }

/^Perception/			{
				BaseChanceLow += BCLO["Perception"];
                BaseChanceHigh += BCHI["Perception"];
                Experience += EXPM["Perception"];
                }

/^Body/			{
				BaseChanceLow += BCLO["Body"];
                BaseChanceHigh += BCHI["Body"];
                Experience += EXPM["Body"];
                }

/^Belief/			{
				BaseChanceLow += BCLO["Belief"];
                BaseChanceHigh += BCHI["Belief"];
                Experience += EXPM["Belief"];
                }

/^Stream/			{
				BaseChanceLow += BCLO["Stream"];
                BaseChanceHigh += BCHI["Stream"];
                Experience += EXPM["Stream"];
                }

/^Fabric/			{
				BaseChanceLow += BCLO["Fabric"];
                BaseChanceHigh += BCHI["Fabric"];
                Experience += EXPM["Fabric"];
                }

/^Creation/		{

				BaseChanceLow += BCLO["Creation"];
                BaseChanceHigh += BCHI["Creation"];
                Experience += EXPM["Creation"];
                }
/^Transmutation/		{

				BaseChanceLow += BCLO["Transmutation"];
                BaseChanceHigh += BCHI["Transmutation"];
                Experience += EXPM["Transmutation"];
                }
/^Summoning/		{

				BaseChanceLow += BCLO["Summoning"];
                BaseChanceHigh += BCHI["Summoning"];
                Experience += EXPM["Summoning"];
                }
/^Binding/		{

				BaseChanceLow += BCLO["Binding"];
                BaseChanceHigh += BCHI["Binding"];
                Experience += EXPM["Binding"];
                }
/^Concentration/		{

				BaseChanceLow += BCLO["Concentration"];
                BaseChanceHigh += BCHI["Concentration"];
                Experience += EXPM["Concentration"];
                }
/^Dissapation/		{

				BaseChanceLow += BCLO["Dissapation"];
                BaseChanceHigh += BCHI["Dissapation"];
                Experience += EXPM["Dissapation"];
                }
/^Translocation/		{

				BaseChanceLow += BCLO["Translocation"];
                BaseChanceHigh += BCHI["Translocation"];
                Experience += EXPM["Translocation"];
                }
/^Locomotion/		{

				BaseChanceLow += BCLO["Locomotion"];
                BaseChanceHigh += BCHI["Locomotion"];
                Experience += EXPM["Locomotion"];
                }
/^Restoration/		{

				BaseChanceLow += BCLO["Restoration"];
                BaseChanceHigh += BCHI["Restoration"];
                Experience += EXPM["Restoration"];
                }
/^Identification/		{

				BaseChanceLow += BCLO["Identification"];
                BaseChanceHigh += BCHI["Identification"];
                Experience += EXPM["Identification"];
                }

/^@BEGINDESC/   {

				BaseChanceLow = 100 - BaseChanceLow;
                BaseChanceHigh = 100 - BaseChanceHigh;

                # Toggle description flag
                BeginDesc = "TRUE"  ;
				
				# print out the total scores 
                print "@RANGE\t" Range
                print "@DURATION\t" Duration
                print "@BASECHANCE\t(" BaseChanceLow ")-(" BaseChanceHigh ")"
                print "@RESIST\t" Resistance
                print "@EXPM\t" Experience
        		}

/./			{
				if ( BeginDesc == "TRUE" ) {
                	print $0
				}
            }
				
/^@ENDDESC/		{
				BeginDesc = "FALSE";
                }












