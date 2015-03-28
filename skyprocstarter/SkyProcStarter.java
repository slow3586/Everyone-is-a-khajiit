package skyprocstarter;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import lev.gui.LSaveFile;
import skyproc.*;
import skyproc.gui.SPMainMenuPanel;
import skyproc.gui.SUM;
import skyproc.gui.SUMGUI;
import skyprocstarter.YourSaveFile.Settings;
import skyproc.ARMA;
import skyproc.genenums.Gender;
import skyproc.genenums.Perspective;
/**
 *
 * @author Your Name Here
 */
public class SkyProcStarter implements SUM {

    /*
     * The important functions to change are:
     * - getStandardMenu(), where you set up the GUI
     * - runChangesToPatch(), where you put all the processing code and add records to the output patch.
     */

    /*
     * The types of records you want your patcher to import. Change this to
     * customize the import to what you need.
     */
    GRUP_TYPE[] importRequests = new GRUP_TYPE[]{
	GRUP_TYPE.NPC_,
        GRUP_TYPE.ARMO,
        GRUP_TYPE.ARMA,
        GRUP_TYPE.HDPT,
        GRUP_TYPE.FLST,
        GRUP_TYPE.RACE,
    };
    public static String myPatchName = "Khajiit patch";
    public static String authorName = "slow";
    public static String version = "1.0";
    public static String welcomeText = "";
    public static String descriptionToShowInSUM = "Turn every NPC into a khajiit.";
    public static Color headerColor = new Color(66, 181, 184);  // Teal
    public static Color settingsColor = new Color(72, 179, 58);  // Green
    public static Font settingsFont = new Font("Serif", Font.BOLD, 15);
    public static SkyProcSave save = new YourSaveFile();
    public static Customization customization;

    // Do not write the bulk of your program here
    // Instead, write your patch changes in the "runChangesToPatch" function
    // at the bottom
    public static void main(String[] args) {
	try {
	    SPGlobal.createGlobalLog();
	    SUMGUI.open(new SkyProcStarter(), args);
	} catch (Exception e) {
	    // If a major error happens, print it everywhere and display a message box.
	    System.err.println(e.toString());
	    SPGlobal.logException(e);
	    JOptionPane.showMessageDialog(null, "There was an exception thrown during program execution: '" + e + "'  Check the debug logs or contact the author.");
	    SPGlobal.closeDebug();
	}
    }

    @Override
    public String getName() {
	return myPatchName;
    }

    // This function labels any record types that you "multiply".
    // For example, if you took all the armors in a mod list and made 3 copies,
    // you would put ARMO here.
    // This is to help monitor/prevent issues where multiple SkyProc patchers
    // multiply the same record type to yeild a huge number of records.
    @Override
    public GRUP_TYPE[] dangerousRecordReport() {
	// None
	return new GRUP_TYPE[0];
    }

    @Override
    public GRUP_TYPE[] importRequests() {
	return importRequests;
    }

    @Override
    public boolean importAtStart() {
	return false;
    }

    @Override
    public boolean hasStandardMenu() {
	return true;
    }

    // This is where you add panels to the main menu.
    // First create custom panel classes (as shown by YourFirstSettingsPanel),
    // Then add them here.
    @Override
    public SPMainMenuPanel getStandardMenu() {
	SPMainMenuPanel settingsMenu = new SPMainMenuPanel(getHeaderColor());
        
        customization = new Customization(settingsMenu);
        settingsMenu.addMenu(customization, false, save, Settings.OTHER_SETTINGS);
	settingsMenu.setWelcomePanel(customization);

	return settingsMenu;
    }

    // Usually false unless you want to make your own GUI
    @Override
    public boolean hasCustomMenu() {
	return false;
    }

    @Override
    public JFrame openCustomMenu() {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasLogo() {
	return false;
    }

    @Override
    public URL getLogo() {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasSave() {
	return true;
    }

    @Override
    public LSaveFile getSave() {
	return save;
    }

    @Override
    public String getVersion() {
	return version;
    }

    @Override
    public ModListing getListing() {
	return new ModListing(getName(), false);
    }

    @Override
    public Mod getExportPatch() {
	Mod out = new Mod(getListing());
	out.setAuthor(authorName);
	return out;
    }

    @Override
    public Color getHeaderColor() {
	return headerColor;
    }

    // Add any custom checks to determine if a patch is needed.
    // On Automatic Variants, this function would check if any new packages were
    // added or removed.
    @Override
    public boolean needsPatching() {
	return false;
    }

    // This function runs when the program opens to "set things up"
    // It runs right after the save file is loaded, and before the GUI is displayed
    @Override
    public void onStart() throws Exception {
    }

    // This function runs right as the program is about to close.
    @Override
    public void onExit(boolean patchWasGenerated) throws Exception {
    }

    // Add any mods that you REQUIRE to be present in order to patch.
    @Override
    public ArrayList<ModListing> requiredMods() {
        ArrayList<ModListing> ml = new ArrayList<>();
        ml.add(new ModListing("Catlike Khajiit.esp"));
        ml.add(new ModListing("Childclothes_For_AdultBased_Children.esp"));
	return ml;
    }

    @Override
    public String description() {
	return descriptionToShowInSUM;
    }

    // This is where you should write the bulk of your code.
    // Write the changes you would like to make to the patch,
    // but DO NOT export it.  Exporting is handled internally.
    @Override
    public void runChangesToPatch() throws Exception {
        //settings
        if(!customization.modifiedIni.getValue()){
            JOptionPane.showMessageDialog(null, "You need to modify your My Documents/My Games/Skyrim/Skyrim.ini file. Add a bUseFaceGenPreprocessedHeads=0 line under [General] and check the checkbox in the settings next time you run the patcher.");
        }   
        
	Mod patch = SPGlobal.getGlobalPatch();
	Mod merger = new Mod(getName() + "Merger", false);
	merger.addAsOverrides(SPGlobal.getDB());
        
        Random random = new Random();
        com.rits.cloning.Cloner cloner = new com.rits.cloning.Cloner();
        
        //need to change children shoes first so they don't have human feet
        for (ARMO h : merger.getArmors()) {
            if(h.getEDID().equals("ClothesChildrenShoes")){
                h.setModel("", Gender.MALE);
                h.setModel("", Gender.FEMALE);
                h.getArmatures().clear();
                patch.addRecord(h);
            }
        }
        
        for (ARMA h : merger.getArmatures()) {
            if(h.getEDID().equals("ChildrenShoesAA")){
                h.setModelPath("", Gender.MALE, Perspective.FIRST_PERSON);
                h.setModelPath("", Gender.FEMALE, Perspective.FIRST_PERSON);
                h.setModelPath("", Gender.MALE, Perspective.THIRD_PERSON);
                h.setModelPath("", Gender.FEMALE, Perspective.THIRD_PERSON);
                patch.addRecord(h);
            }
        }
        
            
        //get old vanilla races
        //i use formids because i hope nobody's going to modify skyrim.esm
        ArrayList<FormID> npcraces = new ArrayList<FormID>();
        npcraces.add(new FormID("00013740", "Skyrim.esm")); //argonian
        npcraces.add(new FormID("00013741", "Skyrim.esm")); //breton
        npcraces.add(new FormID("00013742", "Skyrim.esm")); //dark elf
        npcraces.add(new FormID("00013743", "Skyrim.esm")); //high elf
        npcraces.add(new FormID("00013744", "Skyrim.esm")); //imperial
        npcraces.add(new FormID("00013745", "Skyrim.esm")); //khajiit
        npcraces.add(new FormID("00013746", "Skyrim.esm")); //nord
        npcraces.add(new FormID("00013747", "Skyrim.esm")); //orc
        npcraces.add(new FormID("00013748", "Skyrim.esm")); //redguard
        npcraces.add(new FormID("00013749", "Skyrim.esm")); //woodelf
        npcraces.add(new FormID("00067CD8", "Skyrim.esm")); //old
        npcraces.add(new FormID("0007EAF3", "Skyrim.esm")); //?
        
        ArrayList<FormID> vampireraces = new ArrayList<FormID>();
        vampireraces.add(new FormID("00088794", "Skyrim.esm")); //nord
        vampireraces.add(new FormID("0008883A", "Skyrim.esm")); //argonian
        vampireraces.add(new FormID("0008883C", "Skyrim.esm")); //breton
        vampireraces.add(new FormID("0008883D", "Skyrim.esm")); //dark elf
        vampireraces.add(new FormID("00088840", "Skyrim.esm")); //high elf
        vampireraces.add(new FormID("00088844", "Skyrim.esm")); //imperial
        vampireraces.add(new FormID("00088845", "Skyrim.esm")); //khajiit
        vampireraces.add(new FormID("00088846", "Skyrim.esm")); //redguard
        vampireraces.add(new FormID("00088884", "Skyrim.esm")); //woodelf
        vampireraces.add(new FormID("000A82B9", "Skyrim.esm")); //ork
        
        ArrayList<FormID> childraces = new ArrayList<FormID>();
        childraces.add(new FormID("0002C659", "Skyrim.esm")); //IMPERIAL
        childraces.add(new FormID("0002C65A", "Skyrim.esm")); //redguard
        childraces.add(new FormID("0002C65B", "Skyrim.esm")); //nord
        childraces.add(new FormID("0002C65C", "Skyrim.esm")); //breton
        
        //arrays of headparts good for use
        ArrayList<FormID> goodeyes = new ArrayList<FormID>();
        ArrayList<FormID> goodhair = new ArrayList<FormID>();
        for (HDPT h : merger.getHeadParts()) {
            if(h.getEDID().contains("Khajiit")){
                if(h.getEDID().contains("Eyes")){
                    goodeyes.add(h.getForm());
                    SPGlobal.log(version, "Added eye: " + h.getFormStr() + "; " + h.getName());
                }
                if(customization.alterHeadParts.getValue())//was supposed to work for eyes as well but turns out you always have to change them
                {
                    if(h.getEDID().contains("Hair")){
                        goodhair.add(h.getForm());
                        SPGlobal.log(version, "Added hair: " + h.getFormStr() + "; " + h.getName());
                    }
                }
            }
        }
        
        //arrays of races good to use
        ArrayList<FormID> newraces = new ArrayList<FormID>();
        ArrayList<FormID> newvampireraces = new ArrayList<FormID>();
        //find catlike khajiit races
        for (RACE r : merger.getRaces()) {
            if(r.getEDID().contains("_zKhajiitRace")){
                if( (r.getEDID().contains("Bobcat") && !customization.allowBobcat.getValue()) ||
                    (r.getEDID().contains("Housecat") && !customization.allowHousecat.getValue()) ||
                    (r.getEDID().contains("Lion") && !customization.allowLion.getValue()) ||
                    (r.getEDID().contains("VTweaked") && !r.getEDID().contains("Scoop") && !customization.allowTweaked.getValue()) ||
                    (r.getEDID().contains("VTweakedScoop") && !customization.allowTweakedScoop.getValue()) )
                {}
                else
                {
                    if(r.getEDID().contains("Vampire"))
                        newvampireraces.add(r.getForm());
                    else
                        newraces.add(r.getForm());
                }
            }
        }
        //vanilla khajiit races
        if(customization.allowVanilla.getValue()){
            newraces.add(new FormID("00013745", "Skyrim.esm"));
            newvampireraces.add(new FormID("00088845", "Skyrim.esm"));
        }

        NPC_.TintLayer templayer = null;
        boolean tintlayerfound=false; 
        //i NEED a tint layer with 1.0000 interp because setinterpolation function in skyproc is broken as hell
        //i use a character preset from catlike khajiit to get that tint layer
        //i then clone it using the cloning library (https://code.google.com/p/cloning/)
        //because there's no other way to make a tint layer
        for (NPC_ n : merger.getNPCs()) {
            if(!tintlayerfound){
                ArrayList<NPC_.TintLayer> temparray = n.getTinting();
                if(n.getEDID().equals("_ZKhajiitFemalePreset02")){
                    templayer=cloner.deepClone(temparray.get(1));
                    templayer.setColor(RGBA.Blue, (short)0);
                    templayer.setColor(RGBA.Red, (short)0);
                    templayer.setColor(RGBA.Green, (short)0);
                    tintlayerfound=true;
                }
            }
        }
        
        //oops?
        if(!tintlayerfound){
            SPGlobal.log(version, "Couldn't find the khajiit preset from Catlike Khajiit.esp!");
            JOptionPane.showMessageDialog(null, "Critical error! Couldn't find the khajiit preset from Catlike Khajiit.esp! Either the file is corrupted or it's my fault.");
            return;
        }
        
        //does the npc need changing
	for (NPC_ n : merger.getNPCs()) {
            FormID race = n.getRace();
            boolean dothechange = false;
            boolean vampire = false;
            boolean child = false;
            //is a human!
            for(FormID validrace : npcraces){
                if(race.equals(validrace)){
                    dothechange=true;
                    break;
                }
            }
            
            //not a human?
            if(!dothechange){
                for(FormID validrace : vampireraces){ //maybe he's a vampire though
                    if(race.equals(validrace)){
                        dothechange=true;
                        vampire=true;
                        break;
                    }
                }
            }
            
            if(!dothechange){
                for(FormID validrace : childraces){ //a child maybe?
                    if(race.equals(validrace)){
                        dothechange=true;
                        child=true;
                        break;
                    }
                }
            }
            
            //if so change him
            if(dothechange){
                SPGlobal.log(version, "Changing NPC: " + n.getFormStr() + "; " + n.getName());
                
                if(vampire)
                    n.setRace(newvampireraces.get(random.nextInt(newvampireraces.size())));
                else
                    n.setRace(newraces.get(random.nextInt(newraces.size())));
                
                boolean female = false;
                if(n.get(NPC_.NPCFlag.Female)==true)
                    female = true;
                
                //head
                ArrayList<FormID> headparts = n.getHeadParts();
                headparts.clear();
                headparts.add(goodeyes.get(random.nextInt(goodeyes.size())));
                if(customization.alterHeadParts.getValue())
                {
                    headparts.add(goodhair.get(random.nextInt(goodhair.size())));
                }
                n.setEyePreset(random.nextInt(6));
                n.setNosePreset(random.nextInt(6));
                n.setMouthPreset(random.nextInt(6));
                n.setFeatureSet(new FormID("000B79B6", "Skyrim.esm"));
                if(customization.alterHeights.getValue()){
                n.setHeight((float)random.nextInt(5) / 100 + 0.97F);
                }
                if(child){
                n.setHeight(0.87F);
                }
                if(customization.alterWeights.getValue()){
                n.setWeight((float)random.nextInt(60));
                }
                n.setFaceValue(NPC_.FacePart.EyesInOut, -0.76000F);
                n.setFaceValue(NPC_.FacePart.EyesUpDown, -0.46000F);
                n.setFaceValue(NPC_.FacePart.EyesForwardBack, -0.1000F);
                
                //tint layers
                //these are tint layers valid for khajiit
                int[] tintsmale = {2,3,7,8,9,10,11,12,13,21,22,23,24,25,26,27,28,29,30};
                int[] tintsfemale = {5,6,15,16,17,18,19,20,31,32,33,34,35,36,37,38,39,40};
                //random numbers - colors for tint layers
                short maxrandom = 20;
                short minvalue = 66;
                short basecolor = (short)random.nextInt(255-maxrandom-minvalue);
                float mainred = (short) (minvalue+basecolor+random.nextInt(maxrandom));
                float maingreen = (short) (minvalue+basecolor+random.nextInt(maxrandom));
                float mainblue = (short) (minvalue+basecolor+random.nextInt(maxrandom));
                basecolor = 25;
                float secondaryred = (short) (basecolor+random.nextInt(maxrandom));
                float secondarygreen = (short) (basecolor+random.nextInt(maxrandom));
                float secondaryblue = (short) (basecolor+random.nextInt(maxrandom));
                
                //i hope you have experience with tint layers because this is messy
                NPC_.TintLayer newlayer;
                ArrayList<NPC_.TintLayer> tintlayers = n.getTinting();
                tintlayers.clear();
                int count = 0;
                int chance = 12;
                if(!female) //male
                {
                    tintlayers.add(cloner.deepClone(templayer));
                    newlayer = tintlayers.get(0);
                    newlayer.setPreset(0);
                    newlayer.setIndex(1);
                    newlayer.setColor(RGBA.Red, (short)mainred);
                    newlayer.setColor(RGBA.Green, (short)maingreen);
                    newlayer.setColor(RGBA.Blue, (short)mainblue);
                    count++;
                    for (int tint : tintsmale){
                        if(random.nextInt(100)<chance)
                        {
                            tintlayers.add(cloner.deepClone(templayer));
                            newlayer = tintlayers.get(count);
                            newlayer.setPreset(0);
                            newlayer.setIndex(tint);
                            newlayer.setColor(RGBA.Red, (short)secondaryred);
                            newlayer.setColor(RGBA.Green, (short)secondarygreen);
                            newlayer.setColor(RGBA.Blue, (short)secondaryblue);
                            count++;
                        }
                    }
                }
                else //female
                {
                    tintlayers.add(cloner.deepClone(templayer));
                    newlayer = tintlayers.get(0);
                    newlayer.setPreset(0);
                    newlayer.setIndex(4);
                    newlayer.setColor(RGBA.Red, (short)mainred);
                    newlayer.setColor(RGBA.Green, (short)maingreen);
                    newlayer.setColor(RGBA.Blue, (short)mainblue);
                    count++;
                    for (int tint : tintsfemale){
                        if(random.nextInt(100)<chance)
                        {
                            tintlayers.add(cloner.deepClone(templayer));
                            newlayer = tintlayers.get(count);
                            newlayer.setPreset(0);
                            newlayer.setIndex(tint);
                            newlayer.setColor(RGBA.Red, (short)secondaryred);
                            newlayer.setColor(RGBA.Green, (short)secondarygreen);
                            newlayer.setColor(RGBA.Blue, (short)secondaryblue);
                            count++;
                        }
                    }
                }
                    
                //head color
                n.setFaceTint(RGB.Blue, mainblue/255);
                n.setFaceTint(RGB.Red, mainred/255);
                n.setFaceTint(RGB.Green, maingreen/255);
                patch.addRecord(n);
            }
            
        }
    }
}
