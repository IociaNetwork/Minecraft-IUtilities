package iocia.network.minecraft.plugins.iutilities.item.itembuilder.abstractions;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Base abstraction for all {@linkplain org.bukkit.inventory.ItemStack item} builders.
 */
public interface ItemBuilder<T extends ItemBuilder<T>> {

    //region Metadata
    //region Enchantment Data
    /**
     * Add a <b>safe</b> {@linkplain Enchantment enchantment} to the item.
     * A safe enchantment will not allow the enchantment level cap to be broken.
     * If multiple enchantments of the same type are added, the most recent enchantment
     * will be added to the item.
     * @param enchantment {@link Enchantment} to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addSafeEnchantment(Enchantment enchantment, int level);

    /**
     * Adds a {@linkplain Map map} of <b>safe</b> {@linkplain Enchantment enchantments} to the item.
     * A safe enchantment will not allow the enchantment level cap to be broken.
     * @param enchantments {@linkplain Enchantment Enchantments} to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addSafeEnchantments(Map<Enchantment, Integer> enchantments);

    /**
     * Add an <b>unsafe</b> {@linkplain Enchantment enchantment} to the item.
     * An unsafe enchantment will allow the enchantment level cap to be broken.
     * If multiple enchantments of the same type are added, the most recent enchantment
     * will be added to the item.
     * @param enchantment {@link Enchantment} to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addUnsafeEnchantment(Enchantment enchantment, int level);

    /**
     * Adds a {@linkplain Map map} of <b>unsafe</b> {@linkplain Enchantment enchantments} to the item.
     * An unsafe enchantment will allow the enchantment level cap to be broken.
     * @param enchantments {@linkplain Enchantment Enchantments} to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addUnsafeEnchantments(Map<Enchantment, Integer> enchantments);
    //endregion
    //region Lore
    /**
     * Adds a line of lore to the item being built.
     * The lore will appear on the item in the order in which it is added.
     * @param lore Lore to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addLore(String lore);

    /**
     * Adds a {@linkplain Collection collection} of lore to the item.
     * If the implementation of the collection maintains order, then the lore
     * will be added to the item in order.
     * @param lore Lore to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addLore(Collection<String> lore);

    /**
     * Adds and array of {@linkplain String strings} to add as lines of lore
     * on the item. The order in which the strings are set in the array, is the
     * order the strings will be added to the item as lore.
     * @param lore Array of strings to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addLore(String... lore);
    //endregion
    //region ItemFlags
    /**
     * Adds an {@linkplain ItemFlag item flag} to the item being built.
     * @param itemFlag Item flag to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addItemFlag(ItemFlag itemFlag);

    /**
     * Adds a {@linkplain Set set} of {@linkplain ItemFlag item flags}
     * to the item being built.
     * @param itemFlags Item flags to add.
     * @return ItemBuilder to continue chaining build methods.
     */
    T addItemFlags(Set<ItemFlag> itemFlags);
    //endregion
    /**
     * Sets the display name of the item. The display name is the name
     * of the item that will be seen by players who hover over the item.
     * @param displayName Display name to set.
     * @return ItemBuilder to continue chaining build methods.
     */
    T setDisplayName(String displayName);

    /**
     * Sets the localized name of the item.
     * @param localizedName Localized name to set.
     * @return ItemBuilder to continue chaining build methods.
     */
    T setLocalizedName(String localizedName);
    //endregion

    /**
     * Builds the {@linkplain ItemStack item} based off of the criteria set with the build methods.
     * @return
     */
    ItemStack build();
}
