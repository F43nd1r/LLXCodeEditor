package com.app.lukas.llxcodeeditor;

import java.util.HashMap;

/**
 * Created by Lukas on 10.01.2015.
 */
final public class Methods {

    public static HashMap<String,String> Object;
    static {
        Object = new HashMap<>();
        Object.put("equals","Boolean");
        Object.put("getClass","unknown");
        Object.put("hashCode","Number");
        Object.put("toString","String");
    }

    public static HashMap<String,String> String;
    static {
        String = new HashMap<>(Object);
        String.put("bold","String");
        String.put("charAt","String");
        String.put("charCodeAt","Number");
        String.put("concat","String");
        String.put("indexOf","Number");
        String.put("italics","String");
        String.put("lastIndexOf","Number");
        String.put("match","Array");
        String.put("replace","String");
        String.put("search","Number");
        String.put("slice","String");
        String.put("small","String");
        String.put("split","Array");
        String.put("sub","String");
        String.put("substr","String");
        String.put("sup","String");
        String.put("toLowerCase","String");
        String.put("toUpperCase","String");
        String.put("trim","String");
    }

    public static HashMap<String,String> Number;
    static {
        Number = new HashMap<>(Object);
        Number.put("toFixed","Number");
        Number.put("toPrecision","Number");
        Number.put("toString","String");
    }

    @SuppressWarnings("unused")
    public static HashMap<String,String> Boolean = new HashMap<>(Object);

    public static HashMap<String,String> Array;
    static {
        Array = new HashMap<>(Object);
        Array.put("concat","Array");
        Array.put("indexOf","Number");
        Array.put("join","String");
        Array.put("lastIndexOf","Number");
        Array.put("pop","unknown");
        Array.put("push","Number");
        Array.put("reverse","Array");
        Array.put("shift","unknown");
        Array.put("slice","Array");
        Array.put("sort","Array");
        Array.put("splice","Array");
        Array.put("toString","String");
        Array.put("unshift","Number");
    }

    public static HashMap<String,String> LL;
    static {
        LL = new HashMap<>(Object);
        LL.put("bindClass","Boolean");
        LL.put("createImage","Image");
        LL.put("createScript","Script");
        LL.put("deleteScript","void");
        LL.put("getAllDesktops","LLArray");
        LL.put("getAllScriptMatching","LLArray");
        LL.put("getContainerById","Container");
        LL.put("getContext","Context");
        LL.put("getCurrentDesktop","Desktop");
        LL.put("getCurrentScript","Script");
        LL.put("getDesktopByName","Desktop");
        LL.put("getEvent","Event");
        LL.put("getHomeDesktop","Desktop");
        LL.put("getItemById","Item");
        LL.put("getLockscreenDesktop","Desktop");
        LL.put("getOpenFolders","LLArray");
        LL.put("getScriptById","Script");
        LL.put("getScriptByName","Script");
        LL.put("goToDesktop","void");
        LL.put("goToDesktopPosition","void");
        LL.put("isLocked","Boolean");
        LL.put("isPaused","Boolean");
        LL.put("loadRawResource","String");
        LL.put("pickColor","Number");
        LL.put("pickImage","Image");
        LL.put("pickNumericValue","Number");
        LL.put("runScript","void");
        LL.put("save","void");
        LL.put("sendTaskerIntent","Boolean");
        LL.put("startActivity","Boolean");
        LL.put("startActivityForResult","Boolean");
        LL.put("unlock","void");
        LL.put("writeToLogFile","void");
    }

    public  static HashMap<String,String> Window;
    static {
        Window = new HashMap<>(Object);
        Window.put("alert","void");
        Window.put("clearTimeout","void");
        Window.put("confirm","Boolean");
        Window.put("prompt","String");
        Window.put("setTimeout","Number");
    }

    public static HashMap<String,String> Android;
    static {
        Android = new HashMap<>(Object);
        Android.put("makeNewToast","show");
    }

    public static HashMap<String,String> LLArray;
    static {
        LLArray = new HashMap<>(Object);
        LLArray.put("getAt","unknown");
        LLArray.put("getLength","Number");
        LLArray.put("toString","String");
    }

    public static HashMap<String,String> Box;
    static {
        Box = new HashMap<>(Object);
        Box.put("getAlignmentH","String");
        Box.put("getAlignmentV","String");
        Box.put("getColor","Number");
        Box.put("getSize","Number");
        Box.put("setAlignment","void");
        Box.put("setColor","void");
        Box.put("setSize","void");
    }

    public static HashMap<String,String> Container;
    static {
        Container = new HashMap<>(Object);
        Container.put("addFolder","Folder");
        Container.put("addPanel","Panel");
        Container.put("addShortcut","Shortcut");
        Container.put("addStopPoint","StopPoint");
        Container.put("cancelFling","void");
        Container.put("getBoundingBox","Rect");
        Container.put("getCellHeight","Number");
        Container.put("getCellWidth","Number");
        Container.put("getHeight","Number");
        Container.put("getId","Number");
        Container.put("getItemById","Item");
        Container.put("getItemByName","Item");
        Container.put("getItemZIndex","Number");
        Container.put("getItems","LLArray");
        Container.put("getOpener","Item");
        Container.put("getParent","Container");
        Container.put("getPositionScale","Number");
        Container.put("getPositionX","Number");
        Container.put("getPositionY","Number");
        Container.put("getProperties","PropertySet");
        Container.put("getTag","String");
        Container.put("getType","String");
        Container.put("getWidth","String");
        Container.put("removeItem","void");
        Container.put("setItemZIndex","void");
        Container.put("setPosition","void");
        Container.put("setTag","void");
        Container.put("toString","String");
        Container.put("translateIntoScreenCoordX","Number");
        Container.put("translateIntoScreenCoordY","Number");
    }

    public static HashMap<String,String> Desktop;
    static {
        Desktop = new HashMap<>(Container);
        Desktop.put("getName","String");
    }

    public static HashMap<String,String> Event;
    static {
        Event = new HashMap<>(Object);
        Event.put("getContainer","Container");
        Event.put("getData","String");
        Event.put("getDate","Number");
        Event.put("getItem","Item");
        Event.put("getSource","String");
        Event.put("getTouchScreenX","Number");
        Event.put("getTouchScreenY","Number");
        Event.put("getTouchX","Number");
        Event.put("getTouchY","Number");
    }

    public static HashMap<String,String> EventHandler;
    static {
        EventHandler = new HashMap<>();
        EventHandler.put("getAction","Number");
        EventHandler.put("getData","String");
    }

    public static HashMap<String,String> Item;
    static {
        Item = new HashMap<>(Object);
        Item.put("getBoxBackground","Image");
        Item.put("getCell","Rect");
        Item.put("getHeight","Number");
        Item.put("getId","Number");
        Item.put("getName","String");
        Item.put("getParent","Container");
        Item.put("getPositionX","Number");
        Item.put("getPositionY","Number");
        Item.put("getProperties","PropertySet");
        Item.put("getRotation","Number");
        Item.put("getScaleX","Number");
        Item.put("getScaleY","Number");
        Item.put("getSkewX","Number");
        Item.put("getSkewY","Number");
        Item.put("getTag","String");
        Item.put("getType","String");
        Item.put("getWidth","Number");
        Item.put("isVisible","Boolean");
        Item.put("setBoxBackground","void");
        Item.put("setCell","void");
        Item.put("setName","void");
        Item.put("setPosition","void");
        Item.put("setRotation","void");
        Item.put("setScale","void");
        Item.put("setSize","void");
        Item.put("setSkew","void");
        Item.put("setTag","void");
        Item.put("setVisibility","void");
        Item.put("toString","String");
    }

    public static HashMap<String,String> Shortcut;
    static {
        Shortcut = new HashMap<>(Item);
        Shortcut.put("getCustomIcon","Image");
        Shortcut.put("getDefaultIcon","Image");
        Shortcut.put("getImage","Image");
        Shortcut.put("getIntent","Intent");
        Shortcut.put("getLabel","String");
        Shortcut.put("launch","void");
        Shortcut.put("setCustomIcon","void");
        Shortcut.put("setDefaultIcon","void");
        Shortcut.put("setImage","void");
        Shortcut.put("setIntent","void");
        Shortcut.put("setLabel","void");
        Shortcut.put("toString","void");
    }

    public static HashMap<String,String> Folder;
    static {
        Folder = new HashMap<>(Shortcut);
        Folder.put("close","void");
        Folder.put("getContainer","Container");
        Folder.put("getWindowBackground","Image");
        Folder.put("isOpen","Boolean");
        Folder.put("launch","void");
        Folder.put("open","void");
        Folder.put("openFrom","void");
        Folder.put("setWindowBackgroundImage","void");
    }

    public static HashMap<String,String> Image;
    static {
        Image = new HashMap<>(Object);
        Image.put("delete","void");
        Image.put("draw","Canvas");
        Image.put("getBitmap","Bitmap");
        Image.put("getHeight","Number");
        Image.put("getWidth","Number");
        Image.put("isNinePatch","Boolean");
        Image.put("save","void");
        Image.put("update","void");
    }

    public static HashMap<String,String> Panel;
    static {
        Panel = new HashMap<>(Item);
        Panel.put("getContainer","Container");
    }

    public static HashMap<String,String> PropertyEditor;
    static {
        PropertyEditor = new HashMap<>(Object);
        PropertyEditor.put("commit","void");
        PropertyEditor.put("getBox","Box");
        PropertyEditor.put("setBoolean","PropertyEditor");
        PropertyEditor.put("setEventHandler","PropertyEditor");
        PropertyEditor.put("setFloat","PropertyEditor");
        PropertyEditor.put("setInteger","PropertyEditor");
        PropertyEditor.put("setString","PropertyEditor");
    }

    public static HashMap<String,String> PropertySet;
    static {
        PropertySet = new HashMap<>(Object);
        PropertySet.put("edit","PropertyEditor");
        PropertySet.put("getBoolean","Boolean");
        PropertySet.put("getBox","Box");
        PropertySet.put("getEventHandler","EventHandler");
        PropertySet.put("getFloat","Number");
        PropertySet.put("getInteger","Number");
        PropertySet.put("getString","String");
    }

    public static HashMap<String,String> Rect;
    static {
        Rect = new HashMap<>(Object);
        Rect.put("getBottom","Number");
        Rect.put("getLeft","Number");
        Rect.put("getRight","Number");
        Rect.put("getTop","Number");
        Rect.put("toString","String");
    }

    public static HashMap<String,String> Script;
    static {
        Script = new HashMap<>(Object);
        Script.put("getId","Number");
        Script.put("getName","String");
        Script.put("getTag","String");
        Script.put("getText","String");
        Script.put("hasFlag","Boolean");
        Script.put("setFlag","void");
        Script.put("setName","void");
        Script.put("setTag","void");
        Script.put("setText","void");
    }

    public static HashMap<String,String> StopPoint;
    static {
        StopPoint = new HashMap<>(Item);
        StopPoint.put("getDirection","Number");
        StopPoint.put("getMatchingEdges","Number");
        StopPoint.put("getMatchingWhat","Number");
        StopPoint.put("isBarrier","Boolean");
        StopPoint.put("isDesktopWide","Boolean");
        StopPoint.put("isSnapping","Boolean");
        StopPoint.put("setBarrier","void");
        StopPoint.put("setDesktopWide","void");
        StopPoint.put("setDirection","void");
        StopPoint.put("setMatchingEdges","void");
        StopPoint.put("setMatchingWhat","void");
        StopPoint.put("setSnapping","void");
    }

    public static HashMap<String,String> Toast;
    static {
        Toast = new HashMap<>(Object);
        Toast.put("show","void");
    }

    private Methods(){}
}
