package com.hkbank.pbcrs.model;

import java.util.List;

public class SysMenu {
    private String MENU_ID;
    private String  MENU_NAME;
    private String MENU_LEVEL;
    private String MENU_PARENT_ID;
    private String MENU_ADDRESS;
    private String MENU_ORDER;
    private String USERID;
    private List<SysMenu> children;

    public String getUSERID() {
        return USERID;
    }
    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public String getMENU_ORDER() {
        return MENU_ORDER;
    }

    public void setMENU_ORDER(String MENU_ORDER) {
        this.MENU_ORDER = MENU_ORDER;
    }

    public String getMENU_ID() {
        return MENU_ID;
    }

    public void setMENU_ID(String MENU_ID) {
        this.MENU_ID = MENU_ID;
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public String getMENU_LEVEL() {
        return MENU_LEVEL;
    }

    public void setMENU_LEVEL(String MENU_LEVEL) {
        this.MENU_LEVEL = MENU_LEVEL;
    }

    public String getMENU_PARENT_ID() {
        return MENU_PARENT_ID;
    }

    public void setMENU_PARENT_ID(String MENU_PARENT_ID) {
        this.MENU_PARENT_ID = MENU_PARENT_ID;
    }

    public String getMENU_ADDRESS() {
        return MENU_ADDRESS;
    }

    public void setMENU_ADDRESS(String MENU_ADDRESS) {
        this.MENU_ADDRESS = MENU_ADDRESS;
    }


}
