package hw.zako.fakelags.manager;

import hw.zako.fakelags.trolling.TrollingFactory;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

@Getter
public final class TrollingManager {

    private final HashMap<Player, HashSet<AbstractTrolling>> trollings = new HashMap<>();

    public void addTrolling(Player player, TrollingType type) {
        this.trollings.computeIfAbsent(player, p -> new HashSet<>())
                .add(TrollingFactory.getTrolling(type));
    }

    public void removeTrolling(Player player, TrollingType type) {
        final HashSet<AbstractTrolling> trollingPlayer = this.trollings.get(player);

        if (trollingPlayer == null) return;
        trollingPlayer.removeIf((trolling) -> trolling.getType() == type);
    }

    public boolean isAlreadyTrollingSetup(Player player, TrollingType type) {
        final HashSet<AbstractTrolling> trollingPlayer = this.trollings.get(player);

        if (trollingPlayer == null) return false;

        return trollingPlayer.stream()
                .filter((trolling) -> trolling.getType() == type)
                .findFirst()
                .orElse(null) != null;
    }

}
