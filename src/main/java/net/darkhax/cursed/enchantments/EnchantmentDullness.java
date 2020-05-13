package net.darkhax.cursed.enchantments;

import net.darkhax.bookshelf.enchantment.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentDullness extends EnchantmentModifierCurse {
    
    public EnchantmentDullness() {
        
        super(Rarity.VERY_RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND);
    }
    
    @Override
    public float calcDamageByCreature (int level, CreatureAttribute creatureType) {
        
        return -(1.0F + Math.max(0, level - 1) * 0.5F);
    }
}