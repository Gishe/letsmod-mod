package items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items
{
    public static Item wand;
    public static Item card;
    public static Item deathstone;
    
    public static void init()
    {
        wand = new ItemWand(ItemInfo.WAND_ID);
        card = new ItemCard(ItemInfo.CARD_ID);
        deathstone = new ItemDeathstone(ItemInfo.DEATHSTONE_ID);
    }
    
    public static void addNames()
    {
        LanguageRegistry.addName(wand, ItemInfo.WAND_NAME);
        
        for (int i =0; i < ItemInfo.CARD_NAMES.length; i++)
        {
            LanguageRegistry.addName(new ItemStack(card, 1, i), ItemInfo.CARD_NAMES[i]);
        }
        
        for (int i =0; i < ItemInfo.DEATHSTONE_NAMES.length; i++)
        {
            LanguageRegistry.addName(new ItemStack(deathstone, 1, i), ItemInfo.DEATHSTONE_NAMES[i]);
        }
    }
    
    public static void registerRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(wand), 
                new Object[] {
                    "  X",
                    " / ",
                    "/  ",
                    'X', Item.goldenCarrot,
                    '/', Item.stick
        });
        
        for (int i =0; i < ItemInfo.DEATHSTONE_NAMES.length; i++)
        {
            GameRegistry.addRecipe(new ItemStack(deathstone, 1, (i+1)%ItemInfo.DEATHSTONE_NAMES.length),
                    new Object[]
                            {
                            "X",
                            'X', new ItemStack(deathstone, 1, i)
                            }
                            );
        }
        
    }
}
