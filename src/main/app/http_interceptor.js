var module = require("main_module");

module.factory('myInterceptor', ['$log', 'Session', function($log, session) {

    var requestInterceptor = {
        request: function(config) {
        if(!session.isSessionActive()){
           session.invalidate();
        } if(session.getToken()){
            config.headers.Authorization = 'Bearer ' + session.getToken();
        }

        return config;
        }
    };

    return requestInterceptor;
}]);


