package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockHeliumExtractor extends ItemBlockNC {

    public ItemBlockHeliumExtractor(Block block) {
        super(
                block,
                "Uses RF to carefully extract Liquid Helium from",
                "its cells, so that it can be transferred as a fluid.");
    }
}
