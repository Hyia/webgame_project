package practice.webgameproject.strategy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static final int MAX_WIDTH_IN_SCREEN = 11;
	private static final int MAX_HEIGHT_IN_SCREEN = 11;
	
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
		ModelXYval center = game.getXYval_ID(locationID);
		List<Integer> kinds = new ArrayList<Integer>();
		List<Integer> locations = new ArrayList<Integer>();
		Map<String, Boolean> udlr = new HashMap<String, Boolean>();
		
		for(int i=0; i<mapData.size(); i++){
			ModelXYval xy = mapData.get(i);
			kinds.add(xy.getKind());
			locations.add(xy.getLocationID());
			
			if(xy.getLocationID() == null){
				if(i == (int)(MAX_WIDTH_IN_SCREEN/2)){
					//반드시 위쪽이 널
					udlr.putIfAbsent("uua", true);
				}else if(i == (int)(MAX_HEIGHT_IN_SCREEN/2)*MAX_WIDTH_IN_SCREEN){
					//왼쪽이 널
					udlr.putIfAbsent("lua", true);
				}else if(i == (int)(MAX_HEIGHT_IN_SCREEN/2)*MAX_WIDTH_IN_SCREEN + (int)Math.floor(MAX_WIDTH_IN_SCREEN)-1 ){
					//오른쪽이 널
					udlr.putIfAbsent("rua", true);
				}else if(i == MAX_WIDTH_IN_SCREEN * MAX_HEIGHT_IN_SCREEN - (int)(MAX_WIDTH_IN_SCREEN/2)){
					//아래쪽이 널
					udlr.putIfAbsent("dua", true);
				}
			}
		}
		
		model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN); // FIXME 화면 크기에 맞춰서 계산된 가로세로 크기를 넣을것...
		model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("kind", kinds); //TODO 만들어
		model.addAttribute("locations", locations); //TODO 만들어
		model.addAttribute("udlr", udlr); //TODO 만들어
		
		
		return "/children/worldmap";
	}
	@RequestMapping("/{locationID}/info")
	public String mapDetail(HttpSession session, Model model, @PathVariable("locationID") Integer locationID){
		
		model.addAttribute("mapWidth", MAX_WIDTH_IN_SCREEN);
		model.addAttribute("mapHeight", MAX_HEIGHT_IN_SCREEN);
		model.addAttribute("centerLocation", locationID);
		
		
		return "/children/worldmap";//TODO 지형 상세 페이지로 이동시킬것.
	}
}
