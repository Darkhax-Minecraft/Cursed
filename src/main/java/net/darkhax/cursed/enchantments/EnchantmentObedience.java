package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.cursed.lib.EnchantmentTickingCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class EnchantmentObedience extends EnchantmentTickingCurse {
    
    private static final String NBT_KEY = "ObedienceOwnerId";
    
    public EnchantmentObedience() {
        
        super(Rarity.VERY_RARE, EnchantmentType.VANISHABLE, EquipmentSlotType.values());
    }
    
    @Override
    public void onItemTick (LivingEntity user, int level, ItemStack item, EquipmentSlotType slot) {
        
        final CompoundNBT tag = item.getOrCreateTag();
        
        if (tag.hasUUID(NBT_KEY)) {
            
            final UUID ownerId = tag.getUUID(NBT_KEY);
            
            if (!user.getUUID().equals(ownerId)) {
                
                user.spawnAtLocation(item);
                user.setItemSlot(slot, ItemStack.EMPTY);
            }
        }
        
        else {
            
            tag.putUUID(NBT_KEY, user.getUUID());
        }
    }
}