package practice.webgameproject.strategy.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelCastleTroop;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelHeroTroop;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelOutResource;
import practice.webgameproject.strategy.model.ModelSlot;
import practice.webgameproject.strategy.model.ModelUnit;
import practice.webgameproject.strategy.model.ModelWaitList_Building;
import practice.webgameproject.strategy.model.ModelWaitList_Unit;
import practice.webgameproject.strategy.model.ModelXYval;

public class DaoGame implements IServices{
	
	 @Autowired
	 @Qualifier("sqlSession")
	 SqlSession session;
	
	//Member 관련 Methods
	@Override
	public ModelMembers getMember(ModelMembers member) {
		
		// 1이 Return 로그인, 0이 Return 무언가 틀림  Member 일치 정보 없음.
		return session.selectOne("mapper.mysql.mapperWebGame.getMembersLogin",member);
	}

	@Override
	public int insertMember(ModelMembers member) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//Castle 관련 Get Methods
	@Override
	public List<ModelCastle> getCastleList(ModelMembers targetUser) {
		
		// targetUser의 UserID로 Castle 조회 Return(ModelCastle).
		return session.selectList("mapper.mysql.mapperWebGame.getCastle",targetUser);
	}

	@Override
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle) {
		
		// ModelCastle 의 LocationID로 HeroTable 조회해서 Hero 성에 소속된 Hero Return
		return session.selectList("mapper.mysql.mapperWebGame.getherotable",targetCastle);
	}

	@Override
	public List<ModelSlot> getCastleTroop_SlotList(ModelCastle targetTroop) {
		// ModelCastle의 LocationID로 조회하여서 ModelSlot으로 Return
		return session.selectList("mapper.mysql.mapperWebGame.getCatleTroopData",targetTroop);
	}
	
	@Override
	public ModelXYval getModelXYval(ModelCastle targetXYval) {
		// ModelCastle의 LocationID로 조회하여서 ModelXYval으로 Return
		return session.selectOne("mapper.mysql.mapperWebGame.getXYval",targetXYval);
	}
	
	@Override
	public List<ModelWaitList_Unit> getModelWaitList_Unit(ModelCastle targetWitList) {
		// ModelCastle의 LocationID로  ModelWaitList_Unit Return 
		return session.selectOne("mapper.mysql.mapperWebGame.getWaitUnitList",targetWitList);
	}

	@Override
	public List<ModelWaitList_Building> getModelWaitList_Building(ModelCastle targetWitList) {
		// ModelCastle의 LocationID로  ModelWaitList_Building Return
		return session.selectOne("mapper.mysql.mapperWebGame.getWaitBuildingList",targetWitList);
	}
	
	
	//Castle 관련 Insert Methods
	

	@Override
	public int insertBuilding(ModelBuilding building) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	//Hero 관련 Get Methods

	@Override
	public List<ModelHeroTroop> getHeroTroop_SlotList(ModelHeroTable targetTroop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelUnit> getUnitInformation(ModelUnit targetUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelBuilding> getBuilding(ModelCastle targetBuilding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllProduction(ModelCastle targetCastleProduction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertCastle(ModelCastle newCastle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertXYval(ModelXYval newXYval) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertHero(ModelHeroTable newHero) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertWaitBuildingList(ModelWaitList_Building newBuildingList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertWaitUnitgList(ModelWaitList_Unit newUnitList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertCastletroop(ModelCastleTroop newCastleTroop) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSlot(ModelSlot newSlot) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOutResource(ModelOutResource newOutResource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertHerotable(ModelHeroTable newHero) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertHerotroop(ModelHeroTroop newHeroTroop) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	

	

	

	

}
