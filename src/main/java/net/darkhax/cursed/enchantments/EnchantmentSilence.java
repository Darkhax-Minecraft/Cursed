package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

public class EnchantmentSilence extends EnchantmentCurse {
    
    public EnchantmentSilence() {
        
        super(EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET);
        MinecraftForge.EVENT_BUS.addListener(this::onEntitySound);
    }
    
    private void onEntitySound (PlaySoundAtEntityEvent event) {
        
        if (event.getEntity() instanceof LivingEntity && EnchantmentHelper.getItemEnchantmentLevel(this, ((LivingEntity) event.getEntity()).getItemBySlot(EquipmentSlotType.FEET)) > 0) {
            
            event.setCanceled(true);
        }
    }
}