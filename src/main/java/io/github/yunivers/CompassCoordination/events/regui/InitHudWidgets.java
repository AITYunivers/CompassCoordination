package io.github.yunivers.CompassCoordination.events.regui;

import io.github.yunivers.CompassCoordination.gui.hud.widget.CompassWidget;
import io.github.yunivers.regui.event.InGameHudWidgetInitEvent;
import net.mine_diver.unsafeevents.listener.EventListener;

public class InitHudWidgets
{
    @EventListener
    public void initHudWidgets(InGameHudWidgetInitEvent event)
    {
        event.hudWidgets.add(new CompassWidget());
    }
}
