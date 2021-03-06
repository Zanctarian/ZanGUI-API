

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

/**
 * 
 * I have worked really hard to make GUI's both fast and
 * easy.. to go! Please support me and don't steal this
 * API to claim it as your own >_<. I know this is a little
 * messy but I was going for end result other than neatness.
 * 
 * Code critics beware!
 * 
 * This is an early version of the API, so make sure to get
 * a hold of me if you find a bug.
 * 
 * Supported Versions (As I know and tested): 1.8 -> 1.13.2
 * 
 * @author Zanctarian
 *
 * Copyright © Zanctarian 2018
 */
public class ZanGUI implements Listener {
	
	final Inventory i;
	final Plugin pl;
	static Logger log;
	
	final static String prefix = "[ZanGUI] ";
	
	ClickListener clickListener;
	InventoryOpenListener openListener;
	InventoryCloseListener closeListener;
	
	/**
	 * <p>API created by Zanctarian to make GUI's easy for beginners.</p>
	 * 
	 * @author Zanctarian
	**/
	public ZanGUI(Plugin plugin, String title, int rows)
	{
		this.pl = plugin;
		pl.getServer().getPluginManager().registerEvents(this, plugin);
		log = pl.getLogger();
		this.i = Bukkit.createInventory(null, rows*9, ChatColor.translateAlternateColorCodes('&', title));
	}
	
	/**
	 * <p>Gets the inventory used in this class.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code Inventory i = zangui.getRawInventory();}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return Inventory
	 */
	public Inventory getRawInventory()
	{
		return i;
	}
	
	/**
	 * <p>Place an item into the inventory.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code ItemStack item = <p>{@code ItemStack i = ZanGUI.createItem("STICK");}</p>}</p>
	 * <p>{@code zangui.placeItem(0, item);}</p>
	 * 
	 * <p>The code above would place an item on the first slot of a 9 slot inventory.</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI placeItem(int slot, ItemStack item)
	{
		i.setItem(slot, item);
		return this;
	}
	
	/**
	 * <p>Removes an item from the inventory based on what slot it is.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.removeItem(0);}</p>
	 * 
	 * <p>The example removes an item from the first slot on a 9 slot inventory.</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	@SuppressWarnings("deprecation")
	public ZanGUI removeItem(int slot)
	{
		if(i.getItem(slot) != null)i.remove(slot);
		return this;
	}

	/**
	 * <p>Removes an item from the inventory based on what item it is.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code ItemStack i = ZanGUI.createItem("STICK");}</p>
	 * <p>{@code zangui.removeItem(i);}</p>
	 * 
	 * <p>The example removes an item from a 9 slot inventory.</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI removeItem(ItemStack item)
	{
		if(i.contains(item))i.remove(item);
		return this;
	}
	
	/**
	 * <p>Check to see if an item exist in a slot.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code if(zangui.doesSlotContainItem(2))Bukkit.broadcastMessage("This slot works!");}
	 * 
	 * @author Zanctarian
	 * 
	 * @return true/false
	**/
	public boolean doesSlotContainItem(int slot)
	{
		return i.getItem(slot) != null;
	}
	
	/**
	 * <p>Check to see if an item exist in an inventory.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code ItemStack item = ZanGUI.createItem("STICK");}</p>
	 * <p>{@code if(zangui.isItemInInventory(item))Bukkit.broadcastMessage("This item works!");}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return true/false
	**/
	public boolean isItemInInventory(ItemStack item)
	{
		return i.contains(item);
	}
	
	/**
	 * <p>Make a player open the custom GUI.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.openInventory(player);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI openInventory(Player to)
	{
		to.openInventory(i);
		return this;
	}
	
	/**
	 * <p>Force the GUI to close. This already checks if the
	 * GUI is opened.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.forceCloseInventory(player);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI forceCloseInventory(Player to)
	{
		if(hasInventoryOpened(to))to.closeInventory();
		return this;
	}
	
	/**
	 * <p>Check to see if a player has this GUI opened.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code if(zangui.hasInventoryOpened(player))Bukkit.broadcastMessage("Yes");}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public boolean hasInventoryOpened(Player to)
	{
		return check(to.getInventory(), i);
	}
	
	/**
	 * <p>Update the inventory while the player is in it.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.placeItem(0, newItem);}</p>
	 * <p>{@code zangui.updateInventory(player);)</p> 
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	@SuppressWarnings("deprecation")
	public ZanGUI updateInventory(Player to)
	{
		
		if(hasInventoryOpened(to))to.updateInventory();
		return this;
	}
	
	
	/**
	 * <p>Hook in the item click listener.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.hookItemClick(new ClickListener(ClassWithListener.class));}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI hookItemClick(ClickListener clickListener)
	{
		this.clickListener = clickListener;
		return this;
	}
	
	/**
	 * <p>Hook in the open inventory listener.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.hookInventoryOpen(new InventoryOpenListener(ClassWithListener.class));}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI hookInventoryOpen(InventoryOpenListener openListener)
	{
		this.openListener = openListener;
		return this;
	}
	
	/**
	 * <p>Hook in the close inventory listener.</p>
	 * 
	 * <h4>Example:</h4>
	 * <p>{@code ZanGUI zangui = new ZanGUI(plugin, "Title", 1);}</p>
	 * <p>{@code zangui.hookInventoryClose(new InventoryCloseListener(ClassWithListener.class));}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return The default class.
	**/
	public ZanGUI hookInventoryClose(InventoryCloseListener closeListener)
	{
		this.closeListener = closeListener;
		return this;
	}
	
	/**
	 * <p>Ignore this</p>
	 * 
	 * @author Zanctarian
	 */
	@EventHandler
	public void onFirstRegistration(InventoryClickEvent e)
	{
		if(clickListener!=null && check(e.getInventory(), i))
			clickListener.handleClickEvent(e, this);
	}
	
	/**
	 * <p>Ignore this</p>
	 * 
	 * @author Zanctarian
	 */
	@EventHandler
	public void onSecondRegistration(InventoryOpenEvent e)
	{
		if(openListener!=null && check(e.getInventory(), i))
			openListener.handleInventoryOpenEvent(e, this);	
	}
	
	/**
	 * <p>Ignore this</p>
	 * 
	 * @author Zanctarian
	 */
	@EventHandler
	public void onThirdRegistration(InventoryCloseEvent e)
	{
		if(closeListener!=null && check(e.getInventory(), i))
			closeListener.handleInventoryCloseEvent(e, this);
	}
	
	boolean check(Inventory inv0, Inventory inv1){
		if (!inv0.getName().equals(inv1.getName())) return false;
		if (inv0.getSize() != inv1.getSize()) return false;
		if (!inv0.getTitle().equals(inv1.getTitle())) return false;
		if (!inv0.getType().equals(inv1.getType())) return false;
		if (inv0.getSize() != inv1.getSize()) return false;
		if (inv0.getViewers().size() != inv1.getViewers().size()) return false;
		
		for (int index = 0; index < inv0.getSize(); index++){
			ItemStack a = inv0.getItem(index);
			ItemStack b = inv1.getItem(index);
			if (!((a == null && b == null) || a.equals(b))) return false;
		}
		
		for (int index = 0; index < inv0.getViewers().size(); index++){
			HumanEntity a = inv0.getViewers().get(index);
			HumanEntity b = inv1.getViewers().get(index);
			if (!((a == null && b == null) || a.equals(b))) return false;
		}
		
		return true;
	}
	
	/**
	 * <p>Creates an item without the trouble!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createItem(Material.BOOK);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createItem(Material m)
	{
		return new ItemStack(m, 1);
	}
	
	/**
	 * <p>Creates an item with bytes without the trouble!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createItem(Material.SKULL_ITEM, (byte) 3);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createItem(Material m, byte b)
	{
		return new ItemStack(m, 1, b);
	}
	
	/**
	 * <p>Creates an item without the trouble!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createItem("BOOK");}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createItem(String s)
	{
		return new ItemStack(Material.valueOf(s.toUpperCase()), 1);
	}
	
	/**
	 * <p>Creates an item with bytes without the trouble!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createItem("SKULL_ITEM", (byte) 3);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createItem(String s, byte b)
	{
		return new ItemStack(Material.valueOf(s.toUpperCase()), 1, b);
	}
	
	/**
	 * <p>Creates a skeleton skull easily!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createSkeletonSkull();}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createSkeletonSkull()
	{
		return new ItemStack(Material.SKULL_ITEM, 1, (byte) 0);
	}
	
	/**
	 * <p>Creates a wither skeleton skull easily!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createWitherSkeletonSkull();}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createWitherSkeletonSkull()
	{
		return new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
	}
	
	/**
	 * <p>Creates a zombie skull easily!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createZombieSkull();}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createZombieSkull()
	{
		return new ItemStack(Material.SKULL_ITEM, 1, (byte) 2);
	}
	
	/**
	 * <p>Creates a human skull easily!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createHumanSkull();}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createHumanSkull()
	{
		return new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
	}
	
	/**
	 * <p>Creates a creeper skull easily!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createCreeperSkull();}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createCreeperSkull()
	{
		return new ItemStack(Material.SKULL_ITEM, 1, (byte) 4);
	}
	
	/**
	 * <p>Creates a player skull easily!</p>
	 * 
	 * <p>{@code ItemStack is = ZanGUI.createPlayerSkull("Notch");}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static ItemStack createPlayerSkull(String playerName)
	{
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta m = (SkullMeta) i.getItemMeta();
		m.setOwner(playerName);
		i.setItemMeta(m);
		return i;
	}
	
	/**
	 * <p>Registers a click listener.</p>
	 * 
	 * <p>{@code ClickListener clickListener = new ClickListener(ClassWithListeningMethod.class);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static class ClickListener {
		
		Class<?> currentClass;
		Types type;
		
		public ClickListener(Class<?> currentClass)
		{
			this.currentClass = currentClass;
			this.type = Types.CLICK_EVENT;
		}
		
		void handleClickEvent(InventoryClickEvent e, ZanGUI gui)
		{
			try {
				if(e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR))
				{	
					e.setCancelled(true);
					ClickData data = new ClickData(e, type, currentClass);
					data.invokeFunction(gui);
				}
			} catch (ZanGUIException ex) {
				log.severe(prefix+ex.getMessage());
			}
		}
		
	}
	
	/**
	 * <p>Registers an inventory open listener.</p>
	 * 
	 * <p>{@code InventoryOpenListener inventoryOpenListener = new InventoryOpenListener(ClassWithListeningMethod.class);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static class InventoryOpenListener {
		
		Class<?> currentClass;
		Types type;
		
		public InventoryOpenListener(Class<?> currentClass)
		{
			this.currentClass = currentClass;
			this.type = Types.OPEN_EVENT;
		}
		
		void handleInventoryOpenEvent(InventoryOpenEvent e, ZanGUI gui)
		{
			try {
				OpenData data = new OpenData(e, type, currentClass);
				data.invokeFunction(gui);
			} catch (ZanGUIException ex) {
				log.severe(prefix+ex.getMessage());
			}
		}
		
	}
	
	/**
	 * <p>Registers an inventory close listener.</p>
	 * 
	 * <p>{@code InventoryCloseListener inventoryCloseListener = new InventoryCloseListener(ClassWithListeningMethod.class);}</p>
	 * 
	 * @author Zanctarian
	 * 
	 * @return ItemStack
	**/
	public static class InventoryCloseListener {
		
		Class<?> currentClass;
		Types type;
		
		public InventoryCloseListener(Class<?> currentClass)
		{
			this.currentClass = currentClass;
			this.type = Types.CLOSE_EVENT;
		}
		
		void handleInventoryCloseEvent(InventoryCloseEvent e, ZanGUI gui)
		{
			try {
				CloseData data = new CloseData(e, type, currentClass);
				data.invokeFunction(gui);
			} catch (ZanGUIException ex) {
				log.severe(prefix+ex.getMessage());
			}
		}
		
	}
	
	static class ClickData {
		
		Types type;
		InventoryClickEvent listen;
		Class<?> clazz;
		
		ClickData(InventoryClickEvent listen, Types type, Class<?> clazz) throws ZanGUIException
		{
			this.type = type;
			this.listen = listen;
			this.clazz = clazz;
		}
		
		void invokeFunction(ZanGUI gui) throws ZanGUIException
		{
			try {
				ObjectFactory.create(clazz, type, listen, gui);
			} catch (Exception e) {
				throw new ZanGUIException(e.getMessage());
			}
		}
		
	}
	
	static class OpenData {
		
		Types type;
		InventoryOpenEvent listen;
		Class<?> clazz;
		
		OpenData(InventoryOpenEvent listen, Types type, Class<?> clazz) throws ZanGUIException
		{
			this.type = type;
			this.listen = listen;
			this.clazz = clazz;
		}
		
		void invokeFunction(ZanGUI gui) throws ZanGUIException
		{
			try {
				ObjectFactory.create(clazz, type, listen, gui);
			} catch (Exception e) {
				throw new ZanGUIException(e.getMessage());
			}
		}
		
	}
	
	static class CloseData {
		
		Types type;
		InventoryCloseEvent listen;
		Class<?> clazz;
		
		CloseData(InventoryCloseEvent listen, Types type, Class<?> clazz) throws ZanGUIException
		{
			this.type = type;
			this.listen = listen;
			this.clazz = clazz;
		}
		
		void invokeFunction(ZanGUI gui) throws ZanGUIException
		{
			try {
				ObjectFactory.create(clazz, type, listen, gui);
			} catch (Exception e) {
				throw new ZanGUIException(e.getMessage());
			}
		}
		
	}
	
	
	static class ZanGUIException extends Exception {

		/**
		 * 
		 */
		static final long serialVersionUID = 1L;
		
		ZanGUIException(String s)
		{
			super(s);		
		}
	}
	
	static class ObjectFactory {
	    public static Object create(Class<?> clazz, Types type, Event e, ZanGUI gui) throws Exception {
	        Object object = clazz.newInstance();
	        Method[] methods = clazz.getDeclaredMethods();
	        for (Method method : methods) {
	            Annotation[] annotations = method.getDeclaredAnnotations();
	            for (Annotation annotation : annotations) {
	                if(annotation instanceof GUIEventHandler) {
	                		if(method.getParameterCount() < 1)
	                			return null;
	                		switch(type) {
	                		case CLICK_EVENT:
	                			if(!(e instanceof InventoryClickEvent))
	                				break;
	                			if(method.getParameterTypes()[0].equals(GUIClickEvent.class))
	                				method.invoke(object, (GUIClickEvent) new GCE((InventoryClickEvent) e, gui));
	                			break;
	                		case OPEN_EVENT:
	                			if(!(e instanceof InventoryOpenEvent))
	                				break;
	                			if(method.getParameterTypes()[0].equals(GUIOpenEvent.class))
	                				method.invoke(object, (GUIOpenEvent) new GOE((InventoryOpenEvent) e, gui));
	                			break;
	                		case CLOSE_EVENT:
	                			if(!(e instanceof InventoryCloseEvent))
	                				break;
	                			if(method.getParameterTypes()[0].equals(GUICloseEvent.class))
	                				method.invoke(object, (GUICloseEvent) new GCLE((InventoryCloseEvent) e, gui));
	                			break;
	                		}
	                }
	            }
	        }
	        return object;
	    }
	}
	
	/**
	 * <p>Event for the ClickListener.</p>
	 * 
	 * <p>{@code @GUIEventHandler}</p>
	 * <p><code>public void anyName(GUIClickEvent e) {</code></p>
	 * <p><code>//Insert code here</code></p>
	 * <p><code>}</code></p>
	 * 
	 * @author Zanctarian
	**/
	public static interface GUIClickEvent {
		public InventoryClickEvent getEvent();
		public ZanGUI getGUI();
	}
	
	static class GCE extends Event implements GUIClickEvent {

		HandlerList list = new HandlerList();
		
		InventoryClickEvent e;
		ZanGUI gui;
		
		public GCE(InventoryClickEvent e, ZanGUI gui)
		{
			this.e = e;
			this.gui = gui;
		}
		
		@Override
		public HandlerList getHandlers() {
			return list;
		}

		@Override
		public InventoryClickEvent getEvent() {
			return e;
		}

		@Override
		public ZanGUI getGUI() {
			return gui;
		}
		
	}
	
	/**
	 * <p>Event for the InventoryOpenListener.</p>
	 * 
	 * <p>{@code @GUIEventHandler}</p>
	 * <p><code>public void anyName(GUIOpenEvent e) {</code></p>
	 * <p><code>//Insert code here</code></p>
	 * <p><code>}</code></p>
	 * 
	 * @author Zanctarian
	**/
	public static interface GUIOpenEvent {
		public InventoryOpenEvent getEvent();
		public ZanGUI getGUI();
	}
	
	static class GOE extends Event implements GUIOpenEvent {

		HandlerList list = new HandlerList();
		
		InventoryOpenEvent e;
		ZanGUI gui;
		
		public GOE(InventoryOpenEvent e, ZanGUI gui)
		{
			this.e = e;
			this.gui = gui;
		}
		
		@Override
		public HandlerList getHandlers() {
			return list;
		}

		@Override
		public InventoryOpenEvent getEvent() {
			return e;
		}

		@Override
		public ZanGUI getGUI() {
			return gui;
		}
		
	}
	
	/**
	 * <p>Event for the InventoryCloseListener.</p>
	 * 
	 * <p>{@code @GUIEventHandler}</p>
	 * <p><code>public void anyName(GUICloseEvent e) {</code></p>
	 * <p><code>//Insert code here</code></p>
	 * <p><code>}</code></p>
	 * 
	 * @author Zanctarian
	**/
	public static interface GUICloseEvent {
		public InventoryCloseEvent getEvent();
		public ZanGUI getGUI();
	}
	
	static class GCLE extends Event implements GUICloseEvent {

		HandlerList list = new HandlerList();
		
		InventoryCloseEvent e;
		ZanGUI gui;
		
		public GCLE(InventoryCloseEvent e, ZanGUI gui)
		{
			this.e = e;
			this.gui = gui;
		}
		
		@Override
		public HandlerList getHandlers() {
			return list;
		}

		@Override
		public InventoryCloseEvent getEvent() {
			return e;
		}

		@Override
		public ZanGUI getGUI() {
			return gui;
		}
		
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface GUIEventHandler {}
	
	enum Types {
		CLICK_EVENT, OPEN_EVENT, CLOSE_EVENT;
	}

}
