package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockRTG extends ItemBlockNC {

    public ItemBlockRTG(Block block) {
        super(block, "Generates a constant stream of " + NuclearCraft.RTGRF + " RF/t");
    }
}
