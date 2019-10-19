package net.darkhax.cursed.enchantments;

import net.darkhax.tempshelf.CurseEnchantmentTicking;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BlindnessCurseEnchantment extends CurseEnchantmentTicking {
    
    public BlindnessCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
        this.setRegistryName("cursed", "blindness");
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (level > 0) {
            
            user.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 0, false, false));
        }
    }
}