package com.infumia.t3sl4.util.nbt.nms.v1_14_R1;

import com.infumia.t3sl4.util.nbt.api.NBTBase;
import org.jetbrains.annotations.NotNull;

public abstract class NBTBaseEnvelope<T extends net.minecraft.server.v1_14_R1.NBTBase> implements NBTBase<T> {

    @NotNull
    private final T nbt;

    public NBTBaseEnvelope(@NotNull final T nbt) {
        this.nbt = nbt;
    }

    @NotNull
    @Override
    public T value() {
        return nbt;
    }

    @NotNull
    @Override
    public String toString() {
        return nbt.toString();
    }
}
