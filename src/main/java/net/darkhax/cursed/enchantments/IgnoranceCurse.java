package net.darkhax.cursed.enchantments;

import net.darkhax.bookshelf.enchantment.EnchantmentCurse;
import net.darkhax.bookshelf.util.MathsUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class IgnoranceCurse extends EnchantmentCurse {
    
    public IgnoranceCurse() {
        
        super(EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
        
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
    }
    
    private void onBlockBreak (BlockEvent.BreakEvent event) {
        
        if (!event.getWorld().isRemote()) {
            
            final ItemStack item = event.getPlayer().getHeldItemMainhand();
            final int level = EnchantmentHelper.getEnchantmentLevel(this, item);
            
            if (level > 0 && !item.getItem().canHarvestBlock(item, event.getState())) {
                
                final ServerPlayerEntity damagerEntity = event.getPlayer() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getPlayer() : null;
                item.attemptDamageItem(MathsUtils.nextIntInclusive(event.getWorld().getRandom(), 10, 20) * level, event.getWorld().getRandom(), damagerEntity);
                
                final LivingEntity user = event.getPlayer();
                user.world.playSound(null, user.getPosX(), user.getPosY() + 1, user.getPosZ(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
}