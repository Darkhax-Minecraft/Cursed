package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public class EnchantmentMisfortune extends EnchantmentModifierCurse {
    
    private final AttributeModifier modifier = new AttributeModifier(UUID.fromString("b8ebd2cb-02d2-4c78-884d-12ee6d863e5e"), "Curse of Misfortune", 5f, Operation.ADDITION);
    
    public EnchantmentMisfortune() {
        
        super(CursedMod.TOOL, EquipmentSlotType.MAINHAND);
    }
    
    @Override
    public void applyModifiers (int level, ItemAttributeModifierEvent event) {
        
        if (level > 1) {
            
            event.addModifier(Attributes.LUCK, this.modifier);
        }
    }
}