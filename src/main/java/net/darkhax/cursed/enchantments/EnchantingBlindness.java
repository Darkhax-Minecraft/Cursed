package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EnchantingBlindness extends EnchantmentCurse {
    
    public EnchantingBlindness() {
        
        super(EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
        MinecraftForge.EVENT_BUS.addListener(this::onUserTick);
    }
    
    private void onUserTick (LivingUpdateEvent event) {
        
        final LivingEntity user = event.getEntityLiving();
        
        if (user != null && !user.level.isClientSide && user.isAlive() && user.tickCount % 10 == 0) {
            
            final int level = EnchantmentHelper.getEnchantmentLevel(this, user);
            
            if (level > 0) {
                
                user.addEffect(new EffectInstance(Effects.BLINDNESS, 50, level, false, false));
            }
        }
    }
}