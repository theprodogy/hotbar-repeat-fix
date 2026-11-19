package dev.revsies.hotbarrepeatfix.hotbar.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import dev.revsies.hotbarrepeatfix.hotbar.PendingClicks;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin {

    @SuppressWarnings("UnusedVariable")
    @Inject(method = "click", at = @At("TAIL"))
    private static void hotbarrepeatfix$afterClick(InputConstants.Key key, CallbackInfo ci) {
        PendingClicks.apply(key);
    }
}
