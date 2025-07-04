set SP_CHARSET_FIXED= YES
set SP_ENCODING=XML
set SGML_CATALOG_FILES=d:/docbook/docbook.cat;d:/jade/catalog;d:/jade/xml.soc
set SGML_SEARCH_PATH=d:/docbook;d:/jade;g:/BardicTales/Support

nsgmls -wxml -t sgml %1
