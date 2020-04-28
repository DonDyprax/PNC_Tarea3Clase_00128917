package com.uca.capas.Tarea3Clase.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		return "ingresar";
	}
	
	@RequestMapping("/validacion")
	public ModelAndView validacion(@RequestParam String names, @RequestParam String lastNames,
								   @RequestParam String dob, @RequestParam String pob,
								   @RequestParam String school, @RequestParam String phone,
								   @RequestParam String cellphone) {
		
		ModelAndView mav = new ModelAndView();
		ArrayList<String> errores = new ArrayList<>();
		Date date, minimumDate;
		
		if(names.length() < 1) {
			errores.add("Los nombres deben tener 1 caracter como minimo.");
		}
		if(names.length() > 25) {
			errores.add("Los nombres deben tener 25 caracteres como maximo.");
		}
		
		if(lastNames.length() < 1) {
			errores.add("Los apellidos deben tener 1 caracter como minimo.");
		}
		if(lastNames.length() > 25) {
			errores.add("Los apellidos deben tener 25 caracteres como maximo.");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			date = sdf.parse(dob);
			minimumDate = sdf.parse("2003-01-01");
			
			if(date.compareTo(minimumDate) > 0) {
				errores.add("La fecha de nacimiento debe de ser antes del 01-01-2003");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		if(school.length() < 1) {
			errores.add("El lugar de estudio debe tener 1 caracter como minimo.");
		}
		if(lastNames.length() > 100) {
			errores.add("El lugar de estudio debe tener 100 caracteres como maximo.");
		}
		
		if(phone.length() != 8) {
			errores.add("El numero de telefono fijo debe tener 8 numeros.");
		}
		
		if(cellphone.length() != 8) {
			errores.add("El numero de telefono movil debe tener 8 numeros.");
		}
		
		if(errores.size() == 0) {
			mav.setViewName("exito");
		} else {
			mav.addObject("errores", errores);
			mav.setViewName("fracaso");
		}
			
		return mav;
	}
}
