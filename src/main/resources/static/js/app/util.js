(function (_$$) {

    // contains utility methods
    _$$.util = {

        logInfo: function (msg, stack) {
            if (_$$.env.debug) {
                console.log(msg);
                _$$.util.logStack(stack);
            }
        },

        logError: function (msg, stack) {
            if (_$$.env.debug) {
                console.error(msg);
                _$$.util.logStack(stack);
            }
        },

        logStack: function (stack) {
            if (stack) {
                console.log(new Error("").stack);
            }
        },

        errorSanitize: function (msg) {
            if (msg.indexOf("java.") > -1) {
                return "Unknown Error";
            }

            return msg;
        },

        extractError: function (errorResponse) {
            var errorMsg = _$$.util.deepGet(errorResponse, "response.data.message");
            return _$$.util.isValidString(errorMsg) ? _$$.util.errorSanitize(errorMsg) : "Unknown Error";
        },

        merge: function (src, dest) {
            if (typeof src != "object") {
                throw new Error("Source must be Javascript objects");
            }

            if (typeof dest != "object") {
                dest = {};
            }

            for (var i in src) {
                dest[i] = src[i];
            }

            return dest;
        },

        constant : function(key) {
            return _$$.util.deepGet(_$$.constants, key);
        },

        registerServices : function(request) {
            _$$.util.merge(request, _$$.services);
        },

        registerComponents : function(request) {
            _$$.util.merge(request, _$$.components);
        }

    }

})(rootObject);