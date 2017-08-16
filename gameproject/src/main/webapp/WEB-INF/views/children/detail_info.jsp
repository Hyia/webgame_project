<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){

    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>
<div id='info_area'>
	<div id='portrait'></div>
	<div id='explanation'></div>
	<div id='command_area'>
		<input type="button" id="btn_a" />
		<input type="button" id="btn_b" />
		<input type="button" id="btn_c" />
	</div>
</div>


</body>
</html>
