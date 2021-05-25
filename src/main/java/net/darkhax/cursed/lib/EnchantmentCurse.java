package net.darkhax.cursed.lib;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentCurse extends Enchantment {
    
    private final EquipmentSlotType[] slots;
    
    public EnchantmentCurse(EnchantmentType type, EquipmentSlotType... slots) {
        
        super(Rarity.VERY_RARE, type, slots);
        this.slots = slots;
    }
    
    public EquipmentSlotType[] getSlots () {
        
        return this.slots;
    }
    
    @Override
    public int getMinCost (int level) {
        
        return 25;
    }
    
    @Override
    public int getMaxCost (int level) {
        
        return 50;
    }
    
    @Override
    public int getMaxLevel () {
        
        return 1;
    }
    
    @Override
    public boolean isTreasureOnly () {
        
        return true;
    }
    
    @Override
    public boolean isCurse () {
        
        return true;
    }
    
    public boolean isValidSlot (EquipmentSlotType slot) {
        
        for (final EquipmentSlotType validSlot : this.slots) {
            
            if (validSlot == slot) {
                
                return true;
            }
        }
        
        return false;
    }
}