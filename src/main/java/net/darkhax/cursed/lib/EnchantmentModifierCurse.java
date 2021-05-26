package net.darkhax.cursed.lib;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public abstract class EnchantmentModifierCurse extends EnchantmentCurse {
    
    public EnchantmentModifierCurse(EnchantmentType type, EquipmentSlotType... slots) {
        
        super(type, slots);
        MinecraftForge.EVENT_BUS.addListener(this::checkModifiers);
    }
    
    private void checkModifiers (ItemAttributeModifierEvent event) {
        
        if (event.getSlotType() == MobEntity.getEquipmentSlotForItem(event.getItemStack()) && this.isValidSlot(event.getSlotType()) && event.getItemStack().hasTag()) {
            
            final int level = EnchantmentHelper.getItemEnchantmentLevel(this, event.getItemStack());
            this.applyModifiers(level, event);
        }
    }
    
    public abstract void applyModifiers (int level, ItemAttributeModifierEvent event);
}