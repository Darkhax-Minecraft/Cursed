package net.darkhax.cursed.enchantments;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EnchantmentEcho extends EnchantmentCurse {
    
    private final List<SoundEvent> sounds = new ArrayList<>();
    
    public EnchantmentEcho() {
        
        super(EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
        this.sounds.add(SoundEvents.ZOMBIE_AMBIENT);
        this.sounds.add(SoundEvents.SKELETON_SHOOT);
        this.sounds.add(SoundEvents.CREEPER_PRIMED);
        this.sounds.add(SoundEvents.PHANTOM_SWOOP);
        this.sounds.add(SoundEvents.ELDER_GUARDIAN_CURSE);
        this.sounds.add(SoundEvents.SPIDER_AMBIENT);
        this.sounds.add(SoundEvents.BLAZE_SHOOT);
        this.sounds.add(SoundEvents.TNT_PRIMED);
        this.sounds.add(SoundEvents.ENDERMAN_SCREAM);
        this.sounds.add(SoundEvents.GHAST_SCREAM);
        this.sounds.add(SoundEvents.SLIME_JUMP);
        
        MinecraftForge.EVENT_BUS.addListener(this::onUserTick);
    }
    
    private void onUserTick (LivingUpdateEvent event) {
        
        final LivingEntity user = event.getEntityLiving();
        
        if (user instanceof ServerPlayerEntity && !user.level.isClientSide && user.isAlive() && user.tickCount % 1200 == 0 && user.getRandom().nextDouble() < 0.20) {
            
            final int level = EnchantmentHelper.getEnchantmentLevel(this, user);

            if (level > 0) {
                
                final SoundEvent sound = this.sounds.get(user.level.random.nextInt(this.sounds.size()));
                
                if (sound != null) {
                    
                    CursedMod.playSound((ServerPlayerEntity) user, user.getX(), user.getY() + 1, user.getZ(), sound, SoundCategory.MASTER, 20f, 1f);
                }
            }
        }
    }
}