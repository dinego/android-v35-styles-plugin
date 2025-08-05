var exec = require('cordova/exec');

var AndroidV35Styles = {
    /**
     * Apply API 35 specific configurations
     * @param {Function} successCallback - Success callback function
     * @param {Function} errorCallback - Error callback function
     */
    applyV35Configurations: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'AndroidV35Styles', 'applyV35Configurations', []);
    },

    /**
     * Check if device supports API 35 features
     * @param {Function} successCallback - Success callback function
     * @param {Function} errorCallback - Error callback function
     */
    isV35Supported: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'AndroidV35Styles', 'isV35Supported', []);
    },

    /**
     * Get current display cutout mode
     * @param {Function} successCallback - Success callback function
     * @param {Function} errorCallback - Error callback function
     */
    getDisplayCutoutMode: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'AndroidV35Styles', 'getDisplayCutoutMode', []);
    },

    /**
     * Set display cutout mode
     * @param {String} mode - Cutout mode ('shortEdges', 'longEdges', 'never', 'default')
     * @param {Function} successCallback - Success callback function
     * @param {Function} errorCallback - Error callback function
     */
    setDisplayCutoutMode: function(mode, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'AndroidV35Styles', 'setDisplayCutoutMode', [mode]);
    }
};

module.exports = AndroidV35Styles; 