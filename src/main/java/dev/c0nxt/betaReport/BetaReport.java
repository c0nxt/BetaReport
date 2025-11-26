package dev.c0nxt.betaReport;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public final class BetaReport extends JavaPlugin {

    private String webhookUrl;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        // Plugin startup logic
        webhookUrl = getConfig().getString("discord_webhook");

        Objects.requireNonNull(this.getCommand("beta")).setExecutor(new Command(this));
    }

    @Override
    public void onDisable() {
    }

    public void sendMessage(String message) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(webhookUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonPayload = "{\"content\": \"" + message + "\"}";

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonPayload.getBytes());
            os.flush();
        }

        if (connection.getResponseCode() != 204) {
            throw new RuntimeException("Failed: HTTP error code : " + connection.getResponseCode());
        }
    }

}
