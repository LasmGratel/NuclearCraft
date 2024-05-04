package nc.handler;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.IFuelHandler;
import nc.block.NCBlocks;

public class FuelHandler implements IFuelHandler {

    public int getBurnTime(ItemStack fuel) {
        Item item = fuel.getItem();

        if (item == Item.getItemFromBlock(NCBlocks.graphiteBlock)) {
            return 16000;
        }

        return 0;
    }
}
