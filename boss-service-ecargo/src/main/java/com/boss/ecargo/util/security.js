function enceypt(e,c){
e=encodeURI(e);
c=encodeURI(c);
for(var a="",b="",d=0; 4>d;d++)
	b+=Math.floor(10*Math.random());
for(var n=b,d=0; d<b.length; d++)
	a+=b.charCodeAt(d).toString();
for(var d=Math.floor(a.length/5),k=Number(a.charAt(d)+a.charAt(2*d)+a.charAt(3*d)),b=Math.ceil(b.length/2),f=Math.pow(2,16)-1,g=Math.round(1E4*Math.random())%1E3,a=a+g;8<a.length;)
	a=(parseInt(a.substring(0,4))+parseInt(a.substring(4,8))).toString();
for(var a=(k*a+b)%f,h,l="",m="",p=a,d=0; d<e.length;d++)
	h=parseInt(e.charCodeAt(d)^Math.floor(a/f*20)),l=16>h?l+("0"+h.toString(16)):l+h.toString(16),a=(k*a+b)%f;
for(d=0; d<c.length; d++)
	e=parseInt(c.charCodeAt(d)^Math.floor(p/f*20)),m=16>e?m+("0"+e.toString(16)):m+e.toString(16),p=(k*p+b)%f;
for(g=g.toString(16); 8>g.length; )
	g="0"+g;
l+=g;
m+=g;
return l+"-"+m+"-"+n
}

function enceyptStr(e){e=encodeURI(e);
for(var c="",a="",b=0;
4>b;
b++)a+=Math.floor(10*Math.random());
for(var d=a,b=0;
b<a.length;
b++)c+=a.charCodeAt(b).toString();
for(var b=Math.floor(c.length/5),n=Number(c.charAt(b)+c.charAt(2*b)+c.charAt(3*b)),a=Math.ceil(a.length/2),k=Math.pow(2,16)-1,f=Math.round(1E4*Math.random())%1E3,c=c+f;
8<c.length;
)c=(parseInt(c.substring(0,4))+parseInt(c.substring(4,8))).toString();
for(var c=(n*c+a)%k,g,h="",b=0;
b<e.length;
b++)g=parseInt(e.charCodeAt(b)^Math.floor(c/k*20)),
    h=16>g?h+("0"+g.toString(16)):h+g.toString(16),c=(n*c+a)%k;
for(f=f.toString(16);
8>f.length;
)f="0"+f;
return[h+f,d]};
