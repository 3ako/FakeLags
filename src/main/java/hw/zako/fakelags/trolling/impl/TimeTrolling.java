package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;
import org.bukkit.entity.Player;

import java.util.Random;

public final class TimeTrolling extends AbstractTrolling {

    public TimeTrolling() {
        super(TrollingType.RANDOM_TIME);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {

    }

    @Override
    public void packetSend(PacketSendEvent e) {
        final User user = e.getUser();
        final Random random = new Random();
        final WrapperPlayServerTimeUpdate packet = new WrapperPlayServerTimeUpdate(0, random.nextInt((int) ((Player) e.getPlayer()).getWorld().getFullTime()));
        user.sendPacket(packet);
    }

}
