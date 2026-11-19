package dev.revsies.hotbarrepeatfix.hotbar.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.platform.InputConstants;
import dev.revsies.hotbarrepeatfix.hotbar.RepeatGuard;
import net.minecraft.client.KeyboardHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {

    @WrapOperation(
        method = "keyPress",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/KeyMapping;click(Lcom/mojang/blaze3d/platform/InputConstants$Key;)V")
    )
    private void hotbarrepeatfix$guardRepeat(
        InputConstants.Key key,
        Operation<Void> original,
        @Local(argsOnly = true) int action
    ) {
        if (action != InputConstants.REPEAT) {
            original.call(key);
            return;
        }
        RepeatGuard.enter();
        try {
            original.call(key);
        } finally {
            RepeatGuard.exit();
        }
    }
}
