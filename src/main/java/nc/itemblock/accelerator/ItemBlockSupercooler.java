package nc.itemblock.accelerator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockSupercooler extends ItemBlockNC {

    public ItemBlockSupercooler(Block block) {
        super(
                block,
                "Used in the construction of particle accelerators to",
                "cool the superconducting electromagnets. Requires",
                NuclearCraft.electromagnetHe + " mB of Liquid Helium per second to run continuously.");
    }
}
