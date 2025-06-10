package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.sound.SoundCategory;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSoundEffect;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class SoundsTrolling extends AbstractTrolling {

    public SoundsTrolling() {
        super(TrollingType.SPAM_SOUNDS);
    }

    @Override
    public void packetReceive(PacketReceiveEvent event) {
        final Player player = (Player) event.getPlayer();

        player.playSound(player.getLocation(), Sound.values()[ThreadLocalRandom.current().nextInt(Sound.values().length)], 100, 100);
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

}
