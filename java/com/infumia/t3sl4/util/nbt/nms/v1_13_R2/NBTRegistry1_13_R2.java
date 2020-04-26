package com.infumia.t3sl4.util.nbt.nms.v1_13_R2;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import com.infumia.t3sl4.util.nbt.api.NBTList;
import com.infumia.t3sl4.util.nbt.api.NBTRegistry;
import com.infumia.t3sl4.util.nbt.nms.v1_13_R2.compound.NBTCompoundOf;
import com.infumia.t3sl4.util.nbt.nms.v1_13_R2.list.NBTTagListOf;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.cactoos.list.Mapped;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NBTRegistry1_13_R2 implements NBTRegistry {

    @NotNull
    @Override
    public ItemStack toItemStack(@NotNull NBTCompound nbtCompound) {
        return CraftItemStack.asBukkitCopy(
            net.minecraft.server.v1_13_R2.ItemStack.a(
                getTag(
                    nbtCompound.toString()
                )
            )
        );
    }

    @NotNull
    @Override
    public NBTCompound toCompound(@NotNull ItemStack itemStack) {
        return new NBTCompoundOf(
            CraftItemStack.asNMSCopy(itemStack).save(new NBTTagCompound())
        );
    }

    @NotNull
    @Override
    public NBTCompound toCompound(@NotNull String nbtString) {
        return new NBTCompoundOf(
            getTag(nbtString)
        );
    }

    @NotNull
    @Override
    public NBTList toTagStringList(@NotNull List<String> nbtString) {
        final NBTTagList nbtTagList = new NBTTagList();

        nbtTagList.addAll(
            new Mapped<>(
                NBTTagString::new,
                nbtString
            )
        );
        return new NBTTagListOf(nbtTagList);
    }

    @NotNull
    @Override
    public NBTList toTagStringList(@NotNull String nbtString) {
        final NBTTagList nbtTagList = new NBTTagList();

        nbtTagList.add(
            new NBTTagString(nbtString)
        );
        return new NBTTagListOf(nbtTagList);
    }

    @NotNull
    @Override
    public NBTCompound toCompound(@NotNull CreatureSpawner creatureSpawner) {
        final BlockPosition position = new BlockPosition(
            creatureSpawner.getX(),
            creatureSpawner.getY(),
            creatureSpawner.getZ()
        );
        final World world = ((CraftWorld)creatureSpawner.getWorld()).getHandle();
        final TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(position);

        if (spawner == null)
            return new NBTCompoundOf(
                new NBTTagCompound()
            );

        return new NBTCompoundOf(
            spawner.aa_()
        );
    }

    @NotNull
    @Override
    public CreatureSpawner toSpawner(@NotNull CreatureSpawner creatureSpawner, @NotNull NBTCompound nbtCompound) {
        final BlockPosition position = new BlockPosition(
            creatureSpawner.getX(),
            creatureSpawner.getY(),
            creatureSpawner.getZ()
        );
        final World world = ((CraftWorld)creatureSpawner.getWorld()).getHandle();
        final TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(position);

        if (spawner == null)
            return creatureSpawner;

        spawner.load(
            getTag(
                nbtCompound.toString()
            )
        );

        return creatureSpawner;
    }

    @Override
    public NBTCompound toCompound(@NotNull org.bukkit.entity.Entity entity) {
        net.minecraft.server.v1_13_R2.Entity nms = ((CraftEntity)entity).getHandle();
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        return new NBTCompoundOf(
            nms.save(nbtTagCompound)
        );
    }

    @Override
    public org.bukkit.entity.Entity toEntity(@NotNull Entity original, @NotNull NBTCompound applied) {
        net.minecraft.server.v1_13_R2.Entity nms = ((CraftEntity)original).getHandle();
        nms.f(
            getTag(
                applied.toString()
            )
        );
        return nms.getBukkitEntity();
    }

    @NotNull
    private NBTTagCompound getTag(@NotNull final String nbtString) {
        try {
            return MojangsonParser.parse(nbtString);
        } catch (CommandSyntaxException e) {
            return new NBTTagCompound();
        }
    }

}
