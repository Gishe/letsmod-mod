package items;

import java.util.List;

import base.TestMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemDeathstone extends Item
{

    private Icon[] icons;
    
    public ItemDeathstone(int par1)
    {
        super(par1);
        setCreativeTab(TestMod.tab);
        setHasSubtypes(true);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return ItemInfo.DEATHSTONE_UNLOCALIZED_NAME + itemStack.getItemDamage();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        icons = new Icon[ItemInfo.DEATHSTONE_ICONS.length];
        for (int i = 0; i < ItemInfo.DEATHSTONE_ICONS.length; i ++)
        {
            icons[i] = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.DEATHSTONE_ICONS[i]);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int dmg)
    {
        return icons[dmg];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tab, List list)
    {
        for(int i =0; i < ItemInfo.DEATHSTONE_NAMES.length; i++)
        {
            ItemStack stack = new ItemStack(id, 1, i);
            list.add(stack);
        }
    }

}
