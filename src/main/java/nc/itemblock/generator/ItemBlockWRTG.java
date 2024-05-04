package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockWRTG extends ItemBlockNC {

    public ItemBlockWRTG(Block block) {
        super(block, "Generates a constant stream of " + NuclearCraft.WRTGRF + " RF/t");
    }
}
