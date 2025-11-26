package dev.c0nxt.betaReport;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {
    private BetaReport plugin;

    public Command(BetaReport plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
            if (sender instanceof Player){
                StringBuilder text = new StringBuilder();
                for(String arg : args){
                    text.append(arg).append(" ");
                }
                try {

                    plugin.sendMessage("**" +((Player) sender).getDisplayName()+ ":** " + text);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        return true;
    }
}
