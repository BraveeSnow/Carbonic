package world.snows.forever.carbonic.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ChemicalItem extends Item {
    private final String formula;

    public ChemicalItem(String formula, Item.Properties properties) {
        super(properties);
        this.formula = formula;
    }

    public ChemicalItem(String formula) {
        this(formula, new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flags) {
        components.add(Component.literal(ChatFormatting.DARK_GREEN + this.formula));
        super.appendHoverText(stack, level, components, flags);
    }
}
