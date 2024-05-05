package hw.zako.fakelags.trolling;

import hw.zako.fakelags.trolling.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractTrolling implements ITrolling {

    private final TrollingType type;

    public static AbstractTrolling byType(TrollingType type) {
        switch (type) {
            case SPAM_SOUNDS -> { return new SoundsTrolling(); }
            case RANDOM_TIME -> { return new TimeTrolling(); }
            case SWAP_ITEMS -> { return new ItemsTrolling(); }
            case FANTOM_BLOCKS -> { return new BlocksTrolling(); }
            case ENTITIES_NO_MOVE -> { return  new FreezeTrolling(); }
            default -> { return new AllTrolling(); }
        }
    }

}
