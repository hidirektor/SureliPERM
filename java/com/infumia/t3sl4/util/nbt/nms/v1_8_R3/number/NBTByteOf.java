package com.infumia.t3sl4.util.nbt.nms.v1_8_R3.number;

import com.infumia.t3sl4.util.nbt.nms.v1_8_R3.NBTNumberEnvelope;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import org.jetbrains.annotations.NotNull;

public class NBTByteOf extends NBTNumberEnvelope<NBTTagByte> {

    public NBTByteOf(@NotNull final NBTTagByte nbt) {
        super(nbt);
    }

}
