package practice.webgameproject.strategy.controller;

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

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelMembers;

@Controller
@RequestMapping(value="/town")
public class TownController {
	private static final Logger logger = LoggerFactory.getLogger(TownController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	@RequestMapping(value = "/{locationID}", method = RequestMethod.GET)
	public String home(Model model, HttpSession session,@PathVariable("locationID") Integer locationID ) {
		ModelMembers member = (ModelMembers)session.getAttribute("UserInfo");
		if(member.getUserID() != game.getCastleHost(locationID)){
			//니가 주인이 아닐 때
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		
		
		model.addAttribute("buildings", "여기에 성내건물 리스트가 붙어요");
		
		return "/children/city";
	}
	
	
}
