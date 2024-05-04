package mekanism.api.gas;

import net.minecraft.tileentity.TileEntity;

import mekanism.api.transmitters.IGridTransmitter;

public interface IGasTransmitter extends IGridTransmitter<IGasHandler, GasNetwork> {

    public boolean canTransferGasToTube(TileEntity tile);
}
