package com.qintess.eventos.configuracoes;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInicia extends AbstractAnnotationConfigDispatcherServletInitializer {
				
	@Override
	protected Class<?>[] getRootConfigClasses(){ //configuracao dos componentes externos
		return new Class[] {
				HibernateConfig.class,
				WebMvcConfig.class,
				WebSecurityConfig.class
		};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses(){ //configuracao do mvc spring
		return new Class[] {
		};
	}
	
	@Override
	protected String[] getServletMappings(){ //configuracao do mapeamento do servlet do spring
		return new String[] {
				"/"
		};
	}
	
	@Override
    protected Filter[] getServletFilters() {

      CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
      characterEncodingFilter.setEncoding("UTF-8");
      return new Filter[] { characterEncodingFilter};
    }
	
}
