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

    private String noPermissions;

    private final int allPacketCancelChance;

    public Configuration(FileConfiguration fileConfiguration) {
        final ConfigurationSection messages = fileConfiguration.getConfigurationSection("messages");
        final String permissionsEnableLag = fileConfiguration.getString("permissions_to_enable_lag");

        if (messages != null) {
            this.disableMessage = messages.getString("disable_lag");
            this.enableMessage = messages.getString("enable_lag");
            this.notFoundType = messages.getString("not_found_type");
            this.notFoundPlayer = messages.getString("not_found_player");
            this.noPermissions = messages.getString("no_permissions");
        }

        if (permissionsEnableLag != null) {
            this.enablePermission = new Permission(permissionsEnableLag);
        }

        this.allPacketCancelChance = fileConfiguration.getInt("packets_cancel_chance", 65);
    }

}
