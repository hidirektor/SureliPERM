package com.infumia.t3sl4.util.nbt.nms.v1_10_R1.number;

import com.infumia.t3sl4.util.nbt.nms.v1_10_R1.NBTNumberEnvelope;
import net.minecraft.server.v1_10_R1.NBTTagShort;
import org.jetbrains.annotations.NotNull;

public class NBTShortOf extends NBTNumberEnvelope<NBTTagShort> {

    public NBTShortOf(@NotNull final NBTTagShort nbt) {
        super(nbt);
    }

}
