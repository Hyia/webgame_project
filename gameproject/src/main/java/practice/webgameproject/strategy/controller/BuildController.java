package practice.webgameproject.strategy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import practice.webgameproject.strategy.engine.Engine;

/**
 * unit and building construct order to be here
 */
@Controller
public class BuildController {
	private static final Logger logger = LoggerFactory.getLogger(BuildController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

}
