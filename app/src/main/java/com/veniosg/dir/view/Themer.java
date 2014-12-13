/*
 * Copyright (C) 2014 George Venios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.veniosg.dir.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import com.veniosg.dir.R;
import com.veniosg.dir.fragment.PreferenceFragment;

import static android.graphics.Color.LTGRAY;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.L;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static com.veniosg.dir.view.Themer.getThemedColor;

public class Themer {
    public static enum Theme {
        DIR,
        GRAYSCALE,
        DARK
    }

    public static final void applyTheme(Activity act) {
        switch (Theme.values()[PreferenceFragment.getThemeIndex(act)]) {
            case DIR:
                act.setTheme(R.style.Theme_Dir);
                break;
            case GRAYSCALE:
                act.setTheme(R.style.Theme_Dir_Grayscale);
                break;
            case DARK:
                act.setTheme(R.style.Theme_Dir_Dark);
                break;
        }
    }

    public static void setStatusBarColour(Activity activity, boolean actionModeEnabled) {
        if (activity != null) {
            int statusColourAttr = actionModeEnabled ?
                    R.attr.colorActionModeStatusBar : android.R.attr.statusBarColor;
            activity.getWindow().setStatusBarColor(getThemedColor(activity, statusColourAttr));
        }
    }

    public static final int getThemedResourceId(Context ctx, int attributeId) {
        return getThemedResourceId(ctx.getTheme(), attributeId);
    }


    public static final int getThemedResourceId(Resources.Theme theme, int attributeId) {
        TypedArray a = theme.obtainStyledAttributes(new int[]{attributeId});
        int result = a.getResourceId(0, -1);
        a.recycle();
        return result;
    }

    public static final int getThemedColor(Context ctx, int attributeId) {
        return getThemedColor(ctx.getTheme(), attributeId);
    }

    public static final int getThemedColor(Resources.Theme theme, int attributeId) {
        TypedArray a = theme.obtainStyledAttributes(new int[] {attributeId});
        int result = a.getColor(0, -1);
        a.recycle();
        return result;
    }

    public static final float getThemedDimension(Context ctx, int attributeId) {
        TypedArray a = ctx.getTheme().obtainStyledAttributes(new int[] {attributeId});
        float result = a.getDimension(0, 0);
        a.recycle();
        return result;
    }
}
