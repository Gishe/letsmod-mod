package Config;
import items.ItemInfo;

import java.io.File;

import Blocks.BlockInfo;
import net.minecraftforge.common.Configuration;


public class ConfigHandler
{
    public static void init(File file)
    {
        Configuration config = new Configuration(file);
        
        config.load();
        ItemInfo.WAND_ID = config.getItem(ItemInfo.WAND_KEY, ItemInfo.WAND_DEFAULT).getInt();
        ItemInfo.CARD_ID = config.getItem(ItemInfo.CARD_KEY, ItemInfo.CARD_DEFAULT).getInt();
        ItemInfo.DEATHSTONE_ID = config.getItem(ItemInfo.DEATHSTONE_KEY, ItemInfo.DEATHSTONE_DEFAULT).getInt();
        
        BlockInfo.BOMB_ID = config.getBlock(BlockInfo.BOMB_KEY, BlockInfo.BOMB_DEFAULT).getInt();
        BlockInfo.COUNTING_ID =config.getBlock(BlockInfo.COUNTING_KEY,  BlockInfo.COUNTING_DEFAULT).getInt(); 
        
        
        config.save();
    }
}
