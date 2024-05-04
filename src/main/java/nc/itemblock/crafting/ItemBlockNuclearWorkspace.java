package nc.itemblock.crafting;

import net.minecraft.block.Block;

import nc.NuclearCraft;
import nc.itemblock.ItemBlockNC;

public class ItemBlockNuclearWorkspace extends ItemBlockNC {

    public ItemBlockNuclearWorkspace(Block block) {
        super(
                block,
                "An advanced 5x5 crafting table used",
                "to make many things in NuclearCraft.",
                (NuclearCraft.workspace ? "Currently enabled." : "Currently disabled."));
    }
}
