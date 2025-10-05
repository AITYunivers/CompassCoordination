package io.github.yunivers.CompassCoordination.gui.hud.widget;

import io.github.yunivers.CompassCoordination.config.Config;
import io.github.yunivers.regui.event.HudWidgetRenderEvent;
import io.github.yunivers.regui.gui.hud.widget.HudWidget;
import io.github.yunivers.regui.util.EHudDock;
import io.github.yunivers.regui.util.EHudPriority;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class CompassWidget extends HudWidget
{
    public CompassWidget()
    {
        super(EHudDock.LEFT);
        priority = EHudPriority.LOWEST;
    }

    @SuppressWarnings("MalformedFormatString")
    @Override
    public void render(InGameHud hud, float tickDelta, ScreenScaler scaler, int xOffset, int yOffset, HudWidget prevWidget)
    {
        super.render(hud, tickDelta, scaler, xOffset, yOffset, prevWidget);
        HudWidgetRenderEvent eResult = this.renderEvent(0); // Pre-Render
        PlayerEntity player = hud.minecraft.player;
        ItemStack hand = player.getHand();
        if (eResult.cancelNextRender || hand == null || hand.getItem() != Item.COMPASS)
            return;

        int height = scaler.getScaledHeight();
        this.zOffset = -89.0F;

        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, (float)height - 2 - 8, 0.0F);

        eResult = this.renderEvent(1); // Pre-Render Coordinates
        if (!eResult.cancelNextRender)
        {
            int x = 2;
            ArrayList<String> messages = new ArrayList<>();
            if (Config.Config.showX)
                messages.add("X: " + String.format("%." + Config.Config.coordPrec.toString() + "f", player.x));
            if (Config.Config.showY)
                messages.add("Y: " + String.format("%." + Config.Config.coordPrec.toString() + "f", player.y));
            if (Config.Config.showZ)
                messages.add("Z: " + String.format("%." + Config.Config.coordPrec.toString() + "f", player.z));
            if (Config.Config.showFacing)
                messages.add(DirectionAsString(Direction.getEntityFacingOrder(player)));

            for (int i = messages.size() - 1; i >= 0; i--)
            {
                int y = -i * 9;
                this.fill(x, y - 1, x + hud.minecraft.textRenderer.getWidth(messages.get(messages.size() - 1 - i)) + 4, y + 8, 255 / 2 << 24);
                GL11.glEnable(3042);
                hud.minecraft.textRenderer.drawWithShadow(messages.get(messages.size() - 1 - i), x + 2, y, 16777215 + (255 << 24));
            }
        }

        this.renderEvent(2); // Post-Render Coordinates
        GL11.glPopMatrix();
        GL11.glEnable(3008);
        GL11.glDisable(3042);
        this.renderEvent(3); // EOF
    }

    private String DirectionAsString(Direction[] dirs)
    {
        for (Direction dir : dirs)
            if (dir != Direction.UP && dir != Direction.DOWN)
            {
                if (Config.Config.rotateFacing)
                    dir = dir.rotateYCounterclockwise();
                return dir.getName().substring(0, 1).toUpperCase() + dir.getName().substring(1);
            }
        return "Error";
    }
}
