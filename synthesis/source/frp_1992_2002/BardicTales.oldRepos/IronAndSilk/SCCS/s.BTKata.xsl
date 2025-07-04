h41723
s 00072/00000/00000
d D 1.1 00/02/04 15:11:15 jmochel 2 1
cC
cK04001
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 00/02/04 15:11:12 jmochel 1 0
c BitKeeper file g:/BardicTales/IronAndSilk/BTKata.xsl
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|20000204200134|52760|7093d84f5cb6fcad
cHdevilmountain.bedford.foliage.com
cK07843
cPIronAndSilk/BTKata.xsl
cR84cc634f5cb6fcaf
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
    ..  $Author: jimjm $
    ..  $Revision: 1.2 $
    ..  $Date: 1998/06/29 01:53:57 $
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
E 2
I 1
E 1
