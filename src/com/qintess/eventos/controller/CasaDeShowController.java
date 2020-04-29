package com.qintess.eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.eventos.dao.Dao;
import com.qintess.eventos.modelo.CasaDeShow;

@Controller
@RequestMapping("/casaDeShow")
public class CasaDeShowController {

	@Autowired
	private Dao dao;
	
	@RequestMapping("")
	public String view(Model modelo) {
		modelo.addAttribute("casaDeShow", new CasaDeShow());
		List<CasaDeShow> casaDeShows = dao.buscaTodos(CasaDeShow.class);
		modelo.addAttribute("casaDeShows", casaDeShows);
		return "casaDeShow";
	}
	
	@RequestMapping("/salva")
	public String salva(@ModelAttribute CasaDeShow casaDeShow,RedirectAttributes redirectAtt) {
		
		try {
			if(casaDeShow.getId() == 0) {
				
				dao.salva(casaDeShow);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Casa de Show cadastrada com sucesso!");
			}
			else {
				dao.altera(casaDeShow);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Casa de Show alterada com sucesso!");
			}
				
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "redirect:/casaDeShow";
	}
	
	@RequestMapping("/altera/{id}")
	public String carregaAltera(@PathVariable("id") int id, Model modelo, RedirectAttributes redirectAtt) {
		
		try {
			modelo.addAttribute("casaDeShow", dao.buscaPorId(CasaDeShow.class, id));
			modelo.addAttribute("casaDeShows", dao.buscaTodos(CasaDeShow.class));
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "casaDeShow";
	}
	
	@RequestMapping("/deleta/{id}")
	public String deleta(@PathVariable("id") int id,
						 RedirectAttributes redirectAtt) {
		CasaDeShow casaDeShow = dao.buscaPorId(CasaDeShow.class, id);
		dao.deleta(casaDeShow);
		redirectAtt.addFlashAttribute("mensagemSucesso", "Casa de Show deletada com sucesso!");
		return "redirect:/casaDeShow";
	}

}