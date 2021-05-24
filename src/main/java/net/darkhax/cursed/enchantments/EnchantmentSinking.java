package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.lib.EnchantmentTickingCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.vector.Vector3d;

public class EnchantmentSinking extends EnchantmentTickingCurse {
    
    public EnchantmentSinking() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET);
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (level > 0 && user.isInWaterOrBubble()) {
            
            final Vector3d motion = user.getDeltaMovement();
            
            user.setDeltaMovement(motion.x(), Math.max(-0.09f, motion.y() - 0.1f), motion.z());
        }
    }
}