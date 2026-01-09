package net.Wekston.wenchantments.enchantments;

import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class LightningStrikeEnchantment extends Enchantment {

    public LightningStrikeEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
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
        return getMinCost(level) + 50;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.canApplyAtEnchantingTable(this);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        Level world = attacker.level();

        if (!world.isClientSide()) {
            if (world.random.nextFloat() < (0.1f * level)) {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(world);

                if (lightningBolt != null) {
                    lightningBolt.moveTo(target.getX(), target.getY(), target.getZ());

                    lightningBolt.setVisualOnly(false);
                    world.addFreshEntity(lightningBolt);
                }
                target.setSecondsOnFire(2);
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