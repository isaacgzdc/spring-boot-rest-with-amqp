package com.example.misiones;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class Example001ApplicationTests {
	  private static final String SEPARADOR = ",";
	  private static final String ESPACIO = " ";
	@Test
	void testChomp() {
		final String sep = ", ";
		StringBuilder texto = new StringBuilder();
		texto.append(ESPACIO + SEPARADOR);
		texto.append(ESPACIO + SEPARADOR);
		texto.append(ESPACIO + SEPARADOR);
		
		String res = texto.toString().substring(0, texto.toString().lastIndexOf(SEPARADOR));
		
		System.out.println("'"+res + "' len="+res.length());
		
	}
	
	@Test
	void contextLoads() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Stuff[] stuff = new Stuff[3];
		
	}
	
	public record Stuff(String text, LocalDateTime date, Double amount) {}

}
