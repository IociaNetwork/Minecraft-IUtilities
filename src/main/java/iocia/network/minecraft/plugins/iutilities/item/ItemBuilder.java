package iocia.network.minecraft.plugins.iutilities.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ItemBuilder {
    /*---Data---*/
    //Base Data
    private Material material = Material.AIR;
    private int amount = 1;
    private short damage = 0;

    //Metadata
    private String displayName = null;
    private String localizedName = null;
    private List<String> lore = null;
    private boolean unbreakable = false;
    private Set<ItemFlag> itemFlags = null;
    private Map<Enchantment, EnchantmentData> enchantments = null;

    /*---Constructors---*/
    public ItemBuilder() {

    }

    /*---Methods---*/
    //Base Data
    public ItemBuilder Material(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder Amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder Damage(short damage) {
        this.damage = damage;
        return this;
    }

    //Metadata
    public ItemBuilder DisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder LocalizedName(String localizedName) {
        this.localizedName = localizedName;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder addLore(String lore) {
        if (this.lore == null)
            this.lore = new ArrayList<>();
        this.lore.add(lore);
        return this;
    }

    public ItemBuilder addLore(List<String> lore) {
        if (this.lore == null)
            this.lore = new ArrayList<>();
        this.lore.addAll(lore);
        return this;
    }

    public ItemBuilder addLore(String... lore) {
        return addLore(Arrays.asList(lore));
    }

    public ItemBuilder isUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder setFlags(Set<ItemFlag> flags) {
        this.itemFlags = flags;
        return this;
    }

    public ItemBuilder setFlags(ItemFlag... flags) {
        return setFlags(new HashSet<>(Arrays.asList(flags)));
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        if (this.itemFlags == null)
            this.itemFlags = new HashSet<>();
        this.itemFlags.add(flag);
        return this;
    }

    public ItemBuilder addFlags(Set<ItemFlag> flags) {
        if (this.itemFlags == null)
            this.itemFlags = new HashSet<>();
        this.itemFlags.addAll(flags);
        return this;
    }

    public ItemBuilder addFlags(ItemFlag... flags) {
        return addFlags(new HashSet<>(Arrays.asList(flags)));
    }

    public ItemBuilder addSafeEnchanements(Map<Enchantment, Integer> enchantments) {
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        enchantments.forEach((k,v) -> this.enchantments.put(k, new EnchantmentData(v, true)));
        return this;
    }

    public ItemBuilder addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        enchantments.forEach((k,v) -> this.enchantments.put(k, new EnchantmentData(v, false)));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, Integer level, boolean levelSafe) {
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        this.enchantments.put(enchantment, new EnchantmentData(level, levelSafe));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, Integer level) {
        return addEnchantment(enchantment, level, true);
    }

    //Builders
    public ItemStack build() {
        //Base Data
        ItemStack itemStack = new ItemStack(material, amount, damage);

        //Metadata
        final ItemMeta itemMeta = itemStack.getItemMeta();
        if (displayName != null)
            itemMeta.setDisplayName(displayName);
        if (localizedName != null)
            itemMeta.setLocalizedName(localizedName);
        if (lore != null && !lore.isEmpty())
            itemMeta.setLore(lore);
        itemMeta.setUnbreakable(unbreakable);
        if (itemFlags != null && !itemFlags.isEmpty())
            itemMeta.addItemFlags(itemFlags.toArray(new ItemFlag[itemFlags.size()]));
        if (enchantments != null && !enchantments.isEmpty())
            enchantments.forEach((k,v) -> itemMeta.addEnchant(k, v.getLevel(), v.getLevelSafe()));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    /*---Builder Classes---*/
    private class EnchantmentData {
        /*---Data---*/
        private int level;
        private boolean levelSafe;

        /*---Constructors---*/
        EnchantmentData(int level, boolean levelSafe) {
            this.level = level;
            this.levelSafe = levelSafe;
        }

        /*---Methods---*/
        int getLevel() {
            return level;
        }

        boolean getLevelSafe() {
            return levelSafe;
        }
    }
}
