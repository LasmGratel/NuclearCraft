package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockSolarPanel extends ItemBlockNC {

    public ItemBlockSolarPanel(Block block) {
        super(
                block,
                "Generates a constant stream",
                "of " + NuclearCraft.solarRF + " RF/t during the day,",
                "and generates less during dawn, dusk and night.");
    }
}
