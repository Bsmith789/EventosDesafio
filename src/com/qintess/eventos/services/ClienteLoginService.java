package com.qintess.eventos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qintess.eventos.dao.Dao;
import com.qintess.eventos.modelo.Cliente;

@Service
public class ClienteLoginService implements UserDetailsService {

	@Autowired
	private Dao dao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		
		Cliente cliente = null;
		UserBuilder builder = null;
		
		try {
			cliente = dao.buscaTodos(Cliente.class).stream()
							.filter(x -> x.getEmail().equals(username))
							.findFirst()
							.get();
			
			if(cliente != null) {
				builder = User.withUsername(username);
				builder.password(cliente.getSenha());
				String[] papeis = cliente.getPapeis().stream().map(a -> a.getNome()).toArray(String[]::new);
				builder.roles(papeis);
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("Usuario não identificado em nossos servidores!");
		}
		
		
		return builder.build();
	}
	
	
}

