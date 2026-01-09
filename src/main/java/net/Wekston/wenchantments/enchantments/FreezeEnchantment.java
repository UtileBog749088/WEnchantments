package net.Wekston.wenchantments.enchantments;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class FreezeEnchantment extends Enchantment {

    public FreezeEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMaxLevel() {
        int i = 5;
        return i;
    }

    @Override
    public int getMinCost(int level) {
        return 1 + level * 8;
    }

    @Override
    public int getMaxCost(int level) {
        return getMinCost(level) + 70;
    }
    @Override
    public boolean canEnchant(ItemStack p_44668_) {
        return p_44668_.getItem() instanceof SwordItem ? true : super.canEnchant(p_44668_);
    }
    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        Level world = attacker.level();

        if (!world.isClientSide() && target instanceof LivingEntity livingTarget) {
            if (world.random.nextFloat() < (0.05f * level)) {

                livingTarget.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SLOWDOWN,
                        3*20,
                        49,
                        false,
                        false,
                        false
                ));
                livingTarget.addEffect(new MobEffectInstance(
                        MobEffects.WEAKNESS,
                        3*20,
                        49,
                        false,
                        false,
                        false
                ));
            }
        }

        super.doPostAttack(attacker, target, level);
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }
    @Override
    public boolean isDiscoverable() {
        return true;
    }
}