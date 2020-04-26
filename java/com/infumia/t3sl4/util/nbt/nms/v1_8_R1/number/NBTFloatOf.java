package com.infumia.t3sl4.util.nbt.nms.v1_8_R1.number;

import com.infumia.t3sl4.util.nbt.nms.v1_8_R1.NBTNumberEnvelope;
import net.minecraft.server.v1_8_R1.NBTTagFloat;
import org.jetbrains.annotations.NotNull;

public class NBTFloatOf extends NBTNumberEnvelope<NBTTagFloat> {

    public NBTFloatOf(@NotNull final NBTTagFloat nbt) {
        super(nbt);
    }

}
