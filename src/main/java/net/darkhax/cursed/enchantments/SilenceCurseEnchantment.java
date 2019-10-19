package net.darkhax.cursed.enchantments;

import net.darkhax.tempshelf.CurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;

public class SilenceCurseEnchantment extends CurseEnchantment {
    
    public SilenceCurseEnchantment() {
        
        super(EnchantmentType.ARMOR_FEET, EquipmentSlotType.FEET);
        this.setRegistryName("cursed", "silence");
        MinecraftForge.EVENT_BUS.addListener(this::onEntitySound);
    }
    
    private void onEntitySound (PlaySoundAtEntityEvent event) {
        
        if (event.getEntity() instanceof LivingEntity && EnchantmentHelper.getEnchantmentLevel(this, ((LivingEntity) event.getEntity()).getItemStackFromSlot(EquipmentSlotType.FEET)) > 0) {
            
            event.setCanceled(true);
        }
    }
}