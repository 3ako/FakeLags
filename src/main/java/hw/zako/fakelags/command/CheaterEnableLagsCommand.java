package hw.zako.fakelags.command;

import hw.zako.fakelags.TrollingManager;
import hw.zako.fakelags.TrollingType;
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

public class CheaterEnableLagsCommand implements CommandExecutor, TabCompleter {

    private final TrollingManager trollingManager;

    public CheaterEnableLagsCommand(TrollingManager trollingManager) {
        this.trollingManager = trollingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
       if (args.length != 2) {
           return false;
       }

       final Player target = Bukkit.getPlayer(args[1]);
       final TrollingType type = TrollingType.valueOf(args[0]);

       if (target == null) {
           commandSender.sendMessage("Игрок не найден.");
           return true;
       }

       if (trollingManager.isTrolling(target, type)) {
           trollingManager.removeTrolling(target, type);
       } else {
           trollingManager.addTrolling(target, type);
       }

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
