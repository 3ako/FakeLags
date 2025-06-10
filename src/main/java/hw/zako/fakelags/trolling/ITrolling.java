package hw.zako.fakelags.trolling;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;

public interface ITrolling {

    void packetReceive(PacketReceiveEvent event);

    void packetSend(PacketSendEvent event);

}
