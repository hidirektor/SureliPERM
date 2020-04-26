package com.infumia.t3sl4.util.nbt.nms.v1_12_R1.list;

import com.infumia.t3sl4.util.nbt.nms.v1_12_R1.NBTListEnvelope;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.jetbrains.annotations.NotNull;

public class NBTTagListOf extends NBTListEnvelope<NBTBase, NBTTagList> {

    public NBTTagListOf(@NotNull final NBTTagList nbt) {
        super(nbt);
    }
}
