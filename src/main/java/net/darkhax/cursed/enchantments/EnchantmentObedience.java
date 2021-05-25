package net.darkhax.cursed.enchantments;

import java.util.UUID;

import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EnchantmentObedience extends EnchantmentCurse {
    
    private static final String NBT_KEY = "ObedienceOwnerId";
    
    public EnchantmentObedience() {
        
        super(EnchantmentType.VANISHABLE, EquipmentSlotType.values());
        MinecraftForge.EVENT_BUS.addListener(this::onUserTick);
    }
    
    private void onUserTick (LivingUpdateEvent event) {
        
        final LivingEntity user = event.getEntityLiving();
        
        if (user != null && !user.level.isClientSide && user.isAlive() && user.tickCount % 10 == 0) {
            
            for (EquipmentSlotType slot : this.getSlots()) {
                
                final ItemStack stack = event.getEntityLiving().getItemBySlot(slot);
                
                if (!stack.isEmpty() && stack.hasTag()) {
                    
                    final int level = EnchantmentHelper.getItemEnchantmentLevel(this, stack);
                    
                    if (level > 0) {
                        
                        final CompoundNBT tag = stack.getOrCreateTag();
                        
                        if (tag.hasUUID(NBT_KEY)) {
                            
                            final UUID ownerId = tag.getUUID(NBT_KEY);
                            
                            if (!user.getUUID().equals(ownerId)) {
                                
                                user.spawnAtLocation(stack);
                                user.setItemSlot(slot, ItemStack.EMPTY);
                            }
                        }
                        
                        else {
                            
                            tag.putUUID(NBT_KEY, user.getUUID());
                        }
                    }
                }
            }
        }
    }
}