zh='http://';zai=0;zop=0
z0='';zg=0;q='>'
x0='<a href';x1='="';x2=x0+x1;x3='" title="';x4='" target=_blank>';x5='"><b>Buy a Link Now!</b></a>';x6='" style="color: ';x9='jsc='
xa='&nbsp;&nbsp;';xb='<br>'
sc='script'
scb='<'+sc+q
sce='<\/'+sc+q
scs='<'+sc+' language="JavaScript" src="'
uy='about.com';u3='';zru=''
ul='Featured Sponsor';um='Sponsored Link';un='https://sprinks.'+uy+'/userforms/create_user.htm?pc=abt_'+gs
if(!zi){zz='';zx='30514';zde=4;zdp=20;zds=20;zfp=100;zfs=100}
if(!zc(0,x9)){zc(1,x9,1,9999999,'')}
f0='<font ';f1='face="';f2=f0+f1;f3='size=';f7='<b>';f8='</b>';f9='</font>';fz=', geneva, helvetica" ';fa='verdana'+fz;fb='arial'+fz
c0='color=';c1=' '+c0;c2=' bg'+c0
qa='<\/td>';qb='<\/tr>';qc='<\/table>';qd=qa+qb;qf=' class=';qg='</span>';qh=' height=';qi=' width='
ac=new Array('','#cc0000','#666666','#ffffff','#000000','#0066cc','#000066','#cccccc','#999999','#ccccff')
aa=new Array('','left','center','right','top','bottom')
af=new Array('',fa,fb)
at=new Array('',um,um+'s','Great Links!','Advertisement',x2+un+x5,x2+un+x6+ac[3]+x5,ul,ul+'s','Special Offer')
al=new Array(999,40,74,120,170)
ap=new Array('<p>','')
as=new Array('</p>',xb)
zo4=new Array(
'13522372323220224022013001',
'11522372323220224022013001',
'13522372329220224022013001',
'12522302429220224022013001',
'11522372329220224022013001',
'12522302423220224022013001')
zo6=new Array(
'33222300009220224022013001',
'32222300009220224022013001',
'31222300009220224022013001')
function w(p){document.writeln(p)}
function zin(){if(zai)return
var n=navigator.appName.substring(0,8)
zbV=navigator.appVersion
zbT=(n+parseFloat(zbV)).toLowerCase()
zbC=n+parseFloat(zbV.substring(0,1))
zrn=Math.random()
zat='" target="_top"'
zN4=zbT.indexOf("netscape4.0")>0
zTV=zbT.indexOf("webtv")>0
zast=''
zai=1}
function es(p,c){p=unescape(p);p=escape(p)
if(p.length)zc(1,c,p,zde,'')
else p=zc(0,c)
return p}
function zr(m){return Math.floor(Math.random()*999)%m}
function ztm0(t){zas=x1+zh+'ad.'+t+'/';zaj=x0+zas+'jump';zai='<img src'+zas+'ad';zaf='<iframe src'+zas+'adi'}
function ztm(s,e){var r=0,t='doubleclick.net';s=s*1;e=e*1;if(xd>s&&xd<e){t=uy;r=1}
ztm0(t);return r}
function adunit(k,bp,dn,c,gs,g,h,o,sn,p){zin();if(!p||!p.length)p=sn;k=es(k,'kw=')
var s='',e=o=='1'?'':o,a,m,aux,ax,d=g+'x'+h,hw=qh+h+qi+g,r='\r',v=ze(e+g+''+h+'=',zz),l=v.length,b=0,e=0,t=0,q=100
if(!l){v=zx;l=v.length}
if(l>4){t=1*v.charAt(0);b=1*v.substring(1,3);e=1*v.substring(3,5);if(l>7)q=1*v.substring(6,8)}
m=ztm(b,e);if(!m&&zr(100)>q){m=1;t=1*v.substring(5,6)}
if(m){if(t==2)return;if(t==1)ztm0(uy);if(t==3&&gsi)return zsa('',g,h)}
if(o.length>1){o=s[0];s=substring(1)}
ax='/'+bp+dn+'/'+c+'_'+gs+';svc='+s+';site='+gs+';kw='+k+';chan='+c+';syn=about;'
aux=ax+'pos='+p+';sz='+d+';ord='+o+zrn
if(o=='7'){w(scs+zas+'adj'+aux+'.js">'+sce);return}
a=zast+aux+r+zas+r+g+r+h+r+sn+r
if(zN4||zTV){w(zaj+aux+zat+'>')
w(zai+aux+'"'+hw+' border=0>')}
else{w('<nolayer>')
w(zaf+aux+zat+hw+' border=0 marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=no>')
if(zbC!='Netscape2'){w(zaj+aux+zat+'>')
w(zai+aux+'"'+hw+' border=0></a>')}
w('</iframe></nolayer><ilayer id="'+sn+'" visibility=hide'+hw+'></ilayer>')}
zast=a}
function wc(a,b,g,h,u){var j=2+g*1,k=2+h*1
document.writeln('<layer clip="',j,',',k,'" src',b,'adl',a,zat,' visibility="hide" onload="moveToAbsolute(',u,'.pageX,',u,'.pageY); visibility=\'show\';"></layer>')}
function pl(){var s=zast,t,i=s.indexOf('\r')
if(i<0){t=s;s=''}
else{t=s.substring(0,i);s=s.substring(i+1)}
zast=s
return t}
function adclose(){zac(0)}
function zac(a,k,b,d,c,g,o){var l,r,s,t,u,v;zin();if(zast)while(zast.length){r=pl();s=pl();t=pl();u=pl();v=pl();wc(r,s,t,u,v)}
s=zc(0,'zsp=');if((s||(ztp&&(ztp>zr(100))))&&ztp>-1){l=document.URL;w('<img src="'+zh+'pixel3.'+uy+'/mp/pixel/pixel.cgi?s=&r=&u='+l+'"'+qh+1+qi+1+'>');ztp=-1}
if(parseInt(zbV)<4||zTV)return
if(a&1)zcc(k,b,d,c,g,o)
if(a&2&&zafc('ps4=',zfd,zdd))adunit(k,b,d,c,g,1,1,'7','dhslot1')
if(zru.length&&(1==zr(1000)))w('<img src="'+zh+'pixel3.'+uy+'/mp/pixel/pixel.cgi?p='+gs+zru+'"'+qh+1+qi+'1 alt=>')}
function ze(p,c){var e,r='',i=c.indexOf(p);if(i<0)return r
i+=p.length;e=c.indexOf(";",i);if(e<0)e=c.length
r=c.substring(i,e);return r}
function zc(o,p,v,m,d){var eD=new Date(),Dt,iV,D=';Path=/;Domain='+d+'.'+uy+';Expires='
if(o){eD.setTime(eD.getTime()+(60000*m))
Dt=eD.toGMTString()
document.cookie=p+v+D+Dt;return v}
if(!document.cookie)return ''
return ze(p,document.cookie)}
function zct(p,m,d){var r=0,i=0,v=zc(0,p)
if(v.length)i=parseInt(v)
if(i<2)zc(1,p,i+1,m,d)
return(i==1)}
function zSb(s,b,e){var r,c=0;if(e<0){c=1;e=-e};r=s.substring(b,e);if(c)r=parseInt(r);return r}
function zsQ(){var r=0,z=(zr(100)<zpo)?zoA[zr(zoA.length)]:'';if(z.length){r=1;zpu(zSb(z,0,-4),z.substring(12),zSb(z,4,-8),zSb(z,8,-12))};return r}
function zsprn(t,g,f){var r=zsT(f);if(r){if(!zsQ())zsP(t,g,'',xg)};return r}
function zsT(f){return (f||((zr(100)<zfs)&&zct("ps3=",zds,'')))&&gsi&&(zs.length>16)}
function zsP(t,g,k,d,r){zc(1,"st=",t&256?1:0,4,'');if(g)zc(1,"gs=",g,4,'');if(k)zc(1,"k=",k,4,'');if(d)zc(1,"xg=",d,4,'');if(r)zc(1,"r=",r,4,'');zpu(t,'f.'+uy+'/z/js/spr00.htm',630,480,'Spr')}
function zcc(k,b,d,c,g,o){zin()
if(parseInt(zbV)<4||zTV)return
if(zsprn(768,g,0));
else if(zr(100)<zfp&&zct("ps1=",zdp,''))adunit(k,b,d,c,g,1,1,o,'popslot1')}
function zafc(n,f,d){var x=zc(0,n)
if(!x.length){x=(zr(100)<f)?1:0;zc(1,n,x,d,'')}else x=parseInt(x)
return x}
function zo(s,t){if(t)return s+"=yes,";return s+"=no,"}
function zpu(o,u,w,h,n,x,y){var f=1,c=",",s="width=",j=640,k=460;zin()
if(!n||!n.length){var t=new Date();n="About"+t.getMilliseconds()}
if(self.screen){j=screen.width;k=screen.height}
if(u.indexOf(':')<12)u=zh+u
if(!w)w=-80
if(!h)h=-95
if(w<0&&w>-101)w=parseInt(j*(-w/100))-10
if(h<0&&h>-101)h=parseInt(k*(-h/100))-40
s+=w+c+"height="+h+c
if(x)s+="screenX="+x+c+"screenY="+y+c
if(o&512)s+=zo("scrollbars",o&1)+zo("resizable",o&2)+zo("status",o&4)+zo("menubar",o&8)+zo("toolbar",o&16)+zo("location",o&32)+zo("directories",o&64)+zo("dependent",o&128)
if(o>1024)s+=zo("fullscreen",o&1024)+zo("alwaysRaised",o&2048)
if(o&256)f=2
zow(f,u,n,s);void(0)}
function oLd(c){var l,f,i,p='zfz=';c=0;zac(0)
if(zop&&zc(0,"ofp=")!='1')setTimeout("zpt()",(60+zrn*80)*1000)
else if(c){f=zc(0,p);i=parseInt(f);l=f.length
if(l==0||i<1)zc(1,p,1,22,'')
else if(i==1&&c/1000>zrn)zc(1,p,2,22,'')}}
function oUl(e){zpt()}
function zpt(){if(zop&&zc(0,"ofp=")!='1')zsprn(512,gs,1);zop=0;zc(1,'ofp=',1,32,'')}
function sf(u,i,v){if(!i)return un
return zh+'sprinks-clicks.'+uy+'/?bid='+i+'&ref='+gs+rp+rs+'&lnk='+u}
function zsb(){rp='_sbox';fi=0
wt('100%','',0,0,0);wd(0,0,1,0,8,2);w(f2+fa+f3+2+c1+'white>'+xa)
w(f7+um+'s'+f8+f9+qd+qd+qc)
e0=f7;e1=f8;e2='<p>';e3='</p>';e4=f2+fa+f3+2+q;e5=f2+fb+f3+2+c1+ac[4]+q;e6=f2+fb+f3+2+c1+'gray>';e7=f2+fb+f3+1+c1+'#0066CC>';e8=xa;e9=f9
var i=0,l=zs.length;if(l>6)i=6;while(i<l)zsae(0,i==6,zs[i++],zs[i++],zs[i++],zs[i++],zs[i++],zs[i++])
w('<p>'+f9+rf(1,2,6)+at[5]+f9+xb+xb)}
function zrp(s,v,n){var i=s.indexOf(v);if(i>=0)s=s.substring(0,i)+n+s.substring(i+v.length);return s}
function zar(t,a,b,p){if((t&1)&&zx.charAt(0)==a)zx=b+zx.substring(a.length)
if(t&2){var v='='+a,n='='+b,i,l;if(t&4){v=p+v;n=p+n}
while(zz.indexOf(v)>=0)zz=zrp(zz,v,n)}}
function rf(f,s,c){if(!f)return '';return f0+f1+af[f]+f3+(s+fi)+c1+ac[c]+q}
function zrf(i){return rf(o(i++),o(i++),o(i++))}
function zrc(x){x=o(x);return x?'<span'+qf+zcn+x+q:''}
function zsae(m,h,d,i,u,t,r,b){var l=(m&&d.length>m)?d.substring(0,m)+'...':d,h0=e0;h1=e1;if(!h)h0=h1=''
w(e2+e4+x2+sf(u,i)+x3+d+x4+f7+t+f8+'</a>'+e9+xb+e5+h0+l+h1+e9+xb)
if(e6.length)w(e6+r+e8+e9);if(e7.length)w(e7+'(Cost to Advertiser: '+b+')'+e9);w(e3)}
function o(i){return 1*z0.charAt(i)}
function zsa(op,g,h,r){rp='_u'+g+h;if(r&&r.length)rp=r;fi=0;zru+=rp
z0=op;if(!op.length){if(h==125)return;if(h==60||h==86)z0=zo4[zr(zo4.length)];if(h==86)fi=1;if(h==600){h=87;g=140;z0=zo6[zr(zo6.length)];return znsa('snv','ns0v','',180,0,rp)}}
var n=o(0),t=at[o(6)],c=0,i=(zr(o(1))+1)*6,l=zs.length,k=Math.floor((h-10)/n),s=1
wt(g,h,0,1,0,ac[o(2)])
if(t){wd(0,12,o(7),4);s+=12;w(zrf(3)+f7+t+f8+f9+qd)}
wd();wt(g,n-s,0,3,0,ac[o(10)])
e0=f7;e1=f8;e2='<p>';e3='</p>';e4=zrf(11);e5=zrf(14);e6=zrf(17);e7=zrf(20);e8=xa;if(h==87)e3=e3+xb;e9=f9
if(i+12>=l)i=6;if(i>=l)i=0
while(c++<n&&i<l){wd('',k,o(9));zsae(al[o(8)],0,zs[i++],zs[i++],zs[i++],zs[i++],zs[i++],zs[i++]);w(qd)}
w(qc+qd);t=at[o(23)]
if(t){wd(0,12,o(7),4);w(f7+zrf(3)+t+f9+f8+qd)};if(h==87)wi(g+2,1)
w(qc)}
function wi(g,h){w('<img'+qh+h+qi+g+' alt="">')}
function wt(g,h,b,p,s,c){w('<table border='+b+' cellpadding='+p+' cellspacing='+s);if(h)w(qh+h);if(g)w(qi+g);if(c)w(c2+c);w(q)}
function wd(g,h,a,v,c,s,x){w('<tr><td');if(g)w(qi+g);if(h)w(qh+h);if(a)w(' align='+aa[a]);if(v)w(' valign='+aa[v]);if(c)w(c2+ac[c]);if(s)w(' colspan='+s);if(x)w(qf+zcn+x);w(q)}
function znsa(p,s,op,g,h,r){wsss();zcn=s;rp=r;fi=0;op='31000800612234'
z0=op;if(!op.length){if(h==125)return;if(h==60||h==86)z0=zo4[zr(zo4.length)];if(h==86)fi=1;if(h==600){h=87;g=140;z0=zo6[zr(zo6.length)]}}
var n=o(0),c=0,i=(zr(o(1))+1)*6+(o(2)*1),l=zs.length,k=Math.floor((h-10)/n),s=1
wt('180',0,0,0,0,0)
e0=f7;e1=f8;e2='<p>';e3='</p>';e4=zrc(10);e5=zrc(11);e6=zrc(12);e7=zrc(13);e8=xb;e9=qg
if(i+12>=l)i=6;if(i>=l)i=0;zcn=p;wd('100%',0,0,0,0,0,' ');zcn=s
while(c++<n&&i<l){zsae(al[o(8)],0,zs[i++],zs[i++],zs[i++],zs[i++],zs[i++],zs[i++])}
w(qd+qc)}
function wsss(){w('<style>')
w('.ns0v1 {font: bold 13pt verdana,arial,helvetica; color: 999999; text-decoration: none}')
w('.ns0v2 a, .ns0v2 a:visited, .ns0v2 a:link {font-weight: bold; color: 000000}')
w('.ns0v3 {color: 999999}')
w('.ns0v4 {font: 8pt verdana,arial,helvetica; color: 0000ff}')
w('</style>')}

