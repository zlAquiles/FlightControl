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

package org.spazzinq.flightcontrol.object;

import lombok.Getter;
import org.spazzinq.flightcontrol.FlightControl;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public abstract class StorageManager {
    protected final FlightControl pl;

    @Getter protected CommentConf conf;
    protected final File confFile;
    protected String fileName;

    protected boolean reloadIgnored;

    public StorageManager(String fileName) {
        this.fileName = fileName;
        pl = FlightControl.getInstance();
        confFile = new File(pl.getDataFolder(), fileName);
    }

    public boolean load() {
        boolean initIgnored = !reloadIgnored;

        if (initIgnored) {
            ignoreReload();

            initializeConf();
            migrateFromOldVersion();
            updateFormatting();
            initializeValues();
        }

        return initIgnored;
    }

    protected abstract void initializeConf();

    protected abstract void initializeValues();

    protected abstract void updateFormatting();

    protected abstract void migrateFromOldVersion();

    public void set(String path, Object value) {
        ignoreReload();
        conf.set(path, value);
        conf.save();
    }

    private void ignoreReload() {
        reloadIgnored = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                reloadIgnored = false;
            }
        }, 500);
    }
}
