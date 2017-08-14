<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){

    	var $mapTable = "<table>";
    	var width = ${mapWidth};
    	var height = ${mapHeight};
    	var kind = ${kind};
    	var locationID = ${locations};

    	//var $mapTable = "<table>";

    	<% int counter = 0; %>
    	var k = 0;
    	for(var i=0; i<height; i++){
    		$mapTable += "<tr>";
    		for(var j=0; j<width; j++, k++){
    	    	
    	    	$mapTable += "<td>";
    	    	if(locationID[k] !=null){ 	    		
    	   	    	$mapTable += "<a href='/map/"+locationID[k]+"/info' >";
      	    	}
    	    	switch(kind[k]){
    	    	case -1:
    	        	$mapTable += "<img src='/images/img_invaild.png' />";
    	    		break;
    	    	case 1:
    	        	$mapTable += "<img src='/images/img_grass.png' />";
    	    		break;
    	    	case 2:
    	        	$mapTable += "<img src='/images/img_castle.png' />";
    	    		break;
    	    	case 3:
    	        	$mapTable += "<img src='/images/img_outresource.png' />";
    	    		break;
    	    		default:
    	            	$mapTable += "<img src='/images/img_invaild.png' />";
    	    	}
    			<% counter++; %>
    	    	$mapTable += "</a></td>";
    		}
    		$mapTable += "</tr>";
    	}

    	$mapTable += "</table>";


        $("#map").append($mapTable);
    	
    	
    	
    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>
<div id='map'>
</div>


</body>
</html>
