package hw.zako.fakelags;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrollingManager implements Listener {
    private final Map<Player, Set<TrollingType>> playersTrollings = new HashMap<>();

    public boolean isTrolling(Player player, TrollingType type) {
        final Set<TrollingType> types = playersTrollings.get(player);
        if (types == null) return false;
        return types.contains(type);
    }

    public void addTrolling(Player player, TrollingType type) {
        final Set<TrollingType> types = playersTrollings.computeIfAbsent(player, p -> new HashSet<>());
        types.add(type);
    }

    public void removeTrolling(Player player, TrollingType type) {
        final Set<TrollingType> types = playersTrollings.get(player);
        if (types == null) return;
        types.remove(type);
    }

    @EventHandler
    private void on(PlayerQuitEvent e) {
        playersTrollings.remove(e.getPlayer());
    }
}
