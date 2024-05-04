package nc.itemblock.reactor;

import net.minecraft.block.Block;

import nc.itemblock.ItemBlockNC;

public class ItemBlockBlastBlock extends ItemBlockNC {

    public ItemBlockBlastBlock(Block block) {
        super(
                block,
                "An incredibly blast-resistant block which is",
                "ideal for protecting against reactor meltdowns.",
                "It is three times as resistant as Obsidian.");
    }
}
