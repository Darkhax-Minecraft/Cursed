package net.darkhax.cursed.enchantments;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.darkhax.cursed.CursedMod;
import net.darkhax.cursed.lib.EnchantmentModifierCurse;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class EnchantmentMidas extends EnchantmentModifierCurse {
    
    public static final GlobalLootModifierSerializer<MidasModifier> SERIALIZER = new Serializer();
    
    public EnchantmentMidas() {
        
        super(CursedMod.TOOL, EquipmentSlotType.MAINHAND);
    }
    
    static class MidasModifier extends LootModifier {
        
        private MidasModifier(ILootCondition[] conditionsIn) {
            
            super(conditionsIn);
        }
        
        @Nonnull
        @Override
        protected List<ItemStack> doApply (List<ItemStack> generatedLoot, LootContext context) {
            
            return generatedLoot.stream().map(stack -> stack = new ItemStack(Items.GOLD_NUGGET)).collect(Collectors.toList());
        }
    }
    
    static class Serializer extends GlobalLootModifierSerializer<MidasModifier> {
        
        @Override
        public MidasModifier read (ResourceLocation name, JsonObject json, ILootCondition[] conditions) {
            
            return new MidasModifier(conditions);
        }
        
        @Override
        public JsonObject write (MidasModifier instance) {
            
            return new JsonObject();
        }
    }
}