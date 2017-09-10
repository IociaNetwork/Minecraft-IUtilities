package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Responsible for building regular {@linkplain org.bukkit.inventory.ItemStack item stacks} from the
 * build data given.
 */
public class ItemStackBuilder extends ItemMetadataBuilder<ItemStackBuilder> {
    /*---Constructors---*/
    /**
     * Creates a new ItemStackBuilder object. This object is responsible for making basic {@linkplain ItemStack item stacks}.
     * If more advanced creation features are needed, such as creating player skulls, then a more specific build should be used.
     */
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
        if (lore != null && !lore.isEmpty()) {
            if (clearLore) {
                metadata.setLore((List<String>) lore);
            } else {
                Collection<String> combinedLore = new ArrayList<>();
                if (metadata.hasLore())
                    combinedLore.addAll(metadata.getLore());
                combinedLore.addAll(lore);
                metadata.setLore((List<String>) combinedLore);
            }
        }
        metadata.setUnbreakable(unbreakable);
        if (itemFlags != null && !itemFlags.isEmpty())
            metadata.addItemFlags(itemFlags.toArray(new ItemFlag[itemFlags.size()]));
        if (enchantments != null && !enchantments.isEmpty()) {
            enchantments.forEach((k, v) -> metadata.addEnchant(k, v.getLevel(), v.isLevelSafe()));
        }
        return item;
    }
}
