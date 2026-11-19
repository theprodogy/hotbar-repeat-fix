package dev.revsies.hotbarrepeatfix.hotbar;

import com.mojang.blaze3d.platform.InputConstants;
import dev.revsies.hotbarrepeatfix.hotbar.mixin.KeyMappingAccessor;
import net.minecraft.client.KeyMapping;

public final class PendingClicks {

    private PendingClicks() {
    }

    public static void apply(InputConstants.Key key) {
        KeyMapping[] mappings = HotbarKeys.mappings();
        boolean repeat = RepeatGuard.active();
        for (KeyMapping mapping : mappings) {
            if (!HotbarKeys.isBound(mapping, key)) {
                continue;
            }
            KeyMappingAccessor bound = (KeyMappingAccessor) mapping;
            if (repeat) {
                bound.hotbarrepeatfix$setClickCount(Math.max(0, bound.hotbarrepeatfix$getClickCount() - 1));
            } else {
                for (KeyMapping other : mappings) {
                    ((KeyMappingAccessor) other).hotbarrepeatfix$setClickCount(other.equals(mapping) ? 1 : 0);
                }
            }
        }
    }
}
