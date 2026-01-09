package net.Wekston.wenchantments.enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

public class LavaEnchantment extends Enchantment {

    public LavaEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinCost(int level) {
        return 10 ;
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 60;
    }


    public static void onEntityMoved(LivingEntity living, Level level, BlockPos pos, int EnchantLevel) {
        if (living.onGround() && !living.isInWater()) {
            BlockState blockstate = Blocks.BASALT.defaultBlockState();
            int radius = Math.min(16, 2 + EnchantLevel);
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (BlockPos blockpos : BlockPos.betweenClosed(
                    pos.offset(-radius, -1, -radius),
                    pos.offset(radius, -1, radius))) {

                if (blockpos.closerToCenterThan(living.position(), radius)) {
                    mutablePos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState aboveState = level.getBlockState(mutablePos);

                    if (aboveState.isAir()) {
                        BlockState currentState = level.getBlockState(blockpos);

                        boolean isLava = currentState.getBlock() == Blocks.LAVA && currentState.getValue(LiquidBlock.LEVEL) == 0;

                        if (isLava
                                && blockstate.canSurvive(level, blockpos)
                                && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty())) {
                            level.setBlockAndUpdate(blockpos, blockstate);

                            if (level instanceof ServerLevel serverLevel) {
                                serverLevel.sendParticles(ParticleTypes.LAVA,
                                        blockpos.getX() + 0.5, blockpos.getY() + 1.1, blockpos.getZ() + 0.5,
                                        3, 0.2, 0.1, 0.2, 0.01);
                            }
                        }
                    }
                }
            }
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
    @Override
    public boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other) && other != Enchantments.FROST_WALKER;
    }
}