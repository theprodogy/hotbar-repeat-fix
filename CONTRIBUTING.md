# Contributing to Hotbar Repeat Fix

Thanks for helping out. This mod is small on purpose, so most contributions are bug reports, support for a new Minecraft version, or fixes to how hotbar input behaves.

By taking part you agree to follow the [Code of Conduct](CODE_OF_CONDUCT.md).

## Reporting bugs

Open a [bug report](https://github.com/theprodogy/hotbar-repeat-fix/issues/new?template=bug_report.md) and include:

- your Minecraft version, Fabric Loader version, Java version and the mod jar you used
- other mods installed, especially input or keybind mods
- steps to reproduce, what you expected, and what happened
- your `latest.log` if the game crashed or the mod failed to load

Security problems go through [SECURITY.md](SECURITY.md), not public issues.

## Suggesting changes

Open a [feature request](https://github.com/theprodogy/hotbar-repeat-fix/issues/new?template=feature_request.md) first for anything bigger than a bug fix. The mod only fixes hotbar input and keeps all other gameplay vanilla. Requests that add advantages beyond vanilla behaviour, such as macros, input buffering or automation, won't be accepted.

## Development setup

You need JDK 21 or newer and Git. Gradle downloads the Java 25 daemon and toolchains it needs.

```sh
git clone https://github.com/theprodogy/hotbar-repeat-fix.git
cd hotbar-repeat-fix
./gradlew build collectJars
```

The jars land in `build/jars`. To test in game, run `./gradlew :versions:<mc>:runClient`, for example `./gradlew :versions:1.21.11:runClient`.

The project layout and how to add a Minecraft version are in the [README](README.md#build).

## Code rules

Every pull request has to meet these, or the build fails or the review will ask for changes:

- **Zero warnings.** The build runs `-Xlint:all -Werror`, Error Prone and NullAway. Check with `./gradlew clean build --warning-mode all --no-build-cache`.
- **No comments** in code or build scripts. Class and method names have to explain the code.
- **One folder per function, one class per job.** A new feature gets its own package, with each part (mixin, accessor, logic) in its own `.java` file.
- **Shared code first.** Code goes in `common/src/main/java` and has to compile on every supported version. Use `versions/<mc>/src/main/java` only when a version really differs.
- **Vanilla gameplay.** Only change what the fix needs.

## Pull requests

1. Fork the repo and create a branch from `main`.
2. Keep each pull request to one logical change.
3. Write commit messages as short, lowercase, imperative lines with an area prefix, for example `hotbar: ignore repeat on mouse binds` or `build: add 26.4`.
4. Build every version locally and test in game on at least one.
5. Add your change to the `Unreleased` section of [CHANGELOG.md](CHANGELOG.md).
6. Open the pull request and fill in the template.

## License

By contributing, you agree that your contributions are licensed under the [MIT License](LICENSE).
