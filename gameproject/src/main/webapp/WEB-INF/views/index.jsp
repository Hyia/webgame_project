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
     <script>
      $(document).ready( function(e){
      	if((${isLogedin == false})){
      		alert("!?!!?");
      		return;
      	}
         	setInterval(function(){
         		if(${isLogedin == true}){
       		   $('#sidebar').load('/children/loginAfter');
             	}
     		}, 1000) /* time in milliseconds (ie 1 seconds)*/    	
     
     	
     		$('#maincontent').load('/town/${param.locationID}');
         	$('#tabMenu1 li').eq(1).addClass('select');
         	
         	$('#tabMenu1').click(function(e) {
         		swicth(this){
         			case:
         		}
         		$('#tabMenu1').children().removeClass('select');
         	})
                
      
      });
  	</script>
</head>

<body>
<div id="wrapper">
	<div id="contents" >
       <h1 align="center">Web Game Project</h1>
       <div id="menubar" align="center">
       		<ul class="tab-menu" id="tabMenu1">
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
		<!-- login after -->
	    	<c:if test="${isLogedin != null && isLogedin == true}">
	    		<%@ include file="children/loginAfter.jsp" %>
	    	</c:if>
	   </div>
    </div>
    
    <div id="maincontent" >
    </div>
</div>
</body>
</html>