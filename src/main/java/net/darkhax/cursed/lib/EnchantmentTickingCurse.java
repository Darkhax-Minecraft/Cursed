package net.darkhax.cursed.lib;

import net.darkhax.bookshelf.enchantment.EnchantmentTicking;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentTickingCurse extends EnchantmentTicking {
    
    public EnchantmentTickingCurse(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType... slots) {
        
        super(rarityIn, typeIn, slots);
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