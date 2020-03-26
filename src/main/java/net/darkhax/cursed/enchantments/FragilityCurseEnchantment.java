package net.darkhax.cursed.enchantments;

import net.darkhax.bookshelf.enchantment.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

public class FragilityCurseEnchantment extends EnchantmentCurse {
    
    public FragilityCurseEnchantment() {
        
        super(EnchantmentType.BREAKABLE, EquipmentSlotType.values());
        
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingHurt);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingAttack);
    }
    
    private void onBlockBreak (BlockEvent.BreakEvent event) {
        
        if (!event.getWorld().isRemote()) {
            
            final LivingEntity user = event.getPlayer();
            final ItemStack item = event.getPlayer().getHeldItemMainhand();
            final int level = EnchantmentHelper.getEnchantmentLevel(this, item);
            
            if (level > 0 && Math.random() < 0.15f * level) {
                
                final ServerPlayerEntity damagerEntity = event.getPlayer() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getPlayer() : null;
                item.attemptDamageItem(1, event.getWorld().getRandom(), damagerEntity);
                user.world.playSound(null, user.getPosX(), user.getPosY() + 1, user.getPosZ(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
    
    private void onLivingHurt (LivingHurtEvent event) {
        
        if (event.getEntityLiving() != null && !event.getEntityLiving().world.isRemote) {
            
            final LivingEntity user = event.getEntityLiving();
            for (final ItemStack item : event.getEntityLiving().getArmorInventoryList()) {
                
                final int level = EnchantmentHelper.getEnchantmentLevel(this, item);
                
                if (level > 0 && Math.random() < 0.15f * level) {
                    
                    final Entity damageSourcEntity = event.getSource().getImmediateSource();
                    final ServerPlayerEntity damagerEntity = damageSourcEntity instanceof ServerPlayerEntity ? (ServerPlayerEntity) damageSourcEntity : null;
                    item.attemptDamageItem(1, event.getEntityLiving().world.getRandom(), damagerEntity);
                    user.world.playSound(null, user.getPosX(), user.getPosY() + 1, user.getPosZ(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
                }
            }
        }
    }
    
    private void onLivingAttack (LivingAttackEvent event) {
        
        if (event.getEntityLiving() != null && !event.getEntityLiving().world.isRemote) {
            
            final ItemStack item = event.getEntityLiving().getHeldItemMainhand();
            final int level = EnchantmentHelper.getEnchantmentLevel(this, item);
            final LivingEntity user = event.getEntityLiving();
            
            if (level > 0 && Math.random() < 0.15f * level) {
                
                final ServerPlayerEntity damagerEntity = event.getEntityLiving() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getEntityLiving() : null;
                item.attemptDamageItem(1, event.getEntityLiving().world.getRandom(), damagerEntity);
                user.world.playSound(null, user.getPosX(), user.getPosY() + 1, user.getPosZ(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
}