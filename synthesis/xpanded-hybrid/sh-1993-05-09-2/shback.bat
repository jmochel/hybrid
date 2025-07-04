find . -name *.bak -exec rm {} ;
find . -name *.err -exec rm {} ;
find . -name *.log -exec rm {} ;
find . -name *.dvi -exec rm {} ;
find . -name *.toc -exec rm {} ;
find . -name *.aux -exec rm {} ;
find . -name *.tof -exec rm {} ;
find . -name *.dlg -exec rm {} ;
find . -name *.zip -exec rm {} ;
pkzip -Rap c:\frp\sh\sh.zip c:\frp\sh\*.*

