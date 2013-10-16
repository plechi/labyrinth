 <!--javascript functions-->
function switchlayer(Layer_Name)
{
  var GECKO = document.getElementById? 1:0 ;
  var NS = document.layers? 1:0 ;
  var IE = document.all? 1:0 ;

  if (GECKO)
       {document.getElementById(Layer_Name).style.display=
           (document.getElementById(Layer_Name).style.display=='block') ? 'none' : 'block';}
  else if (NS)
       {document.layers[Layer_Name].display=(document.layers[Layer_Name].display==
           'block') ? 'none' : 'block';}
  else if (IE)
       {document.all[Layer_Name].style.display=(document.all[Layer_Name].style.display==
           'block') ? 'none' : 'block';}
}
function hidelayer(Layer_Name)
{
  var GECKO = document.getElementById? 1:0 ;
  var NS = document.layers? 1:0 ;
  var IE = document.all? 1:0 ;

  if (GECKO)
       {document.getElementById(Layer_Name).style.display='none';}
  else if (NS)
       {document.layers[Layer_Name].display='none';}
  else if (IE)
       {document.all[Layer_Name].style.display='none';}
}