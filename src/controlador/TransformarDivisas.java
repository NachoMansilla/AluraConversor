package controlador;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransformarDivisas {
    public double convertir(double valorTo, double valorFrom, double cantidad) {
        if (valorFrom == 0) {
            throw new IllegalArgumentException("valorFrom no puede ser cero");
        }
        double resultado = valorTo / valorFrom * cantidad;

        BigDecimal bd = new BigDecimal(Double.toString(resultado));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
        
    }
}

