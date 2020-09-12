package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentEncumbrance extends EnchantmentModifierCurse {
    
    public EnchantmentEncumbrance() {
        
        super(Rarity.VERY_RARE, EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("ddc9c1e8-936c-452e-a93f-3f65787dfa94"), "Curse of Encumbrance", -0.35d, Operation.MULTIPLY_TOTAL));
    }
}