package net.darkhax.cursed.enchantments;

import net.darkhax.bookshelf.enchantment.EnchantmentTickingCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class RadianceCurseEnchantment extends EnchantmentTickingCurse {
    
    public RadianceCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_CHEST, EquipmentSlotType.CHEST);
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (level > 0) {
            
            user.addPotionEffect(new EffectInstance(Effects.GLOWING, 100, 0, false, false));
        }
    }
}