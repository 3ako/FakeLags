package hw.zako.fakelags;

import com.github.retrooper.packetevents.PacketEvents;
import hw.zako.fakelags.command.LagsCommand;
import hw.zako.fakelags.config.Configuration;
import hw.zako.fakelags.listener.PacketListener;
import hw.zako.fakelags.listener.PlayerListener;
import hw.zako.fakelags.manager.TrollingManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FakeLags extends JavaPlugin {

    @Getter
    private static TrollingManager trollingManager;

    @Getter
    private static PacketListener packetListener;

    @Getter
    private static Configuration configuration;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        trollingManager = new TrollingManager();
        packetListener = new PacketListener();
        configuration = new Configuration(this.getConfig());

        PacketEvents.getAPI().getEventManager().registerListener(packetListener);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("lags").setExecutor(new LagsCommand());
    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().getEventManager().unregisterListener(packetListener);
    }

}
