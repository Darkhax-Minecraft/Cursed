package net.darkhax.cursed.lib;

import net.darkhax.bookshelf.enchantment.EnchantmentTicking;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentTickingCurse extends EnchantmentTicking {
    
    public EnchantmentTickingCurse(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
        
        super(rarityIn, typeIn, slots);
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
}