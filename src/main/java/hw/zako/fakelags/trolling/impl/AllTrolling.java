package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import hw.zako.fakelags.FakeLags;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;

public final class AllTrolling extends AbstractTrolling {

    public AllTrolling() {
        super(TrollingType.ALL);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {
        if (Math.random() > FakeLags.getConfiguration().getAllPacketCancelChance() / 100.0) {
            e.setCancelled(true);
        }
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

}
