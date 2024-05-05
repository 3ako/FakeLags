package hw.zako.fakelags.command;

import hw.zako.fakelags.FakeLags;
import hw.zako.fakelags.config.Configuration;
import hw.zako.fakelags.trolling.TrollingType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LagsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!commandSender.hasPermission(FakeLags.getConfiguration().getEnablePermission())) {
            return true;
        }

        if (args.length != 2) {
            return false;
        }

        final Player target = Bukkit.getPlayer(args[1]);
        final TrollingType type;

        try {
            type = TrollingType.valueOf(args[0]);
        } catch (Exception ignore) {
            commandSender.sendMessage(FakeLags.getConfiguration().getNotFoundType());
            return true;
        }

        if (target == null) {
            commandSender.sendMessage(FakeLags.getConfiguration().getNotFoundPlayer());
            return true;
        }

        if (FakeLags.getTrollingManager().isAlreadyTrollingSetup(target, type)) {
            FakeLags.getTrollingManager().removeTrolling(target, type);
            commandSender.sendMessage(FakeLags.getConfiguration().getDisableMessage()
                    .replaceAll("%player%", target.getName())
                    .replaceAll("%type%", type.name()));

            return true;
        }

        FakeLags.getTrollingManager().addTrolling(target, type);
        commandSender.sendMessage(FakeLags.getConfiguration().getEnableMessage()
                .replaceAll("%player%", target.getName())
                .replaceAll("%type%", type.name()));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            Arrays.stream(TrollingType.values())
                    .forEach((trollingType -> result.add(trollingType.name())));
        } else if (args.length == 2) {
            Bukkit.getOnlinePlayers()
                    .forEach((player) -> result.add(player.getName()));
        }

        return result;
    }

}
