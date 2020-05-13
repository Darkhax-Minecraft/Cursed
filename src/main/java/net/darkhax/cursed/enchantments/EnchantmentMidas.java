package net.darkhax.cursed.enchantments;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.darkhax.bookshelf.enchantment.EnchantmentModifierCurse;
import net.darkhax.cursed.CursedMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
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
    }
}