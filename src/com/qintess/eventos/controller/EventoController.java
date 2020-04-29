package com.qintess.eventos.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.eventos.dao.Dao;
import com.qintess.eventos.modelo.Evento;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping("")
	public String carrega(Model modelo) {
		
		modelo.addAttribute("eventos", dao.buscaTodos(Evento.class));
		modelo.addAttribute("evento", new Evento());
		
		return "evento";
	}
	
	@RequestMapping("/salva")
	public String salva(@ModelAttribute Evento evento,
						@RequestParam(required=false, value="cancela") String cancela,
						@RequestParam(required=false, value="imagem") MultipartFile imagem,
						RedirectAttributes redirectAtt) {
		
		byte[] bImagem;
		
		try {
			if(cancela != null) {
				return "redirect:/evento";
			}
			
			if(imagem != null && imagem.getSize() > 0) {
				bImagem = imagem.getBytes();
				evento.setImagemProd(bImagem);
			}

			if(evento.getId() == 0) {
				dao.salva(evento);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Evento cadastrado com sucesso!");
			} else {
				dao.altera(evento);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Evento alterado com sucesso!");
			}
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "redirect:/evento";
	}
	
	
	@RequestMapping("/deleta/{id}")
	public String deleta(@PathVariable(name="id") int id,
						 RedirectAttributes redirectAtt) {
		Evento evento = dao.buscaPorId(Evento.class, id);
		dao.deleta(evento);
		redirectAtt.addFlashAttribute("mensagemSucesso", "Evento deletado com sucesso!");
		return "redirect:/evento";
	}
	
	@RequestMapping("/altera/{id}")
	public String carregaAlterar(@PathVariable(name="id") int id,
			                     Model model,
			                     RedirectAttributes redirectAtt) {
		try {
			
			Evento evento = dao.buscaPorId(Evento.class, id);
			byte[] encodeBase64 = Base64.getEncoder().encode(evento.getImagemProd());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			evento.setImagemEncoded(base64Encoded);
			
			model.addAttribute("eventos", dao.buscaTodos(Evento.class));
			model.addAttribute("evento", evento);
			//model.addAttribute("imagemEvento", base64Encoded);
			
		} catch (UnsupportedEncodingException e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "evento";
	}

}
