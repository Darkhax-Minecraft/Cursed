package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.bookshelf.enchantment.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;

public class EncumbranceCurseEnchantment extends EnchantmentModifierCurse {
    
    public EncumbranceCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
        this.addAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("ddc9c1e8-936c-452e-a93f-3f65787dfa94"), "Curse of Encumbrance", -0.35d, Operation.MULTIPLY_TOTAL));
    }
}