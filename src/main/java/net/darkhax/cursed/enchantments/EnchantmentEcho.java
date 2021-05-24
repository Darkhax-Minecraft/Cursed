package net.darkhax.cursed.enchantments;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentTickingCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class EnchantmentEcho extends EnchantmentTickingCurse {
    
    private final List<SoundEvent> sounds = new ArrayList<>();
    
    public EnchantmentEcho() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
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
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (user instanceof ServerPlayerEntity && level > 0 && Math.random() < 0.00233 * level) {
            
            final SoundEvent sound = this.sounds.get(user.level.random.nextInt(this.sounds.size()));
            
            if (sound != null) {
                
                CursedMod.playSound((ServerPlayerEntity) user, user.getX(), user.getY() + 1, user.getZ(), sound, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
}