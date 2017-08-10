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
	height:500px;
	padding:10px;
	position:absolute;
	top:10px;
	margin-left:10%;
	margin-right:10%;
	overflow:hidden;
}
#contents{
	border:1px solid #487be1;
	width:700px;
	float:left;
	padding:10px;
}
#sidebar{
	border:1px solid #487be1;
	width:200px;
	height:400px;
	float:right;
	padding:10px;	
	margin-left:3%;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login Page</title>

	<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){
    	if((${isLogedin == false})){
    		alert("!?!!?");
    	}
    });
	</script>
</head>

<body>
<div id="wrapper">
	<div id="contents" >
       contents
    </div>
    
    <div id="sidebar">
    	<!-- 로그인을 안했을 때의 div -->
	    <c:if test="${isLogedin == null ||  isLogedin == false}">
	    	<div id="login">
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
	        </div>
	    </c:if>
	    
	    <!-- 로그인을 했을때의 div -->
	    <c:if test="${isLogedin != null && isLogedin == true}">
	    	<%@ include file="children/loginAfter.jsp" %>
	    </c:if>
        
    </div>
</div>
</body>
</html>