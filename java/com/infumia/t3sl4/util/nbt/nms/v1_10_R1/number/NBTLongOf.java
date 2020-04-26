package com.infumia.t3sl4.util.nbt.nms.v1_10_R1.number;

import com.infumia.t3sl4.util.nbt.nms.v1_10_R1.NBTNumberEnvelope;
import net.minecraft.server.v1_10_R1.NBTTagLong;
import org.jetbrains.annotations.NotNull;

public class NBTLongOf extends NBTNumberEnvelope<NBTTagLong> {

    public NBTLongOf(@NotNull final NBTTagLong nbt) {
        super(nbt);
    }

}
