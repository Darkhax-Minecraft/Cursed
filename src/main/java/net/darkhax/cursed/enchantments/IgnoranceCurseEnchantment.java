package net.darkhax.cursed.enchantments;

import net.darkhax.tempshelf.CurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class IgnoranceCurse extends CurseEnchantment {
    
    public IgnoranceCurse() {
        
        super(EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
        this.setRegistryName("cursed", "ignorance");
        
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
    }
    
    private void onBlockBreak (BlockEvent.BreakEvent event) {
        
        if (!event.getWorld().isRemote()) {
            
            final ItemStack item = event.getPlayer().getHeldItemMainhand();
            
            if (!ForgeHooks.canToolHarvestBlock(event.getWorld(), event.getPos(), item)) {
                
                final int level = EnchantmentHelper.getEnchantmentLevel(this, item);
                
                if (level > 0) {
                    
                    final ServerPlayerEntity damagerEntity = event.getPlayer() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getPlayer() : null;
                    item.attemptDamageItem(15 * level, event.getWorld().getRandom(), damagerEntity);
                    
                    final LivingEntity user = event.getPlayer();
                    user.world.playSound(null, user.posX, user.posY + 1, user.posZ, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
                }
            }
        }
    }
}