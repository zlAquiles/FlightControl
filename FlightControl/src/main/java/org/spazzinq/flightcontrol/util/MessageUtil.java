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

package org.spazzinq.flightcontrol.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class MessageUtil {
    public static void msg(CommandSender s, String msg) {
        msg(s, msg, false);
    }

    public static void msg(CommandSender s, String msg, boolean actionBar) {
        if (msg != null && !msg.isEmpty()) {
            String finalMsg = ChatColor.translateAlternateColorCodes('&', msg);

            if (actionBar && s instanceof Player) {
                ActionbarUtil.sendActionbar((Player) s, finalMsg);
            } else {
                s.sendMessage((s instanceof ConsoleCommandSender ? "[FlightControl] " : "")
                        + finalMsg);
            }
        }
    }

    public static void msgVar(CommandSender s, String msg, boolean actionBar, HashMap<String, String> toReplace) {
        String finalMsg = msg;

        for (Map.Entry<String, String> entry : toReplace.entrySet()) {
            finalMsg = finalMsg.replaceAll("%" + entry.getKey() + "%", entry.getValue());
        }

        msg(s, finalMsg, actionBar);
    }

    public static void msgVar(CommandSender s, String msg, boolean actionBar, String var, String value) {
        msg(s, msg.replace("%" + var + "%", value), actionBar);
    }
}
