package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class TimeTrolling extends AbstractTrolling {

    public TimeTrolling() {
        super(TrollingType.RANDOM_TIME);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {}

    @Override
    public void packetSend(PacketSendEvent event) {
        final User user = event.getUser();

        final WrapperPlayServerTimeUpdate packet = new WrapperPlayServerTimeUpdate(0, ThreadLocalRandom.current().nextInt((int) ((Player) event.getPlayer()).getWorld().getFullTime()));
        user.sendPacket(packet);
    }

}
