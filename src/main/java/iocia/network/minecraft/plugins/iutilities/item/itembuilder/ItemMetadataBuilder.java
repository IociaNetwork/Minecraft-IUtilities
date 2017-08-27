package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Responsible for controlling all the metadata which can go on all {@linkplain javafx.scene.paint.Material materials}.
 * @param <T> Builder class which is extending this class.
 */
public abstract class ItemMetadataBuilder<T extends ItemMetadataBuilder<T>> extends BaseItemData<T> {
    /*---Constants---*/
    private static final String LORE_NULL_EXCEPTION = "Lore cannot be null. Cannot add lore that is null.";
    private static final String LORE_INNER_NULL_EXCEPTION = "One of the lore entries given was null. A null lore string cannot be added.";

    /*---Data---*/
    protected String displayName = null;
    protected String localizedName = null;
    protected Collection<String> lore = null;
    protected boolean clearLore = false;
    protected boolean unbreakable = false;
    
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

    /**
     * Adds a line of lore to the {@link org.bukkit.inventory.ItemStack}.
     * The lore will appear on the {@link org.bukkit.inventory.ItemStack} in the order
     * in which they are added.
     * @param lore Line of lore to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given lore is null.
     */
    public T addLore(@NotNull String lore) throws IllegalArgumentException {
        if (lore == null)
            throw new IllegalArgumentException(LORE_NULL_EXCEPTION);
        if (this.lore == null)
            this.lore = new LinkedList<>();
        this.lore.add(lore);
        return me();
    }

    /**
     * Adds a {@link Collection<String>} of lore to the item being built. Each entry in the given {@link Collection<String>} is a new
     * line of lore on the item.
     * @param lore {@linkplain Collection Collection} of lore to be added. Lore will be added to the item in
     *                                               the order it is within the collection (if the collection maintains a fixed order).
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given lore {@link Collection} is null.
     * @throws NullPointerException Thrown if any of the lore entries in the given {@link Collection} is null. It is possible for some
     * of the lore to be added before the exception is thrown.
     */
    public T addLore(@NotNull Collection<String> lore) throws IllegalArgumentException, NullPointerException {
        if (lore == null)
            throw new IllegalArgumentException(LORE_NULL_EXCEPTION);
        if (this.lore == null)
            this.lore = new LinkedList<>();
        lore.forEach(l -> {
            if (l == null)
                throw new NullPointerException(LORE_INNER_NULL_EXCEPTION);
            this.lore.add(l);
        });
        return me();
    }

    /**
     * Adds an array of {@linkplain String Strings} to the lore being added to this item.
     * The lore will maintain its order as it is in the given array. Each new entry in the array is
     * a new line of lore on the item.
     * @param lore Array of {@linkplain String Strings} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given array is null.
     * @throws NullPointerException Thrown if any of the lore entries in the given array is null. It is possible for some
     * of the lore to be added before the exception is thrown.
     */
    public T addLore(@NotNull String... lore) throws IllegalArgumentException, NullPointerException {
        return addLore(Arrays.asList(lore));
    }

    /**
     * Clears any pre-existing lines of lore the item may have before adding any potential new lore.
     * It is important to note that if {@linkplain ItemMetadataBuilder#clearLore() clear lore} is called after any
     * of the addLore methods, the lore from the addLore methods will still be added. {@linkplain ItemMetadataBuilder#clearLore() Clear lore}
     * will only remove pre-existing lines of lore not added by any of the add lore methods.
     * @return ItemBuilder to continue chaining build methods.
     */
    public T clearLore() {
        this.clearLore = true;
        return me();
    }

    /**
     * Declares that the item will be unbreakable once built.
     * @return ItemBuilder to continue chaining build methods.
     */
    public T unbreakable() {
        this.unbreakable = true;
        return me();
    }
}
