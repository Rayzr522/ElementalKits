
package com.rayzr522.elementalkits.utils.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.rayzr522.elementalkits.utils.ListUtils;
import com.rayzr522.elementalkits.utils.TextUtils;

/**
 * Created by Rayzr522 on 7/4/16.
 */
public class ParseLore extends ParseCommand {

    private List<String> descriptors = Arrays.asList("with lore", "lore");

    public List<String> getDescriptors() {
        return descriptors;
    }

    public ItemStack apply(ItemStack base, String args) {

        String[] lines = args.split("\n");

        ItemMeta meta = base.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : ListUtils.empty();

        for (String line : lines) {

            lore.add(TextUtils.colorize(line));

        }

        meta.setLore(lore);
        base.setItemMeta(meta);

        return base;

    }
}
