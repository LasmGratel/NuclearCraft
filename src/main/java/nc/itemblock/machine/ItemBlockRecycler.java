package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockRecycler extends ItemBlockNC {

    public ItemBlockRecycler(Block block) {
        super(
                block,
                "Uses RF to extract materials from depleted cells,",
                "and can accept speed and efficiency upgrades.");
    }
}
