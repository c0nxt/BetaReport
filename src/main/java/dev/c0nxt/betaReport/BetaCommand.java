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
            if (sender.hasPermission("betareport.admin")) {
                if(args.length == 2){
                    if (args[0].equalsIgnoreCase("updateLink")) {
                        betaReport.getConfig().set("discord_webhook", args[1]);
                        betaReport.saveConfig();
                    }
                } else if (args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "Usage: /beta updateLink <discord_webhook>");
                } else if(args.length == 0){
                    sender.sendMessage(ChatColor.RED + "Usage: /beta updateLink <discord_webhook>");
                }
                return true;
            }
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
