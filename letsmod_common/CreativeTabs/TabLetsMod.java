package CreativeTabs;

import items.ItemInfo;
import net.minecraft.creativetab.CreativeTabs;
import Blocks.BlockInfo;
import base.ModInformation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabLetsMod extends CreativeTabs
{
    @SideOnly(Side.CLIENT)
    public static TabLetsMod instance;
    
    public TabLetsMod()
    {
        super(CreativeTabs.getNextID(), ModInformation.ID);
        
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex() {

        
        return BlockInfo.BOMB_ID;
    }

}
