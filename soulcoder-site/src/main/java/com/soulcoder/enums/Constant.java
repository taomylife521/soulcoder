/*
 * Copyright (c) 2018.鎵�鏈変唬鐮佺増鏉冨綊缂栫爜鑰呮墍鏈�!
 */

package com.soulcoder.enums;

/**
 * Created by Aministrator on 2018-01-08.
 */
public class Constant {
    public static final Integer SUPER_ADMIN=1;

    public enum MenuType{
        //菜单类型
        CATALOG(0),
        MENU(1),
        BUTTON(2);
        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
