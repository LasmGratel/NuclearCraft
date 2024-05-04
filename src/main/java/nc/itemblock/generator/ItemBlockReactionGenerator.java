package nc.itemblock.generator;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;
import nc.tile.generator.TileReactionGenerator;

public class ItemBlockReactionGenerator extends ItemBlockNC {

    public ItemBlockReactionGenerator(Block block) {
        super(
                block,
                "Uses basic nuclear fuel and Universal Reactant",
                "to generate " + (TileReactionGenerator.power) + " RF/t");
    }
}
