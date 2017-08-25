package practice.webgameproject.strategy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.engine.child.Army;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelLog;
import practice.webgameproject.strategy.model.ModelMembers;

/**
 * info hero
 * hire hero
 * fire hero
 * hero lists
 */
@Controller
@RequestMapping(value="/hero")
public class HeroController {
	private static final Logger logger = LoggerFactory.getLogger(HeroController.class);

	@Autowired
	@Qualifier("gameEngine")
	Engine game;
	
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String getHerolist2(Model model,HttpSession session){
		return getHerolist(model, session);
	}

	@RequestMapping(value = "/herolist", method=RequestMethod.GET)
	public String getHerolist(Model model,HttpSession session){
		if(session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS) == null){
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		String userID = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		
		List<ModelHeroTable> heros = game.getUserAllHero(userID);
		if(heros == null){
			heros = new ArrayList<ModelHeroTable>();
		}
		model.addAttribute("pageType", "list");
		model.addAttribute("heros", heros);
		
		return "/children/heros";
	}
	
	
	@RequestMapping(value = "/{heroId}", method=RequestMethod.GET)
	public String getHero(Model model,HttpSession session, @PathVariable("heroId") Integer heroID){
		if(session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS) == null){
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		String userID = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		ModelHeroTable hero = game.getHero(heroID);
		
		String innerHtml = "";
		innerHtml += "<table id='"+hero.getHeroID()+"'><tr><td colspan='3'>보유병력</td></tr><tr>";
		Army arm = game.getHeroTroops(hero.getHeroID());
		for(int j=0; j<arm.getUnits().size();j++){
			if(arm.getUnits().get(j)!=null){
				innerHtml+= "<td>"+arm.getUnits().get(j).getName()+"("+arm.getUnitAmountList().get(j)+")</td>";
			}else{
				innerHtml+="<td>빔</td>";
			}
		}
		innerHtml+="</tr></table>";
		
		model.addAttribute("pic", "[사진"+hero.getPotrait()+"]");
		model.addAttribute("expl", ("무명의 영웅("+hero.getHeroID()+")입니다.<br>영웅은 병력을 이끌며 사기에 영향을 미칩니다.<br><hr>"
				+ innerHtml));
		
		model.addAttribute("btnaex", "사진변경");
		model.addAttribute("btnbex", "이름변경");
		model.addAttribute("btncex", "해고");
		
		model.addAttribute("btna", "alert('사진변경은 미구현입니다.');");
		model.addAttribute("btnb", "alert('이름변경은 미구현입니다.');");
		model.addAttribute("btnc", "alert('해고는 미구현입니다.');");
		
		return "/children/detail_info";
	}
	
	
	
	
}
