package iocia.network.minecraft.plugins.iutilities.item.itembuilder.abstractions;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Base abstraction for all {@linkplain org.bukkit.inventory.ItemStack item} builders.
 */
public interface ItemBuilder<T extends ItemBuilder<T>> {

    //region Enchantment Data
    /**
     * Add a <b>safe</b> {@linkplain Enchantment enchantment} to the item.
     * A safe enchantment will not allow the enchantment level cap to be broken.
     * If multiple enchantments of the same type are added, the most recent enchantment
     * will be added to the item.
     * @param enchantment {@link Enchantment} to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given {@linkplain Enchantment enchantment} is null,
     * or the given level is less than one.
     */
    T addSafeEnchantment(Enchantment enchantment, int level) throws IllegalArgumentException;

    /**
     * Adds a {@linkplain Map map} of <b>safe</b> {@linkplain Enchantment enchantments} to the item.
     * A safe enchantment will not allow the enchantment level cap to be broken.
     * @param enchantments {@linkplain Enchantment Enchantments} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given enchantment map is null.
     */
    T addSafeEnchantments(Map<Enchantment, Integer> enchantments) throws IllegalArgumentException;

    /**
     * Add an <b>unsafe</b> {@linkplain Enchantment enchantment} to the item.
     * An unsafe enchantment will allow the enchantment level cap to be broken.
     * If multiple enchantments of the same type are added, the most recent enchantment
     * will be added to the item.
     * @param enchantment {@link Enchantment} to add.
     * @param level Level of the enchantment.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given {@linkplain Enchantment enchantment} is null,
     * or the given level is less than one.
     */
    T addUnsafeEnchantment(Enchantment enchantment, int level) throws IllegalArgumentException;

    /**
     * Adds a {@linkplain Map map} of <b>unsafe</b> {@linkplain Enchantment enchantments} to the item.
     * An unsafe enchantment will allow the enchantment level cap to be broken.
     * @param enchantments {@linkplain Enchantment Enchantments} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given enchantment map is null.
     */
    T addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) throws IllegalArgumentException;
    //endregion


    /**
     * Builds the {@linkplain ItemStack item} based off of the criteria set with the build methods.
     * @return
     */
    ItemStack build();
}
