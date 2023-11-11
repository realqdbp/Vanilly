# ServerPlugin
A plugin primarily designed to be used on my private server.
It's features are listed below and can all be toggled on or off in the config after the first server start


## Requirements
   - PaperMC 1.20.2 (soon to be PurpurMC)
   - ### For AFK (without command)
     - In server.properties "player-idle-timeout" must be set to the desired time
   - ### For Deaths
     - A whitelist must be active


## Features
All features can be enabled or disabled after the first plugin start in the config (true -> enabled, false -> disabled).

All command aliases are listed at the bottom.

   - Ingame explanations are available with **/features**
   - **Info**
     - `/info featurename` explains the feature ingame
   - **AFK**
     - `/afk <optional reason>` Sets yourself in afk mode
     - Players name changes to display the afk time and optionally the reason why
     - Players can also be set afk if they are afk for too long
     - While being afk, the player:
       - Is Invulnerable
       - Has no collision
     - Moving in any way kicks you out of AFK
   - **Backpack**
     - `/backpack` opens a players personal backpack
     - size equal to a double chest
     - items are persistent (no loss on death)
   - **Craft**
     - `/craft` opens the crafting menu
   - **Enderchest**
     - `/enderchest` opens your personal enderchest
   - **Freecam**
     - `/freecam` sets the player into freecam mode
     - While in freecam:
       - the player is in spectator mode
       - the player can use `/switchworld`
     - Disabling freecam sets you back to you initiation position
   - **Switchworld**
     - `/switchworld` is used in freecam to switch between world
   - **Upgrade**
     - `/upgrade` opens a menu with different late game upgrades
     - **WIP**
   - **SkipNight**
     - `/skipnight` can be used to accelerate through the night
     - the player using this command gets the phantom spawn flag removed
   - **Deaths**
     - `/deahts` opens a menu with all whitelisted players and their death count
     - Clicking on a player opens their specific deaths info
   - **Enhanced Eating**
     - Players only need to eat to completely fill their hunger bar
     - The correct amount of food will be consumed
     - Only food that's edible works with this feature (a list is below)
   - **Enhanced Sleep**
     - Only one player needs to be in bed
     - The time doesn't skip, but instead accelerates just like `/skipnight` does
   - **Light**
     - Allows for the "Light" Block to be crafted
     - Recipe is: 8 Torches and an Iron block in the Middle
   - **DoubleDoor Opening**
     - Opens the aligning door next to the clicked one automatically as well
   - **MapImages**
     - `/mapimage <image_url> <shrink / resize>`
     - While holding an empty map the player can use this command to import a picture onto the map (or multiple)
     - Explanation WIP
   - **InvisibleItemFrames**
     - Allows for the "Invisible Item Frame" Item to be crafted
     - Recipe is: Item Frame and Glass Pane

## Command Aliases
   - `/backpack`
     - `/bp` , `/pv`
   - `/freecam`
     - `/fc`
   - `/switchworld`
     - `/sw`
     - Overworld:
       - `o`, `ov`, `overworld`
     - Nether
       - `n`, `nether`
     - End
       - `e`, `end`
   - `/enderchest`
     - `/ec`
   - `/skipnight`
     - `sn`
   - `/craft`
     - `/c`
   - `/mapimage`
     - `mi`

## Edible Food Map
   - Apple
   - Baked Potato
   - Beetroot
   - Beetroot Soup
   - Bread
   - Carrot
   - Cooked Chicken
   - Cooked Cod
   - Cooked Mutton
   - Cooked Porkchop
   - Cooked Rabbit
   - Cooked Salmon
   - Cookie
   - Dried Kelp
   - Glow Berries
   - Melon Slice
   - Mushroom Stew
   - Pumpkin Pie
   - Rabbit Stew
   - Cooked Beef
   - Sweet Berries
   - Golden Carrot

## WIP
   - Some feature will be edited or new ones created
   - This Project shows what's going on currently: [Project](https://github.com/users/realqdbp/projects/4)