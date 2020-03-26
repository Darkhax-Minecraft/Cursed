package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.bookshelf.enchantment.EnchantmentTickingCurse;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ObedienceCurseEnchantment extends EnchantmentTickingCurse {
    
    private static final String NBT_KEY = "ObedienceOwnerId";
    
    public ObedienceCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ALL, EquipmentSlotType.values());
    }
    
    @Override
    public void onItemTick (LivingEntity user, int level, ItemStack item, EquipmentSlotType slot) {
        
        final CompoundNBT tag = item.getOrCreateTag();
        
        if (tag.hasUniqueId(NBT_KEY)) {
            
            final UUID ownerId = tag.getUniqueId(NBT_KEY);
            
            if (!user.getUniqueID().equals(ownerId)) {
                
                user.entityDropItem(item);
                user.setItemStackToSlot(slot, ItemStack.EMPTY);
            }
        }
        
        else {
            
            tag.putUniqueId(NBT_KEY, user.getUniqueID());
        }
    }
}