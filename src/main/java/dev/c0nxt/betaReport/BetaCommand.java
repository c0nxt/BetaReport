package dev.c0nxt.betaReport;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public record BetaCommand(BetaReport betaReport) implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            StringBuilder text = new StringBuilder();
            Arrays.stream(args).forEach(x -> text.append(x).append(" "));
            try {
                betaReport.getDiscordMessageManager().sendMessage("**" + ((Player) sender).getDisplayName() + ":** " + text);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + betaReport.getConfig().getString("error_message"));
            }
        }
        return true;
    }
}
