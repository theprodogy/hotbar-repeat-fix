package dev.revsies.hotbarrepeatfix.hotbar;

import com.mojang.blaze3d.platform.InputConstants;
import dev.revsies.hotbarrepeatfix.hotbar.mixin.KeyMappingAccessor;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public final class HotbarKeys {

    private HotbarKeys() {
    }

    public static KeyMapping[] mappings() {
        return Minecraft.getInstance().options.keyHotbarSlots;
    }

    public static boolean isBound(KeyMapping mapping, InputConstants.Key key) {
        return ((KeyMappingAccessor) mapping).hotbarrepeatfix$getKey().equals(key);
    }
}
