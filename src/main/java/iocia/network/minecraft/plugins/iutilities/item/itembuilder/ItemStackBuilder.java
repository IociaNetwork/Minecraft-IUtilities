package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder extends BaseAbstractItemBuilder<ItemStackBuilder> {
    /*---Data---*/
    private Material material;
    private int amount;
    private short damage;
    
    /*---Constructors---*/
    /**
     * Creates a new {@link ItemBuilder item builder} for regular {@linkplain ItemStack item stacks}.
     * @param material Material of the item.
     * @param amount Amount of the item.
     * @param damage Damage value of the item.
     */
    public ItemStackBuilder(Material material, int amount, short damage) {
        this.material = material;
        this.amount = amount;
        this.damage = damage;
    }

    /**
     * Creates a new {@link ItemBuilder item builder} for regular {@linkplain ItemStack item stacks}.
     * @param material Material of the item.
     * @param amount Amount of the item.
     */
    public ItemStackBuilder(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    /**
     * Creates a new {@link ItemBuilder item builder} for regular {@linkplain ItemStack item stacks}.
     * @param material Material of the item.
     * @param damage Damage value of the item.
     */
    public ItemStackBuilder(Material material, short damage) {
        this(material, 1, damage);
    }

    /**
     * Creates a new {@link ItemBuilder item builder} for regular {@linkplain ItemStack item stacks}.
     * @param material Material of the item.
     */
    public ItemStackBuilder(Material material) {
        this(material, 1, (short) 0);
    }

    /**
     * Creates a new {@link ItemBuilder item builder} for regular {@linkplain ItemStack item stacks}.
     */
    public ItemStackBuilder() {
        this(Material.AIR, 1, (short) 0);
    }
    
    /*---Methods---*/
    @Override
    protected ItemStackBuilder me() {
        return this;
    }

    /**
     * Builds the {@linkplain ItemStack item} based off of the criteria set with the build methods.
     *
     * @return New item stack.
     */
    @Override
    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount, damage);
        ItemMeta meta = item.getItemMeta();
        if (displayName != null)
            meta.setDisplayName(displayName);
        if (localizedName != null)
            meta.setLocalizedName(localizedName);
        if (lore != null)
            meta.setLore(lore);
        if (itemFlags != null)
            meta.addItemFlags(itemFlags.toArray(new ItemFlag[itemFlags.size()]));
        if (enchantments != null)
            enchantments.forEach((e,d) -> meta.addEnchant(e, d.getLevel(), !d.isLevelSafe()));
        item.setItemMeta(meta);
        return item;
    }
}
