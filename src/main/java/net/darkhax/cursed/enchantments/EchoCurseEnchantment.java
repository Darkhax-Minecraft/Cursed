package net.darkhax.cursed.enchantments;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.darkhax.tempshelf.CurseEnchantmentTicking;
import net.darkhax.tempshelf.Utils;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EchoCurseEnchantment extends CurseEnchantmentTicking {
    
    private final Method playSound = ObfuscationReflectionHelper.findMethod(MobEntity.class, "func_184639_G");
    
    public EchoCurseEnchantment() {
        
        super(Rarity.VERY_RARE, EnchantmentType.ARMOR_HEAD, EquipmentSlotType.HEAD);
        this.setRegistryName("cursed", "echo");
    }
    
    @Override
    public void onUserTick (LivingEntity user, int level) {
        
        if (!user.world.isRemote && level > 0 && Math.random() < 0.00233 * level) {
            
            final LivingEntity randomMob = this.getMobForChunk(user.world, user.getPosition());
            
            if (randomMob instanceof MobEntity) {
                
                try {
                    
                    final SoundEvent sound = (SoundEvent) this.playSound.invoke(randomMob);
                    
                    if (sound != null) {
                        
                        user.world.playSound(null, Utils.getX(user), Utils.getY(user) + 1, Utils.getZ(user), sound, SoundCategory.MASTER, 20f, 1f);
                    }
                }
                
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    
                }
            }
        }
    }
    
    private LivingEntity getMobForChunk (World world, BlockPos pos) {
        
    	// world.getBiomeManager().getBiome(pos);
        final Biome biome = world.func_225523_d_().func_226836_a_(pos);
        
        if (biome != null) {
            
            final List<SpawnListEntry> validMobs = biome.getSpawns(EntityClassification.MONSTER);
            
            if (validMobs != null && !validMobs.isEmpty()) {
                
                final SpawnListEntry entry = validMobs.get(world.getRandom().nextInt(validMobs.size()));
                
                if (entry != null) {
                    
                    try {
                        
                        final Entity entity = entry.entityType.create(world);
                        
                        if (entity instanceof LivingEntity) {
                            
                            return (LivingEntity) entity;
                        }
                    }
                    
                    catch (final Exception e) {
                        
                    }
                }
            }
        }
        
        return null;
    }
}