package practice.webgameproject.strategy.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.service.ServiceGame;
import practice.webgameproject.strategy.service.TempararyServiceGame;

/**
 * Handles requests for the application home page.
 */
@Controller
@Repository
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//@Autowired
	//ServiceGame service;
//	ServiceGame service = new TempararyServiceGame();
//	@Autowired
//	@Qualifier("serviceGame")
//	ServiceGame service;
	
	@Autowired
	Engine game;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("index page in");
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home_index(Locale locale, Model model) {
		return home(locale,model);
	}
	
	@RequestMapping(value = "/loginreq", method = RequestMethod.POST)
	public String cliced_btn_test(Model model, HttpServletRequest request) {
		
		logger.info("뷁");
		
		String userID = request.getParameter("id");
		String userPWD = request.getParameter("pwd");
		ModelMembers member = new ModelMembers(userID, userPWD, null, null, null, null);
		
		if(game.isValidLogin(member)){
			member = game.getUserInfo(member);
			model.addAttribute("isLogedin", "true");
			model.addAttribute("usernickname", member.getUserNicName());
			model.addAttribute("resource", member.getSaveProduction());
			return "index";
		}
		 

		/**
		member = service.getMember(member);
		if(member!=null){
			model.addAttribute("isLogedin", "true");
			model.addAttribute("usernickname", member.getUserNicName());
			model.addAttribute("resource", member.getSaveProduction());
			return "index";
		}*/
		
		

		model.addAttribute("isLogedin", "false");
		model.addAttribute("errorMsg", "ㅈㄹ");
		
		return "index";
	}
	
}
