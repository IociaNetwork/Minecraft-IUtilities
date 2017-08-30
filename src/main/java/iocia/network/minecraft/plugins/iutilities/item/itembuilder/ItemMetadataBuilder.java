package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import com.sun.istack.internal.NotNull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.*;

/**
 * Responsible for controlling all the metadata which can go on all {@linkplain javafx.scene.paint.Material materials}.
 * @param <T> Builder class which is extending this class.
 */
public abstract class ItemMetadataBuilder<T extends ItemMetadataBuilder<T>> extends BaseItemData<T> {
    /*---Constants---*/
    private static final String LORE_NULL_EXCEPTION = "Lore cannot be null. Cannot add lore that is null.";
    private static final String LORE_INNER_NULL_EXCEPTION = "One of the lore entries given was null. A null lore string cannot be added.";
    private static final String ITEM_FLAG_NULL_EXCEPTION = "ItemFlag cannot be null. Cannot add an ItemFlag that is null.";
    private static final String ITEM_FLAG_INNER_NULL_EXCEPTION = "At least one of the item flags given was null. A null item flag cannot be added.";
    private static final String ENCHANTMENT_NULL_EXCEPTION = "Enchantment cannot be null. Cannot add an enchantment that is null.";
    private static final String ENCHANTMENT_INNER_NULL_EXCEPTION = "At least one of the enchantment map entries given was null. A null enchantment cannot be added.";

    /*---Data---*/
    protected String displayName = null;
    protected String localizedName = null;
    protected Collection<String> lore = null;
    protected boolean clearLore = false;
    protected boolean unbreakable = false;
    protected Set<ItemFlag> itemFlags = null;
    protected Map<Enchantment, EnchantmentData> enchantments = null;
    
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

    /**
     * Adds an {@link ItemFlag} to the item being created.
     * @param flag {@link ItemFlag} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given flag is null.
     */
    public T addItemFlag(@NotNull ItemFlag flag) throws IllegalArgumentException {
        if (flag == null)
            throw new IllegalArgumentException(ITEM_FLAG_NULL_EXCEPTION);
        if (this.itemFlags == null)
            this.itemFlags = new HashSet<>();
        this.itemFlags.add(flag);
        return me();
    }

    /**
     * Adds an entire {@linkplain Set set} of {@linkplain ItemFlag ItemFlags} to the item being built.
     * @param flags {@linkplain ItemFlag ItemFlags} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when flags {@linkplain Set set} is null.
     * @throws NullPointerException Thrown when any of the elements in the given {@linkplain Set set} is null.
     */
    public T addItemFlags(@NotNull Set<ItemFlag> flags) throws IllegalArgumentException, NullPointerException {
        if (flags == null)
            throw new IllegalArgumentException(ITEM_FLAG_NULL_EXCEPTION);
        if (this.itemFlags == null)
            this.itemFlags = new HashSet<>();
        flags.forEach(f -> {
            if (f == null)
                throw new NullPointerException(ITEM_FLAG_INNER_NULL_EXCEPTION);
            this.itemFlags.add(f);
        });
        return me();
    }

    /**
     * Adds an array of {@link ItemFlag item flags} to the item being built.
     * @param flags Array of {@linkplain ItemFlag item flags} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given array is null.
     * @throws NullPointerException Thrown if any element in the given is null.
     */
    public T addItemFlags(@NotNull ItemFlag... flags) throws IllegalArgumentException, NullPointerException {
        return addItemFlags(new HashSet<>(Arrays.asList(flags)));
    }

    /**
     * Adds an {@link Enchantment} to the item being built. If multiple enchantments of the same
     * type are added, the most recent enchantment will be the one added to the item.
     * @param enchantment {@link Enchantment} to add.
     * @param level Level of the {@linkplain Enchantment enchantment}.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given {@link Enchantment} is null.
     */
    public T addSafeEnchantment(@NotNull Enchantment enchantment, int level) throws IllegalArgumentException {
        if (enchantment == null)
            throw new IllegalArgumentException(ENCHANTMENT_NULL_EXCEPTION);
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        this.enchantments.put(enchantment, new EnchantmentData(level, true));
        return me();
    }

    /**
     * Adds an {@link Enchantment} to the item being built. If multiple enchantments of the same
     * type are added, the most recent enchantment will be the one added to the item.
     * @param enchantment {@link Enchantment} to add.
     * @param level Level of the {@linkplain Enchantment enchantment}.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given {@link Enchantment} is null.
     */
    public T addUnsafeEnchantment(@NotNull Enchantment enchantment, int level) throws IllegalArgumentException {
        if (enchantment == null)
            throw new IllegalArgumentException(ENCHANTMENT_NULL_EXCEPTION);
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        this.enchantments.put(enchantment, new EnchantmentData(level, false));
        return me();
    }

    /**
     * Adds a {@link Map} of enchantments to the item being built.
     * @param enchantments Enchantments to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given enchantment {@link Map} is null.
     * @throws NullPointerException Thrown if any of the entries within the given {@link Map} are null.
     */
    public T addSafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) throws IllegalArgumentException, NullPointerException {
        if (enchantments == null)
            throw new IllegalArgumentException(ENCHANTMENT_NULL_EXCEPTION);
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        enchantments.forEach((k, v) -> {
            if (k == null || v == null)
                throw new NullPointerException(ENCHANTMENT_INNER_NULL_EXCEPTION);
            this.enchantments.put(k, new EnchantmentData(v, true));
        });
        return me();
    }

    /**
     * Adds a {@link Map} of enchantments to the item being built.
     * @param enchantments Enchantments to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given enchantment {@link Map} is null.
     * @throws NullPointerException Thrown if any of the entries within the given {@link Map} are null.
     */
    public T addUnsafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) throws IllegalArgumentException, NullPointerException {
        if (enchantments == null)
            throw new IllegalArgumentException(ENCHANTMENT_NULL_EXCEPTION);
        if (this.enchantments == null)
            this.enchantments = new HashMap<>();
        enchantments.forEach((k, v) -> {
            if (k == null || v == null)
                throw new NullPointerException(ENCHANTMENT_INNER_NULL_EXCEPTION);
            this.enchantments.put(k, new EnchantmentData(v, false));
        });
        return me();
    }

    /*---Internal Classes---*/
    /**
     * Used as a wrapper for the enchantment data.
     */
    private class EnchantmentData {
        /*---Constants---*/
        private static final String LEVEL_LESS_THAN_ONE = "Level cannot be less than one.";

        /*---Data---*/
        private int level;
        private boolean levelSafe;

        /*---Constructors---*/
        /**
         * Creates a new EnchantmentData object.
         * @param level Level of the enchantment.
         * @param levelSafe Whether to apply the level safely or not.
         */
        EnchantmentData(int level, boolean levelSafe) {
            if (level < 1)
                throw new IllegalArgumentException(LEVEL_LESS_THAN_ONE);
            this.level = level;
            this.levelSafe = levelSafe;
        }

        /**
         * Gets the level.
         * @return Level of the enchantment.
         */
        public int getLevel() {
            return level;
        }

        /**
         * Returns whether or not the enchantment is level safe.
         * @return True if level safe; false if not.
         */
        public boolean isLevelSafe() {
            return levelSafe;
        }
    }
}
