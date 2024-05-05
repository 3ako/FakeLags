package hw.zako.fakelags;

import com.github.retrooper.packetevents.event.*;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.sound.SoundCategory;
import com.github.retrooper.packetevents.protocol.world.states.WrappedBlockState;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrollingPacketListener extends PacketListenerAbstract {

    private final TrollingManager trollingManager;

    public TrollingPacketListener(TrollingManager trollingManager) {
        super(PacketListenerPriority.NORMAL);
        this.trollingManager = trollingManager;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (eventCheck(event, ConnectionState.PLAY)) {
            final Player player = (Player) event.getPlayer();
            final Random random = new Random();

            if (trollingManager.isTrolling(player, TrollingType.ALL)) {
                if (Math.random() > 0.65) {
                    event.setCancelled(true);
                }
            }

            if (trollingManager.isTrolling(player, TrollingType.SPAM_SOUNDS)) {
                final Location playerLocation = player.getLocation();

                event.getUser().sendPacket(new WrapperPlayServerSoundEffect(Sound.values()[random.nextInt(Sound.values().length)].ordinal(), SoundCategory.values()[random.nextInt(SoundCategory.values().length)], new Vector3i(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()), 100, 100));
            }

            if (trollingManager.isTrolling(player, TrollingType.SWAP_ITEMS)) {
                event.getUser().sendPacket(new WrapperPlayServerHeldItemChange(random.nextInt(9)));
            }

            if (trollingManager.isTrolling(player, TrollingType.FANTOM_BLOCKS)
                && (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION
                    || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION
                    || event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION)) {
                final Location playerLocation = player.getLocation();
                final int radius = 3;

                for (Block block : getNearbyBlocks(playerLocation, radius)) {
                    if (block.getType() == Material.AIR &&
                            playerLocation.distance(block.getLocation()) >= radius - 1) {
                        event.getUser().sendPacket(new WrapperPlayServerBlockChange(
                                new Vector3i(block.getX(), block.getY(), block.getZ()),
                                WrappedBlockState.getByString(Material.BARRIER.getKey().getKey()).getGlobalId()
                        ));
                    }
                }
            }


        }
    }

    private List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (eventCheck(event, ConnectionState.PLAY)) {
            final Player player = (Player) event.getPlayer();
            final Random random = new Random();

            if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE
                || event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE_AND_ROTATION
                    || event.getPacketType() == PacketType.Play.Server.ENTITY_MOVEMENT
                    || event.getPacketType() == PacketType.Play.Server.ENTITY_HEAD_LOOK
                    || event.getPacketType() == PacketType.Play.Server.ENTITY_VELOCITY) {
                if (trollingManager.isTrolling(player, TrollingType.ENTITIES_NO_MOVE)) {
                    event.setCancelled(true);
                }
            }

            if (trollingManager.isTrolling(player, TrollingType.RANDOM_TIME)) {
                final World world = player.getWorld();

                event.getUser().sendPacket(new WrapperPlayServerTimeUpdate(0, random.nextInt((int) world.getFullTime())));
            }
        }
    }

    private boolean eventCheck(ProtocolPacketEvent<Object> event, ConnectionState state) {
        if (event.getConnectionState() != state) {
            return false;
        }

        return event.getPlayer() != null;
    }

}
