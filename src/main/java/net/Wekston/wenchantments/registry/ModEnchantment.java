package net.Wekston.wenchantments.registry;

import net.Wekston.wenchantments.EnchantmentsMod;
import net.Wekston.wenchantments.enchantments.FreezeEnchantment;
import net.Wekston.wenchantments.enchantments.LavaEnchantment;
import net.Wekston.wenchantments.enchantments.LightningStrikeEnchantment;
import net.Wekston.wenchantments.enchantments.VampirismEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantment {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EnchantmentsMod.MODID);

    public static final RegistryObject<Enchantment> LIGHTNING_STRIKE =
            ENCHANTMENTS.register("lightning_strike_enchantment",
                    () -> new LightningStrikeEnchantment(
                            Enchantment.Rarity.RARE,
                            EnchantmentCategory.WEAPON,
                            EquipmentSlot.MAINHAND
                    ));


    public static final RegistryObject<Enchantment> FREEZE_ENCHANTMENT =
            ENCHANTMENTS.register("freeze_enchantment",
                    () -> new FreezeEnchantment(
                            Enchantment.Rarity.RARE,
                            EnchantmentCategory.WEAPON,
                            EquipmentSlot.MAINHAND
                    ));

    public static final RegistryObject<Enchantment> VAMPIRISM_ENCHANTMENT =
            ENCHANTMENTS.register("vampirism_enchantment",
                    () -> new VampirismEnchantment(
                            Enchantment.Rarity.RARE,
                            EnchantmentCategory.WEAPON,
                            EquipmentSlot.MAINHAND
                    ));

    public static final RegistryObject<Enchantment> LAVA_WALKER =
            ENCHANTMENTS.register("lava_walker_enchantment",
                    () -> new LavaEnchantment(
                            Enchantment.Rarity.RARE,
                            EnchantmentCategory.ARMOR_FEET,
                            EquipmentSlot.FEET
                    ));
}
