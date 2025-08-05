#!/usr/bin/env node

var fs = require('fs');
var path = require('path');

module.exports = function(context) {
    console.log('Android V35 Styles Plugin: Applying API 35 configurations...');
    
    var platformRoot = path.join(context.opts.projectRoot, 'platforms', 'android');
    var appResPath = path.join(platformRoot, 'app', 'src', 'main', 'res');
    var valuesV35Path = path.join(appResPath, 'values-v35');
    var stylesPath = path.join(valuesV35Path, 'styles.xml');
    
    // Ensure values-v35 directory exists
    if (!fs.existsSync(valuesV35Path)) {
        fs.mkdirSync(valuesV35Path, { recursive: true });
        console.log('Android V35 Styles Plugin: Created values-v35 directory');
    }
    
    // Create or update styles.xml for API 35 with NEVER mode to avoid notch
    var stylesContent = `<?xml version="1.0" encoding="utf-8"?>
<resources>
  <!-- Base application theme for API 35 -->
  <style name="AppTheme" parent="android:Theme.Light">
    <item name="android:windowLayoutInDisplayCutoutMode">never</item>
  </style>

  <style name="AppTheme.NoActionBar" parent="android:Theme.Light.NoTitleBar">
    <item name="android:background">@null</item>
    <item name="android:windowLayoutInDisplayCutoutMode">never</item>
  </style>

  <!-- Additional API 35 specific styles -->
  <style name="AppTheme.FullScreen" parent="android:Theme.Light.NoTitleBar.Fullscreen">
    <item name="android:windowLayoutInDisplayCutoutMode">never</item>
    <item name="android:windowTranslucentStatus">true</item>
    <item name="android:windowTranslucentNavigation">true</item>
  </style>
</resources>`;
    
    try {
        fs.writeFileSync(stylesPath, stylesContent);
        console.log('Android V35 Styles Plugin: API 35 styles configured successfully - App will never go behind notch');
    } catch (error) {
        console.error('Android V35 Styles Plugin: Error configuring API 35 styles:', error);
    }
}; 