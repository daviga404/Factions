package com.massivecraft.factions.entity;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.Perm;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.TerritoryAccess;
import com.massivecraft.massivecore.PredictateIsRegistered;
import com.massivecraft.massivecore.Prioritized;
import com.massivecraft.massivecore.Registerable;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;

public class MPerm extends Entity<MPerm> implements Prioritized, Registerable
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public final static transient String ID_BUILD = "build";
	public final static transient String ID_PAINBUILD = "painbuild";
	public final static transient String ID_DOOR = "door";
	public final static transient String ID_BUTTON = "button";
	public final static transient String ID_LEVER = "lever";
	public final static transient String ID_CONTAINER = "container";
	
	public final static transient String ID_NAME = "name";
	public final static transient String ID_DESC = "desc";
	public final static transient String ID_MOTD = "motd";
	public final static transient String ID_INVITE = "invite";
	public final static transient String ID_KICK = "kick";
	public final static transient String ID_TITLE = "title";
	public final static transient String ID_HOME = "home";
	public final static transient String ID_SETHOME = "sethome";
	public final static transient String ID_DEPOSIT = "deposit";
	public final static transient String ID_WITHDRAW = "withdraw";
	public final static transient String ID_TERRITORY = "territory";
	public final static transient String ID_ACCESS = "access";
	public final static transient String ID_REL = "rel";
	public final static transient String ID_DISBAND = "disband";
	public final static transient String ID_FLAGS = "flags";
	public final static transient String ID_PERMS = "perms";

	public final static transient int PRIORITY_BUILD = 1000;
	public final static transient int PRIORITY_PAINBUILD = 2000;
	public final static transient int PRIORITY_DOOR = 3000;
	public final static transient int PRIORITY_BUTTON = 4000;
	public final static transient int PRIORITY_LEVER = 5000;
	public final static transient int PRIORITY_CONTAINER = 6000;
	
	public final static transient int PRIORITY_NAME = 7000;
	public final static transient int PRIORITY_DESC = 8000;
	public final static transient int PRIORITY_MOTD = 9000;
	public final static transient int PRIORITY_INVITE = 10000;
	public final static transient int PRIORITY_KICK = 11000;
	public final static transient int PRIORITY_TITLE = 12000;
	public final static transient int PRIORITY_HOME = 13000;
	public final static transient int PRIORITY_SETHOME = 14000;
	public final static transient int PRIORITY_DEPOSIT = 15000;
	public final static transient int PRIORITY_WITHDRAW = 16000;
	public final static transient int PRIORITY_TERRITORY = 17000;
	public final static transient int PRIORITY_ACCESS = 18000;
	public final static transient int PRIORITY_REL = 19000;
	public final static transient int PRIORITY_DISBAND = 20000;
	public final static transient int PRIORITY_FLAGS = 21000;
	public final static transient int PRIORITY_PERMS = 22000;
	
	// -------------------------------------------- //
	// META: CORE
	// -------------------------------------------- //
	
	public static MPerm get(Object oid)
	{
		return MPermColl.get().get(oid);
	}
	
	public static List<MPerm> getAll()
	{
		return MPermColl.get().getAll(PredictateIsRegistered.get());
	}
	
	public static void setupStandardPerms()
	{
		getPermBuild();
		getPermPainbuild();
		getPermDoor();
		getPermButton();
		getPermLever();
		getPermContainer();
		
		getPermName();
		getPermDesc();
		getPermMotd();
		getPermInvite();
		getPermKick();
		getPermTitle();
		getPermHome();
		getPermSethome();
		getPermDeposit();
		getPermWithdraw();
		getPermTerritory();
		getPermAccess();
		getPermRel();
		getPermDisband();
		getPermFlags();
		getPermPerms();
	}
	
	public static MPerm getPermBuild() { return getCreative(PRIORITY_BUILD, ID_BUILD, ID_BUILD, "edit the terrain", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.ALLY), true, true, true); }
	public static MPerm getPermPainbuild() { return getCreative(PRIORITY_PAINBUILD, ID_PAINBUILD, ID_PAINBUILD, "edit, take damage", new LinkedHashSet<Rel>(), true, true, true); }
	public static MPerm getPermDoor() { return getCreative(PRIORITY_DOOR, ID_DOOR, ID_DOOR, "use doors", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY), true, true, true); }
	public static MPerm getPermButton() { return getCreative(PRIORITY_BUTTON, ID_BUTTON, ID_BUTTON, "use stone buttons", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY), true, true, true); }
	public static MPerm getPermLever() { return getCreative(PRIORITY_LEVER, ID_LEVER, ID_LEVER, "use levers", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY), true, true, true); }
	public static MPerm getPermContainer() { return getCreative(PRIORITY_CONTAINER, ID_CONTAINER, ID_CONTAINER, "use containers", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER), true, true, true); }
	
	public static MPerm getPermName() { return getCreative(PRIORITY_NAME, ID_NAME, ID_NAME, "set name", MUtil.set(Rel.LEADER), false, true, true); }
	public static MPerm getPermDesc() { return getCreative(PRIORITY_DESC, ID_DESC, ID_DESC, "set description", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermMotd() { return getCreative(PRIORITY_MOTD, ID_MOTD, ID_MOTD, "set motd", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermInvite() { return getCreative(PRIORITY_INVITE, ID_INVITE, ID_INVITE, "invite players", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermKick() { return getCreative(PRIORITY_KICK, ID_KICK, ID_KICK, "kick members", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermTitle() { return getCreative(PRIORITY_TITLE, ID_TITLE, ID_TITLE, "set titles", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermHome() { return getCreative(PRIORITY_HOME, ID_HOME, ID_HOME, "teleport home", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY), false, true, true); }
	public static MPerm getPermSethome() { return getCreative(PRIORITY_SETHOME, ID_SETHOME, ID_SETHOME, "set the home", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermDeposit() { return getCreative(PRIORITY_DEPOSIT, ID_DEPOSIT, ID_DEPOSIT, "deposit money", MUtil.set(Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY), false, false, false); } // non editable, non visible.
	public static MPerm getPermWithdraw() { return getCreative(PRIORITY_WITHDRAW, ID_WITHDRAW, ID_WITHDRAW, "withdraw money", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermTerritory() { return getCreative(PRIORITY_TERRITORY, ID_TERRITORY, ID_TERRITORY, "claim or unclaim", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermAccess() { return getCreative(PRIORITY_ACCESS, ID_ACCESS, ID_ACCESS, "grant territory", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermRel() { return getCreative(PRIORITY_REL, ID_REL, ID_REL, "change relations", MUtil.set(Rel.LEADER, Rel.OFFICER), false, true, true); }
	public static MPerm getPermDisband() { return getCreative(PRIORITY_DISBAND, ID_DISBAND, ID_DISBAND, "disband the faction", MUtil.set(Rel.LEADER), false, true, true); }
	public static MPerm getPermFlags() { return getCreative(PRIORITY_FLAGS, ID_FLAGS, ID_FLAGS, "manage flags", MUtil.set(Rel.LEADER), false, true, true); }
	public static MPerm getPermPerms() { return getCreative(PRIORITY_PERMS, ID_PERMS, ID_PERMS, "manage permissions", MUtil.set(Rel.LEADER), false, true, true); }
	
	public static MPerm getCreative(int priority, String id, String name, String desc, Set<Rel> standard, boolean territory, boolean editable, boolean visible)
	{
		MPerm ret = MPermColl.get().get(id, false);
		if (ret != null)
		{
			ret.setRegistered(true);
			return ret;
		}
		
		ret = new MPerm(priority, name, desc, standard, territory, editable, visible);
		MPermColl.get().attach(ret, id);
		ret.setRegistered(true);
		ret.sync();
		
		return ret;
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public MPerm load(MPerm that)
	{
		this.priority = that.priority;
		this.name = that.name;
		this.desc = that.desc;
		this.standard = that.standard;
		this.territory = that.territory;
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
	public MPerm setPriority(int priority) { this.priority = priority; this.changed(); return this; }
	
	// Nice name / Display name
	// Example: "build"
	private String name = "defaultName";
	public String getName() { return this.name; }
	public MPerm setName(String name) { this.name = name; this.changed(); return this; }
	
	// Short description
	// Example: "edit the terrain"
	private String desc = "defaultDesc";
	public String getDesc() { return this.desc; }
	public MPerm setDesc(String desc) { this.desc = desc; this.changed(); return this; }
	
	// Standard value
	// Example: ... set of relations ...
	private Set<Rel> standard = new LinkedHashSet<Rel>();
	public Set<Rel> getStandard() { return this.standard; }
	public MPerm setStandard(Set<Rel> standard) { this.standard = standard; this.changed(); return this; }
	
	// Is this a territory permission?
	private boolean territory = false;
	public boolean isTerritory() { return this.territory; }
	public MPerm setTerritory(boolean territory) { this.territory = territory; this.changed(); return this; }
	
	// If it editable by the faction leader (or for who ever the permission is configured)
	// Example: true (all perms are editable by default)
	private boolean editable = false;
	public boolean isEditable() { return this.editable; }
	public MPerm setEditable(boolean editable) { this.editable = editable; this.changed(); return this; }
	
	// If the flag is visible or hidden.
	// Example: true (yeah we need to see this permission)
	// Explanation: Some flags are rendered meaningless by other plugins.
	private boolean visible = true;
	public boolean isVisible() { return this.visible; }
	public MPerm setVisible(boolean visible) { this.visible = visible; this.changed(); return this; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public MPerm()
	{
		// No argument constructor for GSON
	}
	
	public MPerm(int priority, String name, String desc, Set<Rel> standard, boolean territory, boolean editable, boolean visible)
	{
		this.priority = priority;
		this.name = name;
		this.desc = desc;
		this.standard = standard;
		this.territory = territory;
		this.editable = editable;
		this.visible = visible;
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public String createDeniedMessage(MPlayer mplayer, Faction hostFaction)
	{
		// Null Check
		if (mplayer == null) throw new NullPointerException("mplayer");
		if (hostFaction == null) throw new NullPointerException("hostFaction");
		
		String ret = Txt.parse("%s<b> does not allow you to %s<b>.", hostFaction.describeTo(mplayer, true), this.getDesc());
		
		Player player = mplayer.getPlayer();
		if (player != null && Perm.ADMIN.has(player))
		{
			ret += Txt.parse("\n<i>You can bypass by using " + Factions.get().getOuterCmdFactions().cmdFactionsAdmin.getUseageTemplate(false));
		}
		
		return ret;
	}
	
	public boolean has(Faction faction, Faction hostFaction)
	{
		// Null Check
		if (faction == null) throw new NullPointerException("faction");
		if (hostFaction == null) throw new NullPointerException("hostFaction");
		
		Rel rel = faction.getRelationTo(hostFaction);
		
		Set<Rel> permittedRelations = hostFaction.getPermittedRelations(this);
		return permittedRelations.contains(rel);
	}
	
	public boolean has(MPlayer mplayer, Faction hostFaction, boolean verboose)
	{
		// Null Check
		if (mplayer == null) throw new NullPointerException("mplayer");
		if (hostFaction == null) throw new NullPointerException("hostFaction");
		
		if (mplayer.isUsingAdminMode()) return true;
		
		Rel rel = mplayer.getRelationTo(hostFaction);
		Set<Rel> permittedRelations = hostFaction.getPermittedRelations(this);
		if (permittedRelations.contains(rel)) return true;
		
		if (verboose) mplayer.sendMessage(this.createDeniedMessage(mplayer, hostFaction));
		
		return false;
	}
	
	public boolean has(MPlayer mplayer, PS ps, boolean verboose)
	{
		// Null Check
		if (mplayer == null) throw new NullPointerException("mplayer");
		if (ps == null) throw new NullPointerException("ps");
		
		if (mplayer.isUsingAdminMode()) return true;
		
		TerritoryAccess ta = BoardColl.get().getTerritoryAccessAt(ps);
		Faction hostFaction = ta.getHostFaction();
		
		if (this.isTerritory())
		{
			Boolean hasTerritoryAccess = ta.hasTerritoryAccess(mplayer);
			if (hasTerritoryAccess != null)
			{
				if (verboose && !hasTerritoryAccess)
				{
					mplayer.sendMessage(this.createDeniedMessage(mplayer, hostFaction));
				}
				return hasTerritoryAccess;
			}
		}
		
		return this.has(mplayer, hostFaction, verboose);
	}

	// -------------------------------------------- //
	// UTIL: ASCII
	// -------------------------------------------- //
	
	public static String getStateHeaders()
	{
		String ret = "";
		for (Rel rel : Rel.values())
		{
			ret += rel.getColor().toString();
			ret += rel.toString().substring(0, 3);
			ret += " ";
		}
		
		return ret;
	}
	
	public String getStateInfo(Set<Rel> value, boolean withDesc)
	{
		String ret = "";
		
		for (Rel rel : Rel.values())
		{
			if (value.contains(rel))
			{
				ret += "<g>YES";
			}
			else
			{
				ret += "<b>NOO";
			}
			ret += " ";
		}
		
		String color = "<aqua>";
		if (!this.isVisible())
		{
			color = "<silver>";
		}
		else if (this.isEditable())
		{
			color = "<pink>";
		}
		
		ret += color;
		ret += this.getName();
		
		ret = Txt.parse(ret);
		
		if (withDesc) ret += " <i>" + this.getDesc();
		
		return ret;
	}
	
}
