package com.company.gui;

import com.company.config.GlobalConfig;
import java.util.Locale;
import java.util.PropertyResourceBundle;

public class GuiResource
{
    private static final String RESOURCES = "com.company.gui.Resource Bundle.ContactResources_en";
    private static final String LANGUAGE = "language";

    private static PropertyResourceBundle components = null;

    // Загрузка ресурсов для компонентов
    public static void initComponentResources() {
        String lang = GlobalConfig.getProperty(LANGUAGE);
        if (lang != null && !lang.trim().isEmpty()) {
            components = (PropertyResourceBundle) PropertyResourceBundle.getBundle(RESOURCES, new Locale(lang));
        } else {
            components = (PropertyResourceBundle) PropertyResourceBundle.getBundle(RESOURCES);
        }
    }

    // Получение строки для отображения компонента
    public static String getLabel(String formId, String componentId) {
        return components.getString(formId + "." + componentId);
    }
}
