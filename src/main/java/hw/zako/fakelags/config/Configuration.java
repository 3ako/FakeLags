package hw.zako.fakelags.config;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Configuration {

    private Permission enablePermission;

    private String enableMessage;

    private String disableMessage;

    private String notFoundType;

    private String notFoundPlayer;

    private final int allPacketCancelChance;

    public Configuration(FileConfiguration fileConfiguration) {
        final ConfigurationSection messages = fileConfiguration.getConfigurationSection("messages");
        final String permissionsEnableLag = fileConfiguration.getString("permissions_to_enable_lag");

        if (messages != null) {
            this.disableMessage = fileConfiguration.getString("messages.disable_lag");
            this.enableMessage = fileConfiguration.getString("messages.enable_lag");
            this.notFoundType = fileConfiguration.getString("messages.not_found_type");
            this.notFoundPlayer = fileConfiguration.getString("messages.not_found_player");
        }

        if (permissionsEnableLag != null) {
            this.enablePermission = new Permission(permissionsEnableLag);
        }

        this.allPacketCancelChance = fileConfiguration.getInt("packets_cancel_chance", 65);
    }

}
