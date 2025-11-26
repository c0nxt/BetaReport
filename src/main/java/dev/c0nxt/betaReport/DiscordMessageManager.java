package dev.c0nxt.betaReport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordMessageManager {


    private String webhookUrl;

    public DiscordMessageManager(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void sendMessage(String message) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            JsonObject content = new JsonObject();
            content.addProperty("content", message);
            JsonObject allowed = new JsonObject();
            allowed.add("parse", new JsonArray());

            content.add("allowed_mentions", allowed);
            String body = content.toString();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webhookUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("Content-Type", "application/json")
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            Bukkit.getServer().getLogger().warning(e.getMessage());
        }

    }
    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

}
