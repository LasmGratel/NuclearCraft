package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockHastener extends ItemBlockNC {

    public ItemBlockHastener(Block block) {
        super(
                block,
                "Uses RF to cause radioactive materials to decay quickly,",
                "and can accept speed and efficiency upgrades.");
    }
}
