# Hotbar Repeat Fix

[![Release](https://img.shields.io/github/v/release/theprodogy/hotbar-repeat-fix)](https://github.com/theprodogy/hotbar-repeat-fix/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/theprodogy/hotbar-repeat-fix/total)](https://github.com/theprodogy/hotbar-repeat-fix/releases)
[![Minecraft](https://img.shields.io/badge/minecraft-1.21.11%20%7C%2026.1%20to%2026.3-62b47a)](#supported-versions)
[![Fabric](https://img.shields.io/badge/loader-fabric-dbd0b4)](https://fabricmc.net/)
[![License](https://img.shields.io/github/license/theprodogy/hotbar-repeat-fix)](LICENSE)

A Fabric client mod that makes hotbar switches land on the next tick, every time, with no backlog and no flicking back to an old slot.

## Table of contents

- [Description](#description)
- [Features](#features)
- [Supported versions](#supported-versions)
- [Install](#install)
- [Usage](#usage)
- [How it works](#how-it-works)
- [Build](#build)
- [Contributing](#contributing)
- [Credits](#credits)
- [License](#license)

## Description

Hold a hotbar key (say `1`) for a moment and your OS starts sending key-repeat events, about 30 a second. Vanilla Minecraft counts every repeat as a new press of that slot, but it only handles one hotbar press per slot per tick (20 a second). The extra presses pile up. When you then press `2`, the game keeps working through the leftover `1` presses and your slot keeps jumping back to 1.

This mod is part of a PvP fix community that patches small vanilla input problems like this one, in the same spirit as shield fixes. It changes two things and nothing else:

- **Key-repeat is ignored for hotbar keys.** Holding a hotbar key counts as one press, however long you hold it.
- **The newest hotbar press wins.** A press of any hotbar key, from the keyboard or a mouse button bound to a slot, becomes the only pending switch and clears the rest. At most one switch is ever waiting, and it runs on the next tick.

Everything else stays vanilla. The scroll wheel, attacking, using items and the key-repeat of every other key (holding `Q` still drops items) are untouched.

## Features

- Switches to any slot in one tick, from the keyboard or a mouse button
- No input backlog, no switching back to a slot you already left
- Lunge and attribute swapping keep working: vanilla handles the hotbar before attacks in each tick, and the mod doesn't change that order
- Client only, no config, no Fabric API needed
- One jar per Minecraft version

## Supported versions

| Jar | Minecraft | Java |
|---|---|---|
| `hotbar-repeat-fix-1.0.0+mc1.21.11.jar` | 1.21.11 | 21 to 25 |
| `hotbar-repeat-fix-1.0.0+mc26.1.jar` | 26.1, 26.1.1, 26.1.2 | 25 |
| `hotbar-repeat-fix-1.0.0+mc26.2.jar` | 26.2 | 25 |
| `hotbar-repeat-fix-1.0.0+mc26.3.jar` | 26.3 | 25 |

> [!IMPORTANT]
> Minecraft 26.x itself requires Java 25, so those jars do too. Pick the jar that matches your Minecraft version, because a jar for another version won't load.

## Install

1. Install [Fabric Loader](https://fabricmc.net/use/) 0.20.1 or newer for your Minecraft version.
2. Download the jar that matches your Minecraft version from the [releases](https://github.com/theprodogy/hotbar-repeat-fix/releases).
3. Put it in your `.minecraft/mods` folder and start the game.

## Usage

There is nothing to set up. To see the fix:

1. Hold `1` for a few seconds, then press `2`. You stay on slot 2.
2. Hold `1`, then click a mouse button bound to a hotbar slot. You stay on that slot.
3. Spam `1`, `2`, `3` quickly. Every press lands and nothing flicks back.

> [!NOTE]
> One small difference from vanilla: if you press two hotbar keys within the same tick (50 ms), vanilla ends on the higher slot number and this mod ends on the key you pressed last.

Something not working? See [SUPPORT.md](SUPPORT.md). Changes per release are listed in the [CHANGELOG](CHANGELOG.md).

## How it works

Two mixins, no config:

- `KeyboardHandlerMixin` wraps the `KeyMapping.click` call in `KeyboardHandler.keyPress` and marks repeat events (`InputConstants.REPEAT`).
- `KeyMappingMixin` runs after every `KeyMapping.click`. For hotbar mappings it undoes the click on a repeat, or on a real press sets that slot to one pending click and clears the other eight.

The code lives in `common/src/main/java/dev/revsies/hotbarrepeatfix/hotbar`. Every Minecraft version compiles the same shared code.

## Build

You need JDK 21 or newer to start Gradle. Loom needs a Java 25 Gradle daemon, which `gradle/gradle-daemon-jvm.properties` pins, and Gradle downloads it if it's missing. Each jar is compiled with the Java version its Minecraft version needs.

```
./gradlew build collectJars
```

The jars land in `versions/<mc>/build/libs` and are copied to `build/jars`. The build runs `-Xlint:all -Werror`, Error Prone and NullAway, so any warning fails it.

### Adding a Minecraft version

1. Create `versions/<mc>/build.gradle.kts` with `plugins { id("hrf-remap") }` for obfuscated versions (1.21.11 and older) or `plugins { id("hrf-noremap") }` for unobfuscated ones (26.1 and newer).
2. Add `versions/<mc>/gradle.properties` with `minecraft_version`, `loader_version` and `java_version`.
3. Add the version to the list in `settings.gradle.kts`.
4. Code that only that version needs goes in `versions/<mc>/src/main/java`. Shared code stays in `common/src/main`.

## Contributing

Issues and pull requests are welcome. Read [CONTRIBUTING.md](CONTRIBUTING.md) first: changes stay small and gameplay stays vanilla, the build has to finish with zero warnings, the code has no comments, and each feature gets its own folder with one class per job.

This project follows the [Contributor Covenant Code of Conduct](CODE_OF_CONDUCT.md). Report security problems privately as described in [SECURITY.md](SECURITY.md).

## Credits

Made by revsies for the Minecraft PvP fix community.

Inspired by [Disproven](https://www.youtube.com/@disprovenrare)'s hotbar repeat fix mod, which he covers in [How to do a Perfect Shield Disable Every Single Time](https://www.youtube.com/watch?v=WJwwQVo03jg). His mod is private, so this one was built independently from the video's description of the behaviour as a free, open-source alternative. No code was taken from his mod.

## License

[MIT](LICENSE)
