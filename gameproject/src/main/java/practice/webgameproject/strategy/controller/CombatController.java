package practice.webgameproject.strategy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelLog;
import practice.webgameproject.strategy.model.ModelMembers;

/**
 * attack order to march, order return or etc....
 */
@Controller
@RequestMapping(value="/combat")
public class CombatController {
	private static final Logger logger = LoggerFactory.getLogger(CombatController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	@RequestMapping(value = "/loglist", method=RequestMethod.GET)
	public String getLog(Model model,HttpSession session){
		if(session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS) == null){
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		
		String userID = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		
		List<ModelLog> logs = game.getUserLogs(userID);
		if(logs == null){
			logs = new ArrayList<ModelLog>();
		}
		model.addAttribute("type", "list");
		model.addAttribute("loglist", logs);
		
		return "/children/combatlog";
	}
	
	
	
}
