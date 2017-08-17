package practice.webgameproject.strategy.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.model.ModelMembers;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	public static final String SESSION_NAME_MODELMEMBERS = "UserInfo";
	public static final String SESSION_NAME_USERINIT_LOCATIONID = "userInitLocationID";

//	@Autowired
//	ServiceGame service;
//	ServiceGame service = new TempararyServiceGame();

//	Engine game = null;
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("index page in");
		
		if(session.getAttribute("UserInfo") !=null){
			model.addAttribute("isLogedin", "true");
			model.addAttribute("usernickname", ((ModelMembers)session.getAttribute(SESSION_NAME_MODELMEMBERS)).getUserNicName());
			model.addAttribute("resource", ((ModelMembers)session.getAttribute(SESSION_NAME_MODELMEMBERS)).getSaveProduction());
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home_index(Locale locale, Model model, HttpSession session) {
		return home(locale,model,session);
	}
	
	@RequestMapping(value = "/loginreq", method = RequestMethod.POST)
	public String cliced_btn_test(Model model, HttpServletRequest request, HttpSession session) {
		
		String userID = request.getParameter("id");
		String userPWD = request.getParameter("pwd");
		ModelMembers member = new ModelMembers(userID, userPWD, null, null, null, null);
		if(game.isValidLogin(member)){
			member = game.getUserInfo(member);
			model.addAttribute("isLogedin", "true");
			
			Integer locationID = game.getUserInitLocation(member);

			session.setAttribute(SESSION_NAME_MODELMEMBERS, member);
			session.setAttribute(SESSION_NAME_USERINIT_LOCATIONID, locationID);
			
			
			
			return "redirect:index?locationID="+ locationID ;
//			return "redirect:/town/"+locationID;
		}

		model.addAttribute("isLogedin", "false");

		return "index";
	}
	
	@RequestMapping(value = "/children/loginAfter", method = RequestMethod.GET)
	public String refresher(Model model, HttpSession session){
		ModelMembers member = (ModelMembers)session.getAttribute(SESSION_NAME_MODELMEMBERS);
		member = game.getUserInfo(member);
		session.setAttribute(SESSION_NAME_MODELMEMBERS, member);
		
		return "/children/loginAfter";
	}
	
}
