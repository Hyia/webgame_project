<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<style type="text/css">
 #worldmaptable,#worldmapoutside {width:100%;height:100%; border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none; background-color: #ffcd28;}
 td { border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;}
 #map img{width: 100%;height:100%;}
 #worldmap{width: 100%;height:100%;}
 #worldmapoutside{width: 100%;height:100%;}
</style>
<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){

    	var width = ${mapWidth};
    	var height = ${mapHeight};
    	var kind = ${kind};
    	var locationID = ${locations};

    	var $mapTable = "<table id='worldmaptable'>";

    	var k = 0;
    	for(var i=0; i<height; i++){
    		$mapTable += "<tr>";
    		for(var j=0; j<width; j++, k++){
    	    	
    	    	$mapTable += "<td>";
    	    	if(locationID[k] !=null){ 	    		
    	   	    	$mapTable += "<a href='/map/"+locationID[k]+"/info' name='info'>";
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
    	    	$mapTable += "</a></td>";
    		}
    		$mapTable += "</tr>";
    	}

    	$mapTable += "</table>";


        $("#map").append($mapTable);
    	var udlr = "${udlr}"; 
    	if(udlr.includes("uua")){
    		$("#img_up").attr("src","/images/img_move_arrow_up_unclickable.png");
    	}else{
    		$("#img_up").attr("src","/images/img_move_arrow_up.png").click(function(e){
    			$(this).parents("#maincontent").load("/map/${locations.get(mapWidth/2)}");
    		});
    	}
    	if(udlr.includes("dua")){
    		$("#img_down").attr("src","/images/img_move_arrow_down_unclickable.png");
    	}else{
    		$("#img_down").attr("src","/images/img_move_arrow_down.png").click(function(e){
    			$(this).parents("#maincontent").load("/map/${locations.get( mapWidth * mapHeight - (mapWidth/2) )}");
    		});
    	}
    	if(udlr.includes("lua")){
    		$("#img_left").attr("src","/images/img_move_arrow_left_unclickable.png");
    	}else{
    		$("#img_left").attr("src","/images/img_move_arrow_left.png").click(function(e){
    			$(this).parents("#maincontent").load("/map/${locations.get(((mapHeight/2)*mapWidth) - Math.floor(mapWidth/2))}");
    		});
    	}
    	if(udlr.includes("rua")){
    		$("#img_right").attr("src","/images/img_move_arrow_right_unclickable.png");
    	}else{
    		$("#img_right").attr("src","/images/img_move_arrow_right.png").click(function(e){
    			$(this).parents("#maincontent").load("/map/${locations.get( (mapHeight/2)*mapWidth + Math.floor(mapWidth/2)) }");
    		});
    	}
    	
    	$('a[name="info"]').click(function(e){
    		$(this).parents("#maincontent").load($(this).attr("href"));
    		return false;
    	});
    	
    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>
<div id="worldmap">
<table id="worldmapoutside">
	<tr><td align="center" colspan="${mapWidth+2}">	<img id="img_up"/></td></tr>
	<tr id="tr"><td><img id="img_left"/></td><td><div id='map'></div></td><td><img id="img_right"/></td></tr>
	<tr><td align="center" colspan="${mapWidth+2}"><img id="img_down"/></td></tr>
</table>
</div>


</body>
</html>
