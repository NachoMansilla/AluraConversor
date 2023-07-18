package controlador;

import java.io.IOException;
import java.util.Map;


public class CalcularIntercambio {
    ConsumoAPI datos = ConsumoAPI.getInstance();
    TransformarDivisas transformarDivisas = new TransformarDivisas();
    public double calcular(double cantidadAConvertir, String codigoFrom, String codigoTo) {
        Boolean keyFrom;
        Boolean keyTo;
        double valorDolarTo = 0;
        double valorDolarFrom = 0;




            Map<String, Double> rates = datos.getCurrencyRates();
            if (cantidadAConvertir <= 0) {
                //System.out.println("La cantidad a convertir debe ser mayor a 0");
                return 0.0;
            }

            keyFrom = rates.containsKey(codigoFrom);
            keyTo = rates.containsKey(codigoTo);

            if (keyFrom && keyTo) {
                valorDolarFrom = rates.get(codigoFrom);
                valorDolarTo = rates.get(codigoTo);
            } else {
                //System.out.println("Pais no encontrado");
                return 0.0;
            }

            double resultado = transformarDivisas.convertir(valorDolarTo,valorDolarFrom,cantidadAConvertir);
            //System.out.println(cantidadAConvertir + " " + codigoFrom + " son " + resultado + codigoTo);
            return resultado;




    }
}
