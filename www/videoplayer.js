var exec = require("cordova/exec");

module.exports = {

    DEFAULT_OPTIONS: {
        volume: 1.0,
        scalingMode: 1
    },

    SCALING_MODE: {
        SCALE_TO_FIT: 1,
        SCALE_TO_FIT_WITH_CROPPING: 2
    },

    play: function (path, options, successCallback, errorCallback) {
        options = Object.assign({}, this.DEFAULT_OPTIONS, options || {});
        exec(successCallback, errorCallback, "VideoPlayer", "play", [path, options]);
    },

    close: function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, "VideoPlayer", "close", []);
    }

};
