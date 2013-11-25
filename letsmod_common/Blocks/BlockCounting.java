package Blocks;

import base.TestMod;
import tileentities.TileEntityCounting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCounting extends BlockContainer
{

    public static final int COUNT_MAX = 18;
    
    private static Icon[] icons; 
    
    public BlockCounting(int par1)
    {
        super(par1, Material.clay);
        setCreativeTab(TestMod.tab);
        setHardness(2f);
        setStepSound(Block.soundMetalFootstep);
        setUnlocalizedName(BlockInfo.BOMB_UNLOCALIZED_NAME);
        icons = new Icon[COUNT_MAX];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register)
    {
        blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.COUNTING_TEXTURE + 1);
        
        for(int i =0; i < COUNT_MAX; i ++)
        {
            icons[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.COUNTING_TEXTURE + (i + 1));
        }
        
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        TileEntityCounting countingTe = new TileEntityCounting();
        return countingTe;
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
    {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        
        if (te != null && te instanceof TileEntityCounting)
        {
            TileEntityCounting tec = (TileEntityCounting)te;
            
            return icons[tec.currentValue];
        }
        
        return super.getBlockTexture(world, x, y, z, side);
    }


}
