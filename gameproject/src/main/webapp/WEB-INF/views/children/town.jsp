<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<style type="text/css">
 table { border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;
 background-color: #ffcd28;}
 td { border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none; height:32px; width:32px;}
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
    	for(var i=0;i<5;i++,k++){
    		
    		
    		if(i==0||i==4){
    			$imgS="<a><img src='/images/img_rampart_"+imgn+".png' /></a>";
    			$('tr:eq(i)>td:eq(0)').append($imgS);
    			imgn+=2;
    			$imgS="<a><img src='/images/img_rampart_"+imgn+".png' /></a>";
    			$('tr:eq(i)>td:eq(4)').append($imgS);
    			if(imgn==3){
    				imgn=7;
    			}
    		}else if(i>0&&i<4){
    			$imgS="<a><img src='/images/img_maelBuildTyle.png' /></a>";
    			$('tr:eq(i)>td:gt(1):lt(4)').append($imgS);
    		}
    		
			if(i==)
    		
    	}
    	$('tr:eq(0)>td:eq(0)').append($mapTable);
    	
    	
    	
    });
	</script>

</head>
<body>
<input type="text" id="maplocation" hidden='true' value='null'>

<div id='town'>
		<table>
            <tr>
                <td>#1</td> <td>#2</td> <td>#3</td> <td>#4</td> <td>#5</td>
            </tr>
            <tr>
                <td>#6</td> <td>#7</td> <td>#8</td> <td>#9</td> <td>#10</td>
            </tr>
            <tr>
                <td>#11</td> <td>#12</td> <td>#13</td> <td>#14</td> <td>#15</td>
            </tr>
            <tr>
                <td>#16</td> <td>#17</td> <td>#18</td> <td>#19</td> <td>#20</td>
            </tr>
            <tr>
                <td>#21</td> <td>#22</td> <td>#23</td> <td>#24</td> <td>#25</td>
            </tr>
        </table>
</div>


</body>
</html>
