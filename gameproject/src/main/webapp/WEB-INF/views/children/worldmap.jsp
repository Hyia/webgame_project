<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<script src='/js/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){
    	
    	var width = ${mapWidth};
    	var height = ${mapHeight};
    	var kind = ${kindList};
    	
    	var $mapTable = "<table>";
    	
    	var k = 0;
    	for(var i=0; i<height; i++){
        	$mapTable += "<tr>";
    		for(var j=0; j<width; j++, k++){
            	$mapTable += "<td><a hrf='/maps/ >'";
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
    			
            	$mapTable += "</a></td>";
    		}
        	$mapTable += "</tr>";
    	}

    	$mapTable += "</table>";
    	
    	
    	$("#map").html($mapTable);
    	
    	
    	
    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>
<div id='map'></div>


</body>
</html>
