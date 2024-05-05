package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import hw.zako.fakelags.trolling.AbstractTrolling;
import hw.zako.fakelags.trolling.TrollingType;

public final class FreezeTrolling extends AbstractTrolling {

    public FreezeTrolling() {
        super(TrollingType.ENTITIES_NO_MOVE);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {

    }

    @Override
    public void packetSend(PacketSendEvent e) {
        if (e.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE
                || e.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE_AND_ROTATION
                || e.getPacketType() == PacketType.Play.Server.ENTITY_MOVEMENT
                || e.getPacketType() == PacketType.Play.Server.ENTITY_HEAD_LOOK
                || e.getPacketType() == PacketType.Play.Server.ENTITY_VELOCITY) {
            e.setCancelled(true);
        }
    }

}
