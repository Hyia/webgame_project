package gameproject;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelCastleTroop;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelHeroTroop;
import practice.webgameproject.strategy.model.ModelLog;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelOutResource;
import practice.webgameproject.strategy.model.ModelSlot;
import practice.webgameproject.strategy.model.ModelUnit;
import practice.webgameproject.strategy.model.ModelUnitBuild;
import practice.webgameproject.strategy.model.ModelWaitList_Building;
import practice.webgameproject.strategy.model.ModelWaitList_Unit;
import practice.webgameproject.strategy.model.ModelXYval;

public class testWebGameDB {
	 private static ApplicationContext context = null;
	 private static IServices serviceGame  = null;
	    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
	    serviceGame =context.getBean("serviceGame", IServices.class);
	}
	

	@Test
	public void testGetMember() {
		ModelMembers getMemberOne=new ModelMembers();
		
		getMemberOne.setUserID("tester");
		
		getMemberOne=serviceGame.getMember(getMemberOne);
		
		String result=getMemberOne.getUserNicName();
		
		assertEquals("winter",result);
		
	}
	
	@Test
	public void testGetMemberslogin() {
		ModelMembers loginMembers=new ModelMembers();
		
		loginMembers.setUserID("tester1");
		loginMembers.setUserPW("test");
		
		Integer result=serviceGame.getMembersLogin(loginMembers);
		
		assertSame(1,result);
		
	}

	@Test
	public void testInsertMembers() {
		ModelMembers insertmembers=new ModelMembers();
		
		insertmembers.setUserID("fbrkdals1231");
		insertmembers.setUserPW("rudnfdl2");
		insertmembers.setUserNicName("Winter");
		insertmembers.setUserEmail("fbrkdals1231@naver.com");
		
		
		Integer result=serviceGame.insertMembers(insertmembers);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertLog() {
		
		ModelLog insertLog=new ModelLog();
		Date date=new Date();
		
		
		
		insertLog.setAttackUserID("ATK");
		insertLog.setDefenseUserID("DF");
		insertLog.setAttackUserUseYN(true);
		insertLog.setDefenseUserUseYN(true);
		insertLog.setWhoWins("ATK");
		insertLog.setBattleDate(date);
		
		Integer result=serviceGame.insertLog(insertLog);
		
		assertSame(1,result);
		
	}
	
	@Test
	public void testUpdateMembers_Level() {
		ModelMembers updateMembers = new ModelMembers();
		ModelMembers searchMembers = new ModelMembers();
		
		updateMembers.setUserLevel(1);
		
		searchMembers.setUserID("fbrkdals1231");
		
		Integer result=serviceGame.updateMembers_Level(updateMembers, searchMembers);
		
		assertSame(1, result);
	}
	
	@Test
	public void testUpdateMembers_EXP() {
		ModelMembers updateMembers = new ModelMembers();
		ModelMembers searchMembers = new ModelMembers();
		
		updateMembers.setUserEXP(15);
		
		searchMembers.setUserID("fbrkdals1231");
		
		Integer result=serviceGame.updateMembers_EXP(updateMembers, searchMembers);
		
		assertSame(1, result);
	}
	
	@Test
	public void testUpdateMembers_SaveProduction() {
		ModelMembers updateMembers = new ModelMembers();
		ModelMembers searchMembers = new ModelMembers();
		
		updateMembers.setSaveProduction(300);;
		
		searchMembers.setUserID("fbrkdals1231");
		
		Integer result=serviceGame.updateMembers_SaveProduction(updateMembers, searchMembers);
		
		assertSame(1, result);
	}
	
	@Test
	public void testUpdateMembers_UserData() {
		ModelMembers updateMembers = new ModelMembers();
		ModelMembers searchMembers = new ModelMembers();
		
		updateMembers.setUserEmail("ChangeTest@test");
		updateMembers.setUserNicName("Winter");
		updateMembers.setUserPW("rudnfdl2");
		
		searchMembers.setUserID("fbrkdals1231");
		
		Integer result=serviceGame.updateMembers_UserData(updateMembers, searchMembers);
		
		assertSame(1, result);
	}

	@Test
	public void testDeleteMembers() {
		ModelMembers deleteMember = new ModelMembers();
	
		deleteMember.setUserID("fbrkdals1231");
		deleteMember.setUserPW("rudnfdl2");
		
		Integer result=serviceGame.deleteMembers(deleteMember);
		
		assertSame(1, result);
	}

	@Test
	public void testGetUnitInformation() {
		Integer unitID=1;
		
		ModelUnit result=serviceGame.getUnitInformation(unitID);
		
		//System.out.print(result.toString());
		
		assertEquals("하급", result.getName());
	}
	
	@Test
	public void testGetUnitInformationList() {
		
		List<ModelUnit> result= new ArrayList<ModelUnit>();
		
		result=serviceGame.getUnitInformationList();
		
		//System.out.print(result.toString());
		
		assertSame(3, result.size());
	}

	@Test
	public void testGetUnitBuild() {
		
		List<ModelUnitBuild> result= new ArrayList<ModelUnitBuild>();
		
		
		result=serviceGame.getUnitBuild();
		
		//System.out.print(result.get(0).getBuildTime());
		
		assertSame(1, result.get(0).getUnitID());
		assertSame(2, result.get(1).getUnitID());
		assertSame(3, result.get(2).getUnitID());
		
	}

	@Test
	public void testGetStructures() {
		
		List<ModelUnitBuild> result= new ArrayList<ModelUnitBuild>();
		
		
		result=serviceGame.getUnitBuild();
		
		//System.out.print(result.toString());
		
		assertSame(1, result.get(0).getUnitID());
		assertSame(2, result.get(1).getUnitID());
		assertSame(3, result.get(2).getUnitID());
	}

	@Test
	public void testGetAllXYval() {
		List<ModelXYval> result= new ArrayList<ModelXYval>();
		
		
		result=serviceGame.getAllXYval();
		
		//System.out.print(result.toString());
		
		assertTrue(result.size()>0);
	}

	@Test
	public void testUpdateSlot() {
		ModelSlot updateSlot = new ModelSlot();
		ModelSlot searchSlot = new ModelSlot();
		
		updateSlot.setSlotUID(1);
		updateSlot.setSlotAmount(10);
		
		
		searchSlot.setSlotID(2);
		
		Integer result=serviceGame.updateSlot(updateSlot, searchSlot);
		
		assertSame(1, result);
	}

	@Test
	public void testDeleteXYval() {
		Integer locationID=3;
		
		Integer result=serviceGame.deleteXYval(locationID);
		
		assertSame(1,result);
	}

	@Test
	public void testDeleteSlot() {
		Integer slotID=20;
		
		Integer result=serviceGame.deleteSlot(slotID);
		
		assertSame(1,result);
	}

	@Test
	public void testGetCastleList() {
		ModelMembers serchCastle= new ModelMembers();
		
		serchCastle.setUserID("tester");
		List<ModelCastle> result= new ArrayList<ModelCastle>();
		result= serviceGame.getCastleList(serchCastle);
		
		//for(int i=0;i<result.size();i++){
		//	System.out.println(result.get(i).toString());
		//}
		
		assertSame(1,result.size());
	}

	@Test
	public void testGetCastleOne() {
		Integer locationID=1;
		
		ModelCastle result=serviceGame.getCastleOne(locationID);
		
		assertEquals("winter", result.getName());
	}

	@Test
	public void testGetHeroList_InCastle() {
		ModelCastle castle=new ModelCastle();
		
		castle.setLocationID(1);
		
		List<ModelHeroTable> result =new ArrayList<ModelHeroTable>();
		
		result=serviceGame.getHeroList_InCastle(castle);
		
		for(int i=0;i<result.size();i++){
			System.out.println(result.get(i).toString());
		}
	}

	@Test
	public void testGetCastleTroop_SlotList() {
		ModelCastle locationid=new ModelCastle();
		
		locationid.setLocationID(1);
		
		List<ModelSlot> result=serviceGame.getCastleTroop_SlotList(locationid);
		
		assertSame(3,result.size());
	}

	@Test
	public void testGetXYval_LocationID() {
		Integer locationid=1;
		
		ModelXYval result=serviceGame.getXYval_LocationID(locationid);
		
		assertSame(100,result.getCastleX());
		assertSame(110,result.getCastleY());
	}

	@Test
	public void testGetXYval_XY() {
		ModelXYval xy=new ModelXYval();
		
		xy.setCastleX(100);
		xy.setCastleY(110);
		
		ModelXYval result=serviceGame.getXYval_XY(xy);
		
		assertSame(100,result.getCastleX());
		assertSame(110,result.getCastleY());
	}

	@Test
	public void testGetWaitList_Building() {
		ModelCastle locationID=new ModelCastle();
		
		locationID.setLocationID(1);
		
		List<ModelWaitList_Building> result=serviceGame.getWaitList_Building(locationID);
		
		assertSame(4,result.size());
		
	}

	@Test
	public void testGetWaitList_Unit() {
		ModelCastle locationID=new ModelCastle();
		
		locationID.setLocationID(1);
		
		List<ModelWaitList_Unit> result=serviceGame.getWaitList_Unit(locationID);
		
		assertSame(3,result.size());
	}

	@Test
	public void testGetBuilding() {
		
		ModelCastle locationID=new ModelCastle();
		
		locationID.setLocationID(1);
		
		//List<ModelBuilding> result=serviceGame.getBuilding(locationID);
		
		//assertSame(4,result.size());
	}

	@Test
	public void testGetAllProduction() {
		
		ModelCastle UserID=new ModelCastle();
		
		UserID.setUserID("tester");
		
		Integer result=serviceGame.getAllProduction(UserID);
		Integer re=150;
		//System.out.print(result);
		
		assertEquals(re, result);
		
	}

	@Test
	public void testGetOutResource() {
		
		Integer locationID=1;
		
		ModelOutResource result=serviceGame.getOutResource(locationID);
		
		//System.out.print(result.toString());
		
		assertEquals("tester", result.getUserID());
		
	}

	@Test
	public void testInsertCastle() {
		ModelCastle newCastle=new ModelCastle();
		
		newCastle.setKind(1);
		newCastle.setName("spring");
		newCastle.setUserID("tester");
		newCastle.setLocationID(4);
		
		Integer result=serviceGame.insertCastle(newCastle);
		
		assertSame(1,result);
		
	}

	@Test
	public void testInsertXYval() {
		ModelXYval newXYval=new ModelXYval();
		
		newXYval.setCastleX(-11);
		newXYval.setCastleY(-11);
		newXYval.setKind(1);
		
		Integer result=serviceGame.insertXYval(newXYval);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertBuilding() {
		ModelBuilding newBuilding=new ModelBuilding();
		
		newBuilding.setKind(4);
		newBuilding.setLevel(1);
		newBuilding.setLocationID(2);
		newBuilding.setRoomNumber(2);
		
		Integer result=serviceGame.insertBuilding(newBuilding);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertWaitBuildingList() {
		ModelWaitList_Building newWB=new ModelWaitList_Building();
		Date dt = new Date();

	
		newWB.setKind(1);
		newWB.setLocationID(1);
		newWB.setRoomNumber(5);
		newWB.setWaitTime(dt);
		
		Integer result=serviceGame.insertWaitBuildingList(newWB);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertWaitUnitList() {
		ModelWaitList_Unit newWU=new ModelWaitList_Unit();
		Date dt = new Date();

	
		newWU.setAmount(10);
		newWU.setUnitID(1);
		newWU.setLocationID(1);
		newWU.setRoomNumber(5);
		newWU.setWaitTime(dt);
		
		Integer result=serviceGame.insertWaitUnitList(newWU);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertCastletroop() {
		ModelCastleTroop newCastleTroop=new ModelCastleTroop();
		
		newCastleTroop.setLocationID(2);
		newCastleTroop.setSlotID(20);
		
		Integer result=serviceGame.insertCastletroop(newCastleTroop);
		
		assertSame(1,result);
		
	}

	@Test
	public void testInsertSlot() {
		ModelSlot newslot=new ModelSlot();
		
		newslot.setSlotAmount(0);
		
		Integer result=serviceGame.insertSlot(newslot);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertOutResource() {
		ModelOutResource newOutResource=new ModelOutResource();
		
		newOutResource.setKind(1);
		newOutResource.setProduction(150);
		newOutResource.setUserID("tester1");
		newOutResource.setLocationID(5);
		
		Integer result=serviceGame.insertOutResource(newOutResource);
		
		assertSame(1,result);
	}

	@Test
	public void testMInsertWaitList_Building() {
		List<ModelWaitList_Building> list=new ArrayList<ModelWaitList_Building>();
		Date dt=new Date();
		
		list.add(new ModelWaitList_Building());
		list.add(new ModelWaitList_Building());
		list.add(new ModelWaitList_Building());
		
		list.get(0).setKind(1);
		list.get(1).setKind(2);
		list.get(2).setKind(3);
		
		list.get(0).setLocationID(1);
		list.get(1).setLocationID(1);
		list.get(2).setLocationID(1);
		
		list.get(0).setRoomNumber(1);
		list.get(1).setRoomNumber(2);
		list.get(2).setRoomNumber(3);
		
		list.get(0).setWaitTime(dt);
		list.get(1).setWaitTime(dt);
		list.get(2).setWaitTime(dt);
		
		
		
		
		Integer result=serviceGame.mInsertWaitList_Building(list);
		
		assertSame(3,result);
	}

	@Test
	public void testMInsertWaitList_Unit() {
		List<ModelWaitList_Unit> list=new ArrayList<ModelWaitList_Unit>();
		Date dt=new Date();
		
		list.add(new ModelWaitList_Unit());
		list.add(new ModelWaitList_Unit());
		list.add(new ModelWaitList_Unit());
		
		list.get(0).setUnitID(1);
		list.get(1).setUnitID(2);
		list.get(2).setUnitID(3);
		
		list.get(0).setAmount(1);
		list.get(1).setAmount(2);
		list.get(2).setAmount(3);
		
		
		list.get(0).setLocationID(1);
		list.get(1).setLocationID(1);
		list.get(2).setLocationID(1);
		
		list.get(0).setRoomNumber(1);
		list.get(1).setRoomNumber(2);
		list.get(2).setRoomNumber(3);
		
		list.get(0).setWaitTime(dt);
		list.get(1).setWaitTime(dt);
		list.get(2).setWaitTime(dt);
		
		
		
		
		Integer result=serviceGame.mInsertWaitList_Unit(list);
		
		assertSame(3,result);
	}

	@Test
	public void testUpdateCastle() {
		ModelCastle updateCastle=new ModelCastle();
		ModelCastle searchCastle=new ModelCastle();
		
		updateCastle.setName("테스트");
		
		searchCastle.setLocationID(4);
		
		Integer result=serviceGame.updateCastle(updateCastle, searchCastle);
		
		assertSame(1,result);
	}

	@Test
	public void testUpdateBuilding() {
		ModelBuilding updateBuilding=new ModelBuilding();
		ModelBuilding searchBuilding=new ModelBuilding();
		
		updateBuilding.setLevel(2);
		
		searchBuilding.setLocationID(1);
		searchBuilding.setRoomNumber(2);
		
		Integer result=serviceGame.updateBuilding(updateBuilding, searchBuilding);
		
		assertSame(1,result);
	}

	@Test
	public void testUpdateOutResource() {
		ModelOutResource updateOutResource=new ModelOutResource();
		ModelOutResource searchOutResource=new ModelOutResource();
		
		//updateOutResource
		
		searchOutResource.setLocationID(1);
		
		
		Integer result=serviceGame.updateOutResource(updateOutResource, searchOutResource);
		
		assertSame(1,result);
	}

	@Test
	public void testDeleteCastleTroop() {
		Integer locationID=3;
		
		Integer result=serviceGame.deleteCastleTroop(locationID);
		
		if(result>=0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
	}

	@Test
	public void testDeleteCastleBuilding_All() {
		Integer locationID=2;
		
		Integer result=serviceGame.deleteCastleBuilding_All(locationID);
		
		if(result>=0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
	}
	
	@Test
	public void testDeleteCastleBuilding_One() {
		ModelBuilding delBuilding=new ModelBuilding();
		
		delBuilding.setRoomNumber(1);
		delBuilding.setLocationID(1);
		
		Integer result=serviceGame.deleteCastleBuilding_One(delBuilding);
		
		assertSame(1,result);
	}

	@Test
	public void testDeleteOutResource() {
		Integer locationID=5;
		
		Integer result=serviceGame.deleteOutResource(locationID);
		
		assertSame(1,result);
	}

	@Test
	public void testDeleteCastle_All() {
		String userID = "jtest";
		
		Integer result=serviceGame.deleteCastle_All(userID);
		
		if(result>=0){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
	}
	
	@Test
	public void testDeleteCastle_One() {
		Integer locationID = 5;
		
		Integer result=serviceGame.deleteCastle_One(locationID);
		
		assertSame(1,result);
	}

	@Test
	public void testGetHero() {
		ModelHeroTable targetHero=new ModelHeroTable();
		
		targetHero.setHeroID(2);
		
		ModelHeroTable result=serviceGame.getHero(targetHero);
		
		assertEquals("test",result.getOwner());
		
		System.out.println(result.toString());
	}

	@Test
	public void testGetHeroTroop_SlotList() {
		Integer heroID=1;
		
		List<ModelSlot> result=serviceGame.getHeroTroop_SlotList(heroID);
		
		assertSame(6,result.size());
	}

	@Test
	public void testInsertHerotable() {
		ModelHeroTable newHero=new ModelHeroTable();
		
		newHero.setAGI(100);
		newHero.setCON(100);
		newHero.setSTR(100);
		newHero.setLocationID(1);
		newHero.setSex(false);
		
		Integer result=serviceGame.insertHerotable(newHero);
		
		assertSame(1,result);
	}

	@Test
	public void testInsertHerotroop() {
		ModelHeroTroop newHeroTroop=new ModelHeroTroop();
		
		newHeroTroop.setHeroID(5);
		newHeroTroop.setSlotID(20);
		
		Integer result=serviceGame.insertHerotroop(newHeroTroop);
		
		assertSame(1,result);
		
	}

	@Test
	public void testUpdateHero() {
		ModelHeroTable updateHero=new ModelHeroTable();
		ModelHeroTable searchHero=new ModelHeroTable();
		
		
		updateHero.setAGI(666);
		updateHero.setCON(666);
		updateHero.setSTR(666);
		
		searchHero.setHeroID(5);
		
		
		Integer result=serviceGame.updateHero(updateHero, searchHero);
		
		assertSame(1,result);
	}

	@Test
	public void testDeleteHeroTable() {
		
	}

	@Test
	public void testDeleteHeroTroop() {
		
	}

}
