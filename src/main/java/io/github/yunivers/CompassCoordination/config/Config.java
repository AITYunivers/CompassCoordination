package io.github.yunivers.CompassCoordination.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

@SuppressWarnings("ALL")
public class Config
{
    @ConfigRoot(value = "config", visibleName = "Compass Coordination Config")
    public static final BaseConfig Config = new BaseConfig();

    public static class BaseConfig
    {
        @ConfigEntry(
            name = "Show X Coord",
            description = "Whether or not to show the X coordinate"
        )
        public Boolean showX = true;

        @ConfigEntry(
            name = "Show Y Coord",
            description = "Whether or not to show the Y coordinate"
        )
        public Boolean showY = true;

        @ConfigEntry(
            name = "Show Z Coord",
            description = "Whether or not to show the Z coordinate"
        )
        public Boolean showZ = true;

        @ConfigEntry(
            name = "Show Facing",
            description = "Whether or not to show where the player is facing"
        )
        public Boolean showFacing = true;

        @ConfigEntry(
            name = "Rotate Facing",
            description = "Whether or not to rotate the facing to be less accurate, but inline with other mods like BetterF3"
        )
        public Boolean rotateFacing = false;

        @ConfigEntry(
            name = "Coordinate Precision",
            description = "How many decimals to put in the coordinate",
            minValue = 0,
            maxValue = 32
        )
        public Integer coordPrec = 3;
    }
}
