/*
 * Copyright (C) 2012 www.apkdv.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.neday.graduates.view;

import android.graphics.Bitmap;

import com.dylanc.longan.ScreenKt;

import java.util.HashMap;

/**
 * This class represents a single Droidflake, with properties representing its
 * size, rotation, location, and speed.
 */
class Flake {

    // This map stores pre-scaled bitmaps according to the width. No reason to create
    // new bitmaps for sizes we've already seen.
    private static final HashMap<Integer, Bitmap> bitmapMap = new HashMap<>();
    // These are the unique properties of any flake: its size, rotation, speed,
    // location, and its underlying Bitmap object
    float x, y;
    float rotation;
    float speed;
    float rotationSpeed;
    int width, height;
    Bitmap bitmap;

    /**
     * Creates a new droidflake in the given xRange and with the given bitmap. Parameters of
     * location, size, rotation, and speed are randomly determined.
     */
    static Flake createFlake(float xRange, Bitmap originalBitmap) {
        Flake flake = new Flake();
        // Size each flake with a width between 5 and 55 and a proportional height
        if (ScreenKt.getScreenWidth() >= 1080) {
            flake.width = (int) (5 + (float) Math.random() * 80);
            float hwRatio = originalBitmap.getHeight() / originalBitmap.getWidth();
            flake.height = (int) (flake.width * hwRatio + 60);
        } else {
            flake.width = (int) (5 + (float) Math.random() * 50);
            float hwRatio = originalBitmap.getHeight() / originalBitmap.getWidth();
            flake.height = (int) (flake.width * hwRatio + 40);

        }
        // Position the flake horizontally between the left and right of the range
        flake.x = (float) Math.random() * (xRange - flake.width);
        // Position the flake vertically slightly off the top of the display
        flake.y = 0 - (flake.height + (float) Math.random() * flake.height);

        // Each flake travels at 50-200 pixels per second
        flake.speed = 50 + (float) Math.random() * 150;

        // Flakes start at -90 to 90 degrees rotation, and rotate between -45 and 45
        // degrees per second
        flake.rotation = (float) Math.random() * 180 - 90;
        flake.rotationSpeed = (float) Math.random() * 90 - 45;

        // Get the cached bitmap for this size if it exists, otherwise create and cache one
        flake.bitmap = bitmapMap.get(flake.width);
        if (flake.bitmap == null) {
            flake.bitmap = Bitmap.createScaledBitmap(originalBitmap,
                    flake.width, flake.height, true);
            bitmapMap.put(flake.width, flake.bitmap);
        }
        return flake;
    }
}
