package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.bookshelf.enchantment.EnchantmentModifierCurse;
import net.darkhax.cursed.CursedMod;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;

public class MisfortuneCurseEnchantment extends EnchantmentModifierCurse {
    
    private final AttributeModifier modifier = new AttributeModifier(UUID.fromString("b8ebd2cb-02d2-4c78-884d-12ee6d863e5e"), "Curse of Misfortune", 5f, Operation.ADDITION);
    
    public MisfortuneCurseEnchantment() {
        
        super(CursedMod.TOOL, EquipmentSlotType.MAINHAND);
        this.addAttributeModifier(SharedMonsterAttributes.LUCK, this.modifier);
    }
}