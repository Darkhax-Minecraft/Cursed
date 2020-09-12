package net.darkhax.cursed.enchantments;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.ForgeMod;

public class EnchantmentCurtail extends EnchantmentModifierCurse {
    
    private Map<Attribute, AttributeModifier> modifiers;
    
    public EnchantmentCurtail() {
        
        super(Rarity.VERY_RARE, EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
    }

    @Override
    public Map<Attribute, AttributeModifier> getModifiers (int level) {
        
        if (modifiers == null) {
            
            modifiers = new HashMap<>();
            modifiers.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("0b8f89c3-892f-4b71-bdca-58cc20ce8ee0"), "Curse of Curtail", -0.35d, Operation.MULTIPLY_TOTAL));
        }
        
        return modifiers;
    }
}