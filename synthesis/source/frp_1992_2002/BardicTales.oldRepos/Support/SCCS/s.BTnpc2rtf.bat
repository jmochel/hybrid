h63920
s 00010/00000/00000
d D 1.1 00/02/04 15:10:17 jmochel 2 1
cC
cK26040
cO-rwxrwxrwx
e
s 00000/00000/00000
d D 1.0 00/02/04 15:10:14 jmochel 1 0
c BitKeeper file g:/BardicTales/Support/BTnpc2rtf.bat
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|20000204200134|52760|7093d84f5cb6fcad
cHdevilmountain.bedford.foliage.com
cK07837
cPSupport/BTnpc2rtf.bat
cR84cc635d5cb6fcaf
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
set SP_CHARSET_FIXED= YES
set SP_ENCODING=XML
set SGML_CATALOG_FILES=g:/docbook/docbook.cat;d:/jade/catalog
set SGML_SEARCH_PATH=g:/docbook;d:/jade;G:/BardicTales/Support
set DOCBOOKDIR=g:/docbook
set JADEDIR=d:/jade
set STYLESHEET=/BardicTales/Support/btnpc.dsl

jade -G -wall -wxml -t rtf -d %STYLESHEET% -o %1.rtf %1

E 2
I 1
E 1
