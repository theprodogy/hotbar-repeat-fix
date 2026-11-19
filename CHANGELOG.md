# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.0] - 2026-11-19

### Added

- Key-repeat events are ignored for hotbar keys, so holding a hotbar key counts as one press.
- The newest hotbar press wins, from the keyboard or a mouse button bound to a slot. At most one switch is ever pending, and it runs on the next tick.
- Separate jars for Minecraft 1.21.11 (Java 21), 26.1 to 26.1.2, 26.2 and 26.3 (Java 25).

[Unreleased]: https://github.com/theprodogy/hotbar-repeat-fix/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/theprodogy/hotbar-repeat-fix/releases/tag/v1.0.0
