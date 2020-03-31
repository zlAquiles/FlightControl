/*
 * This file is part of FlightControl, which is licensed under the MIT License.
 *
 * Copyright (c) 2020 Spazzinq
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

package org.spazzinq.flightcontrol.api.events.interfaces;

@SuppressWarnings("unused")
public interface MessageFlightEvent extends FlightEvent {
    /**
     * Returns the tentative message.
     *
     * @return the tentative message
     */
    String getMessage();

    /**
     * Sets the message to send.
     *
     * @param message the message to send
     */
    void setMessage(String message);

    /**
     * Returns true if the message will be sent at the action bar (the area above the hotbar).
     *
     * @return true if the message will be sent at the action bar, false otherwise
     */
    boolean isByActionbar();

    /**
     * Sets if the message will be sent at the action bar (the area above the hotbar).
     *
     * @param byActionbar a boolean to set if the message will be sent at the action bar
     */
    void setByActionbar(boolean byActionbar);
}
