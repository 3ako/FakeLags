package hw.zako.fakelags;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import org.bukkit.entity.Player;

public class TrollingPacketListener extends PacketListenerAbstract {

    private final TrollingManager trollingManager;

    public TrollingPacketListener(TrollingManager trollingManager) {
        super(PacketListenerPriority.NORMAL);
        this.trollingManager = trollingManager;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getConnectionState() != ConnectionState.PLAY) return;
        final Player player = (Player) event.getPlayer();
        if (player == null) return;
        if (trollingManager.isTrolling(player, TrollingType.LAGS)) {
            if (Math.random() > 0.65) {
                event.setCancelled(true);
            }
        };
    }

}
