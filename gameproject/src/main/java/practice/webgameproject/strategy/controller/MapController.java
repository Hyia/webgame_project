package practice.webgameproject.strategy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelXYval;

@Controller
@RequestMapping(value="/map")
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	private static final int MAX_WIDTH_IN_SCREEN = 11;
	private static final int MAX_HEIGHT_IN_SCREEN = 11;
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	@RequestMapping("/")
	public String mapinit(HttpSession session, Model model){
		Integer locationID = (Integer)session.getAttribute("locationID");
		return "redirect:/map/"+locationID;
	}
	@RequestMapping("/{locationID}")
	public String worldmap(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		
		/**
		 * TODO
		 * locationID를 중심으로 맵을 뿌려야함
		 * 
		 * 좌로 맥스 15인 현재 7, 우로 7, 상하로 각 7.
		 * 
		 */
		
		List<ModelXYval> mapData = game.getMap(MAX_WIDTH_IN_SCREEN, MAX_HEIGHT_IN_SCREEN, locationID);
		ModelXYval center = game.getXYval_ID(locationID);
		List<Integer> kinds = new ArrayList<Integer>();
		List<Integer> locations = new ArrayList<Integer>();
		Map<String, Boolean> udlr = new HashMap<String, Boolean>();
		
		for(int i=0; i<mapData.size(); i++){
			ModelXYval xy = mapData.get(i);
			kinds.add(xy.getKind());
			locations.add(xy.getLocationID());
			
			if(xy.getLocationID() == null){
				if(i == (int)(MAX_WIDTH_IN_SCREEN/2)){
					//반드시 위쪽이 널
					udlr.putIfAbsent("uua", true);
				}else if(i == (int)(MAX_HEIGHT_IN_SCREEN/2)*MAX_WIDTH_IN_SCREEN){
					//왼쪽이 널
					udlr.putIfAbsent("lua", true);
				}else if(i == (int)(MAX_HEIGHT_IN_SCREEN/2)*MAX_WIDTH_IN_SCREEN + (int)Math.floor(MAX_WIDTH_IN_SCREEN)-1 ){
					//오른쪽이 널
					udlr.putIfAbsent("rua", true);
				}else if(i == MAX_WIDTH_IN_SCREEN * MAX_HEIGHT_IN_SCREEN - (int)(MAX_WIDTH_IN_SCREEN/2)){
					//아래쪽이 널
					udlr.putIfAbsent("dua", true);
				}
			}
		}
		
		model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN); // FIXME 화면 크기에 맞춰서 계산된 가로세로 크기를 넣을것...
		model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("kind", kinds); //TODO 만들어
		model.addAttribute("locations", locations); //TODO 만들어
		model.addAttribute("udlr", udlr); //TODO 만들어
		
		
		return "/children/worldmap";
	}
	@RequestMapping("/{locationID}/info")
	public String mapDetail(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		logger.info("map info!["+locationID+"]");
		
		ModelMembers user = (ModelMembers) session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS);
		ModelXYval xyval = game.getXYval_ID(locationID);
		String targetOwner;
		switch(xyval.getKind()){
		case IServices.LOCATION_TYPE_CASTLE:
			//성
			logger.info("locationType castle");
			targetOwner = game.getCastleHost(locationID);
			if(targetOwner.equals(user.getUserID())){
				//내 성
				model.addAttribute("btnbex", "주둔");
				model.addAttribute("btnb", "alert('아직 안만듬 ㅋ')");
				model.addAttribute("btncex", "안보임\" hidden=\"true");
			}else{
				//적 혹은 동맹 성
				if(game.isAliance(user.getUserID(), targetOwner)){
					//동맹성
				}else{
					//적 성
					model.addAttribute("btnbex", "공격");
					model.addAttribute("btnb", "alert('아직 안만듬 ㅋ')");
					model.addAttribute("btncex", "안보임\" hidden=\"true");
				}
			}
			
			model.addAttribute("btnaex", "취소");
			model.addAttribute("btna", "$(this).parents('#maincontent').load('/map/"+locationID+"')");
			model.addAttribute("pic", "<img src='/images/img_castle.png'/>");
			model.addAttribute("expl", "성입니다.");
			break;
		case IServices.LOCATION_TYPE_NORMAL:
			//일반 필드
			logger.info("locationType normal");
			model.addAttribute("btnaex", "취소");
			model.addAttribute("btna", "$(this).parents('#maincontent').load('/map/"+locationID+"')");

			model.addAttribute("btnbex", "채집");
			model.addAttribute("btnb", "alert('아직 안만듬 ㅋ')");

			model.addAttribute("btncex", "사냥");
			model.addAttribute("btnc", "alert('아직 안만듬 ㅋ')");
			
			model.addAttribute("pic", "<img src='/images/img_grass.png'/>");
			model.addAttribute("expl", "풀떼기.");
			break;
		case IServices.LOCATION_TYPE_EXTERNALRESOURCE:
			//자원지
			logger.info("locationType resource");
			targetOwner = game.getResourceOwner(locationID); 
			if(targetOwner == null){
				//중립 자원지
				model.addAttribute("btnbex", "점령");
				model.addAttribute("btnb", "alert('아직 안만듬 ㅋ')");
				model.addAttribute("btncex", "채집");
				model.addAttribute("btnc", "alert('아직 안만듬 ㅋ')");
				
			}else if(targetOwner.equals(user.getUserID())){
				//내 자원지
				model.addAttribute("btnbex", "주둔");
				model.addAttribute("btnb", "alert('아직 안만듬 ㅋ')");
				model.addAttribute("btncex", "안보임\" hidden=\"true");
			}else{
				//적 혹은 동맹 자원지
				if(game.isAliance(user.getUserID(), targetOwner)){
					//동맹 자원지
				}else{
					//적 자원지
					model.addAttribute("btnbex", "공격");
					model.addAttribute("btnb", "alert('아직 안만듬 ㅋ')");
					model.addAttribute("btncex", "안보임\" hidden=\"true");
				}
			}
			
			model.addAttribute("btnaex", "취소");
			model.addAttribute("btna", "$(this).parents('#maincontent').load('/map/"+locationID+"')");
			model.addAttribute("pic", "<img src='/images/img_outresource.png'/>");
			model.addAttribute("expl", "자원집니다..");
			break;
			default:
				//에러잼
				return ErrorController.getErrorPage(IServices.ERROR_INVAILD_ACCESS);
		}
		
		
		return "/children/detail_info";//TODO 지형 상세 페이지로 이동시킬것.
	}
	
}
