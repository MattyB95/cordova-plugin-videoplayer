var exec = require("cordova/exec");

// Polyfill for Object.assign for older Android WebViews that may not support it
if (typeof Object.assign !== "function") {
    Object.assign = function (target) {
        if (target == null) {
            throw new TypeError("Cannot convert undefined or null to object");
        }
        var to = Object(target);
        for (var index = 1; index < arguments.length; index++) {
            var nextSource = arguments[index];
            if (nextSource != null) {
                for (var key in nextSource) {
                    if (Object.prototype.hasOwnProperty.call(nextSource, key)) {
                        to[key] = nextSource[key];
                    }
                }
            }
        }
        return to;
    };
}

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
