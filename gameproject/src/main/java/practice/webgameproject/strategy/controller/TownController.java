package practice.webgameproject.strategy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelXYval;

@Controller
@RequestMapping(value="/town")
public class TownController {
	private static final Logger logger = LoggerFactory.getLogger(TownController.class);
	

	private static final int MAX_WIDTH_IN_SCREEN = 4;
	private static final int MAX_HEIGHT_IN_SCREEN = 4;
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	@RequestMapping("/{locationID}")
	public String towninfo(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		
		/**
		 * TODO
		 * 4x4의 시티 내부
		 * 
		 */
		
		List<ModelBuilding> mapData = game.getBuildingInTown(locationID);
		List<Integer> kinds = new ArrayList<Integer>();
		List<Integer> roomNumber = new ArrayList<Integer>();
		
		for(int i=0; i<mapData.size(); i++){
			kinds.add(mapData.get(i).getKind());
			roomNumber.add(mapData.get(i).getRoomNumber());
		}
		
		//model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN);
		//model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("kind", kinds.size() > 0 ? kinds : "null" ); // 만들어
		model.addAttribute("roomNumber", roomNumber.size() > 0 ? kinds : "null"); // 만들어
		model.addAttribute("mapData", mapData.size() > 0 ? kinds : "null"); //TODO 만들어
		model.addAttribute("locationID", locationID);
		return "/children/town";
	}
	
	@RequestMapping("/{locationID}/10/towninfo")
	public String cityDetail(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		
		model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN);
		model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("kindList", "");
		model.addAttribute("centerLocation", locationID);
		
		
		return "/children/popup_Castle";//TODO 지형 상세 페이지로 이동시킬것.
	}
	
	
	//@RequestMapping(value = "/{locationID}", method = RequestMethod.GET)
	//public String home(Model model, HttpSession session,@PathVariable("locationID") Integer locationID ) {
	//	ModelMembers member = (ModelMembers)session.getAttribute("UserInfo");
	//	if(member.getUserID() != game.getCastleHost(locationID)){
	//		//니가 주인이 아닐 때
	//		return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
	//	}
	//	
	//	
	//	model.addAttribute("buildings", "여기에 성내건물 리스트가 붙어요");
	//	
	//	return "/children/city";
	//}
	
	
}
