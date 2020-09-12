package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class EnchantmentRuin extends EnchantmentCurse {
    
    public EnchantmentRuin() {
        
        super(EnchantmentType.BREAKABLE, EquipmentSlotType.values());
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onAnvilUpdate);
    }
    
    private void onAnvilUpdate (AnvilUpdateEvent event) {
        
        if (EnchantmentHelper.getEnchantmentLevel(this, event.getLeft()) > 0 || EnchantmentHelper.getEnchantmentLevel(this, event.getRight()) > 0) {
            
            event.setOutput(ItemStack.EMPTY);
            event.setCanceled(true);
        }
    }
}