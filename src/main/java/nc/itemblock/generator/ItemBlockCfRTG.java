package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockCfRTG extends ItemBlockNC {

    public ItemBlockCfRTG(Block block) {
        super(block, "Generates a constant stream of " + NuclearCraft.CfRTGRF + " RF/t");
    }
}
