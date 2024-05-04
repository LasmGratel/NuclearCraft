package nc.itemblock.storage;

import net.minecraft.block.Block;

import nc.NuclearCraft;

public class ItemBlockLithiumIonBattery extends ItemBlockEnergyStorage {

    public ItemBlockLithiumIonBattery(Block block) {
        super(block, NuclearCraft.lithiumIonRF * 10);
    }
}
