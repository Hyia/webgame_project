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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.engine.child.Army;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelLog;
import practice.webgameproject.strategy.model.ModelMembers;

/**
 * attack order to march, order return or etc....
 */
@Controller
@RequestMapping(value="/combat")
public class CombatController {
	private static final Logger logger = LoggerFactory.getLogger(CombatController.class);
	
	@Autowired
	@Qualifier("gameEngine")
	Engine game;

	@RequestMapping(value = "/loglist", method=RequestMethod.GET)
	public String getLoglist(Model model,HttpSession session){
		if(session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS) == null){
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}
		String userID = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		
		List<ModelLog> logs = game.getUserLogs(userID);
		if(logs == null){
			logs = new ArrayList<ModelLog>();
		}
		model.addAttribute("pageType", "list");
		model.addAttribute("loglist", logs);
		
		return "/children/combatlog";
	}
	
	
	@RequestMapping(value = "/loglist/{logName}", method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getLog(Model model,HttpSession session, @PathVariable("logName") String logName){
		String log = game.getLogFile(logName);
		ModelMembers user = (ModelMembers)session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS);
		
		logger.info("가져온 파일1:["+log+"]");

		if(log.contains(user.getUserID())){
			return log;
		}
		return "권한이 없습니다.";
	}
	
	
	@RequestMapping(value = "/select/{locationID}", method=RequestMethod.GET)
	public String makemarch(Model model,HttpSession session,@PathVariable("locationID") Integer locationID){
		String HERO_PICTURE = "사진";
		if(session.getAttribute(HomeController.SESSION_NAME_MODELMEMBERS) == null){
			return "redirect:/error/"+IServices.ERROR_INVAILD_ACCESS;
		}

		String userID = ((ModelMembers)session.getAttribute("UserInfo")).getUserID();
		List<ModelHeroTable> heros = game.getUserAllHero(userID);
		
		String myjqueryscript = "var list = {};"
				+ "list.counter = 0;"
				+ "list.mylist = [];"
				+ "$('#btn').click(function (e) {"
				+ "var $chkboxs =  $('input[name=selector]');"
				+ "for(var i=0; i< $chkboxs.length; i++){"
				+ "if($('input[name=selector]')[i].checked){"
				+ "list.mylist[list.counter] = $('input[name=selector]')[i].value;"
				+ "list.counter++;"
				+ "}\n}\n"
				+ "list.counter = 0; list.mylist = [];})";
		String innerHtml = "<form id='marchform' method='post' action='/combat/makemarch/"+locationID+"'>";
		for(int i=0; i < heros.size(); i++){
			ModelHeroTable hero = heros.get(i);
			innerHtml += "<table id='"+hero.getHeroID()+"'>"
					+ "<tr><td rowspan='3'><input type='checkbox' name='selector' value='"+hero.getHeroID()+"'/></td>"
					+ "</tr><tr><td rowspan='2'>"+HERO_PICTURE+"</td><td colspan='3'>";
			innerHtml += "무명의 영웅"+hero.getHeroID()+" lv."+hero.getHeroLevel()+"("+hero.getLocationID()+")</td>"
					+ "</tr><tr>";
			Army arm = game.getHeroTroops(hero.getHeroID());
			for(int j=0; j<arm.getUnits().size();j++){
				if(arm.getUnits().get(j)!=null){
					innerHtml+= "<td>"+arm.getUnits().get(j).getName()+"("+arm.getUnitAmountList().get(j)+")</td>";
				}else{
					innerHtml+="<td>빔</td>";
				}
			}
			innerHtml+="</tr></table>";
		}
		
		
		
		model.addAttribute("pic", "전투");
		model.addAttribute("myjqueryscript", myjqueryscript);
		model.addAttribute("expl", innerHtml);
		model.addAttribute("btnaex", "취소");
		model.addAttribute("btna", "$(this).parents('#maincontent').load('/map/"+locationID+"')");
		model.addAttribute("btnbex", "확인");
		model.addAttribute("btnb", "$('#marchform').submit()");
		model.addAttribute("btncex", "안보임\" hidden=\"true");
		
		return "/children/detail_info";
	}
	
	
	@RequestMapping(value = "/makemarch/{locationID}", method=RequestMethod.POST)
	public String march(Model model,HttpSession session,@PathVariable("locationID") Integer locationID, @RequestParam("selector") Integer[] heroIds){
//		for(int i=0; i<heroIds.length;i++){
//			logger.info("가는 영웅은 "+heroIds[i]);
//		}
		int result = game.goBattle(heroIds[0], locationID);
		if(result < 0){
			return ErrorController.getErrorPage(result);
		}
		logger.info("나옴");
		return "redirect:/";
	}
	
	
	
}
