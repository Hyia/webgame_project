<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
        $('login').click( function(e){
            $('#loginform').attr('action', '/phone/writeone1').submit();              
        });
    });
	</script>
</head>

<body>
<div id="wrapper">
	<div id="contents" >
       contents
    </div>
    
    <div id="sidebar">
    	<div id="login">
        <form id="loginform"method="post" action="">
            <p><input type="text" name="login" value="" placeholder="UserID"></p>
            <p><input type="password" name="password" value="" placeholder="UserPW"></p>
            <p class="submit">
            	<input type="submit" name="login" value="Login">
            </p>
            
        </form>
        </div>
    </div>
</div>
</body>
</html>