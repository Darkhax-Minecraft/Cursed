package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EnchantmentSinking extends EnchantmentCurse {
    
    public EnchantmentSinking() {
        
        super(EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET);
        MinecraftForge.EVENT_BUS.addListener(this::onUserTick);
    }
    
    private void onUserTick (LivingUpdateEvent event) {
        
        final LivingEntity user = event.getEntityLiving();
        
        if (user != null && user.isAlive() && user.isInWaterOrBubble()) {
            
            final int level = EnchantmentHelper.getEnchantmentLevel(this, user);
            
            if (level > 0) {
                
                final Vector3d motion = user.getDeltaMovement();            
                user.setDeltaMovement(motion.x(), -0.05, motion.z());
            }
        }
    }
}