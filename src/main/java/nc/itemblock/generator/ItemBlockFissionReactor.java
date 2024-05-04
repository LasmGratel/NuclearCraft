package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockFissionReactor extends ItemBlockNC {

    public ItemBlockFissionReactor(Block block) {
        super(
                block,
                "Uses fissile fuels to generate RF. Check the",
                "individual fuel cells' info for more detail.",
                "Requires a fission multiblock structure to function.");
    }
}
