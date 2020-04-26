package com.infumia.t3sl4.util.nbt.nms.v1_8_R2.base;

import com.infumia.t3sl4.util.nbt.nms.v1_8_R2.NBTBaseEnvelope;
import net.minecraft.server.v1_8_R2.NBTTagString;
import org.jetbrains.annotations.NotNull;

public class NBTStringOf extends NBTBaseEnvelope<NBTTagString> {

    public NBTStringOf(@NotNull final NBTTagString nbt) {
        super(nbt);
    }

}
