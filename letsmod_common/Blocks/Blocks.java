package Blocks;
import tileentities.TileEntityBomb;
import tileentities.TileEntityCounting;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public class Blocks
{
    public static Block bomb;
    public static Block counting;
    
    public static void init()
    {
        bomb = new BlockBomb(BlockInfo.BOMB_ID);
        GameRegistry.registerBlock(bomb, BlockInfo.BOMB_KEY);
        
        counting = new BlockCounting(BlockInfo.COUNTING_ID);
        GameRegistry.registerBlock(counting, BlockInfo.COUNTING_KEY);
    }
    
    public static void addNames()
    {
        LanguageRegistry.addName(bomb, BlockInfo.BOMB_NAME);
        LanguageRegistry.addName(counting, BlockInfo.COUNTING_NAME);
    }
    
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityBomb.class, BlockInfo.BOMB_TE_KEY);
        GameRegistry.registerTileEntity(TileEntityCounting.class, BlockInfo.COUNTING_TE_KEY); 
    }
}
