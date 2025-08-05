package com.cordova.plugin.androidv35styles;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Build;
import android.view.WindowManager;
import android.view.Window;
import android.app.Activity;

public class AndroidV35StylesPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("applyV35Configurations")) {
            this.applyV35Configurations(callbackContext);
            return true;
        } else if (action.equals("isV35Supported")) {
            this.isV35Supported(callbackContext);
            return true;
        } else if (action.equals("getDisplayCutoutMode")) {
            this.getDisplayCutoutMode(callbackContext);
            return true;
        } else if (action.equals("setDisplayCutoutMode")) {
            String mode = args.getString(0);
            this.setDisplayCutoutMode(mode, callbackContext);
            return true;
        }
        return false;
    }

    private void applyV35Configurations(CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Activity activity = cordova.getActivity();
                    Window window = activity.getWindow();
                    
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        WindowManager.LayoutParams layoutParams = window.getAttributes();
                        // Usar NEVER para evitar que o app fique atrás do notch
                        layoutParams.layoutInDisplayCutoutMode = 
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
                        window.setAttributes(layoutParams);
                        
                        callbackContext.success("API 35 configurations applied successfully - App will never go behind notch");
                    } else {
                        callbackContext.error("API 35 features not supported on this device");
                    }
                } catch (Exception e) {
                    callbackContext.error("Error applying API 35 configurations: " + e.getMessage());
                }
            }
        });
    }

    private void isV35Supported(CallbackContext callbackContext) {
        boolean isSupported = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
        callbackContext.success(isSupported ? "true" : "false");
    }

    private void getDisplayCutoutMode(CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Activity activity = cordova.getActivity();
                    Window window = activity.getWindow();
                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    
                    String mode = "default";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        switch (layoutParams.layoutInDisplayCutoutMode) {
                            case WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES:
                                mode = "shortEdges";
                                break;
                            case WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS:
                                mode = "longEdges";
                                break;
                            case WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER:
                                mode = "never";
                                break;
                            default:
                                mode = "default";
                                break;
                        }
                    }
                    
                    callbackContext.success(mode);
                } catch (Exception e) {
                    callbackContext.error("Error getting display cutout mode: " + e.getMessage());
                }
            }
        });
    }

    private void setDisplayCutoutMode(String mode, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Activity activity = cordova.getActivity();
                    Window window = activity.getWindow();
                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        switch (mode) {
                            case "shortEdges":
                                layoutParams.layoutInDisplayCutoutMode = 
                                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                                break;
                            case "longEdges":
                                layoutParams.layoutInDisplayCutoutMode = 
                                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
                                break;
                            case "never":
                                layoutParams.layoutInDisplayCutoutMode = 
                                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
                                break;
                            default:
                                // Por padrão, usar NEVER para evitar notch
                                layoutParams.layoutInDisplayCutoutMode = 
                                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
                                break;
                        }
                        
                        window.setAttributes(layoutParams);
                        callbackContext.success("Display cutout mode set to: " + mode);
                    } else {
                        callbackContext.error("Display cutout features not supported on this device");
                    }
                } catch (Exception e) {
                    callbackContext.error("Error setting display cutout mode: " + e.getMessage());
                }
            }
        });
    }
} 