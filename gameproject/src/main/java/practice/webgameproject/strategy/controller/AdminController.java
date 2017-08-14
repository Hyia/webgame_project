package practice.webgameproject.strategy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;

/**
 * for admin page.
 * FIXME maybe needs login for administer
 */
@Controller
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

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
		if(str.equals("makeMap")){
			game.resetMap(IServices.MAP_MAX_WIDTH, IServices.MAP_MAX_HEIGHT, 0.05);
			model.addAttribute("status", "initialized");
			return "/admin_orders_initialize_game";
		}
		
		return "/admin_orders_initialize_game";
	}
	

}
