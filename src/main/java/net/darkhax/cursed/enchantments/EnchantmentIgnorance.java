package net.darkhax.cursed.enchantments;

import net.darkhax.bookshelf.util.MathsUtils;
import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class EnchantmentIgnorance extends EnchantmentCurse {
    
    public EnchantmentIgnorance() {
        
        super(EnchantmentType.DIGGER, EquipmentSlotType.MAINHAND);
        
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
    }
    
    private void onBlockBreak (BlockEvent.BreakEvent event) {
        
        if (!event.getWorld().isClientSide()) {
            
            final ItemStack item = event.getPlayer().getMainHandItem();
            final int level = EnchantmentHelper.getItemEnchantmentLevel(this, item);
            
            if (level > 0 && !item.getItem().canHarvestBlock(item, event.getState())) {
                
                final ServerPlayerEntity damagerEntity = event.getPlayer() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getPlayer() : null;
                item.hurt(MathsUtils.nextIntInclusive(event.getWorld().getRandom(), 10, 20) * level, event.getWorld().getRandom(), damagerEntity);
                
                final PlayerEntity user = event.getPlayer();
                CursedMod.playSound(user, user.getX(), user.getY() + 1, user.getZ(), SoundEvents.SHIELD_BREAK, SoundCategory.MASTER, 20f, 1f);
            }
        }
    }
}