h65266
s 00009/00000/00000
d D 1.1 00/02/04 15:10:52 jmochel 2 1
cC
cK26551
cO-rwxrwxrwx
e
s 00000/00000/00000
d D 1.0 00/02/04 15:10:48 jmochel 1 0
c BitKeeper file g:/BardicTales/Support/BTHistory2rtf.bat
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|20000204200134|52760|7093d84f5cb6fcad
cHdevilmountain.bedford.foliage.com
cK07841
cPSupport/BTHistory2rtf.bat
cR84cc63555cb6fcaf
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
set SGML_CATALOG_FILES=d:/docbook/docbook.cat;d:/jade/catalog
set SGML_SEARCH_PATH=d:/docbook;d:/jade;g:/BardicTales/Support
set DOCBOOKDIR=d:/docbook
set JADEDIR=d:/jade
set STYLESHEET=G:/BardicTales/Support/BTHistory.dsl

jade -G -wall -wxml -t rtf -d %STYLESHEET% -o %1.rtf %1
E 2
I 1
E 1
