package net.Wekston.wenchantments;

import net.Wekston.wenchantments.registry.ModEnchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EnchantmentsMod.MODID)
public class    EnchantmentsMod
{
    public static final String MODID = "wenchantments";
    public EnchantmentsMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        ModEnchantment.ENCHANTMENTS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
