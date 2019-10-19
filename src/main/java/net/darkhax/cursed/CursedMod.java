package net.darkhax.cursed;

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
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

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
        // registry.register(new MidasCurseEnchantment());
        registry.register(new MisfortuneCurseEnchantment());
        registry.register(new ObedienceCurseEnchantment());
        registry.register(new RadianceCurseEnchantment());
        registry.register(new SilenceCurseEnchantment());
        registry.register(new SinkingCurseEnchantment());
    }
}