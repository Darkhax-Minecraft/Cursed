package net.darkhax.cursed.enchantments;

import net.darkhax.cursed.lib.EnchantmentCurse;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;

public class EnchantmentFading extends EnchantmentCurse {
    
    public EnchantmentFading() {
        
        super(EnchantmentType.VANISHABLE, EquipmentSlotType.values());
        MinecraftForge.EVENT_BUS.addListener(this::onEntityTick);
    }
    
    public void onEntityTick (TickEvent.WorldTickEvent event) {
        
        if (event.phase == Phase.START && event.world instanceof ServerWorld) {
            
            final ServerWorld world = (ServerWorld) event.world;
            
            for (final Entity entity : world.getEntities(EntityType.ITEM, e -> true)) {
                
                if (entity instanceof ItemEntity) {
                    
                    final ItemEntity itemEntity = (ItemEntity) entity;
                    
                    final int fadingLevel = EnchantmentHelper.getItemEnchantmentLevel(this, itemEntity.getItem());
                    
                    if (fadingLevel > 0) {
                        
                        itemEntity.lifespan -= 5;
                    }
                }
            }
        }
    }
}