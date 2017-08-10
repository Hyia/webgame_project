package practice.webgameproject.strategy.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelStructures;
import org.springframework.ui.Model;

/**
 * unit and building construct order to be here
 */
@Controller
@RequestMapping(value="/build")
public class BuildController {
	private static final Logger logger = LoggerFactory.getLogger(BuildController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	/**
	@RequestMapping(value="/id")
	public String testone(HttpSession session){
		String str = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		logger.info(str);
		
		return "redirect:/map/id/"+str;
	}
	
	@RequestMapping(value="/id/{userID}")
	public String testtwo(HttpSession session, @PathVariable("userID") String usrid){
		
		logger.info(usrid);
		
		
		return "/children/worldmap";
	}
	 */
	
	@RequestMapping(value="/build/menu/{locationID}", method = RequestMethod.GET)
	public String structBuildMenu(HttpSession session, @PathVariable("locationID") String locationID, Model model){
		ModelMembers member = (ModelMembers)session.getAttribute("UserInfo");
		if(member.getUserID() != game.getCastleHost(Integer.parseInt(locationID))){
			//니가 주인이 아닐 때
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		//주인이 맞으면 리스트를 들고 건설창을 보여준다
		ModelCastle target = new ModelCastle(null, null, null, Integer.parseInt(locationID), null);
		List<ModelStructures> structlist = game.getBuildableBuildings(target);
		model.addAttribute("struct_list", structlist);
		
		return "/children/buildwindow";
	}
	
	@RequestMapping(value="/build/menu/{locationID}/{roomNumber}")
	public String structMenu(HttpSession session, @PathVariable("locationID") String locationID, @PathVariable("roomNumber") Integer roomNumber){
		return null;
	}
	
	
}
