package hw.zako.fakelags;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;

@Getter
public final class Config {

    private final Permission enablePermission;

    private String enableMessage;

    private String disableMessage;

    private String notFoundType;

    private String notFoundPlayer;

    private String noPermissions;

    private final int allPacketCancelChance;

    public Config(FileConfiguration fileConfiguration) {
        final ConfigurationSection messages = fileConfiguration.getConfigurationSection("messages");
        final String permissionsEnableLag = fileConfiguration.getString("permissions_to_enable_lag");

        this.enablePermission = new Permission(permissionsEnableLag);
        this.allPacketCancelChance = fileConfiguration.getInt("packets_cancel_chance", 65);

        this.parseMessages(messages);
    }

    private void parseMessages(ConfigurationSection section) {
        this.disableMessage = section.getString("disable_lag");
        this.enableMessage = section.getString("enable_lag");
        this.notFoundType = section.getString("not_found_type");
        this.notFoundPlayer = section.getString("not_found_player");
        this.noPermissions = section.getString("no_permissions");
    }

}
