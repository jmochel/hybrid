set SP_CHARSET_FIXED= YES
ste SP_ENCODING=XML
set SGML_CATALOG_FILES=d:/docbook/docbook.cat;d:/jade/catalog;d:/jade/xml.soc
set SGML_SEARCH_PATH=d:/docbook;d:/jade;g:/BardicTales/Support
set DOCBOOKDIR=d:/docbook
set JADEDIR=d:/jade
set STYLESHEET=g:/BardicTales/Support/btnpc.dsl

jade  -wxml -t sgml -i html -d g:/BardicTales/Support/btnpc.dsl -o %1.html %1
