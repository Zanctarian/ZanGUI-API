# ZanGUI-API

An easy to use API for GUIs!

For: Bukkit 1.8 - 1.13.2

## Getting Started

Below is a documentation on how to use this API. The API is a solo-class API so I recommend to copy the code and paste it into a file named "ZanGUI.java" in your project. It can be in any package.

### Prerequisites

A working version of Bukkit 1.8+ and (preferably) an IDE.

## Todo
- Add easy GUI animations

## Overview Example
```
//Method to run our code on
public void registerAndOpenGUI(Player toOpen)
{
  //Initialize the gui
  ZanGUI gui = new ZanGUI(plugin, "Title", 1);
  //Create Notch's head as an item.
  ItemStack head = ZanGUI.createPlayerSkull("Notch");
  //Create book item
  ItemStack book = ZanGUI.createItem(Material.BOOK);
  
  //Place the items then register the click event
  gui.placeItem(0, head)
  .placeItem(0, book)
  .hookItemClick(new ClickListener(ClassWithEventListener.class))
  .openInventory(p);
}

//Listener for the event
@GUIEventHandler
public void anyName(GUIClickEvent e)
{
  InventoryClickEvent ev = e.getEvent();
  ZanGUI gui = e.getGUI();
  
  //code here
}
```

## Intialize

Down here is a list of methods that is apart of the API.

### Initializer/Constructor
```
ZanGUI gui = new ZanGUI(Plugin plugin, String title, int numberOfRows);
```
*Note: Number of rows is how many rows you want in the GUI. Maximum number is 6.*

## All Methods

### Inventory getRawInventory()
Returns the natural inventory from the API.
```
Inventory i = gui.getRawInventory();
```

### ZanGUI placeItem(int slot, ItemStack item)
Place an item in a specified location inside the inventory.
Returns API's class.
```
ItemStack item = ZanGUI.createItem(Material.STICK);
gui.placeItem(0, item);
```

### ZanGUI removeItem(int slot)
Removes an item in a specified slot.
This method naturally checks if the item is in the slot.
Returns API's class.
```
gui.removeItem(0);
```

### ZanGUI removeItem(ItemStack item)
Removes a specified item.
This method naturally checks if the item is in the inventory.
Returns API's class.
```
ItemStack item = ZanGUI.createItem(Material.STICK);
gui.removeItem(item);
```

### boolean doesSlotContainItem(int slot)
Checks if an item exists in a slot.
Returns boolean.
```
if(gui.doesSlotContainItem(0)) {
  //Do something
}
```

### boolean isItemInInventory(ItemStack item)
Checks if an item is in the inventory.
Returns boolean.
```
ItemStack item = ZanGUI.createItem(Material.STICK);
if(gui.isItemInInventory()) {
  //Do something
}
```

### ZanGUI openInventory(Player to)
Makes player open the current inventory.
Returns API's class.
```
gui.openInventory(player);
```

### ZanGUI forceCloseInventory(Player to)
If the inventory is opened, force the player to close the inventory.
Returns API's class.
```
gui.forceCloseInventory(player);
```

### boolean hasInventoryOpened(Player to)
Checks to see if the player has the API's inventory opened.
Returns boolean.
```
if(gui.hasInventoryOpened(player)) {
  //Do something
}
```

### ZanGUI updateInventory(Player to)
Update the API's inventory while said player is in it. This can be used to animate.
Returns API's class.
```
gui.updateInventory(player);
```

### ZanGUI hookItemClick(ClickListener clickListener)
Hook in the ClickListener to this GUI.
Returns API's class.
```
ClickListener listener = new ClickListener(ClassWithListenerInIt.class);
gui.hookItemClick(listener);
```

### ZanGUI hookInventoryOpen(InventoryOpenListener openListener)
Hook in the InventoryOpenListener to this GUI.
Returns API's class.
```
InventoryOpenListener listener = new InventoryOpenListener(ClassWithListenerInIt.class);
gui.hookInventoryOpen(listener);
```

### ZanGUI hookInventoryClose(InventoryCloseListener openListener)
Hook in the InventoryCloseListener to this GUI.
Returns API's class.
```
InventoryCloseListener listener = new InventoryCloseListener(ClassWithListenerInIt.class);
gui.hookInventoryClose(listener);
```

## Static Methods

### ItemStack createItem(Material m)
Creates a new item with said material.
Returns ItemStack.
```
ItemStack item = ZanGUI.createItem(Material.BOOK);
```

### ItemStack createItem(Material m, byte b)
Creates a new item with said material with bytes.
Returns ItemStack.
```
ItemStack item = ZanGUI.createItem(Material.SKULL_ITEM, (byte) 3);
```

### ItemStack createItem(String s)
Creates a new item with said material as a string.
Returns ItemStack.
```
ItemStack item = ZanGUI.createItem("BOOK");
```

### ItemStack createItem(String s, byte b)
Creates a new item with said material as a string with bytes.
Returns ItemStack.
```
ItemStack item = ZanGUI.createItem("SKULL_ITEM", (byte) 3);
```

### ItemStack createSkeletonSkull()
Create an item that is a skull of a skeleton.
Returns ItemStack.
```
ItemStack item = ZanGUI.createSkeletonSkull();
```

### ItemStack createWitherSkeletonSkull()
Create an item that is a skull of a wither skeleton.
Returns ItemStack.
```
ItemStack item = ZanGUI.createWitherSkeletonSkull();
```

### ItemStack createZombieSkull()
Create an item that is a skull of a zombie. Pfft, like they have any.
Returns ItemStack.
```
ItemStack item = ZanGUI.createZombieSkull();
```

### ItemStack createHumanSkull()
Create an item that is a skull of a human.
Returns ItemStack.
```
ItemStack item = ZanGUI.createHumanSkull();
```

### ItemStack createCreeperSkull()
Create an item that is a skull of a creeper.
Returns ItemStack.
```
ItemStack item = ZanGUI.createCreeperSkull();
```

### ItemStack createPlayerSkull(String playerName)
Create an item that is a skull of a specified player.
Returns ItemStack.
```
ItemStack item = ZanGUI.createPlayerSkull("Notch");
```

## Inside Classes

### class ClickListener
Class for registering the GUI's click listener.
```
ClickListener listener = new ClickListener(ClassWithListenerInIt.class);
gui.hookItemClick(listener);
```

### class InventoryOpenListener
Class for registering the GUI's open inventory listener.
```
InventoryOpenListener listener = new InventoryOpenListener(ClassWithListenerInIt.class);
gui.hookInventoryOpen(listener);
```

### class InventoryCloseListener
Class for registering the GUI's close inventory listener.
```
InventoryCloseListener listener = new InventoryCloseListener(ClassWithListenerInIt.class);
gui.hookInventoryClose(listener);
```

### interface GUIClickEvent
Event to register a method listener with. Considered a class with a listener in it.
```
@GUIEventHandler
public void chooseAName(GUIClickEvent e)
{
  //Code here
}
```
#### Methods:
```InventoryClickEvent getEvent();```
```ZanGUI getGUI();```

### interface GUIOpenEvent
Event to register a method listener with. Considered a class with a listener in it.
```
@GUIEventHandler
public void anyName(GUIOpenEvent e)
{
  //Code here
}
```
#### Methods:
```InventoryOpenEvent getEvent();```
```ZanGUI getGUI();```

### interface GUIOpenEvent
Event to register a method listener with. Considered a class with a listener in it.
```
@GUIEventHandler
public void randomName(GUICloseEvent e)
{
  //Code here
}
```
#### Methods:
```InventoryCloseEvent getEvent();```
```ZanGUI getGUI();```

### @interface GUIEventHandler
Annotation for handling events.
```
@GUIEventHandler
public void onClick(GUIClickEvent e){
  //Code here
}

@GUIEventHandler
public void onInventoryOpen(GUIOpenEvent e){
  //Code here
}

@GUIEventHandler
public void onInventoryClose(GUICloseEvent e){
  //Code here
}
```

## Authors

* **Chase Blakemore** - *Zanctarian*
