package world.snows.forever.carbonic.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.machine.entity.ElectrolysisTankBlockEntity;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, Carbonic.MOD_ID);

    public static final RegistryObject<BlockEntityType<ElectrolysisTankBlockEntity>> ELECTROLYSIS_TANK_ENTITY =
            BLOCK_ENTITY_REGISTRY.register("electrolysis_tank_block_entity",
                    () -> BlockEntityType.Builder.of(ElectrolysisTankBlockEntity::new, BlockRegistry.ELECTROLYSIS_TANK.get())
                            .build(null));
}
