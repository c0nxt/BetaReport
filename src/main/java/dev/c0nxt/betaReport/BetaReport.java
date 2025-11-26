package dev.c0nxt.betaReport;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class BetaReport extends JavaPlugin {
    DiscordMessageManager discordMessageManager;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        String webhookUrl = getConfig().getString("discord_webhook");
        discordMessageManager = new DiscordMessageManager(webhookUrl);
        Objects.requireNonNull(this.getCommand("beta")).setExecutor(new BetaCommand(this));

    }

    @Override
    public void onDisable() {

    }
    public DiscordMessageManager getDiscordMessageManager() {
        return this.discordMessageManager;
    }

}
