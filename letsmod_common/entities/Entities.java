package entities;

import base.TestMod;
import cpw.mods.fml.common.registry.EntityRegistry;


public class Entities
{
    public static void init()
    {
        EntityRegistry.registerModEntity(EntitySpaceship.class, "EntitySpaceship", 0, TestMod.instance, 80, 3, true);
    }
}
