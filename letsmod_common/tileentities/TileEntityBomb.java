package tileentities;

import Blocks.BlockInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;



public class TileEntityBomb extends TileEntity
{
    private static final int SPREAD_TIME = 5;
    private static final int SPREAD_LEVELS = 30;
    
    private int timer;
    private int level;
    
    
    
    public TileEntityBomb()
    {
        timer = SPREAD_TIME;
        level = 0;
    }
    
    public boolean isIdle()
    {
        return timer < 0;
    }
    
    
    
    public void updateEntity() {
        
        if (!worldObj.isRemote)
        {
            if (timer == 0 && level < SPREAD_LEVELS)
            {
                spread(xCoord + 1, yCoord, zCoord);
                spread(xCoord - 1, yCoord, zCoord);
                spread(xCoord, yCoord, zCoord + 1);
                spread(xCoord, yCoord, zCoord - 1);  
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
            } else if (timer == SPREAD_TIME * (level - SPREAD_LEVELS))
            {
                worldObj.createExplosion(null, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 4, true);
            }
        
            timer--;
        }
    }
    
    private void spread(int x, int y, int z)
    {
        if (worldObj.isAirBlock(x, y, z))
        {
            worldObj.setBlock(x, y, z, BlockInfo.BOMB_ID);
            
            TileEntity te = worldObj.getBlockTileEntity(x, y, z);
            
            if (te != null && te instanceof TileEntityBomb)
            {
                TileEntityBomb bomb = (TileEntityBomb)te;
                bomb.level = level + 1;
            }
        }
    }
    
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        compound.setShort("Timer", (short)timer);
        compound.setByte("Level", (byte)level);
    }
    
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        timer = compound.getShort("Timer");
        level = compound.getByte("Level");
    }
    
    
}
