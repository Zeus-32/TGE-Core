# The Great Engineer Core

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-62B47A?style=flat-square)](https://www.minecraft.net/)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.229+-E68A2E?style=flat-square)](https://neoforged.net/)
[![Version](https://img.shields.io/badge/version-0.0.2-4C8BF5?style=flat-square)](CHANGELOG.md)
[![License](https://img.shields.io/badge/code-MIT-blue?style=flat-square)](LICENCE.md)

The Great Engineer Core is the gameplay foundation for The Great Engineer modpack.

It defines the early manual crafting layer, the first TGE-specific resources, and the tool framework used by later progression systems. The core mod is intentionally strict: recipes should use TGE tools and Ore & Alloy material outputs instead of loose cross-mod substitutions.

## Core behavior

- Adds the first TGE resource items: glass dust, flint dust, clay dust, brick dust, fire clay dust, fire brick, unfired clay brick, and brick mold.
- Adds a reusable brick mold item for crafting recipes that should not consume the mold.
- Replaces the vanilla clay-to-brick flow with an unfired clay brick flow.
- Adds a dedicated TGE tools creative tab.
- Adds iron manual tools: hammer, wrench, saw, file, mortar, wire cutters, and screwdriver.
- Adds iron vanilla-style tools managed through the same TGE tool system: pickaxe, axe, shovel, hoe, and sword.
- Adds shared tooltips for tool durability, remaining durability, crafting uses, and tier.
- Adds strict `tge:tools/<tool>` item tags for recipes that must require TGE tools specifically.
- Generates basic TGE crafting recipes through datagen.

TGE Core currently depends on Ore & Alloy for canonical material outputs used by plate and rod recipes.

## Tool System

The tool registry is material-driven. Each tool type can receive its own supported materials independently, so future progression can add material tiers without rewriting every tool.

Current material coverage:

| Material | Tools |
|---|---|
| Iron | Hammer, wrench, saw, file, mortar, wire cutters, screwdriver, pickaxe, axe, shovel, hoe, sword |

Tool recipes use common material tags for inputs and TGE-specific tool tags for tool requirements. This keeps ingot, plate, screw, and rod inputs compatible while preventing recipes from accepting unrelated tools from other mods.

## Ore & Alloy Dependency

Ore & Alloy is a required dependency.

TGE Core uses Ore & Alloy as the canonical source for material outputs such as:

- `ore_and_alloy:<material>_plate`
- `ore_and_alloy:<material>_rod`

The current required Ore & Alloy version is `1.0.5` or newer.

## Optional Integrations

- [JEI](https://www.curseforge.com/minecraft/mc-mods/jei): optional recipe viewing support

JEI is not required to run TGE Core.

## Technical Requirements

- Minecraft `1.21.1`
- NeoForge `21.1.229+`
- Ore & Alloy `1.0.5+`
- Java `21`

## License

- Source code: MIT
- Art and other assets: All Rights Reserved

See [LICENCE.md](LICENCE.md).
