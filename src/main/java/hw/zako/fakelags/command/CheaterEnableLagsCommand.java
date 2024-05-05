package hw.zako.fakelags.command;

import hw.zako.fakelags.TrollingManager;
import hw.zako.fakelags.TrollingType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CheaterEnableLagsCommand implements CommandExecutor {
    private final TrollingManager trollingManager;

    public CheaterEnableLagsCommand(TrollingManager trollingManager) {
        this.trollingManager = trollingManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
       if (args.length < 1) {
           return false;
       }

       final Player target = Bukkit.getPlayer(args[0]);

       if (target == null) {
           commandSender.sendMessage("Player offline");
           return true;
       }

       if (trollingManager.isTrolling(target, TrollingType.LAGS)) {
           trollingManager.removeTrolling(target, TrollingType.LAGS);
       } else {
           trollingManager.addTrolling(target, TrollingType.LAGS);
       }

       return true;
    }
}
