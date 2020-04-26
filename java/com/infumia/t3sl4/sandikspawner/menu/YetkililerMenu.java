package com.infumia.t3sl4.sandikspawner.menu;

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
import com.infumia.t3sl4.sandikspawner.menu.Menu;
import com.infumia.t3sl4.sandikspawner.SpawnerAPI;
import com.infumia.t3sl4.sandikspawner.chest.user.User;
import com.infumia.t3sl4.util.title.base.TitlePlayerOf;
import com.infumia.t3sl4.util.oututil.anvilapi.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.cactoos.list.ListOf;
import org.cactoos.list.Mapped;
import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.stream.IntStream;

public final class YetkililerMenu implements Menu {

    @NotNull
    private final SpawnerAPI spawnerAPI;

    @NotNull
    private final IYaml yetkililerMenu;

    @NotNull
    private final Menu previousMenu;

    @NotNull
    private final User user;

    public YetkililerMenu(@NotNull Menu previousMenu, @NotNull SpawnerAPI spawnerAPI, @NotNull IYaml yetkililerMenu, @NotNull Player player) {
        this.previousMenu = previousMenu;
        this.spawnerAPI = spawnerAPI;
        this.yetkililerMenu = yetkililerMenu;
        user = spawnerAPI.findByPlayer(player);
    }

    @Override
    public void show() {
        final Pane pane = new BasicPane(
            yetkililerMenu.getInt("panes.main-pane.x"),
            yetkililerMenu.getInt("panes.main-pane.y"),
            yetkililerMenu.getInt("panes.main-pane.height"),
            yetkililerMenu.getInt("panes.main-pane.length")
        );

        IntStream.range(1,6).forEach(value -> {
            if (value > user.yetkililer.size()) {
                pane.insert(
                    getYetkiliOlmayanElement(value),
                    yetkililerMenu.getInt("yetkili-elements.x" + value),
                    yetkililerMenu.getInt("yetkili-elements.y" + value),
                    false
                );
                return;
            }

            final UUID uuid = user.yetkililer.keySet().toArray(new UUID[0])[value - 1];
            final String name = Bukkit.getOfflinePlayer(uuid).getName();

            if (Bukkit.getOfflinePlayer(uuid).getLastPlayed() == 0 || name == null) {
                pane.insert(
                    getYetkiliOlmayanElement(value),
                    yetkililerMenu.getInt("yetkili-elements.x" + value),
                    yetkililerMenu.getInt("yetkili-elements.y" + value),
                    false
                );
            } else {
                final ItemStack itemStack = yetkililerMenu.getCustomItemStack("elements.yetkili-olan-element");
                pane.insert(
                    new BasicElement(
                        replaceAll(itemStack, uuid, name),
                        "yetkili-olan-element-" + value,
                        new BasicTarget(ElementBasicEvent::cancel),
                        new DragTarget(ElementDragEvent::cancel),
                        new ClickTarget(event -> {
                            event.cancel();
                            event.closeView();
                            user.removeYetkili(uuid);
                            Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin, this::show, 2L);
                        })
                    ),
                    yetkililerMenu.getInt("yetkili-elements.x" + value),
                    yetkililerMenu.getInt("yetkili-elements.y" + value),
                    false
                );
            }
        });

        pane.insert(
            new BasicElement(
                yetkililerMenu.getCustomItemStack("elements.back-element"),
                "back-element",
                new BasicTarget(ElementBasicEvent::cancel),
                new DragTarget(ElementDragEvent::cancel),
                new ClickTarget(event -> {
                    event.cancel();
                    previousMenu.show();
                })
            ),
            yetkililerMenu.getInt("elements.back-element.x"),
            yetkililerMenu.getInt("elements.back-element.y"),
            false
        );

        user.openPage(
            new ControllableDownPage(
                new ChestPage(
                    new Colored(yetkililerMenu.getString("title").orElse("")).value(),
                    yetkililerMenu.getInt("size"),
                    pane
                ),
                new NoClickableDownReq()
            )
        );
    }

    @NotNull
    private BasicElement getYetkiliOlmayanElement(int value) {
        return new BasicElement(
            yetkililerMenu.getCustomItemStack("elements.yetkili-olmayan-element"),
            "yetkili-olmayan-element-" + value,
            new BasicTarget(ElementBasicEvent::cancel),
            new DragTarget(ElementDragEvent::cancel),
            new ClickTarget(event -> {
                event.cancel();
                event.closeView();
                Bukkit.getScheduler().runTaskLater(spawnerAPI.plugin, () -> new AnvilGUI.Builder()
                    .plugin(spawnerAPI.plugin)
                    .text(spawnerAPI.getConfigs().yetkiliAnvil)
                    .onComplete((player, s) -> {
                        if (s.equalsIgnoreCase(player.getName())) {
                            new TitlePlayerOf(
                                event.player()
                            ).sendTitle(spawnerAPI.getLanguage().errorYouCantAddYourself);
                            return AnvilGUI.Response.close();
                        }

                        final Player nullablePlayer = Bukkit.getPlayer(s);

                        if (nullablePlayer != null &&
                            !user.yetkililer.containsKey(nullablePlayer.getUniqueId())) {
                            user.addYetkili(nullablePlayer.getUniqueId());
                            new TitlePlayerOf(
                                event.player()
                            ).sendTitle(spawnerAPI.getLanguage().generalPlayerAdded(nullablePlayer.getName()));
                            return AnvilGUI.Response.close();
                        }

                        if (nullablePlayer != null && user.yetkililer.containsKey(nullablePlayer.getUniqueId())) {
                            new TitlePlayerOf(
                                event.player()
                            ).sendTitle(spawnerAPI.getLanguage().errorPlayerAlreadyAdded(nullablePlayer.getName()));
                            return AnvilGUI.Response.close();
                        }

                        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(s);

                        if (!user.yetkililer.containsKey(offlinePlayer.getUniqueId())
                            && offlinePlayer.getName() != null && offlinePlayer.getLastPlayed() != 0) {
                            user.addYetkili(offlinePlayer.getUniqueId());
                            new TitlePlayerOf(
                                event.player()
                            ).sendTitle(spawnerAPI.getLanguage().generalPlayerAdded(offlinePlayer.getName()));
                            return AnvilGUI.Response.close();
                        }

                        new TitlePlayerOf(
                            event.player()
                        ).sendTitle(spawnerAPI.getLanguage().errorPlayerNotFound);
                        return AnvilGUI.Response.close();
                    }).open(event.player()), 2L);
            })
        );
    }

    @NotNull
    private ItemStack replaceAll(@NotNull ItemStack itemStack, @NotNull UUID uuid, @NotNull String name) {
        return new SetMetaOf(
            itemStack,
            new SetLoreOf(
                new SetDisplayOf(
                    itemStack,
                    replaceAll(
                        new DisplayOf(
                            itemStack
                        ).value().orElse(""),
                        uuid,
                        name
                    )
                ).value(),
                new ListOf<>(
                    new Mapped<>(
                        lore -> replaceAll(lore, uuid, name),
                        new LoreOf(
                            itemStack
                        ).value()
                    )
                )
            )
        ).value();
    }

    @NotNull
    private String replaceAll(@NotNull String text, @NotNull UUID uuid, @NotNull String name) {
        return text
            .replaceAll("%player%", name)
            .replaceAll(
                "%durum%",
                Bukkit.getPlayer(uuid) == null
                    ? spawnerAPI.getLanguage().menuStatusOffline
                    : spawnerAPI.getLanguage().menuStatusOnline
            ).replaceAll("%son-oyuna-giris-tarihi%", toHistory(Bukkit.getOfflinePlayer(uuid).getLastPlayed()))
            .replaceAll(
                "%eklenme-tarihi%",
                new SimpleDateFormat(
                    "yyyy/MM/dd - HH:mm:ss"
                ).format(
                    user.yetkililer.getOrDefault(uuid, new Date().getTime())
                )
            );
    }

    @NotNull
    private String toHistory(long history) {
        final Date date = new Date(new Date().getTime() - history - (2 * 60 * 60 * 1000));
        final Format format = new SimpleDateFormat("HH mm ss");

        return format.format(date);
    }

}
