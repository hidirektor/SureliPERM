package com.infumia.t3sl4.sandikspawner.menu;

import io.github.portlek.inventory.Element;
import io.github.portlek.inventory.Pane;
import io.github.portlek.inventory.element.BasicElement;
import io.github.portlek.inventory.event.ElementBasicEvent;
import io.github.portlek.inventory.event.ElementClickEvent;
import io.github.portlek.inventory.event.ElementDragEvent;
import io.github.portlek.inventory.page.ChestPage;
import io.github.portlek.inventory.page.ControllableDownPage;
import io.github.portlek.inventory.pane.BasicPane;
import io.github.portlek.inventory.requirement.NoClickableDownReq;
import io.github.portlek.inventory.target.BasicTarget;
import io.github.portlek.inventory.target.ClickTarget;
import io.github.portlek.inventory.target.DragTarget;
import io.github.portlek.itemstack.item.meta.get.DisplayOf;
import io.github.portlek.itemstack.item.meta.get.LoreOf;
import io.github.portlek.itemstack.item.meta.set.SetDisplayOf;
import io.github.portlek.itemstack.item.meta.set.SetLoreOf;
import io.github.portlek.itemstack.item.meta.set.SetMetaOf;
import io.github.portlek.itemstack.util.Colored;
import io.github.portlek.mcyaml.IYaml;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.chest.placed.ChestPlaced;
import com.infumia.t3sl4.sandikspawner.chest.user.User;
import com.infumia.t3sl4.sandikspawner.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOfInts;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.IntStream;

public final class ChestSettingsMenu implements Menu {

    @NotNull
    private final SpawnerAPI spawnerAPI;

    @NotNull
    private final User user;

    @NotNull
    private final ChestPlaced placed;

    @NotNull
    private final IYaml sandikSettingsMenu;

    @NotNull
    private final IYaml ganimetDeposuMenu;

    public ChestSettingsMenu(@NotNull SpawnerAPI spawnerAPI, @NotNull Player player, @NotNull ChestPlaced placed) {
        this.spawnerAPI = spawnerAPI;
        user = spawnerAPI.findByPlayer(player);
        this.placed = placed;
        sandikSettingsMenu = spawnerAPI.sandikSettingsMenu;
        ganimetDeposuMenu = spawnerAPI.ganimetDeposuMenu;
    }

    @Override
    public void show() {
        final Pane pane = new BasicPane(
            sandikSettingsMenu.getInt("panes.main-pane.x"),
            sandikSettingsMenu.getInt("panes.main-pane.y"),
            sandikSettingsMenu.getInt("panes.main-pane.height"),
            sandikSettingsMenu.getInt("panes.main-pane.length")
        );

        final ItemStack esyaDusmesiItem = sandikSettingsMenu.getCustomItemStack("elements.esya-dusmesi-element");

        placed.replaceAll(esyaDusmesiItem);

        final Element esyaDusmesiElement = new BasicElement(
            replaceAll(esyaDusmesiItem),
            "esya-dusmesi-element",
            new BasicTarget(ElementBasicEvent::cancel),
            new DragTarget(ElementDragEvent::cancel),
            new ClickTarget(event -> {
                event.cancel();
                placed.changeEsyaDusmesi();
                show();
            })
        );

        pane.insert(
            new BasicElement(
                replaceAll(
                    sandikSettingsMenu.getCustomItemStack("elements.ganimet-deposu-element")
                ),
                "ganimet-deposu-element",
                new BasicTarget(ElementBasicEvent::cancel),
                new DragTarget(ElementDragEvent::cancel),
                new ClickTarget(event -> {
                    event.cancel();
                    new GanimetDeposuMenu(
                        spawnerAPI,
                        this,
                        ganimetDeposuMenu,
                        event.player()
                    ).show();
                })
            ),
            sandikSettingsMenu.getInt("elements.ganimet-deposu-element.x"),
            sandikSettingsMenu.getInt("elements.ganimet-deposu-element.y"),
            false
        );
        pane.insert(
            esyaDusmesiElement,
            sandikSettingsMenu.getInt("elements.esya-dusmesi-element.x"),
            sandikSettingsMenu.getInt("elements.esya-dusmesi-element.y"),
            false
        );

        if (placed.level >= placed.chestType.maxLevel()) {
            pane.insert(
                new BasicElement(
                    replaceAll(
                        sandikSettingsMenu.getCustomItemStack("elements.cant-level-element")
                    ),
                    "cant-level-element",
                    new BasicTarget(ElementBasicEvent::cancel),
                    new DragTarget(ElementDragEvent::cancel),
                    new ClickTarget(ElementClickEvent::cancel)
                ),
                sandikSettingsMenu.getInt("elements.cant-level-element.x"),
                sandikSettingsMenu.getInt("elements.cant-level-element.y"),
                false
            );
        } else {
            pane.insert(
                new BasicElement(
                    replaceAll(sandikSettingsMenu.getCustomItemStack("elements.level-element")),
                    "level-element",
                    new BasicTarget(ElementBasicEvent::cancel),
                    new DragTarget(ElementDragEvent::cancel),
                    new ClickTarget(event -> {
                        event.cancel();
                        event.closeView();

                        final PlayerInventory inventory = event.player().getInventory();
                        final List<Integer> filtered = new ListOf<>(
                            new Filtered<>(
                                i -> {
                                    final ItemStack itemStack = inventory.getItem(i);

                                    return itemStack != null  && itemStack.isSimilar(placed.chestType.getChestItem(1));
                                },
                                new IterableOfInts(
                                    IntStream.range(0, Util.getStorageContents(inventory).length).toArray()
                                )
                            )
                        );

                        if (filtered.isEmpty()) {
                            event.player().sendMessage(spawnerAPI.getLanguage().errorNotEnoughItem);
                            return;
                        }

                        final ItemStack itemStack = inventory.getItem(filtered.get(0));

                        if (itemStack == null) {
                            event.player().sendMessage(spawnerAPI.getLanguage().errorNotEnoughItem);
                            return;
                        }

                        itemStack.setAmount(itemStack.getAmount() - 1);

                        if (itemStack.getAmount() > 0) {
                            event.player().getInventory().setItem(filtered.get(0), itemStack);
                        } else {
                            event.player().getInventory().setItem(filtered.get(0), null);
                        }

                        Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin,() -> {
                            placed.levelUp();
                            show();
                        }, 2L);
                    })
                ),
                sandikSettingsMenu.getInt("elements.level-element.x"),
                sandikSettingsMenu.getInt("elements.level-element.y"),
                false
            );
        }

        user.openPage(
            new ControllableDownPage(
                new ChestPage(
                    replaceAll(
                        sandikSettingsMenu.getString("title").orElse("")
                    ),
                    sandikSettingsMenu.getInt("size"),
                    pane
                ),
                new NoClickableDownReq()
            )
        );
    }

    @NotNull
    public ItemStack replaceAll(@NotNull ItemStack item) {
        return new SetMetaOf(
            item,
            new SetLoreOf(
                new SetDisplayOf(
                    item,
                    replaceAll(
                        new DisplayOf(
                            item
                        ).value().orElse("")
                    )
                ).value(),
                new ListOf<>(
                    new Mapped<>(
                        this::replaceAll,
                        new LoreOf(
                            item
                        ).value()
                    )
                )
            )
        ).value();
    }

    @NotNull
    private String replaceAll(@NotNull String text) {
        return new Colored(
            text
                .replaceAll("%spawner-name%", placed.chestType.getName())
                .replaceAll("%spawner-level%", String.valueOf(placed.level))
        ).value();
    }

}
