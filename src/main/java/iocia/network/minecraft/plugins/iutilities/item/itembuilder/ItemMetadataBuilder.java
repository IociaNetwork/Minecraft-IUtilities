package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

/**
 * Responsible for controlling all the metadata which can go on all {@linkplain javafx.scene.paint.Material materials}.
 * @param <T> Builder class which is extending this class.
 */
public abstract class ItemMetadataBuilder<T extends ItemMetadataBuilder<T>> extends BaseItemData<T> {
    /*---Data---*/
    protected String displayName = null;
    protected String localizedName = null;
    
    /*---Constructors---*/
    /**
     * Creates a new ItemMetadataBuilder.
     */
    public ItemMetadataBuilder() {

    }

    /*---Methods---*/
    /**
     * Sets the name of the {@link org.bukkit.inventory.ItemStack} which will appear to all players
     * when they hover over the item.
     * @param name Display name to set.
     * @return ItemBuilder to continue chaining build methods.
     */
    public T setDisplayName(String name) {
        this.displayName = name;
        return me();
    }

    /**
     * Sets the localized name of the {@link org.bukkit.inventory.ItemStack}.
     * @param name Localized name to set.
     * @return ItemBuilder to continue chaining build methods.
     */
    public T setLocalizedName(String name) {
        this.localizedName = name;
        return me();
    }
}
