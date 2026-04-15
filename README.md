# Sheepish

## Building

1. Create a folder "libs" in the project root directory (next to "gradle", "src", etc.)
2. Download Sinytra Connector: https://modrinth.com/mod/connector/version/2.0.0-beta.10+1.21.1
3. Open or extract the Sinytra Connector JAR as a ZIP file
4. Navigate to `<SinytraConnector.jar>/META-INF/jarjar`
5. Copy "org.sinytra.connector-2.0.0-beta.10+1.21.1-mod.jar" and paste into the "libs" folder created in step 1
6. Open a command prompt or terminal window in the project root directory and execute:
   - Linux: `./gradlew build`
   - macOS: `./gradlew build`
   - Windows: `gradlew.bat build`

## What does it do!?

- Fixes Fabric mods to make them work under Sinytra Connector:
  - Affinity (requires jar editing or [this fork](https://github.com/unilock/affinity))
  - Anshar
  - Anthropohagy
  - Biolith (NeoForge version with Fabric mods)
  - Cerulean
  - Cinderscapes
  - Electromechanics
  - Enderscape (Fabric version)
  - FrozenLib
    - Luna Slimes
    - Trailier Tales
    - Wilder Wild
  - Immersive Cursedness ([this fork](https://github.com/unilock/ImmersiveCursedness/tree/1.21.1))
  - Joy
  - Specter Serialization
  - Terraform Wood API v1
  - Tinkerer's Smithing
- Fixes bugs in various mods:
  - Akashic Tome: fixes left-clicking the tome not reverting it
  - Alternate Current: adds null checks
  - Dynamic Surroundings: prevents crashes by disabling the village bell sound
  - Enderscape: adds null checks
  - Extended Industrialization + Modern Industrialization: properly returns `Ingredient.EMPTY` as a repair material
  - Hexerei: disables dynamic lights to allow using with LambDynamicLights
  - Immersive Engineering: skips applying potion recipes with unbound inputs / outputs
  - Minecraft: prevents crashes from duplicate creative tab items
  - Minestuck: prevents crashes from one of its GUI buttons; allows running alongside Cardinal Components API
  - Mirthdew Encore: adds null checks
  - No Man's Land: allows running alongside Ported
  - Patchouli: gets registry access in a better way maybe
  - Rapscallions and Rockhoppers: prevents crashes due to `RapscallionsAndRockhoppers.biomePopulationPenguinTypeRegistry` being null
  - RenderScale: adds null checks
  - Splinecart: adds null checks
  - Surveyor: adds null checks
- Assorted features:
  - Allows loading Emojiful's pixelated emoji set without the "Blobs", "Discord", or "Pepe" categories
  - Allows disabling splash text modification from Blueprint, FrozenLib, and NeoForge
  - Disables Curvy Rail's on-join chat messages
  - Allows disabling Ender IO's alpha warning message
  - Supposedly prevents Fabric Resource Loader v0 from causing unnecessary resource reloads
  - Disables Lovely Sparkle Pieces' on-join chat messages
  - Makes horse offspring always have equal or better stats compared to their parents (configurable)
  - Prevents dropped items from stacking (configurable)
  - Supposedly shows more debug info for failed commands
  - Prevents certain enchantments from showing up on looted items (configurable)
  - Shows more debug info for unregistered holders during registry freeze
  - Shows more debug info for advancements that fail to be awarded
  - Allows the player to always eat (configurable)
  - Silences OpenGL errors
  - Disables More Creeps and Weirdo's on-join chat messages
  - Allows disabling Sodium's core shader resource pack warnings (configurable)
  - Adjusts the layer that Armor Toughness Bar renders on, allowing it to work with mods like Extended Hotbar
  - Disables Vestiges of the Present's on-join chat messages
  - Attempts to fix [MC-122477](https://bugs.mojang.com/browse/MC/issues/MC-122477)
