package com.infumia.t3sl4.util.nbt.nms.v1_8_R1;

import com.infumia.t3sl4.util.nbt.api.NBTNumber;
import com.infumia.t3sl4.util.reflection.RefClass;
import com.infumia.t3sl4.util.reflection.clazz.ClassOf;
import net.minecraft.server.v1_8_R1.NBTBase;
import org.jetbrains.annotations.NotNull;

public abstract class NBTNumberEnvelope<T extends NBTBase> extends NBTBaseEnvelope<T> implements NBTNumber<T> {

    public NBTNumberEnvelope(@NotNull final T nbt) {
        super(nbt);
    }

    @Override
    public long longValue() {
        try {
            final RefClass refClass = new ClassOf("net.minecraft.server.v1_12_R1.NBTNumber");
            return (long) refClass.getMethod("d").of(value()).call(0);
        } catch (ClassNotFoundException e) {
            return 0;
        }
    }

    @Override
    public int intValue() {
        try {
            final RefClass refClass = new ClassOf("net.minecraft.server.v1_12_R1.NBTNumber");
            return (int) refClass.getMethod("e").of(value()).call(0);
        } catch (ClassNotFoundException e) {
            return 0;
        }
    }

    @Override
    public short shortValue() {
        try {
            final RefClass refClass = new ClassOf("net.minecraft.server.v1_12_R1.NBTNumber");
            return (short) refClass.getMethod("f").of(value()).call(0);
        } catch (ClassNotFoundException e) {
            return 0;
        }
    }

    @Override
    public byte byteValue() {
        try {
            final RefClass refClass = new ClassOf("net.minecraft.server.v1_12_R1.NBTNumber");
            return (byte) refClass.getMethod("g").of(value()).call(0);
        } catch (ClassNotFoundException e) {
            return 0;
        }
    }

    @Override
    public double doubleValue() {
        try {
            final RefClass refClass = new ClassOf("net.minecraft.server.v1_12_R1.NBTNumber");
            return (double) refClass.getMethod("asDouble").of(value()).call(0);
        } catch (ClassNotFoundException e) {
            return 0;
        }
    }

    @Override
    public float floatValue() {
        try {
            final RefClass refClass = new ClassOf("net.minecraft.server.v1_12_R1.NBTNumber");
            return (float) refClass.getMethod("i").of(value()).call(0);
        } catch (ClassNotFoundException e) {
            return 0;
        }
    }
}
