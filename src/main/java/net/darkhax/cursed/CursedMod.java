package net.darkhax.cursed;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.cursed.enchantments.EnchantingBlindness;
import net.darkhax.cursed.enchantments.EnchantmentCurtail;
import net.darkhax.cursed.enchantments.EnchantmentDelicacy;
import net.darkhax.cursed.enchantments.EnchantmentDullness;
import net.darkhax.cursed.enchantments.EnchantmentEcho;
import net.darkhax.cursed.enchantments.EnchantmentEncumbrance;
import net.darkhax.cursed.enchantments.EnchantmentFading;
import net.darkhax.cursed.enchantments.EnchantmentFragility;
import net.darkhax.cursed.enchantments.EnchantmentIgnorance;
import net.darkhax.cursed.enchantments.EnchantmentInsomnia;
import net.darkhax.cursed.enchantments.EnchantmentMidas;
import net.darkhax.cursed.enchantments.EnchantmentMisfortune;
import net.darkhax.cursed.enchantments.EnchantmentObedience;
import net.darkhax.cursed.enchantments.EnchantmentRadiance;
import net.darkhax.cursed.enchantments.EnchantmentRuin;
import net.darkhax.cursed.enchantments.EnchantmentSilence;
import net.darkhax.cursed.enchantments.EnchantmentSinking;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemGroup;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("cursed")
public class CursedMod {
    
    public static final EnchantmentType TOOL = EnchantmentType.create("CURSED_TOOL", item -> EnchantmentType.DIGGER.canEnchant(item) || EnchantmentType.WEAPON.canEnchant(item));
    public static final EquipmentSlotType[] ARMOR_SLOTS = { EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET };
    
    private static final Logger LOG = LogManager.getLogger("Cursed");
    private static final ItemGroup GROUP = new ItemGroupCursed();
    private static final RegistryHelper REGISTRY = new RegistryHelper("cursed", LOG).withItemGroup(GROUP);
    
    public CursedMod() {
        
        REGISTRY.enchantments.register(new EnchantingBlindness(), "blindness");
        REGISTRY.enchantments.register(new EnchantmentCurtail(), "curtail");
        REGISTRY.enchantments.register(new EnchantmentEcho(), "echo");
        REGISTRY.enchantments.register(new EnchantmentEncumbrance(), "encumbrance");
        REGISTRY.enchantments.register(new EnchantmentFading(), "fading");
        REGISTRY.enchantments.register(new EnchantmentFragility(), "fragility");
        REGISTRY.enchantments.register(new EnchantmentIgnorance(), "ignorance");
        REGISTRY.enchantments.register(new EnchantmentInsomnia(), "insomnia");
        REGISTRY.enchantments.register(new EnchantmentMidas(), "midas");
        REGISTRY.enchantments.register(new EnchantmentMisfortune(), "misfortune");
        REGISTRY.enchantments.register(new EnchantmentObedience(), "obedience");
        REGISTRY.enchantments.register(new EnchantmentRadiance(), "radiance");
        REGISTRY.enchantments.register(new EnchantmentSilence(), "silence");
        REGISTRY.enchantments.register(new EnchantmentSinking(), "sinking");
        REGISTRY.enchantments.register(new EnchantmentRuin(), "ruin");
        REGISTRY.enchantments.register(new EnchantmentDullness(), "dullness");
        REGISTRY.enchantments.register(new EnchantmentDelicacy(), "delicacy");
        
        REGISTRY.lootModifiers.register(EnchantmentMidas.SERIALIZER, "midas_modifier");
        
        REGISTRY.initialize(FMLJavaModLoadingContext.get().getModEventBus());
    }
    
    public static Collection<Enchantment> getEnchantments () {
        
        return REGISTRY.enchantments.getValues();
    }
    
    public static void playSound (PlayerEntity target, double posX, double posY, double posZ, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        
        if (target instanceof ServerPlayerEntity && !(target instanceof FakePlayer)) {
            
            final ServerPlayerEntity player = (ServerPlayerEntity) target;
            final PlaySoundAtEntityEvent event = ForgeEventFactory.onPlaySoundAtEntity(player, sound, category, volume, pitch);
            
            if (player.connection != null && !event.isCanceled() && event.getSound() != null) {
                
                player.connection.send(new SPlaySoundEffectPacket(event.getSound(), event.getCategory(), posX, posY, posZ, event.getVolume(), event.getPitch()));
            }
        }
    }
}