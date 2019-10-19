package net.darkhax.tempshelf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EnchantmentTicking extends Enchantment {
    
    /**
     * Tracks all ticking enchantment types.
     */
    private static final List<EnchantmentTicking> tickingEnchantments = new ArrayList<>();
    
    /**
     * A copy of the slots, vanilla doesn't expose this normally.
     */
    private final EquipmentSlotType[] validSlots;
    
    public EnchantmentTicking(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
        
        super(rarity, type, slots);
        this.validSlots = slots;
        
        // If no ticking enchantments have been registered, register the event listener.
        if (tickingEnchantments.isEmpty()) {
            
            MinecraftForge.EVENT_BUS.addListener(EnchantmentTicking::handleTickingEnchantments);
        }
        
        // Track the enchantment.
        tickingEnchantments.add(this);
    }
    
    /**
     * Called every tick for living entities. Allows the enchantment to modify them. The level
     * will be 0 if they don't have the enchantment effect, allowing them to clean up any
     * effects left over on the entity.
     * 
     * @param user The entity using the enchantment.
     * @param level The combined level of the enchantment from all valid equipment slots.
     */
    public void onUserTick (LivingEntity user, int level) {
        
    }
    
    /**
     * 
     * @param user
     * @param level
     * @param item
     * @param slot
     */
    public void onItemTick (LivingEntity user, int level, ItemStack item, EquipmentSlotType slot) {
        
    }
    
    /**
     * Calculates the total enchantment level and handles ticking the ticking enchantments.
     * 
     * @param event LivingUpdateEvent passed in by Forge.
     */
    private static void handleTickingEnchantments (LivingUpdateEvent event) {
        
        // Iterate all the constructed ticking enchantments
        for (final EnchantmentTicking tickingEnch : tickingEnchantments) {
            
            // Add up the level of all items with this effect.
            int totalLevel = 0;
            
            // Check all equipment to count up their levels.
            for (final EquipmentSlotType validSlot : tickingEnch.validSlots) {
                
                // Get the item and add to the total level
                final ItemStack stack = event.getEntityLiving().getItemStackFromSlot(validSlot);
                final int level = EnchantmentHelper.getEnchantmentLevel(tickingEnch, stack);
                
                if (level > 0) {
                    
                    totalLevel += level;
                    tickingEnch.onItemTick(event.getEntityLiving(), level, stack, validSlot);
                }
            }
            
            // Tick the enchantment with the user and their total level.
            tickingEnch.onUserTick(event.getEntityLiving(), totalLevel);
        }
    }
}