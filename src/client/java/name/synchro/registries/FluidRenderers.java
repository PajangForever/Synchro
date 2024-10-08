package name.synchro.registries;

import name.synchro.Synchro;
import name.synchro.fluidRender.DebugGasRenderHandler;
import name.synchro.fluidRender.TransparentGasRenderHandler;
import name.synchro.fluidRender.TranslucentGasRenderer;
import name.synchro.registrations.ModFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;

public final class FluidRenderers {
    public static void registerAll() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.WATER_VAPOR_GAS, new TranslucentGasRenderer(ModFluids.WATER_VAPOR_GAS));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.HOT_STEAM_GAS, new TransparentGasRenderHandler());
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.SULFURIC_GAS, new DebugGasRenderHandler(ModFluids.SULFURIC_GAS));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.CHLORIC_GAS, new TranslucentGasRenderer(ModFluids.CHLORIC_GAS));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STRONGLY_REDUCING_GAS, new TranslucentGasRenderer(ModFluids.STRONGLY_REDUCING_GAS));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STRONGLY_OXIDIZING_GAS, new TranslucentGasRenderer(ModFluids.STRONGLY_OXIDIZING_GAS));
        Synchro.LOGGER.debug("Registered mod fluid renderers for " + Synchro.MOD_ID);
    }
}
