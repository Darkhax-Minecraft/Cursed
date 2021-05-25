package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public class EnchantmentCurtail extends EnchantmentModifierCurse {
    
    private static final AttributeModifier modifier = new AttributeModifier(UUID.fromString("0b8f89c3-892f-4b71-bdca-58cc20ce8ee0"), "Curse of Curtail", -0.35d, Operation.MULTIPLY_TOTAL);
    
    public EnchantmentCurtail() {
        
        super(EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
    }
    
    @Override
    public void applyModifiers (int level, ItemAttributeModifierEvent event) {
        
        if (level > 0) {
            
            event.addModifier(ForgeMod.REACH_DISTANCE.get(), modifier);
        }
    }
}