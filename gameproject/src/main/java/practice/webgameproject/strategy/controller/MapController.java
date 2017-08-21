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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.model.ModelXYval;

@Controller
@RequestMapping(value="/map")
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	private static final int MAX_WIDTH_IN_SCREEN = 15;
	private static final int MAX_HEIGHT_IN_SCREEN = 15;
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	@RequestMapping("/")
	public String mapinit(HttpSession session, Model model){
		Integer locationID = (Integer)session.getAttribute("locationID");
		return "redirect:/map/"+locationID;
	}
	
	@RequestMapping("/{locationID}")
	public String worldmap(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		
		/**
		 * TODO
		 * locationID를 중심으로 맵을 뿌려야함
		 * 
		 * 좌로 맥스 15인 현재 7, 우로 7, 상하로 각 7.
		 * 
		 */
		
		List<ModelXYval> mapData = game.getMap(MAX_WIDTH_IN_SCREEN, MAX_HEIGHT_IN_SCREEN, locationID);
		List<Integer> kinds = new ArrayList<Integer>();
		List<Integer> locations = new ArrayList<Integer>();
		
		for(int i=0; i<mapData.size(); i++){
			kinds.add(mapData.get(i).getKind());
			locations.add(mapData.get(i).getLocationID());
		}
		
		model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN);
		model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("kind", kinds.size() > 0 ? kinds : "null"); //TODO 만들어
		model.addAttribute("locations", locations.size() > 0 ? kinds : "null"); //TODO 만들어
		model.addAttribute("mapData", mapData.size() > 0 ? kinds : "null"); //TODO 만들어
		model.addAttribute("centerLocation", locationID);
		
		
		return "/children/worldmap";
	}
	@RequestMapping("/{locationID}/info")
	public String mapDetail(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		
		model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN);
		model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("kindList", "");
		model.addAttribute("centerLocation", locationID);
		
		
		return "/children/worldmap";//TODO 지형 상세 페이지로 이동시킬것.
	}
}
