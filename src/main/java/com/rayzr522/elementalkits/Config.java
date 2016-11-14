
package com.rayzr522.elementalkits;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import com.rayzr522.elementalkits.utils.ListUtils;
import com.rayzr522.elementalkits.utils.item.ItemUtils;

public class Config {

    public static String    PERM_EKIT           = "";
    public static String    PERM_MANAGE         = "";

    public static ItemStack DESCRIPTION_INFERNO = ItemUtils.makeItem("book and quill, named &eDescription: &cInferno");
    public static ItemStack DESCRIPTION_FROST   = ItemUtils.makeItem("book and quill, named &eDescription: &bFrost");
    public static ItemStack DESCRIPTION_VORTEX  = ItemUtils.makeItem("book and quill, named &eDescription: &aVortex");

    public static void load(ElementalKits plugin) {

        FileConfiguration config = plugin.getConfig();

        PERM_EKIT = config.getString("permissions.select-kit");
        PERM_MANAGE = config.getString("permissions.manage");

        List<String> descriptionInferno = ListUtils.colorList(config.getStringList("descriptions.inferno"));
        List<String> descriptionFrost = ListUtils.colorList(config.getStringList("descriptions.frost"));
        List<String> descriptionVortex = ListUtils.colorList(config.getStringList("descriptions.vortex"));

        ItemUtils.setLore(DESCRIPTION_INFERNO, descriptionInferno);
        ItemUtils.setLore(DESCRIPTION_FROST, descriptionFrost);
        ItemUtils.setLore(DESCRIPTION_VORTEX, descriptionVortex);

    }

}
