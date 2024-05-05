package hw.zako.fakelags;

import com.github.retrooper.packetevents.PacketEvents;
import hw.zako.fakelags.command.CheaterEnableLagsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FakeLags extends JavaPlugin {

    private TrollingPacketListener packetListener;
    private TrollingManager trollingManager = new TrollingManager();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(trollingManager, this);
        packetListener = new TrollingPacketListener(trollingManager);
        PacketEvents.getAPI().getEventManager().registerListener(packetListener);

        getCommand("cheaterenablelags").setExecutor(new CheaterEnableLagsCommand(trollingManager));
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().getEventManager().unregisterListener(packetListener);
    }

}
