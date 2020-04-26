package com.infumia.t3sl4.util.nbt.nms.v1_13_R1;

import net.minecraft.server.v1_13_R1.NBTNumber;
import org.jetbrains.annotations.NotNull;

public abstract class NBTNumberEnvelope<T extends NBTNumber> extends NBTBaseEnvelope<T> implements com.infumia.t3sl4.util.nbt.api.NBTNumber<T> {

    public NBTNumberEnvelope(@NotNull final T nbt) {
        super(nbt);
    }

    @Override
    public long longValue() {
        return value().d();
    }

    @Override
    public int intValue() {
        return value().e();
    }

    @Override
    public short shortValue() {
        return value().f();
    }

    @Override
    public byte byteValue() {
        return value().g();
    }

    @Override
    public double doubleValue() {
        return value().asDouble();
    }

    @Override
    public float floatValue() {
        return value().i();
    }
}
