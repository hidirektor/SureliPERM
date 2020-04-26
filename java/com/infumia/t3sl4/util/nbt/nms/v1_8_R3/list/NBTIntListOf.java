package com.infumia.t3sl4.util.nbt.nms.v1_8_R3.list;

import com.infumia.t3sl4.util.nbt.nms.v1_8_R3.NBTListEnvelope;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTTagIntArray;
import org.jetbrains.annotations.NotNull;

public class NBTIntListOf extends NBTListEnvelope<NBTTagInt, NBTTagIntArray> {

    public NBTIntListOf(@NotNull final NBTTagIntArray nbt) {
        super(nbt);
    }
}
