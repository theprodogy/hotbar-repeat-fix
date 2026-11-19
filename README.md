# Hotbar Repeat Fix

Fabric client mod for Minecraft 1.21.11.

Holding a hotbar key makes the OS send key-repeat events. Vanilla counts each repeat as a new press, but only handles one per tick, so a backlog builds up and later slot switches flip back to the old slot. This mod ignores key-repeat for hotbar keys and keeps only the newest hotbar press pending, so every switch lands on the next tick.

## Install

Needs Fabric Loader 0.20.1+ and Java 21. Drop the jar into `mods/`. Fabric API is not required.

## Build

```
./gradlew build
```

The jar ends up in `build/libs`.

## License

MIT
