package dev.revsies.hotbarrepeatfix.hotbar.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(KeyMapping.class)
public interface KeyMappingAccessor {

    @Accessor("clickCount")
    int hotbarrepeatfix$getClickCount();

    @Accessor("clickCount")
    void hotbarrepeatfix$setClickCount(int clickCount);

    @Accessor("key")
    InputConstants.Key hotbarrepeatfix$getKey();
}
