package net.darkhax.cursed.enchantments;

import net.darkhax.tempshelf.CurseEnchantmentTicking;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.Vec3d;

public class SinkingCurseEnchantment extends CurseEnchantmentTicking {
    
    public SinkingCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET);
        this.setRegistryName("cursed", "sinking");
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (level > 0 && user.isInWaterOrBubbleColumn()) {
            
            final Vec3d motion = user.getMotion();
            
            user.setMotion(motion.getX(), Math.max(-0.09f, motion.getY() - 0.1f), motion.getZ());
        }
    }
}
