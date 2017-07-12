<?php 
/* --------------------------------------------------------------------------------------
                                    MAP
   Credits         : phpSGEx by Aldrigo Raffaele
   Last modified by: Fhizban 09.06.2013
   Comments        : No changes
-------------------------------------------------------------------------------------- */

$x = $y = 0;
//recupero planet
//isset()은 모든 변수가 설정되었을 경우에만 TRUE를 반환합니다. 평가는 왼쪽에서 오른쪽으로 이루어지며, 설정되지 않은 변수를 만나면 바로 중단합니다.
// isset()이 설정되어있는가 확인후 아래 메서드 실행.
if( isset($_GET['gal']) ){
	//페이지에서 gal,sys 주소창에 넘어온 값을 get 사용.
	$x=(int)$_GET['gal'];
	$y=(int)$_GET['sys'];

	//x가 0보다 작거나 $config 세팅값 max_x값보다 크면 x=0으로
	//y가 0보다 작거나 $config 세팅값 max_y값보다 크면 y=0으로
    if( $x < 0 || $x > $config["Map_max_x"] ) $x=0;
    if( $y < 0 || $y > $config["Map_max_y"] ) $y=0;
}
	
// tempalte \\
//이부분이 맵의 상하좌우 움직이는 몸체 부분인듯함.
$body="<form action='' method='get'><input type='hidden' name='pg' value='map' /><h2 class='news-title'>World Map</h2><div class='news-body'>  <table width='85%' border='0' cellspacing='0' cellpadding='10'> <tr> <td width='42'>x</td> <td width='40'>System</td> <td width='24'>&nbsp;</td> <td width='111'>cerca utente</td> </tr> <tr> <td> <a href='?pg=map&gal=".($x-1)."&sys=$y'><input type='button' value='<-' /></a><input type='text' style='text-align:center;' name='gal' id='gal' value='$x' size='3'><a href='?pg=map&gal=".($x+1)."&sys=$y'><input type='button' value='->' /></a> </td> <td> <a href='?pg=map&gal=$x&sys=".($y-1)."'><input type='button' value='<-' /></a><input type='text' style='text-align:center;' name='sys' id='sys' value='$y' size='3'><a href='?pg=map&gal=$x&sys=".($y+1)."'><input type='button' value='->' /></a> </td> </tr> <tr><td colspan='2'><div align='center'><input type='submit' value='Visualize'></div></td></form> <td>&nbsp;</td> <form method='get' action=''> <input type='hidden' name='pg' value='profile'> <td><input type='text' name='snusr' size='11' hint='NickName'> <input type='submit' value=' Search '></td></form> </tr> </table> <p>&nbsp;</p> <table width='600' border='1' cellspacing='0' cellpadding='5'> <tr> <td width='61'><div align='center'>Postion</div></td> <td width='62'>Planet</td> <td width='98'>Name</td> <td width='99'>Player</td> <td width='88'>Ally</td> <td width='93'>Actions</td> </tr>";

//show citys
for( $i=1; $i<= $config['Map_max_z']; $i++ ) {
	//TB_PREFIX는 TABLE_PREFIX 의약자 테이블인거 같음.
	//보안상 이유로 사용하는거 같음.
	//$qris는 성의 X,Y,Z 값을 가져오는듯함.
	$qris= $DB->query("SELECT * FROM ".TB_PREFIX."city WHERE x= $x AND y= $y AND z= $i");
	
	
	if( $qris->num_rows ==1 ){ //num_rows는 집합의 행 수를 반환
		$riga= $qris->fetch_array(); //fetch_array()는 한 번에 하나의 데이터로우를 테이블에서 얻어내서 칼럼명을 배열의 인덱스로 하여 각각 $riga 배열에 저장
		$mcuid= $riga['owner']; //owner(아마도 유저말하는듯함)
		$auin= $DB->query("SELECT username, ally_id, rank FROM ".TB_PREFIX."users WHERE id= $mcuid")->fetch_array();
		$cun= $auin['username'];
		$aacu= $auin['ally_id']; //ally_id = nickname????

        if( $aacu != null ) {
            $qan = $DB->query("SELECT name FROM ".TB_PREFIX."ally WHERE id= $aacu");
			$a= $qan->fetch_array();
            $uan = "<a href='?pg=ally&showally=$aacu'>{$a['name']}</a>"; //주소창에 넘겨줄려는듯???
        } else $uan="None";
		
		$ra="";
		if($auin['rank']>0) //rank == level???
			$ra="[<font color='#00FF00'>A</font>]";
		if($auin['rank']!=3 && $mcuid != $user->id && ($auin['ally_id']!=$user->allyId || $auin['ally_id'] == null) )
			$atkb="<a href='?pg=battle&p={$riga['id']}'><img src='img/icons/attack.jpg' border='0'></a>"; //공격을 하는거같음 다른성일경우
		else $atkb="&nbsp;";
			
		$body.= "<tr><td><div align='center'>$i</div></td><td>&nbsp;</td><td>{$riga['name']}</td><td>$ra <a href='?pg=profile&usr=$mcuid'>".$cun." <img src='img/icons/m.jpg' border='0'></a></td><td>".$uan."</td><td>".$atkb."</td></tr>"; //공격에 대한 body 선언 같음.
	} else {
		$body.= "<tr><td><div align='center'>$i</div></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
		<td>
			<form method='post' action=''>
				<input type='hidden' name='map_x' value='$x'>
				<input type='hidden' name='map_y' value='$y'>
				<input type='hidden' name='map_z' value='$i'>
				<input type='hidden' name='colonize' value='do'>
				<input type='image' src='img/icons/colonize.png' title='colonize' onclick='document.thisform.submit()'>
			</form> 
		</td></tr>"; //무슨 파란색불?? 지구본 같은걸 불러옴
	}
}
	
$body.="  </table></div>";
?>