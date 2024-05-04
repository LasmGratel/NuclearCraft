package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockSeparator extends ItemBlockNC {

    public ItemBlockSeparator(Block block) {
        super(
                block,
                "Uses RF to separate materials into different isotopes,",
                "and can accept speed and efficiency upgrades.");
    }
}
