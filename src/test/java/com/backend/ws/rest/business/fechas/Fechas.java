package com.backend.ws.rest.business.fechas;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Fechas {
	
	
	public void fechas() {
		
		Date someDate = new Date();
		Date newDate = new Date(someDate.getTime() + TimeUnit.DAYS.toMillis( 1 ));
		System.out.println("Manana sera: "+newDate);
 
		newDate = new Date(someDate.getTime() + TimeUnit.DAYS.toMillis( -1 ));
		System.out.println("Ayer fue: "+newDate);
 
		newDate = new Date(someDate.getTime() + TimeUnit.HOURS.toMillis( -1 ));
		System.out.println("Hace una hora: "+newDate);
 
		newDate = new Date(someDate.getTime() + TimeUnit.MINUTES.toMillis( -10 ));
		System.out.println("Hace 10 minutos: "+newDate);
		
		
		/*
		
		Brand brandDate = service.find(Brand.class, "601");		
		Date fechaFin = new Date();		
		long tiempoInicial=brandDate.getCreateAt().getTime();
		long tiempoFinal=fechaFin.getTime(); 
		long resta=tiempoFinal - tiempoInicial;
		//el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
		resta=resta /(1000*60);
		
		System.out.println("Resta: " + resta);*/
		
		
		/**
		 *  NO BORRAR RESTAR FECHAS Y OBTENER HORAS
 		  long tiempoInicial=fechaInicio.getTime();
long tiempoFinal=fechaFin.getTime(); 
long resta=tiempoFinal - tiempoInicial;
//el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
resta=resta /(1000*60);
		 */
		
	}

}
