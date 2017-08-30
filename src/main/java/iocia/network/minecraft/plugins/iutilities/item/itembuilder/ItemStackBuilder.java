package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Responsible for building regular {@linkplain org.bukkit.inventory.ItemStack item stacks} from the
 * build data given.
 */
public class ItemStackBuilder extends ItemMetadataBuilder<ItemStackBuilder> {
    /*---Constructors---*/
    public ItemStackBuilder() {

    }

    /*---Methods---*/
    /**
     * Returns the instance of the derivative class.
     * This is used to allow chaining the builder methods.
     *
     * @return Instance of derivative class.
     */
    @Override
    protected ItemStackBuilder me() {
        return this;
    }

    /**
     * Builds the {@link ItemStack} with the current build properties.
     *
     * @return New {@link ItemStack}.
     */
    @Override
    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount, damage);
        final ItemMeta metadata = item.getItemMeta();
        if (displayName != null)
            metadata.setDisplayName(displayName);
        if (localizedName != null)
            metadata.setLocalizedName(localizedName);
        return item;
    }
}
