package practice.webgameproject.strategy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import android.app.AlertDialog;
import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelXYval;
import practice.webgameproject.strategy.service.ServiceGame;

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
		List<ModelCastle> castleList=game.getUserCastleList((ModelMembers)session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS));
		List<Integer> castleLocationID = new ArrayList<Integer>();
		for(int c=0;c<castleList.size();c++){
			castleLocationID.add(castleList.get(c).getLocationID());
		}
		
		
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
		model.addAttribute("castleLocationID",castleLocationID);
		model.addAttribute("locationID", locationID);
		return "/children/town";
	}
	
	@RequestMapping(value = "/{locationID}/insertunit", method = RequestMethod.POST)
	public String cityDetail(@RequestParam(value = "unit1") Integer unit1
							,@RequestParam(value = "unit2") Integer unit2
							,@RequestParam(value = "unit3") Integer unit3
							,@PathVariable("locationID") Integer locationID
							,HttpSession session){
		
		if(unit1!=null&&unit1>0){
			Integer unitid=1;
			game.productUnit((ModelMembers)session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS),locationID , unitid, unit1);
		}else if(unit2!=null&&unit2>0){
			Integer unitid=2;
			game.productUnit((ModelMembers)session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS),locationID , unitid, unit1);	
		}else if(unit3!=null&&unit3>0){
			Integer unitid=3;
			game.productUnit((ModelMembers)session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS),locationID , unitid, unit1);	
		}
		return "redirect:/index";//TODO 지형 상세 페이지로 이동시킬것.
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
