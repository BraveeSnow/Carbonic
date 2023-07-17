package world.snows.forever.carbonic.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.machine.ElectrolysisTankBlock;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, Carbonic.MOD_ID);

    // Ores

    public static final RegistryObject<Block> HALITE_ORE = createBlockItem("halite_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)
            ));

    public static final RegistryObject<Block> ELECTROLYSIS_TANK = createBlockItem("electrolysis_tank",
            () -> new ElectrolysisTankBlock(BlockBehaviour.Properties.copy(Blocks.STONE).dynamicShape().noOcclusion()));

    public static RegistryObject<Block> createBlockItem(String name, Supplier<Block> supplier) {
        RegistryObject<Block> registeredBlock = BLOCK_REGISTRY.register(name, supplier);
        ItemRegistry.ITEM_REGISTRY.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
}
