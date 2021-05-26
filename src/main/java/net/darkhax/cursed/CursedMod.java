package net.darkhax.cursed;

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
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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
    
    private final Logger log = LogManager.getLogger("Cursed");
    private final RegistryHelper registry = new RegistryHelper("cursed", this.log);
    
    public CursedMod() {
        
        this.registry.enchantments.register(new EnchantingBlindness(), "blindness");
        this.registry.enchantments.register(new EnchantmentCurtail(), "curtail");
        this.registry.enchantments.register(new EnchantmentEcho(), "echo");
        this.registry.enchantments.register(new EnchantmentEncumbrance(), "encumbrance");
        this.registry.enchantments.register(new EnchantmentFading(), "fading");
        this.registry.enchantments.register(new EnchantmentFragility(), "fragility");
        this.registry.enchantments.register(new EnchantmentIgnorance(), "ignorance");
        this.registry.enchantments.register(new EnchantmentInsomnia(), "insomnia");
        this.registry.enchantments.register(new EnchantmentMidas(), "midas");
        this.registry.enchantments.register(new EnchantmentMisfortune(), "misfortune");
        this.registry.enchantments.register(new EnchantmentObedience(), "obedience");
        this.registry.enchantments.register(new EnchantmentRadiance(), "radiance");
        this.registry.enchantments.register(new EnchantmentSilence(), "silence");
        this.registry.enchantments.register(new EnchantmentSinking(), "sinking");
        this.registry.enchantments.register(new EnchantmentRuin(), "ruin");
        this.registry.enchantments.register(new EnchantmentDullness(), "dullness");
        this.registry.enchantments.register(new EnchantmentDelicacy(), "delicacy");
        
        this.registry.lootModifiers.register(EnchantmentMidas.SERIALIZER, "midas_modifier");
        
        this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
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