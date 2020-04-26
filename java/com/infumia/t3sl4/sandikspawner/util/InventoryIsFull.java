package com.infumia.t3sl4.sandikspawner.util;

import io.github.portlek.itemstack.item.set.SetAmountOf;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cactoos.Func;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class InventoryIsFull implements Func<ItemStack, Boolean> {

    @NotNull
    private final Player player;

    public InventoryIsFull(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public Boolean apply(@NotNull ItemStack itemStack) {
        if (itemStack.getType() == Material.AIR) {
            return true;
        }

        if (itemStack.getAmount() > 5000) {
            return false;
        }

        if (player.getInventory().firstEmpty() >= 0 && itemStack.getAmount() <= itemStack.getMaxStackSize()) {
            return true;
        }

        if (itemStack.getAmount() > itemStack.getMaxStackSize()) {
            final ItemStack clone = itemStack.clone();

            return apply(
                    new SetAmountOf(
                            clone,
                            itemStack.getMaxStackSize()
                    ).value()
            ) &&
                    apply(
                            new SetAmountOf(
                                    clone,
                                    itemStack.getAmount() - itemStack.getMaxStackSize()
                            ).value()
                    );
        }

        final Map<Integer, ? extends ItemStack> all = player.getInventory().all(itemStack.getType());
        int amount = itemStack.getAmount();

        for (ItemStack element : all.values()) {
            amount = amount - (element.getMaxStackSize() - element.getAmount());
        }

        return amount <= 0;
    }

}
