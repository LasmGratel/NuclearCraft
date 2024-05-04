package nc.itemblock.accelerator;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockSynchrotron extends ItemBlockNC {

    public ItemBlockSynchrotron(Block block) {
        super(
                block,
                "Place at the corner of a particle accelerator ring.",
                "Takes in electron cells and fires them into the accelerator.");
    }
}
