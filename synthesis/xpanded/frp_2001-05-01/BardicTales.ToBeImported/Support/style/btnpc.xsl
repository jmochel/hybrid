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
