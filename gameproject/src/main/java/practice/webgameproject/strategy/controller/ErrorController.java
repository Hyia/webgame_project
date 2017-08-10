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

@Controller
@RequestMapping(value="/error")
public class ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;
	
	
	@RequestMapping(value = "/{errorcode}", method = RequestMethod.GET)
	public String errors(@PathVariable("errorcode") Integer errorcode, Model model) {
		String msg = game.getErrorMsg(errorcode);
		
		model.addAttribute("errormsg", msg);
		return "errorpage";
	}



}
