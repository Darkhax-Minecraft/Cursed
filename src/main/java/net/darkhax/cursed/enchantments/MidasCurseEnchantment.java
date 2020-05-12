package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.CursedMod;
import net.darkhax.tempshelf.CurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MidasCurseEnchantment extends CurseEnchantment {
    
    public MidasCurseEnchantment() {
        
        super(CursedMod.TOOL, EquipmentSlotType.MAINHAND);
        this.setRegistryName("cursed", "midas");
        
        MinecraftForge.EVENT_BUS.addListener(this::onLivingDrops);
    }
    
    private void onLivingDrops (LivingDropsEvent event) {
        
        final Entity looter = event.getSource().getTrueSource();
        
        if (looter instanceof LivingEntity) {
            
            final ItemStack heldItem = ((LivingEntity) looter).getHeldItemMainhand();
            final int level = EnchantmentHelper.getEnchantmentLevel(this, heldItem);
            
            if (level > 0) {
                
                for (final ItemEntity item : event.getDrops()) {
                    
                    item.setItem(new ItemStack(Items.GOLD_NUGGET, 1));
                }
            }
        }
    }
}