h32085
s 00110/00000/00000
d D 1.1 99/12/02 16:04:41 jmochel 2 1
cC
cK60371
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 16:04:37 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/worlds/ea/skills.nr
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK45028
cPworlds/ea/skills.nr
cReb47878f5cb68f68
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
.so header.rof
.he //Skill Handling//
.fo //Page #//
.in 5
.rm 71
.fi
All executions of a skill are based on a difficulty Factor (DF) *
a Stat Basis (SB).
.nf
.sp 2
.ce 1
Modifiers to Difficulty Factor
.sp 1
Condition                                               Effect
-----------------------------------------------------------------
Suprise                                                     -1.0
Unranked in Area                                            -2.0
Setup(1)                                                    +1.0
.sp 1
Adding 1st minor complexity(2)                              -0.5
Adding 1st major complexity(3)                              -1.0 
Adding 2nd minor complexity                                 -1.0
Adding 2nd major complexity                                 -2.0 
.sp 5
(1) Setup should be done for as long as the event itself would take.
.sp 1
(2) Minor complexities are typified by splitting perception during a 
physical action or moving while performing a mental action.
.he //Skills//
.bp
Name    :   Acrobatics
Type    :   Simple Skill
SB      :   (AGL+STR)/2
Bonus   :   5%/rnk
---------------------------------------------------------------
5.0     Walking the length of a balance beam
4.0
3.5     Forward roll
3.0
2.0     Backflip to a stand
1.0
.sp 4
Name    :   Horsemanship
Type    :   Simple Skill
SB      :   (AGL+STR)/2 or (AGL+WP)/2
Bonus   :   8%/rnk
---------------------------------------------------------------
6.0     Moving at walk with saddle
5.0     Moving at a trot with saddle
4.0     Moving at a Canter with saddle
3.0     Moving at a gallop with saddle
2.0     
1.0

*No Saddle means that the DF is cut in half
*Note animal mods
*Note that familiar animals are +15%
.sp 4
Name    :   Stealth
Type    :   Craft
SB      :   (AGL)
Bonus   :   8%/rnk
---------------------------------------------------------------
6.0     Moving at a Crawl (1/4 spd) stealthily
5.0     Moving at a Stalk (1/2 spd) stealthily
4.0     Moving at a Walk  (1/1 spd) stealthily
3.0     
2.0     Moving at a Jog   (1.5 spd) stealthily
1.0     Moving at a Run   (2.0 spd) stealthily

*Note armour mods
.sp 4
.ne 12
Name    :   Tracking
Type    :   Craft
SB      :   (PC+WP)/2 or (PC+MA)/2
Bonus   :   6%
---------------------------------------------------------------
5.0     Tracking a person through mud
4.0     
3.0     Tracking on dry ground
2.0     
1.0     Tracking on rock

*Note Rains causes the DF to be cut in half
.sp 4
Name    :   Navigation
Type    :   Simple Skill
SB      :   (PC) or (MA)
Bonus   :   8%
---------------------------------------------------------------
5.0     Telling True North
4.0     
3.0     
2.0     
1.0 
.sp 4
Name    :   Lore Skill
Type    :   Simple Skill
SB      :   (MA) or (PC)
Bonus   :   8%
---------------------------------------------------------------
5.0     Remembering a simple, Notable event 
4.0     
3.0     
2.0     
1.0     Remembering an obscure event 



E 2
I 1
E 1
