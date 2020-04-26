package com.infumia.t3sl4.util.nbt.nms.v1_12_R1;

import com.infumia.t3sl4.util.nbt.api.NBTCompound;
import com.infumia.t3sl4.util.nbt.api.NBTList;
import com.infumia.t3sl4.util.nbt.api.NBTRegistry;
import com.infumia.t3sl4.util.nbt.nms.v1_12_R1.compound.NBTCompoundOf;
import com.infumia.t3sl4.util.nbt.nms.v1_12_R1.list.NBTTagListOf;
import com.infumia.t3sl4.util.reflection.clazz.ClassOf;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.cactoos.list.Mapped;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NBTRegistry1_12_R1 implements NBTRegistry {

    @NotNull
    @Override
    public ItemStack toItemStack(@NotNull NBTCompound nbtCompound) {
        return CraftItemStack.asBukkitCopy(
            new net.minecraft.server.v1_12_R1.ItemStack(
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

        new Mapped<>(
            NBTTagString::new,
            nbtString
        ).forEach(nbtTagList::add);

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
            spawner.d()
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

        String version = ((CraftServer)Bukkit.getServer()).getServer().getVersion();

        if (version.equalsIgnoreCase("1.12.2"))
            spawner.load(
                getTag(
                    nbtCompound.toString()
                )
            );
        else {
            new ClassOf(spawner)
                .getMethod("a", NBTTagCompound.class)
                .of(spawner)
                .call(
                    getTag(
                        nbtCompound.toString()
                    )
                );
        }

        return creatureSpawner;
    }

    @Override
    public NBTCompound toCompound(@NotNull org.bukkit.entity.Entity entity) {
        net.minecraft.server.v1_12_R1.Entity nms = ((CraftEntity)entity).getHandle();
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        return new NBTCompoundOf(
            nms.save(nbtTagCompound)
        );
    }

    @Override
    public org.bukkit.entity.Entity toEntity(@NotNull Entity original, @NotNull NBTCompound applied) {
        net.minecraft.server.v1_12_R1.Entity nms = ((CraftEntity)original).getHandle();
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
        } catch (MojangsonParseException e) {
            return new NBTTagCompound();
        }
    }

}
