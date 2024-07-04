package name.synchro.mixin;

import name.synchro.mixinHelper.FocusingProvider;
import name.synchro.util.dataDriven.ModDataManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements FocusingProvider , ModDataManager.Provider {
    @Unique
    private Entity focusedEntity;
    @Unique
    private HitResult focusingResult;
    @Unique @Final
    private final ModDataManager modDataManager = new ModDataManager.ForClient();

    @Override
    public Entity synchro$getFocusedEntity() {
        return this.focusedEntity;
    }

    @Override
    public void synchro$setFocusedEntity(Entity entity) {
        this.focusedEntity = entity;
    }

    @Override
    public HitResult synchro$getFocusingResult() {
        return focusingResult;
    }

    @Override
    public void synchro$setFocusingResult(HitResult hitResult) {
        this.focusingResult = hitResult;
    }

    @Override
    public ModDataManager synchro$getModDataManager() {
        return this.modDataManager;
    }
}
