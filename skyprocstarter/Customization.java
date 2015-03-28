/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyprocstarter;

import lev.gui.LCheckBox;
import lev.gui.LComboBox;
import skyproc.SPGlobal;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SPSettingPanel;
import skyproc.gui.SUMGUI;

/**
 *
 * @author Justin Swanson
 */
public class Customization extends SPSettingPanel {

    LCheckBox importOnStartup;
    LCheckBox alterHeights;
    LCheckBox alterWeights;
    LCheckBox alterHeadParts;
    LCheckBox hasCatK;
    LCheckBox modifiedIni;
    LCheckBox allowVanilla;
    LCheckBox allowBobcat;
    LCheckBox allowHousecat;
    LCheckBox allowLion;
    LCheckBox allowTweaked;
    LCheckBox allowTweakedScoop;

    public Customization(SPMainMenuPanel parent_) {
	super(parent_, "Customization", SkyProcStarter.headerColor);
    }

    @Override
    protected void initialize() {
	super.initialize();

	importOnStartup = new LCheckBox("Import Mods on Startup", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	importOnStartup.tie(YourSaveFile.Settings.IMPORT_AT_START, SkyProcStarter.save, SUMGUI.helpPanel, true);
	importOnStartup.setOffset(2);
	importOnStartup.addShadow();
	setPlacement(importOnStartup);
	AddSetting(importOnStartup);

        alterHeights = new LCheckBox("Alter heights", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	alterHeights.tie(YourSaveFile.Settings.ALTER_HEIGHTS, SkyProcStarter.save, SUMGUI.helpPanel, true);
	alterHeights.setOffset(2);
	alterHeights.addShadow();
	setPlacement(alterHeights);
	AddSetting(alterHeights);
        
        alterWeights = new LCheckBox("Alter weights", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	alterWeights.tie(YourSaveFile.Settings.ALTER_WEIGHTS, SkyProcStarter.save, SUMGUI.helpPanel, true);
	alterWeights.setOffset(2);
	alterWeights.addShadow();
	setPlacement(alterWeights);
	AddSetting(alterWeights);
        
        alterHeadParts = new LCheckBox("New eyes/hair", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	alterHeadParts.tie(YourSaveFile.Settings.ALTER_HEADPARTS, SkyProcStarter.save, SUMGUI.helpPanel, true);
	alterHeadParts.setOffset(2);
	alterHeadParts.addShadow();
	setPlacement(alterHeadParts);
	AddSetting(alterHeadParts);
        
        hasCatK = new LCheckBox("I have Catlike Khajiit", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	hasCatK.tie(YourSaveFile.Settings.HAS_CATK, SkyProcStarter.save, SUMGUI.helpPanel, true);
	hasCatK.setOffset(2);
	hasCatK.addShadow();
	setPlacement(hasCatK);
	AddSetting(hasCatK);
        
        modifiedIni = new LCheckBox("I modified skyrim.ini", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	modifiedIni.tie(YourSaveFile.Settings.MODIFIED_INI, SkyProcStarter.save, SUMGUI.helpPanel, true);
	modifiedIni.setOffset(2);
	modifiedIni.addShadow();
	setPlacement(modifiedIni);
	AddSetting(modifiedIni);

        allowVanilla = new LCheckBox("Allow vanilla khajiit", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	allowVanilla.tie(YourSaveFile.Settings.ALLOW_VANILLA, SkyProcStarter.save, SUMGUI.helpPanel, true);
	allowVanilla.setOffset(2);
	allowVanilla.addShadow();
	setPlacement(allowVanilla);
	AddSetting(allowVanilla);
        
        allowBobcat = new LCheckBox("Allow bobcats", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	allowBobcat.tie(YourSaveFile.Settings.ALLOW_BOBCAT, SkyProcStarter.save, SUMGUI.helpPanel, true);
	allowBobcat.setOffset(2);
	allowBobcat.addShadow();
	setPlacement(allowBobcat);
	AddSetting(allowBobcat);
        
        allowHousecat = new LCheckBox("Allow housecats", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	allowHousecat.tie(YourSaveFile.Settings.ALLOW_HOUSECAT, SkyProcStarter.save, SUMGUI.helpPanel, true);
	allowHousecat.setOffset(2);
	allowHousecat.addShadow();
	setPlacement(allowHousecat);
	AddSetting(allowHousecat);
        
        allowLion = new LCheckBox("Allow lions", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	allowLion.tie(YourSaveFile.Settings.ALLOW_LION, SkyProcStarter.save, SUMGUI.helpPanel, true);
	allowLion.setOffset(2);
	allowLion.addShadow();
	setPlacement(allowLion);
	AddSetting(allowLion);
        
        allowTweaked = new LCheckBox("Allow tweaked vanilla", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	allowTweaked.tie(YourSaveFile.Settings.ALLOW_TWEAKED, SkyProcStarter.save, SUMGUI.helpPanel, true);
	allowTweaked.setOffset(2);
	allowTweaked.addShadow();
	setPlacement(allowTweaked);
	AddSetting(allowTweaked);
        
        allowTweakedScoop = new LCheckBox("Allow 'tweaked vanilla + scoop'", SkyProcStarter.settingsFont, SkyProcStarter.settingsColor);
	allowTweakedScoop.tie(YourSaveFile.Settings.ALLOW_TWEAKEDSCOOP, SkyProcStarter.save, SUMGUI.helpPanel, true);
	allowTweakedScoop.setOffset(2);
	allowTweakedScoop.addShadow();
	setPlacement(allowTweakedScoop);
	AddSetting(allowTweakedScoop);

        alignRight();
    }
}
