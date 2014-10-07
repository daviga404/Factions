package com.massivecraft.factions.entity;

import java.util.List;

import com.massivecraft.massivecore.PredictateIsRegistered;
import com.massivecraft.massivecore.Prioritized;
import com.massivecraft.massivecore.Registerable;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.Txt;

public class MFlag extends Entity<MFlag> implements Prioritized, Registerable
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public final static transient String ID_OPEN = "open";
	public final static transient String ID_MONSTERS = "monsters";
	public final static transient String ID_POWERLOSS = "powerloss";
	public final static transient String ID_PVP = "pvp";
	public final static transient String ID_FRIENDLYFIRE = "friendlyfire";
	public final static transient String ID_EXPLOSIONS = "explosions";
	public final static transient String ID_OFFLINEEXPLOSIONS = "offlineexplosions";
	public final static transient String ID_FIRESPREAD = "firespread";
	public final static transient String ID_ENDERGRIEF = "endergrief";
	public final static transient String ID_PERMANENT = "permanent";
	public final static transient String ID_PEACEFUL = "peaceful";
	public final static transient String ID_INFPOWER = "infpower";
	
	public final static transient int PRIORITY_OPEN = 1000;
	public final static transient int PRIORITY_MONSTERS = 2000;
	public final static transient int PRIORITY_POWERLOSS = 3000;
	public final static transient int PRIORITY_PVP = 4000;
	public final static transient int PRIORITY_FRIENDLYFIRE = 5000;
	public final static transient int PRIORITY_EXPLOSIONS = 6000;
	public final static transient int PRIORITY_OFFLINEEXPLOSIONS = 7000;
	public final static transient int PRIORITY_FIRESPREAD = 8000;
	public final static transient int PRIORITY_ENDERGRIEF = 9000;
	public final static transient int PRIORITY_PERMANENT = 10000;
	public final static transient int PRIORITY_PEACEFUL = 11000;
	public final static transient int PRIORITY_INFPOWER = 12000;
	
	// -------------------------------------------- //
	// META: CORE
	// -------------------------------------------- //
	
	public static MFlag get(Object oid)
	{
		return MFlagColl.get().get(oid);
	}
	
	public static List<MFlag> getAll()
	{
		return MFlagColl.get().getAll(PredictateIsRegistered.get());
	}
	
	public static void setupStandardFlags()
	{
		getFlagOpen();
		getFlagMonsters();
		getFlagPowerloss();
		getFlagPvp();
		getFlagFriendlyire();
		getFlagExplosions();
		getFlagOfflineexplosions();
		getFlagFirespread();
		getFlagEndergrief();
		getFlagPermanent();
		getFlagPeaceful();
		getFlagInfpower();
	}
	
	public static MFlag getFlagOpen() { return getCreative(PRIORITY_OPEN, ID_OPEN, ID_OPEN, "Open factions can be joined without invite.", false, true, true); }
	public static MFlag getFlagMonsters() { return getCreative(PRIORITY_MONSTERS, ID_MONSTERS, ID_MONSTERS, "Can monsters spawn in this territory?", false, true, true); }
	public static MFlag getFlagPowerloss() { return getCreative(PRIORITY_POWERLOSS, ID_POWERLOSS, ID_POWERLOSS, "Is power lost on death in this territory?", true, false, true); }
	public static MFlag getFlagPvp() { return getCreative(PRIORITY_PVP, ID_PVP, ID_PVP, "Can you PVP in territory?", true, false, true); }
	public static MFlag getFlagFriendlyire() { return getCreative(PRIORITY_FRIENDLYFIRE, ID_FRIENDLYFIRE, ID_FRIENDLYFIRE, "Can friends hurt eachother here?", false, false, true); }
	public static MFlag getFlagExplosions() { return getCreative(PRIORITY_EXPLOSIONS, ID_EXPLOSIONS, ID_EXPLOSIONS, "Can explosions occur in this territory?", true, false, true); }
	public static MFlag getFlagOfflineexplosions() { return getCreative(PRIORITY_OFFLINEEXPLOSIONS, ID_OFFLINEEXPLOSIONS, ID_OFFLINEEXPLOSIONS, "Can explosions occur if faction is offline?", false, false, true); }
	public static MFlag getFlagFirespread() { return getCreative(PRIORITY_FIRESPREAD, ID_FIRESPREAD, ID_FIRESPREAD, "Can fire spread in territory?", true, false, true); }
	public static MFlag getFlagEndergrief() { return getCreative(PRIORITY_ENDERGRIEF, ID_ENDERGRIEF, ID_ENDERGRIEF, "Can endermen grief in this territory?", false, false, true); }
	public static MFlag getFlagPermanent() { return getCreative(PRIORITY_PERMANENT, ID_PERMANENT, ID_PERMANENT, "A permanent faction will never be deleted.", false, false, true); }
	public static MFlag getFlagPeaceful() { return getCreative(PRIORITY_PEACEFUL, ID_PEACEFUL, ID_PEACEFUL, "Always in truce with other factions.", false, false, true); }
	public static MFlag getFlagInfpower() { return getCreative(PRIORITY_INFPOWER, ID_INFPOWER, ID_INFPOWER, "This flag gives the faction infinite power.", false, false, true); }
	
	public static MFlag getCreative(int priority, String id, String name, String desc, boolean standard, boolean editable, boolean visible)
	{
		MFlag ret = MFlagColl.get().get(id, false);
		if (ret != null)
		{
			ret.setRegistered(true);
			return ret;
		}
		
		ret = new MFlag(priority, name, desc, standard, editable, visible);
		MFlagColl.get().attach(ret, id);
		ret.setRegistered(true);
		ret.sync();
		
		return ret;
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public MFlag load(MFlag that)
	{
		this.priority = that.priority;
		this.name = that.name;
		this.desc = that.desc;
		this.standard = that.standard;
		this.editable = that.editable;
		this.visible = that.visible;
		
		return this;
	}
	
	// -------------------------------------------- //
	// TRANSIENT FIELDS (Registered)
	// -------------------------------------------- //
	
	private transient boolean registered = false;
	public boolean isRegistered() { return this.registered; }
	public void setRegistered(boolean registered) { this.registered = registered; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// The sort order priority 1 is high up, 99999 is far down.
	private int priority = 0;
	@Override public int getPriority() { return this.priority; }
	public MFlag setPriority(int priority) { this.priority = priority; this.changed(); return this; }
	
	// Nice name / Display name
	// Example: "monsters"
	private String name = "defaultName";
	public String getName() { return this.name; }
	public MFlag setName(String name) { this.name = name; this.changed(); return this; }
	
	// Short description
	// Example: "Can monsters spawn in this territory?"
	private String desc = "defaultDesc";
	public String getDesc() { return this.desc; }
	public MFlag setDesc(String desc) { this.desc = desc; this.changed(); return this; }
	
	// Standard value
	// Example: false (per default monsters do not spawn in faction territory)
	private boolean standard = true;
	public boolean isStandard() { return this.standard; }
	public MFlag setStandard(boolean standard) { this.standard = standard; this.changed(); return this; }
	
	// If it editable by the faction leader (or for who ever the permission is configured)
	// Example: true (if players want to turn mob spawning on I guess they should be able to)
	private boolean editable = false;
	public boolean isEditable() { return this.editable; }
	public MFlag setEditable(boolean editable) { this.editable = editable; this.changed(); return this; }
	
	// If the flag is visible or hidden.
	// Example: true (yeah we need to see this flag)
	// Explanation: Some flags are rendered meaningless by other plugins. Say we have a creative mode server without any mobs. The server owner might want to hide this flag.
	private boolean visible = true;
	public boolean isVisible() { return this.visible; }
	public MFlag setVisible(boolean visible) { this.visible = visible; this.changed(); return this; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public MFlag()
	{
		// No argument constructor for GSON
	}
	
	public MFlag(int priority, String name, String desc, boolean standard, boolean editable, boolean visible)
	{
		this.priority = priority;
		this.name = name;
		this.desc = desc;
		this.standard = standard;
		this.editable = editable;
		this.visible = visible;
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public String getStateInfo(boolean value, boolean withDesc)
	{
		String valueDesc = value ? "<g>YES" : "<b>NOO";
		
		String color = "<aqua>";
		if (!this.isVisible())
		{
			color = "<silver>";
		}
		else if (this.isEditable())
		{
			color = "<pink>";
		}
		
		String ret = valueDesc + " " + color + this.getName();
		
		if (withDesc) ret += " <i>" + this.getDesc();
		
		ret = Txt.parse(ret);
		
		return ret;
	}
	
}
