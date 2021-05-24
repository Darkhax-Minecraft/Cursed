package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EnchantmentFragility extends EnchantmentCurse {
    
    public EnchantmentFragility() {
        
        super(EnchantmentType.BREAKABLE, EquipmentSlotType.values());
        
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurt);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingAttack);
    }
    
    private void onBlockBreak (BlockEvent.BreakEvent event) {
        
        if (!event.getWorld().isClientSide()) {
            
            final PlayerEntity user = event.getPlayer();
            final ItemStack item = event.getPlayer().getMainHandItem();
            final int level = EnchantmentHelper.getItemEnchantmentLevel(this, item);
            
            if (level > 0 && Math.random() < 0.15f * level) {
                
                final ServerPlayerEntity damagerEntity = event.getPlayer() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getPlayer() : null;
                item.hurt(1, event.getWorld().getRandom(), damagerEntity);
                
                CursedMod.playSound(damagerEntity, user.getX(), user.getY() + 1, user.getZ(), SoundEvents.SHIELD_BREAK, SoundCategory.MASTER, 1f, 1f);
            }
        }
    }
    
    private void onLivingHurt (LivingHurtEvent event) {
        
        if (event.getEntityLiving() != null && !event.getEntityLiving().level.isClientSide) {
            
            final LivingEntity user = event.getEntityLiving();
            for (final ItemStack item : event.getEntityLiving().getArmorSlots()) {
                
                final int level = EnchantmentHelper.getItemEnchantmentLevel(this, item);
                
                if (level > 0 && Math.random() < 0.15f * level) {
                    
                    final Entity damageSourcEntity = event.getSource().getDirectEntity();
                    final ServerPlayerEntity damagerEntity = damageSourcEntity instanceof ServerPlayerEntity ? (ServerPlayerEntity) damageSourcEntity : null;
                    item.hurt(1, event.getEntityLiving().level.getRandom(), damagerEntity);
                    user.level.playSound(null, user.getX(), user.getY() + 1, user.getZ(), SoundEvents.SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
                }
            }
        }
    }
    
    private void onLivingAttack (LivingAttackEvent event) {
        
        if (event.getEntityLiving() != null && !event.getEntityLiving().level.isClientSide) {
            
            final ItemStack item = event.getEntityLiving().getMainHandItem();
            final int level = EnchantmentHelper.getItemEnchantmentLevel(this, item);
            final LivingEntity user = event.getEntityLiving();
            
            if (level > 0 && Math.random() < 0.15f * level) {
                
                final ServerPlayerEntity damagerEntity = event.getEntityLiving() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getEntityLiving() : null;
                item.hurt(1, event.getEntityLiving().level.getRandom(), damagerEntity);
                user.level.playSound(null, user.getX(), user.getY() + 1, user.getZ(), SoundEvents.SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
}