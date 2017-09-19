package iocia.network.minecraft.plugins.iutilities.item.itembuilder;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.*;

public abstract class BaseAbstractItemBuilder<T extends BaseAbstractItemBuilder<T>> implements ItemBuilder<T> {
    /*---Constants---*/
    private static final String ENCHANTMENT_NULL = "Given enchantment was null. Cannot add a null enchantment.";
    private static final String ENCHANTMENT_LEVEL = "Cannot add an enchantment with a level less than one.";
    private static final String ENCHANTMENT_MAP_NULL = "The given enchantment map was null.";
    private static final String LORE_NULL = "Given string of lore was null.";
    private static final String LORE_COLLECTION_NULL = "The given lore collection was null.";
    private static final String LORE_ARRAY_NULL = "The given lore array was null.";
    private static final String FLAG_NULL = "Given item flag was null.";
    private static final String FLAG_SET_NULL = "Given item flag set was null.";
    private static final String DISPLAY_NAME_NULL = "Given display name was null.";
    private static final String LOCALIZED_NAME_NULL = "Given localized name was null.";

    /*---Data---*/
    protected String displayName = null;
    protected String localizedName = null;
    protected Map<Enchantment, EnchantmentData> enchantments = null;
    protected List<String> lore = null;
    protected Set<ItemFlag> itemFlags = null;
    
    /*---Constructors---*/
    
    /*---Methods---*/
    protected abstract T me();

    /**
     * Add a <b>safe</b> {@linkplain Enchantment enchantment} to the item.
     * A safe enchantment will not allow the enchantment level cap to be broken.
     * If multiple enchantments of the same type are added, the most recent enchantment
     * will be added to the item.
     *
     * @param enchantment {@link Enchantment} to add.
     * @param level       Level of the enchantment.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given enchantment is null, or the level is
     * less than one.
     */
    public T addSafeEnchantment(Enchantment enchantment, int level) throws IllegalArgumentException {
        if (enchantment == null)
            throw new IllegalArgumentException(ENCHANTMENT_NULL);
        if (level < 1)
            throw new IllegalArgumentException(ENCHANTMENT_LEVEL);
        if (enchantments == null)
            enchantments = new HashMap<>();
        enchantments.put(enchantment, new EnchantmentData(level, true));
        return me();
    }

    /**
     * Adds a {@linkplain Map map} of <b>safe</b> {@linkplain Enchantment enchantments} to the item.
     * A safe enchantment will not allow the enchantment level cap to be broken.
     *
     * @param enchantments {@linkplain Enchantment Enchantments} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when given map is null.
     * @see {@link BaseAbstractItemBuilder#addSafeEnchantment(Enchantment, int)} to make sure each entry
     * in the given map follow the restrictions set by the referenced method. Otherwise, any specific enchantment entries
     * which do not follow the restrictions will not be added; valid enchantments will still be added.
     */
    public T addSafeEnchantments(Map<Enchantment, Integer> enchantments) throws IllegalArgumentException {
        if (enchantments == null)
            throw new IllegalArgumentException(ENCHANTMENT_MAP_NULL);
        enchantments.forEach((e,l) -> {
            if (e == null || l < 1)
                return;
            if (this.enchantments == null)
                this.enchantments = new HashMap<>();
            this.enchantments.put(e, new EnchantmentData(l, true));
        });
        return me();
    }

    /**
     * Add an <b>unsafe</b> {@linkplain Enchantment enchantment} to the item.
     * An unsafe enchantment will allow the enchantment level cap to be broken.
     * If multiple enchantments of the same type are added, the most recent enchantment
     * will be added to the item.
     *
     * @param enchantment {@link Enchantment} to add.
     * @param level       Level of the enchantment.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown if the given enchantment is null, or the level is
     * less than one.
     */
    public T addUnsafeEnchantment(Enchantment enchantment, int level) throws IllegalArgumentException {
        if (enchantment == null)
            throw new IllegalArgumentException(ENCHANTMENT_NULL);
        if (level < 1)
            throw new IllegalArgumentException(ENCHANTMENT_LEVEL);
        if (enchantments == null)
            enchantments = new HashMap<>();
        enchantments.put(enchantment, new EnchantmentData(level, false));
        return me();
    }

    /**
     * Adds a {@linkplain Map map} of <b>unsafe</b> {@linkplain Enchantment enchantments} to the item.
     * An unsafe enchantment will allow the enchantment level cap to be broken.
     *
     * @param enchantments {@linkplain Enchantment Enchantments} to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when given map is null.
     * @see {@link BaseAbstractItemBuilder#addUnsafeEnchantment(Enchantment, int)} to make sure each entry
     * in the given map follow the restrictions set by the referenced method. Otherwise, any specific enchantment entries
     * which do not follow the restrictions will not be added; valid enchantments will still be added.
     */
    public T addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) throws IllegalArgumentException {
        if (enchantments == null)
            throw new IllegalArgumentException(ENCHANTMENT_MAP_NULL);
        enchantments.forEach((e,l) -> {
            if (e == null || l < 1)
                return;
            if (this.enchantments == null)
                this.enchantments = new HashMap<>();
            this.enchantments.put(e, new EnchantmentData(l, false));
        });
        return me();
    }

    /**
     * Adds a line of lore to the item being built.
     * The lore will appear on the item in the order in which it is added.
     *
     * @param lore Lore to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given lore is null.
     */
    public T addLore(String lore) throws IllegalArgumentException {
        if (lore  == null)
            throw new IllegalArgumentException(LORE_NULL);
        if (this.lore == null)
            this.lore = new LinkedList<>();
        this.lore.add(lore);
        return me();
    }

    /**
     * Adds a {@linkplain Collection collection} of lore to the item.
     * If the implementation of the collection maintains order, then the lore
     * will be added to the item in order. If any entry in the given collection is null,
     * that entry will not be added.
     *
     * @param lore Lore to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given collection is null.
     */
    public T addLore(Collection<String> lore) throws IllegalArgumentException {
        if (lore == null)
            throw new IllegalArgumentException(LORE_COLLECTION_NULL);
        lore.forEach(l -> {
            if (l == null)
                return;
            if (this.lore == null)
                this.lore = new LinkedList<>();
            this.lore.add(l);
        });
        return me();
    }

    /**
     * Adds and array of {@linkplain String strings} to add as lines of lore
     * on the item. The order in which the strings are set in the array, is the
     * order the strings will be added to the item as lore.
     *
     * @param lore Array of strings to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given array is null.
     */
    public T addLore(String... lore) throws IllegalArgumentException {
        if (lore == null)
            throw new IllegalArgumentException(LORE_ARRAY_NULL);
        return addLore(Arrays.asList(lore));
    }

    /**
     * Adds an {@linkplain ItemFlag item flag} to the item being built.
     *
     * @param itemFlag Item flag to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given item flag is null.
     */
    public T addItemFlag(ItemFlag itemFlag) throws IllegalArgumentException {
        if (itemFlag == null)
            throw new IllegalArgumentException(FLAG_NULL);
        if (itemFlags == null)
            itemFlags = new HashSet<>();
        itemFlags.add(itemFlag);
        return me();
    }

    /**
     * Adds a {@linkplain Set set} of {@linkplain ItemFlag item flags}
     * to the item being built. If any flags in the set are null, then that flag will not be added.
     *
     * @param itemFlags Item flags to add.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given Set is null.
     */
    public T addItemFlags(Set<ItemFlag> itemFlags) throws IllegalArgumentException {
        if (itemFlags == null)
            throw new IllegalArgumentException(FLAG_SET_NULL);
        itemFlags.forEach(f -> {
            if (f == null)
                return;
            if (this.itemFlags == null)
                this.itemFlags = new HashSet<>();
            this.itemFlags.add(f);
        });
        return me();
    }

    /**
     * Sets the display name of the item. The display name is the name
     * of the item that will be seen by players who hover over the item.
     *
     * @param displayName Display name to set.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given display name is null.
     */
    public T setDisplayName(String displayName) throws IllegalArgumentException {
        if (displayName == null)
            throw new IllegalArgumentException(DISPLAY_NAME_NULL);
        this.displayName = displayName;
        return me();
    }

    /**
     * Sets the localized name of the item.
     *
     * @param localizedName Localized name to set.
     * @return ItemBuilder to continue chaining build methods.
     * @throws IllegalArgumentException Thrown when the given localized name is null.
     */
    public T setLocalizedName(String localizedName) throws IllegalArgumentException {
        if (localizedName == null)
            throw new IllegalArgumentException(LOCALIZED_NAME_NULL);
        this.localizedName = localizedName;
        return me();
    }

    /*---Internal Classes---*/
    protected class EnchantmentData {
        /*---Data---*/
        private int level;
        private boolean levelSafe;

        /*---Constructors---*/
        private EnchantmentData(int level, boolean levelSafe) {
            this.level = level;
            this.levelSafe = levelSafe;
        }

        /*---Methods---*/
        public int getLevel() {
            return level;
        }

        public boolean isLevelSafe() {
            return levelSafe;
        }
    }
}
