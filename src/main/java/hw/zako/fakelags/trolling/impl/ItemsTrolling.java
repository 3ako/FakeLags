package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerHeldItemChange;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class ItemsTrolling extends AbstractTrolling {

    public ItemsTrolling() {
        super(TrollingType.SWAP_ITEMS);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {
        final User user = e.getUser();
        final WrapperPlayServerHeldItemChange packet = new WrapperPlayServerHeldItemChange(ThreadLocalRandom.current().nextInt(9));
        user.sendPacket(packet);
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

}
