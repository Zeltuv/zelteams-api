package com.zeltuv.teams.api.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IItemBuilder {
    IItemBuilder amount(int amount);
    IItemBuilder name(String name);
    IItemBuilder skullOwnerProper(String username);
    IItemBuilder skullTexture(String url);
    IItemBuilder defLore(String... text);
    IItemBuilder defLore(List<String> list);
    IItemBuilder appendLore(String... text);
    IItemBuilder appendLore(List<String> text);
    IItemBuilder durability(int durability);
    IItemBuilder enchantment(Enchantment enchantment, int level);
    IItemBuilder enchantment(Enchantment enchantment);
    IItemBuilder type(Material material);
    IItemBuilder clearLore();
    IItemBuilder clearEnchantments();
    IItemBuilder color(Color color);
    IItemBuilder itemflag(ItemFlag... flags);
    IItemBuilder customModelData(int customModelData);
}
