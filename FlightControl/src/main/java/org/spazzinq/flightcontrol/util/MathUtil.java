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

package org.spazzinq.flightcontrol.util;

public final class MathUtil {
    public static float calcConvertedSpeed(float unconvertedSpeed) {
        float actualSpeed;
        float defaultSpeed = 0.1f;
        float maxSpeed = 1f;
        float wrongSpeed = unconvertedSpeed;

        if (wrongSpeed > 10f) {
            wrongSpeed = 10f;
        } else if (wrongSpeed < 0.0001f) {
            wrongSpeed = 0.0001f;
        }

        if (wrongSpeed < 1f) {
            actualSpeed = defaultSpeed * wrongSpeed;
        } else {
            float ratio = ((wrongSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
            actualSpeed = ratio + defaultSpeed;
        }

        return actualSpeed;
    }

    public static int days(long length) {
        return (int) (length / 86400);
    }

    public static short hours(long length) {
        return (short) (length % 86400 / 3600);
    }

    public static short minutes(long length) {
        return (short) (length % 3600 / 60);
    }

    public static short seconds(long length) {
        return (short) (length % 60);
    }

    public static int[] timeArray(long length) {
        return new int[] {days(length), hours(length), minutes(length), seconds(length)};
    }
}
