package net.darkhax.cursed.enchantments;

import java.util.HashMap;
import java.util.Map;

import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public class EnchantmentDelicacy extends EnchantmentModifierCurse {
    
    private final Map<EquipmentSlotType, Map<Integer, AttributeModifier>> modifiers = new HashMap<>();
    
    public EnchantmentDelicacy() {
        
        super(EnchantmentType.ARMOR, CursedMod.ARMOR_SLOTS);
        
        for (final EquipmentSlotType slotType : EquipmentSlotType.values()) {
            
            this.modifiers.put(slotType, new HashMap<>());
        }
    }
    
    @Override
    public void applyModifiers (int level, ItemAttributeModifierEvent event) {
        
        if (level > 0 && this.modifiers.containsKey(event.getSlotType())) {
            
            final AttributeModifier modifier = this.modifiers.get(event.getSlotType()).computeIfAbsent(level, l -> new AttributeModifier("Curse of Delicacy LV" + l, -1d * l, Operation.ADDITION));
            event.addModifier(Attributes.ARMOR, modifier);
        }
    }
}