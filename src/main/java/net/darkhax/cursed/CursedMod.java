package net.darkhax.cursed;

import net.darkhax.cursed.enchantments.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.rmi.registry.Registry;

@Mod("cursed")
public class CursedMod {
    
    public static final EnchantmentType TOOL = EnchantmentType.create("CURSED_TOOL", item -> EnchantmentType.DIGGER.canEnchantItem(item) || EnchantmentType.WEAPON.canEnchantItem(item));
    
    public CursedMod() {
        
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Enchantment.class, this::registerEnchantments);
    }
    
    private void registerEnchantments (Register<Enchantment> event) {
        
        final IForgeRegistry<Enchantment> registry = event.getRegistry();
        registry.register(new BlindnessCurseEnchantment());
        registry.register(new CurtailCurseEnchantment());
        registry.register(new EchoCurseEnchantment());
        registry.register(new EncumbranceCurseEnchantment());
        registry.register(new FadingCurseEnchantment());
        registry.register(new FragilityCurseEnchantment());
        registry.register(new IgnoranceCurse());
        registry.register(new InsomniaCureEnchantment());
        registry.register(new MidasCurseEnchantment());
        registry.register(new MisfortuneCurseEnchantment());
        registry.register(new ObedienceCurseEnchantment());
        registry.register(new RadianceCurseEnchantment());
        registry.register(new SilenceCurseEnchantment());
        registry.register(new SinkingCurseEnchantment());
    }

    private void registerModifierSerializiers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().register(new MidasDropModifier.Serializer().setRegistryName(new ResourceLocation("cursed", "midas_modifier")));
    }
}