package iocia.network.minecraft.plugins.iutilities.item;

import org.bukkit.inventory.ItemStack;

/**
 * Base abstraction of the {@link ItemStack} builders.
 * @param <T> Builder class which is implementing this class.
 */
public abstract class ItemBuilder<T extends ItemBuilder<T>> {
    /*---Constructors---*/
    /**
     * Creates a new {@link ItemBuilder}.
     */
    public ItemBuilder() {

    }
    
    /*---Methods---*/
    /**
     * Returns the instance of the derivative class.
     * This is used to allow chaining the builder methods.
     * @return Instance of derivative class.
     */
    protected abstract T me();

    /**
     * Builds the {@link ItemStack} with the current build properties.
     * @return New {@link ItemStack}.
     */
    public abstract ItemStack build();
}
