package com.infumia.t3sl4.util.nbt.nms.v1_12_R1.number;

import com.infumia.t3sl4.util.nbt.nms.v1_12_R1.NBTNumberEnvelope;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import org.jetbrains.annotations.NotNull;

public class NBTIntOf extends NBTNumberEnvelope<NBTTagInt> {

    public NBTIntOf(@NotNull final NBTTagInt nbt) {
        super(nbt);
    }

}
