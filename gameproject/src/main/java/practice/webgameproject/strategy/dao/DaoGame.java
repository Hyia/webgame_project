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
import practice.webgameproject.strategy.model.ModelStructures;
import practice.webgameproject.strategy.model.ModelUnit;
import practice.webgameproject.strategy.model.ModelUnitBuild;
import practice.webgameproject.strategy.model.ModelWaitList_Building;
import practice.webgameproject.strategy.model.ModelWaitList_Unit;
import practice.webgameproject.strategy.model.ModelXYval;

public class DaoGame implements IServices{
	
	 @Autowired
	 @Qualifier("sqlSession")
	 SqlSession session;

	@Override
	public ModelMembers getMember(ModelMembers targetMember) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertMembers(ModelMembers newMember) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelUnit getUnitInformation(Integer UnitID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelUnitBuild> getUnitBuild(ModelUnitBuild targetUnitBuild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelStructures> getStructures(ModelStructures targetStructures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelXYval> getAllXYval() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelOutResource getOutResource(Integer locationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelCastle> getCastleList(ModelMembers targetUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelCastle getCastleOne(Integer locationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelSlot> getCastleTroop_SlotList(ModelCastle targetTroop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelXYval getXYval_LocationID(Integer targetXYval) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ModelXYval getXYval_XY(ModelCastle targetXYval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelWaitList_Building> getWaitList_Building(ModelCastle targetWitList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelWaitList_Unit> getWaitList_Unit(ModelCastle targetWitList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelBuilding> getBuilding(ModelCastle targetBuilding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllProduction(ModelCastle targetCastleProduction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertCastle(ModelCastle newCastle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertXYval(ModelXYval newXYval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertBuilding(ModelBuilding newBuilding) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertWaitBuildingList(ModelWaitList_Building newBuildingList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertWaitUnitgList(ModelWaitList_Unit newUnitList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertCastletroop(ModelCastleTroop newCastleTroop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertSlot(ModelSlot newSlot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertOutResource(ModelOutResource newOutResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer mInsertWaitList_Building(List<ModelWaitList_Building> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer mInsertWaitList_Unit(List<ModelWaitList_Unit> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelHeroTable getHero(ModelHeroTable targetHero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelHeroTroop> getHeroTroop_SlotList(ModelHeroTable targetTroop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertHerotable(ModelHeroTable newHero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertHerotroop(ModelHeroTroop newHeroTroop) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
