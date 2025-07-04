h65524
s 00009/00000/00000
d D 1.1 00/12/08 09:40:46 jmochel 2 1
cC
cF1
cK30076
cO-rwxrwxrwx
e
s 00000/00000/00000
d D 1.0 00/12/08 09:40:46 jmochel 1 0
c BitKeeper file f:/Repository/BardicTales/Support/BTnpc2html.bat
cBjmochel@devilmountain|ChangeSet|20001208143925|39592|56cfc6eb
cHdevilmountain
cK58253
cPSupport/BTnpc2html.bat
cR6806b1aa
cV3
cX0x180
cZ-05:00
c______________________________________________________________________
e
u
U
f e 0
f x 0x1a1
t
T
I 2
set SP_CHARSET_FIXED= YES
ste SP_ENCODING=XML
set SGML_CATALOG_FILES=d:/docbook/docbook.cat;d:/jade/catalog;d:/jade/xml.soc
set SGML_SEARCH_PATH=d:/docbook;d:/jade;g:/BardicTales/Support
set DOCBOOKDIR=d:/docbook
set JADEDIR=d:/jade
set STYLESHEET=g:/BardicTales/Support/btnpc.dsl

jade  -wxml -t sgml -i html -d g:/BardicTales/Support/btnpc.dsl -o %1.html %1
E 2
I 1
E 1
