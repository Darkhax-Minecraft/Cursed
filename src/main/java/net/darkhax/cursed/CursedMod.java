package net.darkhax.cursed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.cursed.enchantments.BlindnessCurseEnchantment;
import net.darkhax.cursed.enchantments.CurtailCurseEnchantment;
import net.darkhax.cursed.enchantments.EchoCurseEnchantment;
import net.darkhax.cursed.enchantments.EncumbranceCurseEnchantment;
import net.darkhax.cursed.enchantments.FadingCurseEnchantment;
import net.darkhax.cursed.enchantments.FragilityCurseEnchantment;
import net.darkhax.cursed.enchantments.IgnoranceCurse;
import net.darkhax.cursed.enchantments.InsomniaCureEnchantment;
import net.darkhax.cursed.enchantments.MisfortuneCurseEnchantment;
import net.darkhax.cursed.enchantments.ObedienceCurseEnchantment;
import net.darkhax.cursed.enchantments.RadianceCurseEnchantment;
import net.darkhax.cursed.enchantments.SilenceCurseEnchantment;
import net.darkhax.cursed.enchantments.SinkingCurseEnchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("cursed")
public class CursedMod {
    
    public static final EnchantmentType TOOL = EnchantmentType.create("CURSED_TOOL", item -> EnchantmentType.DIGGER.canEnchantItem(item) || EnchantmentType.WEAPON.canEnchantItem(item));
    
    private final Logger log = LogManager.getLogger("Cursed");
    private final RegistryHelper registry = new RegistryHelper("cursed", this.log, null);
    
    public CursedMod() {
        
        this.registry.registerEnchantment(new BlindnessCurseEnchantment(), "blindness");
        this.registry.registerEnchantment(new CurtailCurseEnchantment(), "curtail");
        this.registry.registerEnchantment(new EchoCurseEnchantment(), "echo");
        this.registry.registerEnchantment(new EncumbranceCurseEnchantment(), "encumbrance");
        this.registry.registerEnchantment(new FadingCurseEnchantment(), "fading");
        this.registry.registerEnchantment(new FragilityCurseEnchantment(), "fragility");
        this.registry.registerEnchantment(new IgnoranceCurse(), "ignorance");
        this.registry.registerEnchantment(new InsomniaCureEnchantment(), "insomnia");
        this.registry.registerEnchantment(new MisfortuneCurseEnchantment(), "misfortune");
        this.registry.registerEnchantment(new ObedienceCurseEnchantment(), "obedience");
        this.registry.registerEnchantment(new RadianceCurseEnchantment(), "radiance");
        this.registry.registerEnchantment(new SilenceCurseEnchantment(), "silence");
        this.registry.registerEnchantment(new SinkingCurseEnchantment(), "sinking");
        
        this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
    }
}