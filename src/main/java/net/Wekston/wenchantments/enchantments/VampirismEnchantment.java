package net.Wekston.wenchantments.enchantments;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class VampirismEnchantment extends Enchantment {

    public VampirismEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMaxLevel() {
        int i = 3;
        return i;
    }

    @Override
    public int getMinCost(int level) {
        return 1 + level * 10;
    }

    @Override
    public int getMaxCost(int level) {
        return getMinCost(level) + 80;
    }
    @Override
    public boolean canEnchant(ItemStack p_44668_) {
        return p_44668_.getItem() instanceof SwordItem ? true : super.canEnchant(p_44668_);
    }
    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        Level world = attacker.level();

        if (!world.isClientSide() && target instanceof LivingEntity livingTarget) {
            if (world.random.nextFloat() < (0.1f * level)) {

                float currentHealth = attacker.getHealth();
                float maxHealth = attacker.getMaxHealth();

                float damageAmount = currentHealth * 0.1f;

                float postHealth = currentHealth + damageAmount;
                if (maxHealth>= postHealth) {
                    attacker.setHealth(postHealth);
                    spawnHealParticles(attacker, world);
                }
            }
        }

        super.doPostAttack(attacker, target, level);
    }

    private void spawnHealParticles(LivingEntity entity, Level world) {
        if (!world.isClientSide()) {
            ((ServerLevel) world).sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    entity.getX(),
                    entity.getY() + entity.getBbHeight() / 2,
                    entity.getZ(),
                    10,
                    entity.getBbWidth() / 2,
                    entity.getBbHeight() / 2,
                    entity.getBbWidth() / 2,
                    0.1
            );
        }
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