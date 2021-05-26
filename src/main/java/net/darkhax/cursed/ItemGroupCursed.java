package net.darkhax.cursed;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class ItemGroupCursed extends ItemGroup {
    
    private ItemStack icon;
    
    public ItemGroupCursed() {
        
        super("cursed");
    }
    
    @Override
    public void fillItemList (NonNullList<ItemStack> items) {
        
        for (final Enchantment enchantment : CursedMod.getEnchantments()) {
            
            items.add(EnchantedBookItem.createForEnchantment(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
        }
        
        super.fillItemList(items);
    }
    
    @Override
    public ItemStack makeIcon () {
        
        if (this.icon == null || this.icon.isEmpty()) {
            
            this.icon = new ItemStack(Items.ENCHANTED_BOOK);
        }
        
        return this.icon;
    }
}