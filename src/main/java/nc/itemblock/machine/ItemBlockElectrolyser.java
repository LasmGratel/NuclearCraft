package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockElectrolyser extends ItemBlockNC {

    public ItemBlockElectrolyser(Block block) {
        super(
                block,
                "Uses RF and water to produce Oxygen, Hydrogen and Deuterium,",
                "and can accept speed and efficiency upgrades.");
    }
}
