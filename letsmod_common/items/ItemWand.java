package items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import base.TestMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import entities.EntitySpaceship;

public class ItemWand extends Item
{
    @SideOnly(Side.CLIENT)
    private Icon chargedIcon;
    
    public ItemWand(int id)
    {
        super(id);
        setCreativeTab(TestMod.tab);
        setMaxStackSize(1);
        setUnlocalizedName(ItemInfo.WAND_UNLOCALIZED_NAME);
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target) 
    {
        if (!target.worldObj.isRemote)
        {
            target.motionY = 2;
            if (isCharged(itemStack.getItemDamage()))
            {
                target.motionX = (target.posX - player.posX)* 2;
                target.motionZ = (target.posZ - player.posZ)* 2; 
                //itemStack.setItemDamage(0);
            }
            else {itemStack.setItemDamage(itemStack.getItemDamage() + 1);}
        }
        
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.WAND_ICON);
        chargedIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.WAND_CHARGED_ICON);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean useExtraInformation)
    {
        info.add("This fun thing has been used " + itemStack.getItemDamage() + " times");
        
        if (isCharged(itemStack.getItemDamage()))
        {
            info.add("This item is charged");
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int dmg)
    {
        if (isCharged(dmg))
        {
            return chargedIcon;
        }
        
        return itemIcon;
    }
    
    public boolean isCharged(int dmg)
    {
        return dmg >= 10;
    }
    
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote && player.isSneaking())
        {
            EntitySpaceship ship = new EntitySpaceship(world);
            ship.posX = x + 0.5;
            ship.posY = y + 1.5;
            ship.posZ = z + 0.5;
            
            if (isCharged(stack.getItemDamage()))
            {
                stack.setItemDamage(0);
                ship.setCharged();
            }
            
            world.spawnEntityInWorld(ship);
            return true;
        }
        
        return false;
    }
}
