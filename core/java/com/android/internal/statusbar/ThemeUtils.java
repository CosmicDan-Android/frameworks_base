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

package com.android.internal.statusbar;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.util.Log;

public class ThemeUtils {

    public static final String TAG = "DescendantThemerUtility";

        /* icon and ui themes arrays */
        private static final String[] ROUNDIERUITHEME  = {
            "org.descendant.UI.android.roundier", 
            "org.descendant.UI.system.roundier",        
        };

        private static final String[] SQUAREUITHEME  = {
            "org.descendant.UI.android.square",
            "org.descendant.UI.system.square",
        };

        private static final String[] DESCENDANTICONTHEMES = {
            "org.descendant.qs.descendant.overlay",
            "org.descendant.settings.descendant.overlay",
            "org.descendant.settings.descendant.overlay",
            "org.descendant.generic.wellbeing.overlay",
        };

        private static final String[] TEARDROPICONTHEMES = {
            "org.descendant.qs.teardrop.overlay",
            "org.descendant.settings.teardrop.overlay",
            "org.descendant.settings.teardrop.overlay",
            "org.descendant.generic.wellbeing.overlay",
        };

        private static final String[] SUPERBUBBLEICONTHEMES = {
            "org.descendant.qs.superbubble.overlay",
            "org.descendant.settings.superbubble.overlay",
            "org.descendant.generic.wellbeing.overlay",
        };

        private static final String[] SQUAREICONTHEMES = {
            "org.descendant.qs.square.overlay",
            "org.descendant.settings.square.overlay",
            "org.descendant.generic.wellbeing.overlay",
        };
        /* end of icon and ui themes arrays */

        /*keep a list of iconthemes and ui arrays name */
        public static final String[][] ARRAYSICONTHEMES = {
            DESCENDANTICONTHEMES,
            TEARDROPICONTHEMES,
            SUPERBUBBLEICONTHEMES,
            SQUAREICONTHEMES,
        };

        public static final String[][] ARRAYSUITHEMES = {
            ROUNDIERUITHEME,
            SQUAREUITHEME,
        };
        /* end of list */

        /* methods to unload and load icons plus ui themes */
        public static void unloadUiThemes (IOverlayManager om, int userId) {
            for (int i = 0; i < ARRAYSUITHEMES.length; i++) {
                String[] selectedArray = ARRAYSUITHEMES[i];
                    for (int in = 0; in < selectedArray.length; in++) {
                        try {
                            om.setEnabled(selectedArray[in], false, userId);
                            } catch (RemoteException e) {
                                Log.e(TAG,"unloadUiThemes routine has failed!");
                            }
                    }
            }
        }

        public static void unloadIconsThemes (IOverlayManager om, int userId) {
                for (int i = 0; i < ARRAYSICONTHEMES.length; i++) {
                    String[] selectedArray = ARRAYSICONTHEMES[i];
                        for (int in = 0; in < selectedArray.length; in++) {
                           try {
                               om.setEnabled(selectedArray[in], false, userId);
                               } catch (RemoteException e) {
                                   Log.e(TAG,"unloadIconsThemes routine has failed!");
                               }
                        }
                }
        }

        public static void loadUiThemes(IOverlayManager om, int userId, int setting) {
                switch (setting) {
                    case 1: for (int i = 0; i < ROUNDIERUITHEME.length; i++) {
                              try {
                                  om.setEnabled(ROUNDIERUITHEME[i], true, userId);
                                  } catch(RemoteException e) {
                                    Log.e(TAG,"loadUiThemes routine has failed!");
                                  }
                            }
                    break;
                    case 2: for (int i = 0; i < SQUAREUITHEME.length; i++) {
                              try {
                                  om.setEnabled(SQUAREUITHEME[i], true, userId);
                                  } catch(RemoteException e) {
                                    Log.e(TAG,"loadUiThemes routine has failed!");
                                  } 
                            } 
                    break;
                }
        }

        public static void loadIconsThemes(IOverlayManager om, int userId, int setting) {
                switch (setting) {
                    case 1: for (int i = 0; i < DESCENDANTICONTHEMES.length; i++) {
                              try {
                                  om.setEnabled(DESCENDANTICONTHEMES[i], true, userId);
                                  } catch (RemoteException e) {
                                    Log.e(TAG, "loadIconsThemes routine has failed!");
                                  }
                            }
                    break;
                    case 2: for (int i = 0; i < SQUAREICONTHEMES.length; i++) {
                              try {
                                  om.setEnabled(SQUAREICONTHEMES[i], true, userId);
                                  } catch (RemoteException e) {
                                    Log.e(TAG, "loadIconsThemes routine has failed!");
                                  }
                            }
                    break;
                    case 3: for (int i = 0; i < SUPERBUBBLEICONTHEMES.length; i++) {
                              try {
                                  om.setEnabled(SUPERBUBBLEICONTHEMES[i], true, userId);
                                  } catch (RemoteException e) {
                                    Log.e(TAG, "loadIconsThemes routine has failed!");
                                  }
                            }
                    break;
                    case 4: for (int i = 0; i < TEARDROPICONTHEMES.length; i++) {
                              try {
                                  om.setEnabled(TEARDROPICONTHEMES[i], true, userId);
                                  } catch (RemoteException e) {
                                    Log.e(TAG, "loadIconsThemes routine has failed!");
                                  }
                            }
                    break;
                }
           }
        /* end of methods to unload and load icons plus ui themes */

}
