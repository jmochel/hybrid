H07364
s 00075/00000/00000
d D 1.1 00/12/08 09:40:46 jmochel 2 1
cC
cF1
cK44052
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/12/08 09:40:46 jmochel 1 0
c BitKeeper file f:/Repository/BardicTales/Support/style/btnpc.xsl
cBjmochel@devilmountain|ChangeSet|20001208143925|39592|56cfc6eb
cHdevilmountain
cK00973
cPSupport/style/btnpc.xsl
cR6817a422
cV4
cX0x1a1
cZ-05:00
e
u
U
f e 0
f x 0x1a1
t
T
I 2
<!-- 
    ..  XML Style Sheet - XSL Format
    ..
    ..  Author Jim Jackl-Mochel
    ..  Date 03.30.98
    ..
    ..  Copyright - This code is in the public domain
    ..
    ..  Revision Information 
    ..  ==================== 
    ..  $Author: jmochel $
    ..  $Revision: 1.4 $
    ..  $Date: 1998/06/29 18:38:19 $
    ..
-->
<xsl>

<!-- root rule, sets up the html sheet -->

<rule>
	<root/>
    <HTML>
	<BODY color="black" background-color="gray" font-size="10pt">
    	<children/>
	</BODY>
	</HTML>
</rule>

<!-- Characters rules -->

<rule>
   	<element type="characters">
    	<target-element type="names"/>
    </element>    

    <H2>
    <children/>
    </H2>
</rule>


<!-- Character rules -->

<rule>
	<target-element type="character">
		<attribute name="status" value="fateless"/>
	</target-element>

	<display-group start-indent="12pt">
		<paragraph font-posture="italic">
			<children/>
		</paragraph>
	</display-group>
</rule>

<rule>
   	<element type="character">
    	<target-element type="names"/>
    </element>    

    <H3>
    <children/>
    </H3>
</rule>

<rule>
	<target-element type="species"/>
    <SPAN font-size="9pt">
        <eval> attributeString("gender") </eval>
    </SPAN>
  <BR/>
</rule>


<rule><target-element type="character"><attribute name="status" value="fated"/></target-element><display-group start-indent="12pt"><paragraph font-posture="italic"><children/></paragraph></display-group></rule></xsl>
E 2
I 1
E 1
