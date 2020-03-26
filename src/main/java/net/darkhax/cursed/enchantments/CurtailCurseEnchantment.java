package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.bookshelf.enchantment.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class CurtailCurseEnchantment extends EnchantmentModifierCurse {
    
    public CurtailCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
        this.addAttributeModifier(PlayerEntity.REACH_DISTANCE, new AttributeModifier(UUID.fromString("0b8f89c3-892f-4b71-bdca-58cc20ce8ee0"), "Curse of Curtail", -0.35d, Operation.MULTIPLY_TOTAL));
    }
}