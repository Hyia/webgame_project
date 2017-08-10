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
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Page</title>

</head>

<body>
<div id="wrapper">
	<div id="contents" >
       <h1 align="center">Web Game Project</h1>
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
		            
		            <c:if test="${isLogedin != null && isLogedin == false}">
		            	<p> ${errorMsg} </p>
		            </c:if>
		        </form>
		   </c:if>
		<!-- login after -->
	    	<c:if test="${isLogedin != null && isLogedin == true}">
	    		<%@ include file="children/loginAfter.jsp" %>
	    	</c:if>
	   </div>
    </div>
    
    <div id="maincontent" >
       Main
    </div>
</div>
</body>
</html>