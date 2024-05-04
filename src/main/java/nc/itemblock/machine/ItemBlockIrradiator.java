package nc.itemblock.machine;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockIrradiator extends ItemBlockNC {

    public ItemBlockIrradiator(Block block) {
        super(
                block,
                "Uses RF to bathe materials in neutron radiation,",
                "causing changes in nuclear structure.",
                "Can accept speed and efficiency upgrades.");
    }
}
