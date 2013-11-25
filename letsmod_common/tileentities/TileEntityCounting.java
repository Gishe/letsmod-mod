package tileentities;

import Blocks.BlockCounting;
import Blocks.BlockInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCounting extends TileEntity
{
    public static final int CHANGE_TICKS = 50;
    
    private int timer;
    public int currentValue;
    
    public TileEntityCounting()
    {
        timer = CHANGE_TICKS;
        currentValue = 0;
    }
 
    public void updateEntity() {
        
        if (!worldObj.isRemote)
        {
            if (timer <= 0)
            {
                timer = CHANGE_TICKS;
                currentValue = (currentValue + 1) % BlockCounting.COUNT_MAX;
                
                worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.COUNTING_ID, 1, currentValue);
                
                //worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
            }
        
            timer--;
        }
    }
    
    public boolean receiveClientEvent(int id, int value)
    {
        if (worldObj.isRemote && id == 1)
        {
            currentValue = value;
            
            worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        }
        return true;
    }
    
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("Timer", (short)timer);
        compound.setByte("currentValue", (byte)currentValue);
    }
    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        timer = compound.getShort("Timer");
        currentValue = compound.getByte("currentValue");
    }
    
    
}
