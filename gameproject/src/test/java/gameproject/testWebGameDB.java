package gameproject;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelLog;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelSlot;
import practice.webgameproject.strategy.model.ModelUnit;
import practice.webgameproject.strategy.model.ModelUnitBuild;
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
		
		for(int i=0;i<result.size();i++){
			System.out.println(result.get(i));
		}
	}

	@Test
	public void testGetCastleOne() {
		
	}

	@Test
	public void testGetHeroList_InCastle() {
		
	}

	@Test
	public void testGetCastleTroop_SlotList() {
		
	}

	@Test
	public void testGetXYval_LocationID() {
		
	}

	@Test
	public void testGetXYval_XY() {
		
	}

	@Test
	public void testGetWaitList_Building() {
		
	}

	@Test
	public void testGetWaitList_Unit() {
		
	}

	@Test
	public void testGetBuilding() {
		
	}

	@Test
	public void testGetAllProduction() {
		
	}

	@Test
	public void testGetOutResource() {
		
	}

	@Test
	public void testInsertCastle() {
		
	}

	@Test
	public void testInsertXYval() {
		
	}

	@Test
	public void testInsertBuilding() {
		
	}

	@Test
	public void testInsertWaitBuildingList() {
		
	}

	@Test
	public void testInsertWaitUnitgList() {
		
	}

	@Test
	public void testInsertCastletroop() {
		
	}

	@Test
	public void testInsertSlot() {
		
	}

	@Test
	public void testInsertOutResource() {
		
	}

	@Test
	public void testMInsertWaitList_Building() {
		
	}

	@Test
	public void testMInsertWaitList_Unit() {
		
	}

	@Test
	public void testUpdateCastle() {
		
	}

	@Test
	public void testUpdateBuilding() {
		
	}

	@Test
	public void testUpdateOutResource() {
		
	}

	@Test
	public void testUpdateWaitList_Building() {
		
	}

	@Test
	public void testUpdateWaitList_Unit() {
		
	}

	@Test
	public void testDeleteCastleTroop() {
		
	}

	@Test
	public void testDeleteCastleBuildings() {
		
	}

	@Test
	public void testDeleteOutResource() {
		
	}

	@Test
	public void testDeleteCastle() {
		
	}

	@Test
	public void testGetHero() {
		
	}

	@Test
	public void testGetHeroTroop_SlotList() {
		
	}

	@Test
	public void testInsertHerotable() {
		
	}

	@Test
	public void testInsertHerotroop() {
		
	}

	@Test
	public void testUpdateHero() {
		
	}

	@Test
	public void testDeleteHeroTable() {
		
	}

	@Test
	public void testDeleteHeroTroop() {
		
	}

}
