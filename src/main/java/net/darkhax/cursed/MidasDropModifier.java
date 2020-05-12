package net.darkhax.cursed;

import com.google.gson.JsonObject;
import net.darkhax.cursed.enchantments.MidasCurseEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class MidasDropModifier extends LootModifier {

    MidasDropModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ItemStack tool = context.get(LootParameters.TOOL);
        if (tool != null && !tool.isEmpty()) {
            int midasLevel = EnchantmentHelper.getEnchantmentLevel(new MidasCurseEnchantment(), context.get(LootParameters.TOOL));
            if (midasLevel > 0) {
                return generatedLoot.stream().map(stack -> stack = new ItemStack(Items.GOLD_NUGGET)).collect(Collectors.toList());
            }
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<MidasDropModifier> {
        @Override
        public MidasDropModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
            return new MidasDropModifier(conditionsIn);
        }
    }

}
