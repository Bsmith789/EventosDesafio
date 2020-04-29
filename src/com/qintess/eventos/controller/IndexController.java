package com.qintess.eventos.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qintess.eventos.dao.Dao;
import com.qintess.eventos.modelo.Evento;

@Controller
public class IndexController {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping("/")
	public String index(Model modelo) {
		
		try {
			List<Evento> eventos = dao.buscaTodos(Evento.class);
			modelo.addAttribute("eventos", encodaImagemProduto(eventos));
		 
		} catch (UnsupportedEncodingException e) {
			modelo.addAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "index";
	}
	
	@RequestMapping("/menu")
	public String menu(Model modelo) {
		Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
		
		modelo.addAttribute("usuarioLogado", auth);
		return "menu";
	}
	
	@RequestMapping("/menuLateral")
	public String menuLateral(Model modelo) {
		Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
		
		modelo.addAttribute("usuarioLogado", auth);
		return "menuLateral";
	}
	
	@RequestMapping("/rodape")
	public String rodape() {
		return "rodape";
	}

	private List<Evento> encodaImagemProduto(List<Evento> eventos) throws UnsupportedEncodingException {
		
		for (Evento evento : eventos) {
			byte[] encodeBase64 = Base64.getEncoder().encode(evento.getImagemProd());
			evento.setImagemEncoded(new String(encodeBase64, "UTF-8"));
		}
		return eventos;
	}

}

