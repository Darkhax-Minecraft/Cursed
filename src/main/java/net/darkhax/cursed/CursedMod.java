package net.darkhax.cursed;

import net.darkhax.cursed.enchantments.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

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
        this.registry.registerEnchantment(new IgnoranceCurseEnchantment(), "ignorance");
        this.registry.registerEnchantment(new InsomniaCurseEnchantment(), "insomnia");
        this.registry.registerEnchantment(new MidasCurseEnchantment(), "midas");
        this.registry.registerEnchantment(new MisfortuneCurseEnchantment(), "misfortune");
        this.registry.registerEnchantment(new ObedienceCurseEnchantment(), "obedience");
        this.registry.registerEnchantment(new RadianceCurseEnchantment(), "radiance");
        this.registry.registerEnchantment(new SilenceCurseEnchantment(), "silence");
        this.registry.registerEnchantment(new SinkingCurseEnchantment(), "sinking");
        
        this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void registerModifierSerializiers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new MidasDropModifier.Serializer().setRegistryName(new ResourceLocation("cursed", "midas_modifier")));
    }
}