package net.darkhax.cursed.enchantments;

import net.darkhax.tempshelf.CurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class InsomniaCureEnchantment extends CurseEnchantment {
    
    private static final ITextComponent INSOMNIA_MESSAGE = new TranslationTextComponent("status.cursed.insomnia");
    
    public InsomniaCureEnchantment() {
        
        super(EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
        this.setRegistryName("cursed", "insomnia");
        MinecraftForge.EVENT_BUS.addListener(this::playerSleepEvent);
    }
    
    private void playerSleepEvent (PlayerSleepInBedEvent event) {
        
        final ItemStack helmet = event.getPlayer().getItemStackFromSlot(EquipmentSlotType.HEAD);
        final int level = EnchantmentHelper.getEnchantmentLevel(this, helmet);
        
        if (level > 0 && event.getResultStatus() == null) {
            
            event.setResult(SleepResult.OTHER_PROBLEM);
            event.getPlayer().sendStatusMessage(INSOMNIA_MESSAGE, true);
        }
    }
}