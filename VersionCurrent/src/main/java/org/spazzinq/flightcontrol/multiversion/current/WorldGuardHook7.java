/*
 * This file is part of FlightControl, which is licensed under the MIT License.
 *
 * Copyright (c) 2021 Spazzinq
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.spazzinq.flightcontrol.multiversion.current;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spazzinq.flightcontrol.multiversion.WorldGuardHookBase;

import java.util.Iterator;

@SuppressWarnings("ALL")
public class WorldGuardHook7 extends WorldGuardHookBase {
    private WorldGuardPlatform platform;

    public boolean isMember(Player p) {
        ProtectedRegion region = getRegion(p.getLocation());

        return region != null && region.hasMembersOrOwners() && region.isMember(WorldGuardPlugin.inst().wrapPlayer(p));
    }

    public boolean isOwner(Player p) {
        ProtectedRegion region = getRegion(p.getLocation());

        return region != null && region.hasMembersOrOwners() && region.isOwner(WorldGuardPlugin.inst().wrapPlayer(p));
    }

    @Override protected ProtectedRegion getRegion(Location l) {
        ProtectedRegion region = null;
        Iterator<ProtectedRegion> iter = getRegionContainer().createQuery()
                .getApplicableRegions(BukkitAdapter.adapt(l)).iterator();

        if (iter.hasNext()) {
            region = iter.next();
        }

        return region;
    }

    private RegionContainer getRegionContainer() {
        if (platform == null) {
            try {
                platform = WorldGuard.getInstance().getPlatform();
            } catch (NullPointerException ignored) {}
        }

        return platform.getRegionContainer();
    }
}
