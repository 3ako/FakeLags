package hw.zako.fakelags.trolling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractTrolling implements ITrolling {

    private final TrollingType type;
}
