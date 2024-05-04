package nc.container.machine;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

import nc.crafting.machine.HeliumExtractorRecipes;
import nc.tile.machine.TileHeliumExtractor;

public class ContainerHeliumExtractor extends ContainerMachineFluidOut {

    public ContainerHeliumExtractor(InventoryPlayer inventory, TileHeliumExtractor tileentity) {
        super(inventory, tileentity, HeliumExtractorRecipes.instance());
        addSlotToContainer(new Slot(tileentity, 0, 53, 35));
        addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 1, 108, 35));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
}
