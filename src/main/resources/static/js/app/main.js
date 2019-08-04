
// rootObject is made available across the js context
var rootObject = {
    vue : new Vue(),
    services:{},
    components : {},
    constants : {}
};



(function() {

    // EnvConfig object is housed in a protected scope
    function EnvConfig(init) {
        for (var i in init) {
            if (init.hasOwnProperty(i)) {
                this[i] = init[i];
            }
        }
    }


    // initialization object is hardcoded, but can be swapped for different parameters .. PROD, QA etc..
    rootObject.env = new EnvConfig({
        name : "DEV",
        debug : true,
        url : "http://localhost:8080"
    });


})();
