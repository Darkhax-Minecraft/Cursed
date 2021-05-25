package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public class EnchantmentEncumbrance extends EnchantmentModifierCurse {
    
    private static final AttributeModifier modifier = new AttributeModifier(UUID.fromString("ddc9c1e8-936c-452e-a93f-3f65787dfa94"), "Curse of Encumbrance", -0.35d, Operation.MULTIPLY_TOTAL);
    
    public EnchantmentEncumbrance() {
        
        super(EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
    }
    
    @Override
    public void applyModifiers (int level, ItemAttributeModifierEvent event) {
        
        if (level > 0) {
            
            event.addModifier(Attributes.ATTACK_SPEED, modifier);
        }
    }
}