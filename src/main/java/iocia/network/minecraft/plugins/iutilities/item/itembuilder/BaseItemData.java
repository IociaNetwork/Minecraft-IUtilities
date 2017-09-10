package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import com.sun.istack.internal.NotNull;
import org.bukkit.Material;

/**
 * Responsible for controlling the base data which must go every {@link org.bukkit.inventory.ItemStack}.
 * No matter the {@link Material}, data, or purpose of the {@link org.bukkit.inventory.ItemStack} the data
 * controlled and set in this class must go on every item.
 * @param <T> Builder class which is extending this class.
 */
public abstract class BaseItemData<T extends BaseItemData<T>> extends ItemBuilder<T> {
    /*---Constants---*/
    private static final String MATERIAL_NULL_EXCEPTION = "Cannot set the material to null. ItemStack must always have a valid material.";
    private static final String AMOUNT_LESS_ONE = "Amount cannot be less than one.";
    private static final String DAMAGE_NEGATIVE_EXCEPTION = "Damage value cannot be negative.";
    private static final String DAMAGE_TOO_LARGE_EXCEPTION = "Damage value is too large. Damage value must be within the range [0, " + Short.MAX_VALUE + "].";

    /*---Data---*/
    protected Material material = Material.AIR;
    protected int amount = 1;
    protected short damage = 0;

    /*---Constructors---*/
    /**
     * Creates a new BaseItemData.
     */
    public BaseItemData() {

    }
    
    /*---Methods---*/
    /**
     * Sets the {@link Material} for the {@link org.bukkit.inventory.ItemStack}.
     * @param material {@link Material} to set.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when material is null.
     */
    public T setMaterial(@NotNull Material material) throws IllegalArgumentException {
        if (material == null)
            throw new IllegalArgumentException(MATERIAL_NULL_EXCEPTION);
        this.material = material;
        return me();
    }

    /**
     * Sets the quantity of items in the {@link org.bukkit.inventory.ItemStack}.
     * @param amount Number of items in the stack. Cannot be negative.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when amount is less than one.
     */
    public T setAmount(int amount) throws IllegalArgumentException {
        if (amount < 1)
            throw new IllegalArgumentException(AMOUNT_LESS_ONE);
        this.amount = amount;
        return me();
    }

    /**
     * Sets the damage of the items in the stack. On {@link Material}s which implement a damage value,
     * the damage value dictates an extra trait about the {@link Material} such as the varying
     * colors {@link org.bukkit.material.Wool} can be. For example, {@link org.bukkit.material.Wool}
     * with a damage value of 14 is red wool, but will be orange with a damage value of 1.
     * @param damage Damage value to set. Cannot be negative.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when damage is negative.
     */
    public T setDamage(short damage) throws IllegalArgumentException {
        if (damage < 0)
            throw new IllegalArgumentException(DAMAGE_NEGATIVE_EXCEPTION);
        this.damage = damage;
        return me();
    }

    /**
     * Acts the same as {@link BaseItemData#setDamage(short)} except it will attempt
     * to convert the int parameter to a short beforehand for the lazy.
     * @param damage Damage value to set. Cannot be negative. Must be castable to a short.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when damage cannot be cleanly casted to a short.
     */
    public T setDamage(int damage) throws IllegalArgumentException {
        if (damage > Short.MAX_VALUE)
            throw new IllegalArgumentException(DAMAGE_TOO_LARGE_EXCEPTION);
        return setDamage((short) damage);
    }
}
