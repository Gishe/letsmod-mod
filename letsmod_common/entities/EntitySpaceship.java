package entities;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import network.PacketHandler;

public class EntitySpaceship extends Entity implements IEntityAdditionalSpawnData
{

    private boolean charged;
    
    public EntitySpaceship(World world)
    {
        super(world);
        setSize(1.5f, 0.6f);
    }
    
    public boolean isCharged()
    {
        return charged;
    }
    
    public void setCharged()
    {
        charged = true;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }
    
    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        if (entity != riddenByEntity)
        {
            return entity.boundingBox;
        }
        
        return null;
    }
    
    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }
    
    @Override
    public boolean interactFirst(EntityPlayer player) 
    {
        if (!worldObj.isRemote && riddenByEntity == null)
        {
            player.mountEntity(this);
        }
        
        return true;
    }
    
    @Override
    public double getMountedYOffset()
    {
        return -0.15;
    }
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (!worldObj.isRemote)
        {
            if (riddenByEntity != null)
            {
                motionY = 0.2;                
            }else if (worldObj.isAirBlock((int)posX, (int)posY -1, (int)posZ))
            {
                motionY = -0.1;
            }
            else
            {
                motionY = 0;
            }
            
            
        }else
        {
            sendInformation();
        }
        setPosition(posX + motionX, posY + motionY, posZ + motionZ);
    }
    
    private boolean lastPressedState;
    private void sendInformation()
    {
        boolean state = Minecraft.getMinecraft().gameSettings.keyBindJump.pressed;
        
        if (state && !lastPressedState && charged && riddenByEntity == Minecraft.getMinecraft().thePlayer)
        {
            PacketHandler.sendShipPacket(this);
        }
            
        
        lastPressedState = state;
    }

    @Override
    protected void entityInit()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        charged = compound.getBoolean("charged");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setBoolean("charged", charged);
    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data)
    {
        data.writeBoolean(charged);
        
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data)
    {
        charged = data.readBoolean();
        
    }

}
