rem Action Spells
awk -f spelldes.awk singdown.des > moonsing.txt
awk -f spelldes.awk earthper.des >> moonsing.txt
awk -f spelldes.awk firerock.des >> moonsing.txt
rem Divination Spells
awk -f spelldes.awk augury.des >> moonsing.txt
awk -f spelldes.awk forbear.des >> moonsing.txt
awk -f spelldes.awk noprey.des >> moonsing.txt
awk -f spelldes.awk echos.des >> moonsing.txt
rem Healing Spells
awk -f spelldes.awk rebirth.des >> moonsing.txt


