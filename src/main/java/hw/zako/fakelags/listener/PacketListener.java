package hw.zako.fakelags.listener;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.event.ProtocolPacketEvent;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import hw.zako.fakelags.FakeLags;
import hw.zako.fakelags.trolling.AbstractTrolling;
import org.bukkit.entity.Player;

import java.util.HashSet;

public final class PacketListener extends PacketListenerAbstract {

    @Override
    public void onPacketReceive(PacketReceiveEvent e) {
        if (!eventCheck(e, ConnectionState.PLAY))
            return;

        final Player player = (Player) e.getPlayer();
        final HashSet<AbstractTrolling> playerTrollings = FakeLags.getTrollingManager().getTrollings().get(player);

        if (playerTrollings != null) {
            for (AbstractTrolling trolling : playerTrollings)
                trolling.packetReceive(e);
        }
    }

    @Override
    public void onPacketSend(PacketSendEvent e) {
        if (!eventCheck(e, ConnectionState.PLAY))
            return;

        final Player player = (Player) e.getPlayer();
        final HashSet<AbstractTrolling> playerTrollings = FakeLags.getTrollingManager().getTrollings().get(player);

        if (playerTrollings != null) {
            for (AbstractTrolling trolling : playerTrollings)
                trolling.packetSend(e);
        }
    }

    private boolean eventCheck(ProtocolPacketEvent<Object> event, ConnectionState state) {
        if (event.getConnectionState() != state) {
            return false;
        }

        return event.getPlayer() != null;
    }

}
