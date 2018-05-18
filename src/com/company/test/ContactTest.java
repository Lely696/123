package com.company.test;

import com.company.config.GlobalConfig;
import com.company.gui.ContactFrame;
import com.company.gui.GuiResource;

/**
 * Класс для запуска тестовых вызовов
 */
public class ContactTest
{
    public static void main(String[] args) {
        try {
            GlobalConfig.initGlobalConfig();
            GuiResource.initComponentResources();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return;
        }
        ContactFrame cf = new ContactFrame();
        cf.setVisible(true);
    }
}
