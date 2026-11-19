package dev.revsies.hotbarrepeatfix.hotbar;

public final class RepeatGuard {

    private static boolean active;

    private RepeatGuard() {
    }

    public static void enter() {
        active = true;
    }

    public static void exit() {
        active = false;
    }

    public static boolean active() {
        return active;
    }
}
