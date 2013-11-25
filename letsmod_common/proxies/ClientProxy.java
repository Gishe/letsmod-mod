package proxies;

import client.RenderSpaceship;
import cpw.mods.fml.client.registry.RenderingRegistry;
import entities.EntitySpaceship;

public class ClientProxy extends CommonProxy
{
    public void initSounds()
    {
        
    }
    
    public void initRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntitySpaceship.class, new RenderSpaceship());
    }
}
