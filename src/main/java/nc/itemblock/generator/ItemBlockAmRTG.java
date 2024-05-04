package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockAmRTG extends ItemBlockNC {

    public ItemBlockAmRTG(Block block) {
        super(block, "Generates a constant stream of " + NuclearCraft.AmRTGRF + " RF/t");
    }
}
