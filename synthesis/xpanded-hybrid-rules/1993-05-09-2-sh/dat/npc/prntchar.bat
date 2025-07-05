awk -f npc2ltx.awk %1.n > %1.ltx
run386 c:\tex\bin\virtex &lplain %1.ltx
dvips -olpt2 %1
