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
        
        if (level > 0 && user.isInWaterOrBubbleColumn()) {
            
            final Vector3d motion = user.getMotion();
            
            user.setMotion(motion.getX(), Math.max(-0.09f, motion.getY() - 0.1f), motion.getZ());
        }
    }
}