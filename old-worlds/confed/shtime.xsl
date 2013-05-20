<!-- 
    ..  Xml Style Sheet - XSL Format
    ..
    ..  Author Jim Jackl-Mochel
    ..  Date 03.30.98
    ..
    ..  Copyright - This code is in the public domain
    ..
    ..  Revision Information 
    ..  ==================== 
    ..  $Author: jmochel $
    ..  $Revision: 1.1.1.1 $
    ..  $Date: 2002/09/04 14:37:01 $
    ..
-->
<XSL>
	<!-- Root Rule, sets up the HTML sheet -->
	<RULE>
		<ROOT/>
		<HTML>
			<BODY BGCOLOR="gray">
				<CHILDREN/>
			</BODY>
		</HTML>
	</RULE>
	<!-- Title Rule for the Timeline itself -->
	<RULE>
		<ELEMENT TYPE="timeline">
			<TARGET-ELEMENT TYPE="title"/>
		</ELEMENT>
		<H1>
			<I>
				<U>
					<CENTER>
						<CHILDREN/>
					</CENTER>
				</U>
			</I>
		</H1>
	</RULE>
	<!-- Title Rule for the Epoch -->
	<RULE>
		<ELEMENT TYPE="epoch">
			<TARGET-ELEMENT TYPE="title"/>
		</ELEMENT>
		<H2>
			<CHILDREN/>
		</H2>
	</RULE>
	<!-- Title Rule for the Event -->
	<RULE>
		<ELEMENT TYPE="event">
			<TARGET-ELEMENT TYPE="title"/>
		</ELEMENT>
		<H3>
			<CHILDREN/>
		</H3>
	</RULE>
	<!-- Formatting rule for the date -->
	<RULE>
		<TARGET-ELEMENT TYPE="date"/>
		<P>
			<CHILDREN/>
		</P>
	</RULE>
	<RULE>
		<TARGET-ELEMENT TYPE="desc"/>
		<P>
			<CHILDREN/>
		</P>
	</RULE>
</XSL>
