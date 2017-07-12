<?php 
/* --------------------------------------------------------------------------------------
                                    MAP
   Credits         : phpSGEx by Aldrigo Raffaele
   Last modified by: Fhizban 09.06.2013
   Comments        : No changes
-------------------------------------------------------------------------------------- */

$x = $y = 0;
//recupero planet
//isset()�� ��� ������ �����Ǿ��� ��쿡�� TRUE�� ��ȯ�մϴ�. �򰡴� ���ʿ��� ���������� �̷������, �������� ���� ������ ������ �ٷ� �ߴ��մϴ�.
// isset()�� �����Ǿ��ִ°� Ȯ���� �Ʒ� �޼��� ����.
if( isset($_GET['gal']) ){
	//���������� gal,sys �ּ�â�� �Ѿ�� ���� get ���.
	$x=(int)$_GET['gal'];
	$y=(int)$_GET['sys'];

	//x�� 0���� �۰ų� $config ���ð� max_x������ ũ�� x=0����
	//y�� 0���� �۰ų� $config ���ð� max_y������ ũ�� y=0����
    if( $x < 0 || $x > $config["Map_max_x"] ) $x=0;
    if( $y < 0 || $y > $config["Map_max_y"] ) $y=0;
}
	
// tempalte \\
//�̺κ��� ���� �����¿� �����̴� ��ü �κ��ε���.
$body="<form action='' method='get'><input type='hidden' name='pg' value='map' /><h2 class='news-title'>World Map</h2><div class='news-body'>  <table width='85%' border='0' cellspacing='0' cellpadding='10'> <tr> <td width='42'>x</td> <td width='40'>System</td> <td width='24'>&nbsp;</td> <td width='111'>cerca utente</td> </tr> <tr> <td> <a href='?pg=map&gal=".($x-1)."&sys=$y'><input type='button' value='<-' /></a><input type='text' style='text-align:center;' name='gal' id='gal' value='$x' size='3'><a href='?pg=map&gal=".($x+1)."&sys=$y'><input type='button' value='->' /></a> </td> <td> <a href='?pg=map&gal=$x&sys=".($y-1)."'><input type='button' value='<-' /></a><input type='text' style='text-align:center;' name='sys' id='sys' value='$y' size='3'><a href='?pg=map&gal=$x&sys=".($y+1)."'><input type='button' value='->' /></a> </td> </tr> <tr><td colspan='2'><div align='center'><input type='submit' value='Visualize'></div></td></form> <td>&nbsp;</td> <form method='get' action=''> <input type='hidden' name='pg' value='profile'> <td><input type='text' name='snusr' size='11' hint='NickName'> <input type='submit' value=' Search '></td></form> </tr> </table> <p>&nbsp;</p> <table width='600' border='1' cellspacing='0' cellpadding='5'> <tr> <td width='61'><div align='center'>Postion</div></td> <td width='62'>Planet</td> <td width='98'>Name</td> <td width='99'>Player</td> <td width='88'>Ally</td> <td width='93'>Actions</td> </tr>";

//show citys
for( $i=1; $i<= $config['Map_max_z']; $i++ ) {
	//TB_PREFIX�� TABLE_PREFIX �Ǿ��� ���̺��ΰ� ����.
	//���Ȼ� ������ ����ϴ°� ����.
	//$qris�� ���� X,Y,Z ���� �������µ���.
	$qris= $DB->query("SELECT * FROM ".TB_PREFIX."city WHERE x= $x AND y= $y AND z= $i");
	
	
	if( $qris->num_rows ==1 ){ //num_rows�� ������ �� ���� ��ȯ
		$riga= $qris->fetch_array(); //fetch_array()�� �� ���� �ϳ��� �����ͷο츦 ���̺��� ���� Į������ �迭�� �ε����� �Ͽ� ���� $riga �迭�� ����
		$mcuid= $riga['owner']; //owner(�Ƹ��� �������ϴµ���)
		$auin= $DB->query("SELECT username, ally_id, rank FROM ".TB_PREFIX."users WHERE id= $mcuid")->fetch_array();
		$cun= $auin['username'];
		$aacu= $auin['ally_id']; //ally_id = nickname????

        if( $aacu != null ) {
            $qan = $DB->query("SELECT name FROM ".TB_PREFIX."ally WHERE id= $aacu");
			$a= $qan->fetch_array();
            $uan = "<a href='?pg=ally&showally=$aacu'>{$a['name']}</a>"; //�ּ�â�� �Ѱ��ٷ��µ�???
        } else $uan="None";
		
		$ra="";
		if($auin['rank']>0) //rank == level???
			$ra="[<font color='#00FF00'>A</font>]";
		if($auin['rank']!=3 && $mcuid != $user->id && ($auin['ally_id']!=$user->allyId || $auin['ally_id'] == null) )
			$atkb="<a href='?pg=battle&p={$riga['id']}'><img src='img/icons/attack.jpg' border='0'></a>"; //������ �ϴ°Ű��� �ٸ����ϰ��
		else $atkb="&nbsp;";
			
		$body.= "<tr><td><div align='center'>$i</div></td><td>&nbsp;</td><td>{$riga['name']}</td><td>$ra <a href='?pg=profile&usr=$mcuid'>".$cun." <img src='img/icons/m.jpg' border='0'></a></td><td>".$uan."</td><td>".$atkb."</td></tr>"; //���ݿ� ���� body ���� ����.
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
		</td></tr>"; //���� �Ķ�����?? ������ ������ �ҷ���
	}
}
	
$body.="  </table></div>";
?>