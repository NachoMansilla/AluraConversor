package controlador;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class TestConsumoApPI {

	public static void main(String[] args) {
		ConsumoAPI datos = ConsumoAPI.getInstance();
		Set<String> keys;
			Map<String, Double> rates = datos.getCurrencyRates();
			keys = rates.keySet();
			System.out.println(keys);

		

	}

}
