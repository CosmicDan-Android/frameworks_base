/*
 * Copyright (C) 2019 Descendant
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

package com.android.internal.util.descendant;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.util.Log;

public class DescendantThemeUtils {

    public static final String TAG = "DescendantThemeUtils";

    public enum ThemeType {
        UI_THEME, ICON_THEME 
    }

    private static final String[] uiThemes = {
        "default.uitheme",
        "org.descendant.UI.android.roundier",
        "org.descendant.UI.system.roundier",
        "org.descendant.UI.android.square",
        "org.descendant.UI.system.square"
    };  

    private static final String[] iconThemes = {
        "default.icontheme",
        "org.descendant.qs.teardrop.overlay",
        "org.descendant.settings.teardrop.overlay",
        "org.descendant.qs.superbubble.overlay",
        "org.descendant.settings.superbubble.overlay",
        "org.descendant.qs.square.overlay",
        "org.descendant.settings.square.overlay"
    };

    public static void omniSet(IOverlayManager om, int userId, int setting, ThemeType themeType, Context context) { 
        String[] themes;
        if (themeType == ThemeType.UI_THEME) {
            themes = uiThemes;
        } else {
            themes = iconThemes;
        }

        switch (setting) {
            case 0:
                omniUnset(om, userId, themeType, context);
                break;
            case 1:
                try {
                   om.setEnabled(themes[setting], true, userId);
                   om.setEnabled(themes[setting + 1], true, userId);
                       if (themeType == ThemeType.UI_THEME) {
                          Utils.showSystemUiRestartDialog(context);
                        }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    om.setEnabled(themes[setting+1], true, userId);
                    om.setEnabled(themes[setting+2], true, userId);
                        if (themeType == ThemeType.UI_THEME) {
                            Utils.showSystemUiRestartDialog(context);
                        }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    if (themeType == ThemeType.ICON_THEME) {
                        om.setEnabled(themes[setting + 1], true, userId);
                        om.setEnabled(themes[setting + 2], true, userId);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public static void omniUnset(IOverlayManager om, int userId, ThemeType themeType, Context context) { 
        switch (themeType) {
            case UI_THEME: 
                for (String uiTheme : uiThemes) {
                    try {
                        om.setEnabled(uiTheme, false, userId);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                Utils.showSystemUiRestartDialog(context);
                break;

            case ICON_THEME: 
                for (String iconTheme : iconThemes) {
                    try {
                        om.setEnabled(iconTheme, false, userId);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
