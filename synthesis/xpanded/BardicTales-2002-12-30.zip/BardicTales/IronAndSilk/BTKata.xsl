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
    ..  $Date: 2002/06/24 19:22:04 $
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

	<!-- Name Rule for the Kataset -->
	<RULE>
		<ELEMENT TYPE="kataset">
			<TARGET-ELEMENT TYPE="name"/>
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


	<!-- Name Rule for the Sequence -->
	<RULE>
		<ELEMENT TYPE="kata">
			<TARGET-ELEMENT TYPE="name"/>
		</ELEMENT>
		<H2>
			<CHILDREN/>
		</H2>
	</RULE>

	<!-- Name Rule for the Sequence -->
	<RULE>
		<ELEMENT TYPE="sequence">
			<TARGET-ELEMENT TYPE="name"/>
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
</XSL>
