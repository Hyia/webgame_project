<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<style type="text/css">
 #townTable{width:100%;height:100%;margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;}
 #tt1{width:8%;height:100%;}
 #tt2{width:92%;height:100%;}
 #townSide{width:100%;height:100%;border:1px solid #300;}
 #townSide td{border:1px solid #300;}
 #town { width:100%; height:100%; border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;
 background-color: #ffcd28;}
 #town td { border-collapse:collapse; margin: 0px; padding: 0px;border-spacing: 0px; border-bottom-style: none;}
 img{width: 100%; height: 100%}
 #my_popup{width: 70%;height: 70%; background: white;}
 #my_popup button{position:absolute;right: 0; bottom: 0;}
 #div1{height: 98%;width: 48%; left: 0; position: absolute; margin:5px;border:1px solid #00aaed;}
 #div2{height: 48%;width: 48%; right: 0; top:0; position: absolute;margin:5px;border:1px solid #0b0b0b;}
 #unitInfo td{border:1px solid #00aaed; }
 #div3{height: 48%;width: 48%; right: 0; bottom:0; position: absolute;margin:5px;border:1px solid #FFBB00;}
</style>


<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
<script src='http://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js'></script>
    <script>
    
    $(document).ready( function(e){
    	
        
    	var $imgS="";
    	var kind = ${kind};  	
    	var roomNumber= ${roomNumber};
    	var locationID =${locationID};
    	$('#my_popup').popup();
    	var k = 0;
    	var imgn=1;
    	 for(var i=0;i<5;i++){
    		if(i==0||i==4){
    			
    			$imgS="<a href='/town/"+locationID+"/"+11+"/towninfo'><img src='/images/img_rampart_"+imgn+".png' /></a>";
    			$('#town tr:eq('+i+')>td:eq(0)').append($imgS);
    			imgn+=2;
    			
    			$imgS="<a href='/town/"+locationID+"/"+11+"/towninfo'><img src='/images/img_rampart_"+imgn+".png' /></a>";
    			$('#town tr:eq('+i+')>td:eq(4)').append($imgS);
    			
    			if(imgn==3){
    				imgn=7;
    			}
    			
    			if(i==0){
    				$imgS="<a href='/town/"+locationID+"/"+10+"/towninfo'><img src='/images/img_rampart_2.png' /></a>";
        			$('#town tr:eq(0)>td:gt(0):lt(3)').append($imgS);
    			}else if(i==4){
    				$imgS="<a href='/town/"+locationID+"/"+10+"/towninfo'><img src='/images/img_rampart_8.png' /></a>";
        			$('#town tr:eq(4)>td:gt(0):lt(3)').append($imgS);
    			}
    			
    		}else if(i>0&&i<4){
    			
    			$imgS="<a href='/town/"+locationID+"/"+10+"/towninfo'><img src='/images/img_rampart_4.png' /></a>";
    			$('#town tr:eq('+i+')>td:eq(0)').append($imgS);
    			
    			$imgS="<a href='/town/"+locationID+"/"+10+"/towninfo'><img src='/images/img_rampart_6.png' /></a>";
    			$('#town tr:eq('+i+')>td:eq(4)').append($imgS);
    		}
    	}
    	
    	var kinds=0;
     	for(var k=1;k<4;k++){
 			for(var j=1;j<4;j++,kinds++){
 				if(kind[kinds]==null){
 					$imgS="<a href='/town/"+locationID+"/towninfo'><img src='/images/img_maelBuildTyle.png' /></a>";
 				}else{
 					$imgS="<img src='/images/img_mael"+kind[kinds]+".png' class='my_popup_open' value='"+kind[kinds]+"'/>";
 				}
 				$('#town tr:eq('+k+')>td:eq('+j+')').append($imgS);
 			}
 		}
     	
     	$('img').click(function () {
            var src=$(this).attr('src');
            $('#showimg').attr('src', src);
            
            var val=$(this).attr('value');
 			var $kindHTML="";
            
            switch(val){
	    	case "1":
	    		$('#div2').children().remove();
	    		break;
	    	case "2":
	    		$('#div2').children().remove();
	    		break;
	    	case "3":
	    		$('#div2').children().remove();
	    		break;
	    	case "4":
	    		$('#div2').children().remove();
	        	$kindHTML="<h2>군사기지</h2></br><p>하급, 중급, 상급 유닛을 생산하는 건물 입니다.</p></br>";
	        	$kindHTML+="<table id='unitInfo'><tr> <td>UnitName</td> <td>ATK</td> <td>SPD</td> 	<td>HP</td> <td>BuildTime</td> 	<td>Production</td><td>Quantity</td></tr><tr> <td>하급</td>	<td>5</td> 	 <td>5</td> 	<td>5</td> 	<td>00:00:10</td> 	<td>50</td><td><input type='number' id='unit1val'></td><td><input type='button' id='unit1' value='Make'/></td> </tr><tr> <td>중급</td> 	<td>10</td>  <td>10</td> 	<td>10</td> <td>00:00:25</td> 	<td>100</td><td><input type='number' id='unit2val'></td><td><input type='button' id='unit2' value='Make'/></td> </tr><tr> <td>상급</td> 	<td>20</td>  <td>20</td> 	<td>20</td> <td>00:01:00</td> 	<td>200</td><td><input type='number' id='unit3val'></td><td><input type='button' id='unit3' value='Make'/></td> </tr></table>";
	        	$('#div2').append($kindHTML);
	    		break;
	    	}
        });
 		
    });
	</script>

<div id='towndiv'>
<table id='townTable'>
	<tr>
		<td id='tt1'>
		<table id='townSide'>
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
		<tr><td></td></tr>
		</table>
		</td>
		<td id='tt2'>
		<table id='town'>
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
        </td>
        </tr>
</table>
</div>

<div id="my_popup">
	<div id="div1">
		<img id="showimg"src=''/>
    </div>
    <div id="div2">
    </div>
    <div id="div3">
    </div>

    <!-- Add an optional button to close the popup -->
    <button class="my_popup_close">Close</button>
</div>


