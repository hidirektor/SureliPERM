package com.infumia.t3sl4.util.nbt.nms.v1_9_R2.list;

import com.infumia.t3sl4.util.nbt.nms.v1_9_R2.NBTListEnvelope;
import net.minecraft.server.v1_9_R2.NBTBase;
import net.minecraft.server.v1_9_R2.NBTTagList;
import org.jetbrains.annotations.NotNull;

public class NBTTagListOf extends NBTListEnvelope<NBTBase, NBTTagList> {

    public NBTTagListOf(@NotNull final NBTTagList nbt) {
        super(nbt);
    }
}
