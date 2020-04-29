package com.qintess.eventos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qintess.eventos.dao.Dao;
import com.qintess.eventos.modelo.Cliente;
import com.qintess.eventos.modelo.Papel;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping("")
	public String carrega(Model modelo) {
		
		modelo.addAttribute("cliente", new Cliente());
		List<Cliente> clientes = dao.buscaTodos(Cliente.class);
		modelo.addAttribute("clientes", clientes);
		
		return "cliente";
	}
	
	
	
	@RequestMapping("/salva")
	public String salva(@ModelAttribute Cliente cliente,RedirectAttributes redirectAtt) {
		
		try {
			if(cliente.getId() == 0) {
				
				criaNovoCliente(cliente);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Cliente cadastrado com sucesso!");
			}
			else {
				dao.altera(cliente);
				redirectAtt.addFlashAttribute("mensagemSucesso", "Cliente alterado com sucesso!");
			}
				
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "redirect:/cliente";
	}

	private void criaNovoCliente(Cliente cliente) {
		PasswordEncoder passEncoder = new BCryptPasswordEncoder();
		String hashedPass = passEncoder.encode(cliente.getSenha());
		cliente.setSenha(hashedPass);
		
		dao.salva(cliente);
		
		List<Papel> papeis = new ArrayList<Papel>();
		Papel papel = new Papel();
		
		papel.setNome("USER");
		if(cliente.getNome().equals("admin")) {
			papel.setNome("ADMIN");
		}
		papel.setCliente(cliente);
		papeis.add(papel);
		
		//cliente.setPapeis(papeis);
		dao.salva(papel);
	}
	
	@RequestMapping("/altera/{id}")
	public String carregaAltera(@PathVariable("id") int id, Model modelo, RedirectAttributes redirectAtt) {
		
		try {
			modelo.addAttribute("cliente", dao.buscaPorId(Cliente.class, id));
			modelo.addAttribute("clientes", dao.buscaTodos(Cliente.class));
		} catch (Exception e) {
			redirectAtt.addFlashAttribute("mensagemErro", "ERRO GRAVE: " + e.getMessage());
		}
		
		return "cliente";
	}
	
	@RequestMapping("/deleta/{id}")
	public String deleta(@PathVariable("id") int id,
						 RedirectAttributes redirectAtt) {
		Cliente cliente = dao.buscaPorId(Cliente.class, id);
		dao.deleta(cliente);
		redirectAtt.addFlashAttribute("mensagemSucesso", "Cliente deletado com sucesso!");
		return "redirect:/cliente";
	}

}
