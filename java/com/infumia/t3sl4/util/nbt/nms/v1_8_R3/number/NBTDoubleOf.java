package com.infumia.t3sl4.util.nbt.nms.v1_8_R3.number;

import com.infumia.t3sl4.util.nbt.nms.v1_8_R3.NBTNumberEnvelope;
import net.minecraft.server.v1_8_R3.NBTTagDouble;
import org.jetbrains.annotations.NotNull;

public class NBTDoubleOf extends NBTNumberEnvelope<NBTTagDouble> {

    public NBTDoubleOf(@NotNull final NBTTagDouble nbt) {
        super(nbt);
    }

}
