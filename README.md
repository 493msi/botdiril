# botdiril
Community Discord Bot running on JDA specifically crafted for Vandiland.

## This version is discontinued.

**Features:**
* Economy system
* RNG based (gambling) commands
* Inventory system
* League of Legends data (WIP)
* Music player (WIP)

**This is an Eclipse Maven project. JDK 10+**

Botdiril uses several dependencies (see ``pom.xml``), mainly **JDA** and **lavaplayer**.

**Botdiril pretty much works out of the box under one condition:** Setting up ``settings.json``

**Warning:** Botdiril uses hardcoded emotes for item icons by default, you might have to change them, specifically in ``cz.tefek.botdiril.userdata.items`` and the subpackages. 
For your convenience, the default icons are stored in the `icons` directory.

# Basic configuration:
For Botdiril to work, you need to get at least a Discord bot secret in the developer center and save it in ``settings.json`` in the **project's root folder**.
This is pretty much what you are looking for 90% of the time:
```js
{
    "key" : "<your Discord bot secret key>"
}
```

# Advanced configuration:
This assumes you set up an AWS S3 bucket to publicly show user inventories.
You can see the full JSON structure below.
*Omitting the ``s3config`` automatically disables any S3 linked features.*
```js
{
    "key" : "<your Discord bot secret key>",
    "s3config" : {
        "endpoint" : "<AWS endpoint>",
        "site" : "<publicly accessible AWS S3 site>",
        "key" : "<AWS key>",
        "pass" : "<AWS password>",
        "bucket" : "<S3 bucket>",
        "css" : "<stylesheet URL>",
        "js" : "<javascript URL>",
        "logo" : "<logo URL>"
    }
}
```
For more information, see the source code, specifically the command ``FullInventory``.
