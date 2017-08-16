<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){

    	var $mapTable = "<table>";
    	var width = ${mapWidth};
    	var height = ${mapHeight};
    	var kind = ${kind};
    	var roomNumber= ${roomNumber};
    	var locationID = ${locations};

    	

    	var k = 0;
    	for(var i=0; i<height; i++){
    		$mapTable += "<tr>";
    		for(var j=0; j<width; j++, k++){
    	    	
    	    	$mapTable += "<td>";
    	    		    		
    	   	    	$mapTable += "<a href='/map/"+locationID[k]+"/cityinfo' >";
      	    	
    	        	$mapTable += "<img src='/images/img_maelBuildTyle.png' />";
    	    		
    	    	$mapTable += "</a></td>";
    		}
    		$mapTable += "</tr>";
    	}

    	$mapTable += "</table>";


        $("#city").append($mapTable);
    	
    	
    	
    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>


<div id='city'>
</div>


</body>
</html>
