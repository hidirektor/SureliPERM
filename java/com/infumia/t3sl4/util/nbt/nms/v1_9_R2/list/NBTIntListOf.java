package com.infumia.t3sl4.util.nbt.nms.v1_9_R2.list;

import com.infumia.t3sl4.util.nbt.nms.v1_9_R2.NBTListEnvelope;
import net.minecraft.server.v1_9_R2.NBTTagInt;
import net.minecraft.server.v1_9_R2.NBTTagIntArray;
import org.jetbrains.annotations.NotNull;

public class NBTIntListOf extends NBTListEnvelope<NBTTagInt, NBTTagIntArray> {

    public NBTIntListOf(@NotNull final NBTTagIntArray nbt) {
        super(nbt);
    }
}
