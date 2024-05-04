package nc.itemblock.nuke;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockEMP extends ItemBlockNC {

    public ItemBlockEMP(Block block) {
        super(
                block,
                "A devastating weapon which removes all RF from",
                "every block in a " + (int) (0.5D * NuclearCraft.explosionRadius) + " block radius.");
    }
}
