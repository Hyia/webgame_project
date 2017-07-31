package practice.webgameproject.strategy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.webgameproject.strategy.dao.DaoGame;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelStructures;
import practice.webgameproject.strategy.model.ModelUnit;
import practice.webgameproject.strategy.model.ModelUnitBuild;
import practice.webgameproject.strategy.model.ModelXYval;

@Service("serviceGame")
public class ServiceGame extends DaoGame{
	
	@Autowired
	DaoGame dao;

	//위치랑 타입을 가지고 맵 오브젝트를 만들어줄 메서드. 이걸로 외부자원지도 만듬.
	public int insertMapObject(ModelXYval location, int locationTypeCastle) {
		// TODO Auto-generated method stub
		return ERROR_UNHANDLED_EXCEPTION;
	}

	//'공터'상태인 맵 어딘가의 위치를 반납하줄 메서드.
	public ModelXYval getEmptyField() {
		// TODO Auto-generated method stub
		return null;
	}

	//에러메세지를 반납해줄 메서드
	public String getErrorMsg(int errorCode) {
		// TODO 오류종류가 추가되면 여기도 수정 ㄱㄱ
		/**
	public static final int ERROR_GENERAL_EXCEPTION = -1;
	public static final int ERROR_NOT_MAKE_YET = -100;
	public static final int ERROR_UNHANDLED_EXCEPTION = -10;
	public static final int ERROR_CANNOT_FIND_USER = -20;
	public static final int ERROR_INVAILD_LOCATIONID = -30;
	public static final int ERROR_CANNOT_BUILD_THIS_PLACE = -40;
		 */
		switch(errorCode){
		case ERROR_NOT_MAKE_YET:
			return "아직 만들지 않은 메서드가 포함됩니다.";
		case ERROR_CANNOT_FIND_USER:
			return "유저를 찾을 수 업습니다.";
		case ERROR_INVAILD_ACCESS:
			return "부적절한 접근입니다.";
		case ERROR_CANNOT_BUILD_THIS_PLACE:
			return "여기에 건설할 수 없습니다.";
				
		case ERROR_GENERAL_EXCEPTION:
			return "알 수 없는 오류거나 에러코드를 설정하지 않았습니다.";
		case ERROR_UNHANDLED_EXCEPTION://아무 내용도, break도 없는 case구절.
		default:
			return "알 수 없는 오류입니다.";
		}
	}

	//맴버를 추가함
	public int registerMember(ModelMembers member) {
		// TODO Auto-generated method stub
		int result = ERROR_UNHANDLED_EXCEPTION;
		try{
			result = dao.insertMember(member);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	//건물을 추가함
	public int insertBuilding(ModelBuilding building) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}

	//특정누군가의 어디에 있는 건물정보를 가져옴
	public ModelBuilding getBuilding(Integer locationID, int roomNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	//건물 기본정보를 가져옴
	public ModelStructures getSturcture(Integer kind) {
		// TODO Auto-generated method stub
		return null;
	}

	//건물레벨증가에 필요한 자원을 가져옴
	public int getUpgradeValue(Object target, int baseValue) {
		if(target instanceof ModelBuilding){
			//건설 계산식
			return (((ModelBuilding) target).getLevel()+1) * baseValue;
		}
		return -1;
	}

	//건물 DB를 갱신
	public int updateBuilding(ModelBuilding target) {
		// TODO Auto-generated method stub
		return -1;
	}

	//유저의 자원만을 갱신. TODO 자원 외의 정보만 갱신할 매서드. 코딩 중 헷갈리지 않기 위함.+ DB에 필요이상의 변화 방지.
	public int updateMemberResource(ModelMembers who) {
		// TODO Auto-generated method stub
		return -1;
	}
	
	// FIXME 임시. 건물과 유닛으로 나눠놓자.
	public List<Object> getProducingList(){
		// TODO Auto-generated method stub
		return null;
	}
}
