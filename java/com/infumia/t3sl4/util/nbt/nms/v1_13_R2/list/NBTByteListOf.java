package com.infumia.t3sl4.util.nbt.nms.v1_13_R2.list;

import com.infumia.t3sl4.util.nbt.nms.v1_13_R2.NBTListEnvelope;
import net.minecraft.server.v1_13_R2.NBTTagByte;
import net.minecraft.server.v1_13_R2.NBTTagByteArray;
import org.jetbrains.annotations.NotNull;

public class NBTByteListOf extends NBTListEnvelope<NBTTagByte, NBTTagByteArray> {

    public NBTByteListOf(@NotNull final NBTTagByteArray nbt) {
        super(nbt);
    }
}
