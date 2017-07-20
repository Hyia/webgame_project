package practice.webgameproject.strategy.dao;

import java.util.List;

import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelMembers;

public class DaoGame implements IServices{

	@Override
	public ModelMembers getMember(ModelMembers member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelCastle> getCastleList(ModelMembers targetUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(ModelMembers member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBuilding(ModelBuilding building) {
		// TODO Auto-generated method stub
		return 0;
	}

}
