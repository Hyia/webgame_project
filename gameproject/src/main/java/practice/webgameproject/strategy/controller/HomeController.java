package practice.webgameproject.strategy.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.service.ServiceGame;
import practice.webgameproject.strategy.service.TempararyServiceGame;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

//	@Autowired
//	ServiceGame service;
//	ServiceGame service = new TempararyServiceGame();
	
//	Engine game = new Engine(service);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("index page in");
		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home_index(Locale locale, Model model) {
		return home(locale,model);
	}
	
}
