package net.darkhax.cursed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.cursed.enchantments.EnchantingBlindness;
import net.darkhax.cursed.enchantments.EnchantmentCurtail;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("cursed")
public class CursedMod {
    
    public static final EnchantmentType TOOL = EnchantmentType.create("CURSED_TOOL", item -> EnchantmentType.DIGGER.canEnchantItem(item) || EnchantmentType.WEAPON.canEnchantItem(item));
    
    private final Logger log = LogManager.getLogger("Cursed");
    private final RegistryHelper registry = new RegistryHelper("cursed", this.log, null);
    
    public CursedMod() {
        
        this.registry.registerEnchantment(new EnchantingBlindness(), "blindness");
        this.registry.registerEnchantment(new EnchantmentCurtail(), "curtail");
        this.registry.registerEnchantment(new EnchantmentEcho(), "echo");
        this.registry.registerEnchantment(new EnchantmentEncumbrance(), "encumbrance");
        this.registry.registerEnchantment(new EnchantmentFading(), "fading");
        this.registry.registerEnchantment(new EnchantmentFragility(), "fragility");
        this.registry.registerEnchantment(new EnchantmentIgnorance(), "ignorance");
        this.registry.registerEnchantment(new EnchantmentInsomnia(), "insomnia");
        this.registry.registerEnchantment(new EnchantmentMidas(), "midas");
        this.registry.registerEnchantment(new EnchantmentMisfortune(), "misfortune");
        this.registry.registerEnchantment(new EnchantmentObedience(), "obedience");
        this.registry.registerEnchantment(new EnchantmentRadiance(), "radiance");
        this.registry.registerEnchantment(new EnchantmentSilence(), "silence");
        this.registry.registerEnchantment(new EnchantmentSinking(), "sinking");
        this.registry.registerEnchantment(new EnchantmentRuin(), "ruin");
        
        this.registry.registerGlobalModifier(EnchantmentMidas.SERIALIZER, "midas_modifier");
        
        this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
    }
}