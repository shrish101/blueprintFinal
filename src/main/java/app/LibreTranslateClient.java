package app;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A client for interacting with a LibreTranslate API server.
 * Provides methods to translate text between languages and detect the language of a given text.
 */
public class LibreTranslateClient {
    private final String apiUrl;

    public LibreTranslateClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Translates the given text from a source language to a target language.
     *
     * @param text       the text to translate. Must not be null or empty.
     * @param sourceLang the source language code (e.g., "en" for English). Use "auto" for automatic detection.
     *                   Must not be null or empty.
     * @param targetLang the target language code (e.g., "es" for Spanish). Must not be null or empty.
     * @return the translated text.
     * @throws RuntimeException if the translation fails or an error occurs during the request.
     */
    public String translate(String text, String sourceLang, String targetLang) {
        try {
            // Define the endpoint
            final URL url = new URL(apiUrl + "/translate");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON payload
            final String payload = String.format(
                    "{\"q\":\"%s\", \"source\":\"%s\", \"target\":\"%s\"}",
                    text, sourceLang, targetLang
            );

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                final byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final String jsonResponse = new String(connection.getInputStream().readAllBytes(),
                        StandardCharsets.UTF_8);
                final JsonObject responseObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
                return responseObject.get("translatedText").getAsString();
            }
            else {
                throw new RuntimeException("Failed to translate text. HTTP code: " + connection.getResponseCode());
            }

        }
        catch (Exception e) {
            throw new RuntimeException("Error while translating text: " + e.getMessage(), e);
        }
    }

    /**
     * Detects the language of the given text.
     *
     * @param text the text whose language is to be detected. Must not be null or empty.
     * @return the detected language code (e.g., "en" for English).
     * @throws RuntimeException if language detection fails or an error occurs during the request.
     */
    public String detectLanguage(String text) {
        try {
            // Define the endpoint
            final URL url = new URL(apiUrl + "/detect");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create JSON payload
            final String payload = String.format("{\"q\":\"%s\"}", text);

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                final byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final String jsonResponse = new String(connection.getInputStream().readAllBytes(),
                        StandardCharsets.UTF_8);
                final JsonArray responseArray = JsonParser.parseString(jsonResponse).getAsJsonArray();
                if (responseArray.size() > 0) {
                    final JsonObject detectedLanguageObject = responseArray.get(0).getAsJsonObject();
                    return detectedLanguageObject.get("language").getAsString();
                }
                else {
                    throw new RuntimeException("No language detected.");
                }
            }
            else {
                throw new RuntimeException("Failed to detect language. HTTP code: " + connection.getResponseCode());
            }

        }
        catch (Exception e) {
            throw new RuntimeException("Error while detecting language: " + e.getMessage(), e);
        }
    }
}
