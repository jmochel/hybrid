set SP_CHARSET_FIXED= YES
set SP_ENCODING=XML
set SGML_CATALOG_FILES=d:/docbook/docbook.cat;d:/jade/catalog
set SGML_SEARCH_PATH=d:/docbook;d:/jade;g:/BardicTales/Support
set DOCBOOKDIR=d:/docbook
set JADEDIR=d:/jade
set STYLESHEET=G:/BardicTales/Support/BTHistory.dsl

jade -G -wall -wxml -t rtf -d %STYLESHEET% -o %1.rtf %1
