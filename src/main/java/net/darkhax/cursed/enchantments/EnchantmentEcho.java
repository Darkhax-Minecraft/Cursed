package net.darkhax.cursed.enchantments;

import java.util.ArrayList;
import java.util.List;

import net.darkhax.cursed.lib.EnchantmentTickingCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class EnchantmentEcho extends EnchantmentTickingCurse {
    
    private final List<SoundEvent> sounds = new ArrayList<>();
    
    public EnchantmentEcho() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
        this.sounds.add(SoundEvents.ENTITY_ZOMBIE_AMBIENT);
        this.sounds.add(SoundEvents.ENTITY_SKELETON_SHOOT);
        this.sounds.add(SoundEvents.ENTITY_CREEPER_PRIMED);
        this.sounds.add(SoundEvents.ENTITY_PHANTOM_SWOOP);
        this.sounds.add(SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE);
        this.sounds.add(SoundEvents.ENTITY_SPIDER_AMBIENT);
        this.sounds.add(SoundEvents.ENTITY_BLAZE_SHOOT);
        this.sounds.add(SoundEvents.ENTITY_TNT_PRIMED);
        this.sounds.add(SoundEvents.ENTITY_ENDERMAN_SCREAM);
        this.sounds.add(SoundEvents.ENTITY_GHAST_SCREAM);
        this.sounds.add(SoundEvents.ENTITY_SLIME_JUMP);
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (!user.world.isRemote && level > 0 && Math.random() < 0.00233 * level) {
            
            final SoundEvent sound = this.sounds.get(user.world.rand.nextInt(this.sounds.size()));
            
            if (sound != null) {
                
                user.world.playSound(null, user.getPosX(), user.getPosY() + 1, user.getPosZ(), sound, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
}