package com.infumia.t3sl4.util.nbt.nms.v1_8_R1.base;

import com.infumia.t3sl4.util.nbt.nms.v1_8_R1.NBTBaseEnvelope;
import net.minecraft.server.v1_8_R1.NBTTagString;
import org.jetbrains.annotations.NotNull;

public class NBTStringOf extends NBTBaseEnvelope<NBTTagString> {

    public NBTStringOf(@NotNull final NBTTagString nbt) {
        super(nbt);
    }

}
