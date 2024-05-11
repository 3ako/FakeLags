package hw.zako.fakelags.listener;

import hw.zako.fakelags.FakeLags;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        FakeLags.getTrollingManager().getTrollings().remove(e.getPlayer());
    }

}
