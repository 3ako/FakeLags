package hw.zako.fakelags.trolling;

import hw.zako.fakelags.trolling.impl.*;

public class TrollingFactory {
    public static AbstractTrolling getTrolling(TrollingType type) {
        return switch (type) {
            case ALL_CANCEL -> new AllTrolling();
            case ENTITIES_NO_MOVE -> new FreezeTrolling();
            case SPAM_SOUNDS -> new SoundsTrolling();
            case FANTOM_BLOCKS -> new BlocksTrolling();
            case RANDOM_TIME -> new TimeTrolling();
            case SWAP_ITEMS -> new ItemsTrolling();
        };
    }
}
