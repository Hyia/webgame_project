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
 #town img,#my_popup img{width: 100%; height: 100%;}
 #townSide img{width:100%;height: 100%;}
 #my_popup{width: 70%;height: 70%; background: white;}
 #my_popup button{position:absolute;right: 0; bottom: 0;}
 #div1{height: 98%;width: 48%; left: 0; position: absolute; margin:5px;border:1px solid #00aaed;}
 #div2{height: 48%;width: 48%; right: 0; top:0; position: absolute;margin:5px;border:1px solid #0b0b0b;overflow:auto;}
 #div2 input{height: 100%;width:100%;}
 #div2 table{width:100%;height:40%;left: 0; bottom: 0;border:1px solid #0b0b0b;}
 #div2 imgtd,#div2 btn{width: 20%;height:20%;}
 #div2 explan{width: 70%;height:20%;}
 #buildlist td{border:1px solid #0b0b0b;}
 #unitInfo td{border:1px solid #00aaed; }
 #div3{height: 48%;width: 48%; right: 0; bottom:0; position: absolute;margin:5px;border:1px solid #FFBB00;}
 #div3 table{position:absolute;left: 0; top: 0;}
 #div3 td,#div3 table{border:1px solid #300;}
</style>


<script src='http://code.jquery.com/jquery-3.1.0.js'></script>
<script src='http://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js'></script>
    <script>
    
    $(document).ready( function(e){
    	
        
    	var $imgS="";
    	var kind = ${kind};  	
    	var roomNumber= ${roomNumber};
    	var castleLocationID= ${castleLocationID};
    	var locationID= ${locationID};
    	//var unitEndTimeList=${unitlist};
    	var buildingsKind=${buildingsKind};
    	
    	
    	var explanation=["회관입니다.","성벽입니다.<br>수성시 방어력이 증가합니다.","성벽입니다.<br>수성시 공격력이 증가합니다.","군사기지 입니다.<br>하급, 중급, 상급 유닛을 생산하는 건물 입니다."];
    	
    	$('#my_popup').popup();
    	
    	//Castle OutSide	
    	var k = 0;
    	var imgn=1;
    	 for(var i=0;i<5;i++){
    		if(i==0||i==4){
    			
    			$imgS="<img src='/images/img_rampart_"+imgn+".png' class='my_popup_open'  value='11'/></a>";
    			$('#town tr:eq('+i+')>td:eq(0)').append($imgS);
    			imgn+=2;
    			
    			$imgS="<img src='/images/img_rampart_"+imgn+".png' class='my_popup_open'  value='11' /></a>";
    			$('#town tr:eq('+i+')>td:eq(4)').append($imgS);
    			
    			if(imgn==3){
    				imgn=7;
    			}
    			
    			if(i==0){
    				$imgS="<img src='/images/img_rampart_2.png' class='my_popup_open'  value='10'/></a>";
        			$('#town tr:eq(0)>td:gt(0):lt(3)').append($imgS);
    			}else if(i==4){
    				$imgS="<img src='/images/img_rampart_8.png' class='my_popup_open'  value='10'/></a>";
        			$('#town tr:eq(4)>td:gt(0):lt(3)').append($imgS);
    			}
    			
    		}else if(i>0&&i<4){
    			
    			$imgS="<img src='/images/img_rampart_4.png'class='my_popup_open'value='10'/></a>";
    			$('#town tr:eq('+i+')>td:eq(0)').append($imgS);
    			
    			$imgS="<img src='/images/img_rampart_6.png' class='my_popup_open' value='10'/></a>";
    			$('#town tr:eq('+i+')>td:eq(4)').append($imgS);
    		}
    	}
    	//Castle in ground
    	var kinds=0;
     	for(var k=1;k<4;k++){
 			for(var j=1;j<4;j++,kinds++){
 				if(kind[kinds]==null){
 					$imgS="<img src='/images/img_maelBuildTyle.png' name='"+j+"' class='my_popup_open'  value='0' /></a>";
 				}else{
 					$imgS="<img src='/images/img_mael"+kind[kinds]+".png' name='"+j+"' class='my_popup_open' value='"+kind[kinds]+"'/>";
 				}
 				$('#town tr:eq('+k+')>td:eq('+j+')').append($imgS);
 			}
 		}
     	
     	//left side Castle Link & Img
     	for(var c=0;castleLocationID[c]!=null;c++){
 		$imgS="<a name='castles' href='/town/"+castleLocationID[c]+"'><img src='/images/img_castle.png' /></a>"
 		$('#townSide tr:eq('+c+')>td:eq(0)').append($imgS);
	 	}
	 	
	 	
	 	$('a[name="castles"]').click(function(e){
	 		$(this).parents("#maincontent").load($(this).attr("href"));
			return false;
		});
	 	
	 	var roomNumber;
	 	//Castle In RoomNumber Img Click Event
	 	$('#town img').click(function () {
	        var src=$(this).attr('src');
	        $('#showimg').attr('src', src);
	        
	        var val=$(this).attr('value');
	        roomNumber=$(this).attr('name');
	        
				var $kindHTML="";
	        switch(val){
	        case "0":
	        	$('#div2').children().remove();
	        	$('#div3').remove();
	        	$('#div2').css("height", "90%");
	        	$kindHTML="<table id='buildlist'></table>";
	        	$('#div2').append($kindHTML);
	        	
	        	for(var buildings=0;buildings<buildingsKind.length;buildings++){
	        		if(buildingsKind[buildings]!=2||buildingsKind[buildings]!=3){
	        			$('#buildlist').append("<tr><td name='imgtd'></td><td name='explan'><p>"+explanation[buildings]+"</p></td><td name='btn'><input name='makeBuilding"+(buildings+1)+"' type='button' value='Make'/></td></tr>");
	        			$('#buildlist tr:eq('+buildings+')>td[name="imgtd"]').append("<img src='/images/img_mael"+(buildings+1)+".png'/>");
	        		}
	        		
	        	}
	        	
	        	break;
	    	case "1":
	    		$('#div2').children().remove();
	    		$('#div3').children().remove();
	    		$kindHTML="<p><h2>회관</h2><br>"+explanation[0]+"</p>";
	        	$('#div2').append($kindHTML);
	        	
	        	$kindHTML="<h3>컨텐츠 준비중</h3>";
	        	$('#div3').append($kindHTML);
	    		break;
	    	case "2":
	    		$('#div2').children().remove();
	    		$('#div3').children().remove();
	    		break;
	    	case "3":
	    		$('#div2').children().remove();
	    		$('#div3').children().remove();
	    		break;
	    	case "4":
	    		$('#div2').children().remove();
	    		$('#div3').children().remove();
	    		alert("들왓다.");
	        	$kindHTML="<p><h2>군사기지</h2><br>"+explanation[3]+"</p>";
	        	$kindHTML+="<form action='http://localhost:8080/town/"+locationID+"/insertunit' method='post'><table id='unitInfo'><tr> <td>UnitName</td> <td>ATK</td> <td>SPD</td> 	<td>HP</td> <td>BuildTime</td> 	<td>Production</td><td>Quantity</td></tr><tr> <td>하급</td>	<td>5</td> 	 <td>5</td> 	<td>5</td> 	<td>00:00:10</td> 	<td>50</td><td><input type='number' name='unit1'></td><td><input type='submit' id='unit1btn' value='Make' /></td> </tr><tr> <td>중급</td> 	<td>10</td>  <td>10</td> 	<td>10</td> <td>00:00:25</td> 	<td>100</td><td><input type='number' name='unit2'></td><td><input type='submit' id='unit2btn' value='Make'/></td> </tr><tr> <td>상급</td> 	<td>20</td>  <td>20</td> 	<td>20</td> <td>00:01:00</td> 	<td>200</td><td><input type='number' name='unit3'></td><td><input type='submit' id='unit3btn' value='Make'/></td> </tr></table>";
	        	$('#div2').append($kindHTML);
	        	
	        	
	        	
				//if(unitEndTimeList.size()!=0&&unitlist.size()!=null){
		        //	for(var a=0;a<unitEndTimeList.size();a++){
		        //		$kindHTML="<table><tr><td>"+unitEndTimeList[a]+"</td><td>UnitName</td><td>Amount</td></tr></table>"
		    	//        $('#div3').append($kindHTML);		        			
		        //	}
				//}
				
	    		break;
	    	}
	        
	        $('#div2'). on ( 'click' ,'input[value="Make"]', function(event){
	        	var btn=$(this).attr('name');
	        	alert("건물만들기 들어옴.");
	        	switch(btn){
	        		case "makeBuilding1":
	        			alert("Case 들어옴");
	        			$.post("http://localhost:8080/town/"+locationID+"/insertbuilding",{roomNumber:roomNumber,kind:1});
	        			alert("Case 끝남");
	        			break;
	        	
	        	};
	        });
	 
	 });
 	
 	
 	$('#div2').on( 'click' ,'input[type="submit"]', function(event){
		switch($(this).attr('id')){
		case "unit1btn":
			alert("클릭이벤트 들어왔다.");
			
                	var $insertUnit="";
					$insertUnit ="<tr><td>00:10:00</td><td>하급</td><td>5</td></tr>";
					$('#div3 table:last').append($insertUnit);
					alert("테이블 갯수구하기: "+$('#div table').length);
			break;
		case "unit1btn":
			break;
		case "unit1btn":
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


