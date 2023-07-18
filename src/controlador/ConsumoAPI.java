package controlador;

import okhttp3.*;
import com.fasterxml.jackson.databind.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConsumoAPI {
    private static ConsumoAPI instance = null;
    private Map<String, Double> rates;

    // Constructor privado
    private ConsumoAPI() {
        try {
            rates = getCurrencyRatesFromApi();
        } catch (IOException e) {
            System.out.println("Error al obtener datos" + e.getMessage());
        }
    }

    // Método para obtener la instancia de ConsumoAPI
    public static ConsumoAPI getInstance() {
        if (instance == null) {
            instance = new ConsumoAPI();
        }
        return instance;
    }

    // Función para obtener los datos desde la API
    private Map<String, Double> getCurrencyRatesFromApi() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.currencyapi.com/v3/latest?apikey=pcwpWGQyGftiqAf3R9bUEzOFRqQPILZ2FLHi0KcC";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Error: " + response.code());
        }

        String responseBody = response.body().string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode data = root.get("data");

        Map<String, Double> rates = new HashMap<>();
        data.fields().forEachRemaining(entry -> {
            double rate = entry.getValue().get("value").asDouble();
            BigDecimal bd = new BigDecimal(rate);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            rates.put(entry.getKey(), bd.doubleValue());
        });
        return rates;
    }

    public Map<String, Double> getCurrencyRates() {
        return rates;
    }
}
