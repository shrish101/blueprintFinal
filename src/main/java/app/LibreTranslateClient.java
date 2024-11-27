package app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LibreTranslateClient {
    private final String apiUrl;

    public LibreTranslateClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String translate(String text, String sourceLang, String targetLang) {
        try {
            // Define the endpoint
            URL url = new URL(apiUrl + "/translate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON payload
            String payload = String.format(
                    "{\"q\":\"%s\", \"source\":\"%s\", \"target\":\"%s\"}",
                    text, sourceLang, targetLang
            );

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String jsonResponse = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                return responseObject.get("translatedText").getAsString();
            } else {
                throw new RuntimeException("Failed to translate text. HTTP code: " + connection.getResponseCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while translating text: " + e.getMessage(), e);
        }
    }

    public String detectLanguage(String text) {
        try {
            // Define the endpoint
            URL url = new URL(apiUrl + "/detect");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON payload
            String payload = String.format("{\"q\":\"%s\"}", text);

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String jsonResponse = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                JsonArray responseArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
                if (responseArray.size() > 0) {
                    JsonObject detectedLanguageObject = responseArray.get(0).getAsJsonObject();
                    return detectedLanguageObject.get("language").getAsString();
                } else {
                    throw new RuntimeException("No language detected.");
                }
            } else {
                throw new RuntimeException("Failed to detect language. HTTP code: " + connection.getResponseCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while detecting language: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        // Configure the API URL
        LibreTranslateClient client = new LibreTranslateClient("http://localhost:5000");

        // Translate text
        String text = "Hello, world!";
        String translatedText = client.translate(text, "en", "fr");
        System.out.println("Translated Text: " + translatedText);
    }
}


