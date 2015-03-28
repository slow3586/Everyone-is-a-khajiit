/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyprocstarter;

import skyproc.SkyProcSave;

/**
 *
 * @author Justin Swanson
 */
public class YourSaveFile extends SkyProcSave {

    @Override
    protected void initSettings() {
	//  The Setting,	    The default value,	    Whether or not it changing means a new patch should be made
	Add(Settings.IMPORT_AT_START,		true,	    false);
        Add(Settings.ALTER_WEIGHTS,		false,	    false);
        Add(Settings.ALTER_HEIGHTS,		false,	    false);
        Add(Settings.ALTER_HEADPARTS,           false,	    false);
        Add(Settings.HAS_CATK,                  false,	    false);
        Add(Settings.MODIFIED_INI,		false,	    false);
        Add(Settings.ALLOW_VANILLA,		false,	    false);
        Add(Settings.ALLOW_BOBCAT,		true,	    false);
        Add(Settings.ALLOW_HOUSECAT,		true,	    false);
        Add(Settings.ALLOW_LION,		true,	    false);
        Add(Settings.ALLOW_TWEAKED,		true,	    false);
        Add(Settings.ALLOW_TWEAKEDSCOOP,        true,	    false);

    }

    @Override
    protected void initHelp() {

	helpInfo.put(Settings.IMPORT_AT_START,
		"If enabled, the program will begin importing your mods when the program starts.\n\n"
		+ "If turned off, the program will wait until it is necessary before importing.\n\n"
		+ "NOTE: This setting will not take effect until the next time the program is run.\n\n"
		+ "Benefits:\n"
		+ "- Faster patching when you close the program.\n"
		+ "- More information displayed in GUI, as it will have access to the records from your mods."
		+ "\n\n"
		+ "Downsides:\n"
		+ "- Having this on might make the GUI respond sluggishly while it processes in the "
		+ "background.");
        
        helpInfo.put(Settings.HAS_CATK,
		"You need to have Catlike Khajiit with the playable races option installed.\n\n"
		+ "http://www.nexusmods.com/skyrim/mods/50802/?\n\n"
                + "You also need 'Child clothes for adult-based children'.\n\n"
                + "http://www.nexusmods.com/skyrim/mods/26952/?\n\n"
                );
        
        helpInfo.put(Settings.MODIFIED_INI,
		"You need to modify your My Documents/My Games/Skyrim/Skyrim.ini file."
                + "Add a bUseFaceGenPreprocessedHeads=0 line under [General] and check the checkbox in the settings next time you run the patcher."
                );

        helpInfo.put(Settings.ALLOW_VANILLA,
		"Allow vanilla khajiit race to be used in random generation. You might want to use it if you have mods which require vanilla races only.");
        
        helpInfo.put(Settings.ALLOW_BOBCAT,
		"Allow bobcat race (from catlike khajiit) to be used in random generation.");
                
        helpInfo.put(Settings.ALLOW_HOUSECAT,
		"Allow housecat race (from catlike khajiit) to be used in random generation.");
                        
        helpInfo.put(Settings.ALLOW_LION,
		"Allow lion race (from catlike khajiit) to be used in random generation.");
                                
        helpInfo.put(Settings.ALLOW_TWEAKED,
		"Allow tweaked vanilla race (from catlike khajiit) to be used in random generation.");
        
        helpInfo.put(Settings.ALLOW_TWEAKEDSCOOP,
		"Allow tweaked vanilla race (from catlike khajiit) to be used in random generation.");
        
        helpInfo.put(Settings.ALTER_WEIGHTS,
		"This option will allow the patcher to change weights of npcs. Very optional.");
        
        helpInfo.put(Settings.ALTER_HEIGHTS,
		"This option will allow the patcher to change heights of npcs. Very optional.");
        
        helpInfo.put(Settings.ALTER_HEADPARTS,
		"If you have custom hairstyles and you want the patcher to use them, you can activate this option. They might look ridiculous sometimes.");
        
	helpInfo.put(Settings.OTHER_SETTINGS,
		"What should the patcher do.");
    }

    // Note that some settings just have help info, and no actual values in
    // initSettings().
    public enum Settings {
	IMPORT_AT_START,
	OTHER_SETTINGS,
        ALTER_WEIGHTS,
        ALTER_HEIGHTS,
        ALTER_HEADPARTS,
        HAS_CATK,
        MODIFIED_INI,
        ALLOW_VANILLA,
        ALLOW_HOUSECAT,
        ALLOW_BOBCAT,
        ALLOW_LION,
        ALLOW_TWEAKED,
        ALLOW_TWEAKEDSCOOP
    }
}
