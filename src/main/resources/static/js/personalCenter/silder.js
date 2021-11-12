var obj=null;
var As=document.getElementById('nav').getElementsByTagName('a');
obj = As[1];
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
obj=As[i];}
obj.id='nav_current'
