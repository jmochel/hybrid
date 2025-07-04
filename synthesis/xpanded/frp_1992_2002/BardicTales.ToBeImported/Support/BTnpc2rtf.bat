set SP_CHARSET_FIXED= YES
set SP_ENCODING=XML
set SGML_CATALOG_FILES=g:/docbook/docbook.cat;d:/jade/catalog
set SGML_SEARCH_PATH=g:/docbook;d:/jade;G:/BardicTales/Support
set DOCBOOKDIR=g:/docbook
set JADEDIR=d:/jade
set STYLESHEET=/BardicTales/Support/btnpc.dsl

jade -G -wall -wxml -t rtf -d %STYLESHEET% -o %1.rtf %1

