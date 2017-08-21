package practice.webgameproject.strategy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelLog;
import practice.webgameproject.strategy.model.ModelMembers;

/**
 * info hero
 * hire hero
 * fire hero
 * hero lists
 */
@Controller
@RequestMapping(value="/hero")
public class HeroController {
	private static final Logger logger = LoggerFactory.getLogger(HeroController.class);

	@Autowired
	@Qualifier("gameEngine")
	Engine game;
	
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String getHerolist2(Model model,HttpSession session){
		return getHerolist(model, session);
	}

	@RequestMapping(value = "/herolist", method=RequestMethod.GET)
	public String getHerolist(Model model,HttpSession session){
		if(session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS) == null){
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		String userID = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		
		List<ModelHeroTable> heros = game.getUserAllHero(userID);
		if(heros == null){
			heros = new ArrayList<ModelHeroTable>();
		}
		model.addAttribute("pageType", "list");
		model.addAttribute("heros", heros);
		
		return "/children/heros";
	}
	
	
	@RequestMapping(value = "/{heroId}", method=RequestMethod.GET)
	public String getHero(Model model,HttpSession session, @PathVariable("heroId") String logName){
		
		return "/children/detail_info";
	}
	
	
	
}
