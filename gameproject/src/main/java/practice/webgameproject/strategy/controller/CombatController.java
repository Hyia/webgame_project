package practice.webgameproject.strategy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import practice.webgameproject.strategy.engine.Engine;

/**
 * attack order to march, order return or etc....
 */
@Controller
public class CombatController {
	private static final Logger logger = LoggerFactory.getLogger(CombatController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

}
