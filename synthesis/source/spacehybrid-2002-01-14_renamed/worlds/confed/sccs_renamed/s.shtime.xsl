h47776
s 00072/00000/00000
d D 1.1 99/12/02 16:00:13 jmochel 2 1
cC
cK09514
cO-rw-rw-rw-
e
s 00000/00000/00000
d D 1.0 99/12/02 16:00:09 jmochel 1 0
c BitKeeper file G:/SpaceHybrid/worlds/confed/shtime.xsl
cBjmochel@devilmountain.bedford.foliage.com|ChangeSet|19991202203126|52994|e2968a7f5cb68f67
cHdevilmountain.bedford.foliage.com
cK45001
cPworlds/confed/shtime.xsl
cReb4786585cb68f68
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
    ..  $Author: jmochel $
    ..  $Revision: 1.1.1.1 $
    ..  $Date: 1999/06/11 18:31:06 $
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
E 2
I 1
E 1
