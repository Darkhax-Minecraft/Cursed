package net.darkhax.tempshelf;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CurseEnchantmentModifier extends EnchantmentAttribute {
    
    public CurseEnchantmentModifier(EnchantmentType type, EquipmentSlotType... slots) {
        
        this(Rarity.VERY_RARE, type, slots);
    }
    
    public CurseEnchantmentModifier(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
        
        super(rarity, type, slots);
    }
    
    @Override
    public int getMinEnchantability (int level) {
        
        return 25;
    }
    
    @Override
    public int getMaxEnchantability (int level) {
        
        return 50;
    }
    
    @Override
    public int getMaxLevel () {
        
        return 1;
    }
    
    @Override
    public boolean isTreasureEnchantment () {
        
        return true;
    }
    
    @Override
    public boolean isCurse () {
        
        return true;
    }
}