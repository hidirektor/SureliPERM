package com.infumia.t3sl4.util.nbt.nms.v1_14_R1;

import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import com.infumia.t3sl4.util.nbt.api.mck.MckNBTCompound;
import com.infumia.t3sl4.util.nbt.api.mck.MckNBTList;
import com.infumia.t3sl4.util.nbt.nms.v1_14_R1.compound.NBTCompoundOf;
import com.infumia.t3sl4.util.nbt.nms.v1_14_R1.list.NBTByteListOf;
import com.infumia.t3sl4.util.nbt.nms.v1_14_R1.list.NBTIntListOf;
import com.infumia.t3sl4.util.nbt.nms.v1_14_R1.list.NBTLongListOf;
import com.infumia.t3sl4.util.nbt.nms.v1_14_R1.list.NBTTagListOf;
import net.minecraft.server.v1_14_R1.*;
import org.jetbrains.annotations.NotNull;

public abstract class NBTListEnvelope<V extends NBTBase, T extends NBTList<V>> extends NBTBaseEnvelope<T> implements com.infumia.t3sl4.util.nbt.api.NBTList<V, T> {

    public NBTListEnvelope(@NotNull T nbt) {
        super(nbt);
    }

    @Override
    public void add(@NotNull V value) {
        value().add(value);
    }

    @Override
    public void add(int key, @NotNull V value) {
        value().add(key, value);
    }

    @Override
    public void set(int key, @NotNull V value) {
        value().set(key, value);
    }

    @Override
    public void remove(int key) {
        value().remove(key);
    }

    @NotNull
    @Override
    public V get(int key) {
        return value().get(key);
    }

    @NotNull
    @Override
    public NBTCompound getCompound(int key) {
        if (get(key) instanceof NBTTagCompound)
            return new NBTCompoundOf((NBTTagCompound) get(key));

        return new MckNBTCompound();

    }

    @NotNull
    @Override
    public com.infumia.t3sl4.util.nbt.api.NBTList getList(int key) {
        final V list = get(key);

        if (list instanceof NBTTagList)
            return new NBTTagListOf((NBTTagList) list);

        if (list instanceof NBTTagByteArray)
            return new NBTByteListOf((NBTTagByteArray) list);

        if (list instanceof NBTTagIntArray)
            return new NBTIntListOf((NBTTagIntArray) list);

        if (list instanceof NBTTagLongArray)
            return new NBTLongListOf((NBTTagLongArray) list);

        return new MckNBTList();
    }

    @Override
    public short getShort(int key) {
        if (get(key) instanceof NBTNumber)
            return ((NBTNumber) get(key)).asShort();
        return 0;
    }

    @Override
    public int getInt(int key) {
        if (get(key) instanceof NBTNumber)
            return ((NBTNumber) get(key)).asInt();
        return 0;
    }

    @NotNull
    @Override
    public int[] getIntArray(int key) {
        if (get(key) instanceof NBTTagIntArray)
            return ((NBTTagIntArray) get(key)).getInts();

        return new int[0];
    }

    @Override
    public double getDouble(int key) {
        if (get(key) instanceof NBTNumber)
            return ((NBTTagDouble) get(key)).asDouble();
        return 0;
    }

    @Override
    public float getFloat(int key) {
        if (get(key) instanceof NBTNumber)
            return ((NBTNumber) get(key)).asFloat();
        return 0;
    }

    @NotNull
    @Override
    public String getString(int key) {
        if (get(key) instanceof NBTTagString)
            return get(key).asString();
        return "";
    }

    @Override
    public int size() {
        return value().size();
    }

}