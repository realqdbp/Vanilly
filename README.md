# ServerPlugin
A Plugin that was made to run on my private server in the first place.
It has multiple features, each can be switched on or off.

## Things Needed
 - Papermc
 - Minecraft Server 1.20.1
 - For AFK to work without commands (server.properties -> player-idle-timeout)
 - For Deaths to work, a whitelist needs to be active, as only whitelisted players will be shown other than yourself
 - For Toggling, start the server with plugin once. In the config file, change values to true / false. Restart Server or ingame /reload confirm

## Features
 - Ingame explanaitions with /features
 - Info
   - "/info featurename" lists the explanaition for the specified feature
 - AFK
   - Instead of getting kicked from the Server, you're set into "AFK Mode", making you invulnerable.
   - Alternatively set yourself into AFK mode with "/afk optionalReason".
   - Moving in any way disables AFK mode.
 - Backpack
   - Your personal persistent backpack, as large as a double chest.
 - Craft
   - Opens the crafting menu anywhere you are, so that you dont need to have a crafting table with you all the time.
 - Enderchest
   - Opens your personal enderchest
 - Freecam
   - Sets yourself into freecam mode
   - This enables you to fly around in spectator mode
   - Switchworld can now be used
   - This is normally used to inspect buildings you're working on, so that you don't need to run around all the time.
   - Disabling freecam puts you into the spot where you enabled it.
 - Upgrade
   - Opens a menu with different ultra late game upgrade such as Efficiency upgrade to Efficiency X, faster running, unbreakable upgrades for tools and so on.
   - These upgrade cost a price tho.
 - Skipnight
   - If it's night, accelerate the time with this command.
   - The player using this command also gets the phantom flag removed.
 - Switchworld
   - While in freecam, switch between the worlds with this command.
   - The position is relative to yours (in some cases, i don't remember exactly).
   - n/nether for nether, e/end for end, o/overworld for normal world
 - Tode
   - Opens a menu with all the whitelisted players on the server and their death count.
   - Clicking on a player opens their personal death menu, allowing you to see each individual deaths description like Reason, Time, Position...
 - Eating
   - This makes you only needing to eat once instead of 5 times to completely fill your hunger bar and uses the correct amount of items.
   - Only works with "good" food items, so e.g. rotten flesh is not included
 - Sleep
   - If you're laying in bed, instead of instantly skipping to day, the same thing as with skipnight happens.
   - With this only one player needs to sleep. Not everyone in the overworld.
 - LightRecipe
   - Allows the "Light" Item/Block to be crafted.
   - Current recipe is: Torches and in the middle Iron Block
 - DoubleOpenDoors
   - Opens the aligning door next to the one you clicked automatically with it
 - MapImage
   - Get yourself some image and a picture frame and add the picture into Minecraft.
## Future
 - This project shows all the features that will some day be implemented.
 - https://github.com/users/realqdbp/projects/4