package net.darkhax.tempshelf;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentAttribute extends EnchantmentTicking {
    
    private final Map<IAttribute, AttributeModifier> modifiers = new HashMap<>();
    
    public EnchantmentAttribute(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
        
        super(rarity, type, slots);
    }
    
    public EnchantmentAttribute addAttributeModifier (IAttribute attribute, AttributeModifier modifier) {
        
        this.modifiers.put(attribute, modifier);
        return this;
    }
    
    public Map<IAttribute, AttributeModifier> getModifiers (int level) {
        
        return this.modifiers;
    }
    
    public void removeModifiers (LivingEntity living, int level) {
        
        final AbstractAttributeMap attributeMap = living.getAttributes();
        
        for (final Entry<IAttribute, AttributeModifier> entry : this.getModifiers(level).entrySet()) {
            
            final IAttributeInstance attribute = attributeMap.getAttributeInstance(entry.getKey());
            
            if (attribute != null) {
                
                attribute.removeModifier(entry.getValue());
            }
        }
        
    }
    
    public void applyModifiers (LivingEntity living, int level) {
        
        final AbstractAttributeMap attributeMap = living.getAttributes();
        
        for (final Entry<IAttribute, AttributeModifier> entry : this.getModifiers(level).entrySet()) {
            
            final IAttributeInstance attribute = attributeMap.getAttributeInstance(entry.getKey());
            
            final AttributeModifier attributemodifier = entry.getValue();
            
            if (attribute != null && !attribute.hasModifier(entry.getValue())) {
                
                attribute.applyModifier(attributemodifier);
            }
        }
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        this.removeModifiers(user, level);
        
        if (level > 0) {
            
            this.applyModifiers(user, level);
        }
    }
}