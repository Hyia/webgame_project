package practice.webgameproject.strategy.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
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
import practice.webgameproject.strategy.service.ServiceGame;
import practice.webgameproject.strategy.service.TempararyServiceGame;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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
			model.addAttribute("usernickname", ((ModelMembers)session.getAttribute("UserInfo")).getUserNicName());
			model.addAttribute("resource", ((ModelMembers)session.getAttribute("UserInfo")).getSaveProduction());
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home_index(Locale locale, Model model, HttpSession session) {
		return home(locale,model,session);
	}
	
	@RequestMapping(value = "/loginreq", method = RequestMethod.POST)
	public String cliced_btn_test(Model model, HttpServletRequest request, HttpSession session) {
		
		logger.info("뷁");
		
		String userID = request.getParameter("id");
		String userPWD = request.getParameter("pwd");
		ModelMembers member = new ModelMembers(userID, userPWD, null, null, null, null);
		if(game.isValidLogin(member)){
			member = game.getUserInfo(member);
			model.addAttribute("isLogedin", "true");
			model.addAttribute("usernickname", member.getUserNicName());
			model.addAttribute("resource", member.getSaveProduction());
			
			member.setUserPW(null);
			session.setAttribute("UserInfo", member);
			
			
			return "index";
		}

		model.addAttribute("isLogedin", "false");
		model.addAttribute("errorMsg", "ㅈㄹ");
		
		return "index";
	}
	
	@RequestMapping(value = "/admin_orders_initialize_game", method = RequestMethod.GET)
	public String adminPage(Model model){
		return "/admin_orders_initialize_game";
	}
	@RequestMapping(value = "/admin_orders_initialize_game", method = RequestMethod.POST)
	public String adminPageClicked(@RequestParam("value") String str, Model model){
		logger.info("=====================================");
		logger.info(str);
		logger.info("=====================================");
		
		if(str.equals("startServer")){
			game.EngineInitalizer();
			model.addAttribute("status", "start");
			return "/admin_orders_initialize_game";
		}
		if(str.equals("stopServer")){
			game.shutDown();
			model.addAttribute("status", "stop");
			return "/admin_orders_initialize_game";
		}
		
		
		return "/admin_orders_initialize_game";
	}
	
}
