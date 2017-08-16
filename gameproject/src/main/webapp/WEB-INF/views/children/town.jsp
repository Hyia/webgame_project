<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<style type="text/css">
 table { border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;
 background-color: #ffcd28;}
 td { border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;}
</style>


<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
    <script>
    $(document).ready( function(e){

    	var $imgS="";
    	var width = ${mapWidth};
    	var height = ${mapHeight};
    	var kind = ${kind};
    	var roomNumber= ${roomNumber};
    	var locationID = ${locationID};
    	
    	//"<a href='/town/"+locationID[k]+"/towninfo' >";
    	//$mapTable = "<a><img src='/images/img_rampart_1.png' /></a>";
    	var k = 0;
    	var imgn=1;
    	for(var i=0;i<5;i++){
    		if(i==0||i==4){
    			
    			$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_rampart_"+imgn+".png' /></a>";
    			$('tr:eq('+i+')>td:eq(0)').append($imgS);
    			imgn+=2;
    			
    			$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_rampart_"+imgn+".png' /></a>";
    			$('tr:eq('+i+')>td:eq(4)').append($imgS);
    			
    			if(imgn==3){
    				imgn=7;
    			}
    			
    			if(i==0){
    				$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_rampart_2.png' /></a>";
        			$('tr:eq(0)>td:gt(0):lt(3)').append($imgS);
    			}else if(i==4){
    				$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_rampart_8.png' /></a>";
        			$('tr:eq(4)>td:gt(0):lt(3)').append($imgS);
    			}
    			
    		}else if(i>0&&i<4){
    			$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_maelBuildTyle.png' /></a>";
    			$('tr:eq('+i+')>td:gt(0):lt(3)').append($imgS);
    			
    			$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_rampart_4.png' /></a>";
    			$('tr:eq('+i+')>td:eq(0)').append($imgS);
    			
    			$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_rampart_6.png' /></a>";
    			$('tr:eq('+i+')>td:eq(4)').append($imgS);
    		}
    	
    		
    	}
    	//$('tr:eq(0)>td:eq(0)').append($mapTable);
    	
    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>

<div id='town'>
		<table>
            <tr>
                <td></td> <td></td> <td></td> <td></td> <td></td>
            </tr>
            <tr>
                <td></td> <td></td> <td></td> <td></td> <td></td>
            </tr>
            <tr>
                <td></td> <td></td> <td></td> <td></td> <td></td>
            </tr>
            <tr>
                <td></td> <td></td> <td></td> <td></td> <td></td>
            </tr>
            <tr>
                <td></td> <td></td> <td></td> <td></td> <td></td>
            </tr>
        </table>
</div>


</body>
</html>
