package iocia.network.minecraft.plugins.iutilities.item;

import com.sun.istack.internal.NotNull;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.bukkit.Material;

public abstract class BaseItemData<T extends BaseItemData<T>> extends ItemBuilder<T> {
    /*---Constants---*/
    private static final String MATERIAL_NULL = "Cannot set the material to null. ItemStack must always have a " +
            "valid material";
    private static final String DAMAGE_NEGATIVE_EXCEPTION = "Damage value cannot be negative.";
    private static final String DAMAGE_TOO_LARGE_EXCEPTION = "Damage value is too large. " +
            "Damage value must be within the range [0, " + Short.MAX_VALUE + "].";

    /*---Data---*/
    protected Material material = Material.AIR;
    protected int amount = 1;
    protected short damage = 0;

    /*---Constructors---*/
    BaseItemData() {

    }
    
    /*---Methods---*/

    public T setMaterial(@NotNull Material material) {
        if (material == null)
            throw new IllegalArgumentException(MATERIAL_NULL);
        this.material = material;
        return me();
    }

    public T setAmount(int amount) {
        this.amount = amount;
        return me();
    }

    public T setDamage(short damage) {
        if (damage < 0)
            throw new IllegalArgumentException(DAMAGE_NEGATIVE_EXCEPTION);
        this.damage = damage;
        return me();
    }

    public T setDamage(int damage) {
        if (damage > Short.MAX_VALUE)
            throw new IllegalArgumentException(DAMAGE_TOO_LARGE_EXCEPTION);
        return setDamage((short) damage);
    }
}
