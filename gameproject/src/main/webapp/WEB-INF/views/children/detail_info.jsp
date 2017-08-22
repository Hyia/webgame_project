<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready(function(e){
    	${myjqueryscript}
    	$('#btn_a').click(function(e){
    		${btna}
    	});
    	$('#btn_b').click(function(e){
    		${btnb}
    	});
    	$('#btn_c').click(function(e){
    		${btnc}
    	});
    	
    })
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>
<div id='info_area'>
	<div id='portrait'>${pic}</div>
	<div id='explanation'>${expl}</div>
	<div id='command_area'>
		<input type="button" id="btn_a" value="${btnaex}"/>
		<input type="button" id="btn_b" value="${btnbex}"/>
		<input type="button" id="btn_c" value="${btncex}"/>
	</div>
</div>


</body>
</html>
