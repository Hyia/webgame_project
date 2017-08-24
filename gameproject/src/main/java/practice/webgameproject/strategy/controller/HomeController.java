package practice.webgameproject.strategy.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.engine.child.RemainMarchTimeReturner;
import practice.webgameproject.strategy.model.ModelCastle;
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
	public static final String SESSION_NAME_USERCURRENT_LOCATIONID = "userCurrentLocationID";

//	@Autowired
//	ServiceGame service;
//	ServiceGame service = new TempararyServiceGame();

//	Engine game = null;
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;
	
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("index page in");
		
		if(session.getAttribute("UserInfo") !=null){
			model.addAttribute("isLogedin", "true");
			model.addAttribute("usernickname", ((ModelMembers)session.getAttribute(SESSION_NAME_MODELMEMBERS)).getUserNicName());
			model.addAttribute("resource", ((ModelMembers)session.getAttribute(SESSION_NAME_MODELMEMBERS)).getSaveProduction());
			model.addAttribute("locationID", ((Integer)session.getAttribute(SESSION_NAME_USERINIT_LOCATIONID)));
		}
		
		if(!game.isEngineRunning()){
			game.EngineInitalizer();
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home_index(Locale locale, Model model, HttpSession session) {
		return home(locale,model,session);
	}
	
	@RequestMapping(value = "/loginreq", method = RequestMethod.POST)
	public String cliced_btn_test(Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		String login = request.getParameter("login");//login // register
		logger.info("login btn status: "+login);
		
		String userID = request.getParameter("id");
		String userPWD = request.getParameter("pwd");
		ModelMembers member = new ModelMembers(userID, userPWD, null, null, null, null);
		if(game.isValidLogin(member)){
			member = game.getUserInfo(member);
			
			Integer locationID = game.getUserInitLocation(member);

			redirectAttributes.addFlashAttribute("isLogedin", "true");
			redirectAttributes.addFlashAttribute("locationID", locationID);

			session.setAttribute(SESSION_NAME_MODELMEMBERS, member);
			session.setAttribute(SESSION_NAME_USERINIT_LOCATIONID, locationID);
			session.setAttribute(SESSION_NAME_USERCURRENT_LOCATIONID, locationID);
			
			
			return "redirect:/index";
		}

		model.addAttribute("isLogedin", "false");

		return "index";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String logoutMethod(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/children/loginAfter", method = RequestMethod.GET)
	public String refresher(Model model, HttpSession session){
		ModelMembers member = (ModelMembers)session.getAttribute(SESSION_NAME_MODELMEMBERS);
		member = game.getUserInfo(member);
		session.setAttribute(SESSION_NAME_MODELMEMBERS, member);

		Map<ModelCastle,Map<Integer, String>> remainConstructTime = game.remainConsructTimeAll(member);
		model.addAttribute("constructTimes", remainConstructTime);
		
		Map<ModelCastle,Map<Integer, RemainMarchTimeReturner>> remainMarchTime = game.remainMarchTimeAll(member);
		model.addAttribute("marchTimes", remainMarchTime);
		
		return "/children/loginAfter";
	}
	
}
