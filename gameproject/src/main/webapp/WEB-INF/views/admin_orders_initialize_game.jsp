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
<title>admin Page</title>

	<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){
    	$("input[type='submit']").click(function(event) {
    		var msg = $(this).attr("value");
    		alert(msg + " clicked!");
    		$("#value").attr("value",msg);
    	})
    	
    	if(${status!= null}){
    		$("#textarea").html("status: ${status}");
    	}
    });
	</script>
</head>

<body>
<form action="/admin_orders_initialize_game" method="post">
	<input type="text" id="value" hidden="true" name="value" value="startServcer">
	<input type="submit" id="startServer" value="startServer"/>
	<input type="submit" id="stopServer" value="stopServer"/>
	<input type="submit" id="resultMapr" value="makeMap"/>
</form>

<p id="textarea">status: stop</p>

</body>
</html>