<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<style>
body{
	width:auto;
	height:auto;
}
#wrapper{
	border:1px solid #FFBB00;
	width:1000px;
	height:auto;
	padding:10px;
	position:absolute;
	top:10px;
	margin-left:10%;
	margin-right:10%;
	overflow:hidden;
}
#maincontent{
	border:2px solid #300;
	width:700px;
	height:450px;
	float:left;
	margin:10px;
}
#contents{
	border:1px solid #487be1;
	width:700px;
	float:left;
	padding:10px;
}
#sidebar{
	border: 1px solid #487be1;
	width: 200px;
	height: 550px;
	float: right;
	padding: 10px;
	margin-left: 3%;
}
#menubar {align:center;}
.tab-menu { list-style: block; height: 40px;}
        .tab-menu li {
            width: 99px;
            height: 40px;
            background-position-y: 0;
            text-indent: -1000px;
            overflow: hidden;
            display: inline-block;
            float: center;
        }
 .tab-menu li:hover     { background-position-y: -40px; }
 .tab-menu li.select    { background-position-y: -80px; height: 80px; }
 .tab-menu li.menuitem1 { background-image: url(images/tabbtn_town.png); }
 .tab-menu li.menuitem2 { background-image: url(images/tabbtn_map.png); }
 .tab-menu li.menuitem3 { background-image: url(images/tabbtn_hero.png); }
 .tab-menu li.menuitem4 { background-image: url(images/tabbtn_log.png); }
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Page</title>


<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
<script src='http://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js'></script>
     <script>
      $(document).ready( function(e){
    	  
   		if(${isLogedin == true}){
     		$('#menubar').show();
     		$('#maincontent').load('/town/${locationID}');
         	setInterval(function(){
       		   $('#sidebar').load('/children/loginAfter');
     		}, 1000); /* time in milliseconds (ie 1 seconds)*/    	
             	}
     
     		
     		
             $('#tabMenu > li').click(function (event) {
     			 var index = $(this).index();
     			 var value = $(this).html();
     			 if(value == "town"){
     				 $('#maincontent').load('/town/${locationID}');
     			 }
     			 if(value == "map"){
     	     		$('#maincontent').load('/map/${locationID}');
     			 }
     			 if(value == "hero"){
      	     		$('#maincontent').load('/hero/herolist');
     			 }
     			 if(value == "battleLog"){
      	     		$('#maincontent').load('/combat/loglist');
     			 }
             });
             
             $(window).scroll(function (e) {
                 var y = $(document).scrollTop();
                 var x = $(document).scrollLeft();
                 var z = $(document).height();
                 //$('#log').text("x="+x+", y="+y);


                 if ($(window).scrollTop() == $(document).height() - $(window).height()) {
                     $("#treeData").append(function (e) {
                         
                     });

                 }


             })
      });
      
         	
  	</script>
</head>

<body>
<div id="wrapper">
	<div id="contents" >
       <h1 align="center">Web Game Project</h1>
       <div id="menubar" align="center" hidden="true">
       		<ul class="tab-menu" id="tabMenu">
        		<li class="menuitem1">town</li>
        		<li class="menuitem2">map</li>
      		  	<li class="menuitem3">hero</li>
      		  	<li class="menuitem4">battleLog</li>
   			</ul>
       </div>
    </div>
    
  <div id="sidebar">
	    <div id="loginArea">
	    <!-- login wait -->
	    	<c:if test="${isLogedin == null ||  isLogedin == false}">
		        <form id="loginform"method="post" action="/loginreq">
		            <p><input type="text" name="id" value="" placeholder="UserID"></p>
		            <p><input type="password" name="pwd" value="" placeholder="UserPW"></p>
		            
		            <p class="submit">
		            	<input type="submit" name="login" value="Login">
		            </p>
		            
		            
		        </form>
		   </c:if>
	   </div>
    </div>
    
    
    <div id="maincontent" >
    대문
    </div>
</div>
</body>
</html>