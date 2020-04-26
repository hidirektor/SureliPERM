package com.infumia.t3sl4.sandikspawner.menu;

import com.infumia.t3sl4.sandikspawner.util.InventoryIsFull;
import io.github.portlek.inventory.Pane;
import io.github.portlek.inventory.element.BasicElement;
import io.github.portlek.inventory.event.ElementBasicEvent;
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
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.SumOf;
import org.jetbrains.annotations.NotNull;
import com.infumia.t3sl4.util.oututil.bookapi.BookUtil;

import java.util.List;
import java.util.Objects;

public final class ChestIciMenu {

    @NotNull
    private final SpawnerAPI spawnerAPI;

    @NotNull
    private final IYaml chestIciMenu;

    @NotNull
    private final User user;

    @NotNull
    private final ChestPlaced placed;

    public ChestIciMenu(@NotNull SpawnerAPI spawnerAPI, @NotNull Player player, @NotNull ChestPlaced placed) {
        this.spawnerAPI = spawnerAPI;
        chestIciMenu = spawnerAPI.chestIciMenu;
        user = spawnerAPI.findByPlayer(player);
        this.placed = placed;
    }

    public void show() {
        final Pane pane = new BasicPane(
            chestIciMenu.getInt("panes.main-pane.x"),
            chestIciMenu.getInt("panes.main-pane.y"),
            chestIciMenu.getInt("panes.main-pane.height"),
            chestIciMenu.getInt("panes.main-pane.length")
        );
        final Pane elementsPane = new BasicPane(
            chestIciMenu.getInt("panes.elements-pane.x"),
            chestIciMenu.getInt("panes.elements-pane.y"),
            chestIciMenu.getInt("panes.elements-pane.height"),
            chestIciMenu.getInt("panes.elements-pane.length")
        );

        placed.chest.getInventory().forEach(itemStack -> {
            if (itemStack == null) {
                return;
            }

            pane.add(
                new BasicElement(
                    itemStack,
                    false,
                    new BasicTarget(ElementBasicEvent::cancel),
                    new DragTarget(ElementDragEvent::cancel),
                    new ClickTarget(event -> {
                        event.cancel();

                        if (!new InventoryIsFull(event.player()).apply(event.currentItem())) {
                            return;
                        }

                        placed.chest.getInventory().removeItem(event.currentItem());
                        event.player().getInventory().addItem(event.currentItem());
                        event.closeView();
                        Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin, this::show, 2L);
                    })
                )
            );
        });

        elementsPane.insert(
            new BasicElement(
                chestIciMenu.getCustomItemStack("elements.spawner-settings-element"),
                new BasicTarget(ElementBasicEvent::cancel),
                new DragTarget(ElementDragEvent::cancel),
                new ClickTarget(event -> {
                    event.cancel();
                    event.closeView();

                    Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin,() -> new ChestSettingsMenu(
                        spawnerAPI,
                        event.player(),
                        placed
                    ).show(), 2L);
                })
            ),
            chestIciMenu.getInt("elements.spawner-settings-element.x"),
            chestIciMenu.getInt("elements.spawner-settings-element.y"),
            false
        );

        elementsPane.insert(
            new BasicElement(
                chestIciMenu.getCustomItemStack("elements.general-settings-element"),
                new BasicTarget(ElementBasicEvent::cancel),
                new DragTarget(ElementDragEvent::cancel),
                new ClickTarget(event -> {
                    event.cancel();
                    event.closeView();

                    Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin,() -> new GenelAyarlarMenu(
                        event.player(),
                        spawnerAPI
                    ).show(), 2L);
                })
            ),
            chestIciMenu.getInt("elements.general-settings-element.x"),
            chestIciMenu.getInt("elements.general-settings-element.y"),
            false
        );

        elementsPane.insert(
            new BasicElement(
                replaceAll(
                    chestIciMenu.getCustomItemStack("elements.sell-element")
                ),
                new BasicTarget(ElementBasicEvent::cancel),
                new DragTarget(ElementDragEvent::cancel),
                new ClickTarget(event -> {
                    event.cancel();
                    event.closeView();

                    Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin, this::openBook, 2L);
                })
            ),
            chestIciMenu.getInt("elements.sell-element.x"),
            chestIciMenu.getInt("elements.sell-element.y"),
            false
        );

        user.openPage(
            new ControllableDownPage(
                new ChestPage(
                    replaceAll(
                        chestIciMenu.getString("title").orElse("")
                    ),
                    chestIciMenu.getInt("size"),
                    pane,
                    elementsPane
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
        final String durum = Util.getStorageContents(placed.chest.getInventory()).length == 0
            ? chestIciMenu.getString("sell-element-durum-1").orElse("")
            : chestIciMenu.getString("sell-element-durum-2").orElse("");
        return new Colored(
            text
                .replaceAll("%spawner-name%", placed.chestType.getName())
                .replaceAll("%spawner-level%", String.valueOf(placed.level))
                .replaceAll(
                    "%money%",
                    String.valueOf(
                        new SumOf(
                            new Mapped<>(
                                ItemStack::getAmount,
                                new Filtered<>(
                                    Objects::nonNull,
                                    Util.getStorageContents(placed.chest.getInventory())
                                )
                            )
                        ).intValue()
                        * placed.chestType.money()
                    )
                )
                .replaceAll(
                    "%amount%",
                    String.valueOf(
                        new SumOf(
                            new Mapped<>(
                                ItemStack::getAmount,
                                new Filtered<>(
                                    Objects::nonNull,
                                    Util.getStorageContents(placed.chest.getInventory())
                                )
                            )
                        ).intValue()
                    )
                )
                .replaceAll(
                    "%durum%",
                    durum
                        .replaceAll(
                            "%amount%",
                            String.valueOf(
                                new SumOf(
                                    new Mapped<>(
                                        ItemStack::getAmount,
                                        new Filtered<>(
                                            Objects::nonNull,
                                            Util.getStorageContents(placed.chest.getInventory())
                                        )
                                    )
                                ).intValue()
                            )
                        )
                        .replaceAll(
                            "%spawner-name%",
                            placed.chestType.getName()
                        )
                )
        ).value();
    }

    private void openBook() {
        if (user.getPlayer() == null) {
            return;
        }
        final List<String> pages = spawnerAPI.getConfigs().book;

        final BookUtil.PageBuilder pageBuilder = new BookUtil.PageBuilder();

        for (String list : pages) {
            if (!list.contains("%yesno%")) {
                pageBuilder.add(BookUtil.TextBuilder.of(replaceAll(list)).build()).newLine();
                continue;
            }
            pageBuilder
                .add(
                    BookUtil.TextBuilder
                        .of(spawnerAPI.getConfigs().yes)
                        .onClick(BookUtil.ClickAction.runCommand("/chestsp sell " + placed.uuid.toString()))
                        .build()
                )
                .add(
                    BookUtil.TextBuilder
                        .of(spawnerAPI.getConfigs().no)
                        .onClick(BookUtil.ClickAction.runCommand("/chestsp closeInventory"))
                        .build()
                ).newLine();
        }

        BookUtil.openPlayer(
            user.getPlayer(),
            BookUtil.writtenBook()
                .author("")
                .title("")
                .pages(pageBuilder.build())
                .build()
        );
    }

}
