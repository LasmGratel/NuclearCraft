package nc.block.storage;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import nc.block.NCBlocks;
import nc.tile.storage.TileLithiumIonBattery;

public class BlockLithiumIonBattery extends BlockEnergyStorage {

    public BlockLithiumIonBattery() {
        super("lithiumIonBattery");
    }

    public Item getItemDropped(int par1, Random random, int par3) {
        return Item.getItemFromBlock(NCBlocks.lithiumIonBatteryBlock);
    }

    public TileEntity createNewTileEntity(World world, int par1) {
        return new TileLithiumIonBattery();
    }
}
