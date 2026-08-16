![Growthcraft Trapper](https://raw.githubusercontent.com/GrowthcraftCE/Growthcraft-Trapper/1.16/src/main/resources/growthcraft_fishtrap_logo.png)

# Growthcraft Trapper

[![Version](https://img.shields.io/badge/version-1.21.1.1-orange.svg)](https://github.com/GrowthcraftCE/Growthcraft-Trapper)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-62b47a.svg)](https://www.minecraft.net/)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.x-yellow.svg)](https://neoforged.net/)
[![CurseForge](https://cf.way2muchnoise.eu/short_growthcraft-trapper.svg)](https://www.curseforge.com/minecraft/mc-mods/growthcraft-trapper)
[![Discord](https://img.shields.io/discord/333690296334548994.svg?label=Discord&color=5865f2)](https://discord.gg/Quh76Jn)

Growthcraft Trapper adds passive resource traps to Minecraft. Supply a trap with the right bait, leave it working, and
return later to collect fish, animal drops, or even passive-animal spawn eggs.

Traps continue consuming bait while they operate. If every output slot is full, newly caught loot is lost. Empty traps
regularly or place a hopper underneath to collect their output automatically.

The mod began with Growthcraft's original Fishtrap and is now maintained as a standalone mod. Growthcraft is not
required.

## Traps and Bait

### Fishtraps

Fishtraps are available in every vanilla wood type. Place one in a suitable water location and put bait in its input
slot.

| Bait | Loot category | Examples |
| --- | --- | --- |
| Cod, salmon, tropical fish, or bread | Normal | Cod, salmon, tropical fish, and pufferfish |
| Rotten flesh, pufferfish, or tripwire hook | Fortune | Name tags, saddles, enchanted gear, and nautilus shells |
| Other bait | Junk | Lily pads, leather, bones, string, and other fishing junk |

Fortune bait applies the equivalent of Luck of the Sea III. Hover over the filter icon in the trap GUI to see the loot
category selected by the current bait.

### Animal Traps

Animal traps turn common animal food into renewable mob drops without spawning or killing an entity in the world.

| Bait | Expected loot |
| --- | --- |
| Wheat | Beef or leather |
| Carrots | Porkchops or rabbit drops |
| Wheat seeds | Chicken, feathers, or eggs |
| Leaves | Mutton or colored wool |

Animal traps are available in several material tiers. Their GUI tooltip shows which loot pool the current bait selects.

### Netherite Animal Trap

The Netherite Animal Trap accepts wheat and has a chance to produce cow, sheep, or goat spawn eggs. Its tooltip lists
the expected results when valid bait is present.

## Support

- Report bugs or request features through [GitHub Issues](https://github.com/GrowthcraftCE/Growthcraft-Trapper/issues).
- Join the [Growthcraft Discord](https://discord.gg/Quh76Jn) for discussion and help.
- Download published releases from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/growthcraft-trapper).

When reporting a problem, include the Minecraft, NeoForge, and Growthcraft Trapper versions along with the relevant
`latest.log` or crash report.

## Version History

| Minecraft | Loader | Latest Trapper version | Status |
| --- | --- | --- | --- |
| 1.21.1 | NeoForge | 1.21.1.1 | In development |
| 1.20.6 | NeoForge | 1.20.6.1 | Released |
| 1.20.4 | NeoForge | 1.20.4.1-neo | Released |
| 1.20.4 | Forge | 1.20.4.1 | Released |
| 1.20.1 | Forge | 9.0.2 | Released |

Older versions remain available from the project's
[CurseForge files](https://www.curseforge.com/minecraft/mc-mods/growthcraft-trapper/files/all) and Git history.

## Contributing and Development

Before starting a change, open or comment on a GitHub issue so work is not duplicated. Keep changes targeted to the
appropriate Minecraft version branch.

Requirements:

- Java 21
- The included Gradle Wrapper
- A Minecraft 1.21.1-compatible IDE

Useful commands:

```powershell
.\gradlew.bat compileJava
.\gradlew.bat processResources
.\gradlew.bat runClient
.\gradlew.bat runServer
.\gradlew.bat runData
```

This project uses NeoForge ModDev. IntelliJ run configurations are prepared when the Gradle project is reloaded; the
equivalent command-line synchronization task is `neoForgeIdeSync`.

## License

Growthcraft Trapper is licensed under the [GNU General Public License v3.0](LICENSE.txt).
