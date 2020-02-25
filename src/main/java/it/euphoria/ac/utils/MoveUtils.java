/*
    Class created by NotStxnks_
    Project: EuphoriaAC
    Date: 25/02/2020
    Time: 11:30
*/

package it.euphoria.ac.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class MoveUtils {

    public static boolean isInWater(Player player) {
        return player.getLocation().getBlock().isLiquid() || player.getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid() || player.getLocation().getBlock().getRelative(BlockFace.UP).isLiquid();
    }

    public static boolean isInWeb(Player player) {
        return player.getLocation().getBlock().getType() == XMaterial.COBWEB.parseMaterial() || player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == XMaterial.COBWEB.parseMaterial() || player.getLocation().getBlock().getRelative(BlockFace.UP).getType() == XMaterial.COBWEB.parseMaterial();
    }

    public static boolean isBlocksNear(Player player) {
        return isBlocksNear(player.getLocation());
    }

    public static boolean isBlocksNear(Location loc) {
        boolean nearBlocks = false;
        for (Block block2 : BlockUtils.getSurrounding(loc.getBlock(), true)) {
            if (block2.getType() == Material.AIR) continue;
            nearBlocks = true;
            break;
        }
        for (Block block2 : BlockUtils.getSurrounding(loc.getBlock(), false)) {
            if (block2.getType() == Material.AIR) continue;
            nearBlocks = true;
            break;
        }
        loc.setY(loc.getY() - 0.5);
        if (loc.getBlock().getType() != Material.AIR) {
            nearBlocks = true;
        }
        if (isBlock(loc.getBlock().getRelative(BlockFace.DOWN), new Material[]{XMaterial.OAK_FENCE.parseMaterial(), XMaterial.OAK_FENCE_GATE.parseMaterial(), XMaterial.COBBLESTONE_WALL.parseMaterial(), XMaterial.LADDER.parseMaterial()})) {
            nearBlocks = true;
        }
        return nearBlocks;
    }

    public static boolean isBlock(Block block, Material[] materials) {
        Material type = block.getType();
        int n = materials.length;
        int n2 = 0;
        while (n2 < n) {
            Material m = materials[n2];
            if (m == type) {
                return true;
            }
            ++n2;
        }
        return false;
    }
}
