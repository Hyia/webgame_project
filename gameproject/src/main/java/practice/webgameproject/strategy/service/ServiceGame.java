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

	public int insertBuilding(ModelBuilding building) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}
}
