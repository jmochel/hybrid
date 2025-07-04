h47046
s 00060/00000/00000
d D 1.1 99/12/02 15:41:57 jmochel 2 1
cC
cK06091
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 15:41:54 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/DataTools/Skills/genskillcostlist.awk
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK44891
cPDataTools/Skills/genskillcostlist.awk
cReb47850c5cb68f68
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
# Program to generate the skill cost table
# $ Id $
#

BEGIN {
	SkillMult[0] = 2;
	SkillMult[1] = 2;
	SkillMult[2] = 2;
	SkillMult[3] = 2;
	SkillMult[4] = 3;
	SkillMult[5] = 3;
	SkillMult[6] = 3;
	SkillMult[7] = 4;
	SkillMult[8] = 4;
	SkillMult[9] = 5;
	SkillMult[10] = 5;
	SkillMult[11] = 6;
	SkillMult[12] = 6;
	SkillMult[13] = 7;
	SkillMult[14] = 7;
	SkillMult[15] = 8;
	SkillMult[16] = 8;
	SkillMult[17] = 9;
	SkillMult[18] = 9;
	SkillMult[19] = 10;
	SkillMult[20] = 10;
	SkillMult[21] = 11;
	SkillMult[22] = 11;
	SkillMult[23] = 12;
	SkillMult[24] = 12;
	SkillMult[25] = 13;
	SkillMult[26] = 13;
   	SkillMult[27] = 14;
   	SkillMult[28] = 14;
   	SkillMult[29] = 15;
	SkillMult[30] = 15;
	SkillMult[31] = 16;
       
	# DO the top line of ranks
	printf("Rank    ");		   
	for ( i = 0; i <= 30;i++)
    {
    	printf("%4d",i);
    }
    printf("\n");

	# For each rank

	for ( j = 2; j <= 12; j++)
    {
		printf("%4d",j);		   
		for ( i = 0; i <= 30;i++)
	    {
    		printf("%4d", SkillMult[i] * j);
	    }
	    printf("\n");
	}
	
}

E 2
I 1
E 1
