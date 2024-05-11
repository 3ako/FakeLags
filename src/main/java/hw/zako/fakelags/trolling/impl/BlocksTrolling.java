package hw.zako.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.world.states.WrappedBlockState;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockChange;
import hw.zako.fakelags.trolling.TrollingType;
import hw.zako.fakelags.trolling.AbstractTrolling;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class BlocksTrolling extends AbstractTrolling {

    public BlocksTrolling() {
        super(TrollingType.FANTOM_BLOCKS);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {
        if (e.getPacketType() == PacketType.Play.Client.PLAYER_POSITION
                || e.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION
                || e.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION) {
            final Location playerLocation = ((Player) e.getPlayer()).getLocation();
            final int radius = 3;

            for (Block block : getNearbyBlocks(playerLocation, radius)) {
                if (block.getType() == Material.AIR &&
                        playerLocation.distance(block.getLocation()) >= radius - 1) {
                    e.getUser().sendPacket(new WrapperPlayServerBlockChange(
                            new Vector3i(block.getX(), block.getY(), block.getZ()),
                            WrappedBlockState.getByString(Material.BARRIER.getKey().getKey()).getGlobalId()
                    ));
                }
            }
        }
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

    private List<Block> getNearbyBlocks(Location location, int radius) {
        final List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

}
