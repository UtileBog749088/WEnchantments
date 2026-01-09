package net.Wekston.wenchantments.registry;

import net.Wekston.wenchantments.EnchantmentsMod;
import net.Wekston.wenchantments.enchantments.LavaEnchantment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = EnchantmentsMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityMove(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();
        if (level.isClientSide) return;
        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
        if (!boots.isEmpty()) {
            Enchantment lavaWalker = ForgeRegistries.ENCHANTMENTS.getValue(
                    ResourceLocation.fromNamespaceAndPath(EnchantmentsMod.MODID, "lava_walker_enchantment")
            );
            if (lavaWalker != null) {
                int levelEnchant = EnchantmentHelper.getItemEnchantmentLevel(lavaWalker, boots);
                if (levelEnchant > 0) {
                    LavaEnchantment.onEntityMoved(entity, level, entity.blockPosition(), levelEnchant);
                }
            }
        }
    }
}