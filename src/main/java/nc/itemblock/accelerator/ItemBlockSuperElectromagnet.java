package nc.itemblock.accelerator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockSuperElectromagnet extends ItemBlockNC {

    public ItemBlockSuperElectromagnet(Block block) {
        super(
                block,
                "Used to control the beams in particle accelerators.",
                "Requires " + NuclearCraft.superElectromagnetRF + " RF/t to run continuously.");
    }
}
