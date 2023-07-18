package controlador;

import java.io.IOException;
import java.util.Map;

public class TestTransformar {

	public static void main(String[] args) {
		ConsumoAPI datos = ConsumoAPI.getInstance();
		TransformarDivisas transformarDivisas = new TransformarDivisas();
		
		double cantidadAConvertir = 0;
		double valorConversionTo = 0;
		double valorConversionFrom = 0;
		String codigoPaisTo,codigoPaisFrom;
		Boolean keyFrom;
		Boolean keyTo;

			Map<String, Double> rates = datos.getCurrencyRates();
			
			codigoPaisFrom = "ARS";		
			codigoPaisTo = "CLP";
			cantidadAConvertir = 10000;
			
			
			if(cantidadAConvertir <= 0) {
				System.out.println("La cantidad a convertir debe ser mayor a 0");
				return;
			}
			
			
			keyFrom = rates.containsKey(codigoPaisFrom);
			keyTo = rates.containsKey(codigoPaisTo);
			
			if (keyFrom && keyTo) {
				valorConversionFrom = rates.get(codigoPaisFrom);
				valorConversionTo = rates.get(codigoPaisTo);
			} else {
				System.out.println("Pais no encontrado");
				return;
			}
			
			double resultado = transformarDivisas.convertir(valorConversionTo,valorConversionFrom,cantidadAConvertir);
			System.out.println(cantidadAConvertir + " " + codigoPaisFrom + " son " + resultado + codigoPaisTo);


	}

}
