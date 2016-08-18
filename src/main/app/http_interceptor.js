var module = require("main_module");

module.factory('myInterceptor', ['$log', 'Session','$state', function($log, session, $state) {

    var requestInterceptor = {
        request: function(config) {
        if(!session.isSessionActive()){
           session.invalidate();
        } if(session.getToken()){
            config.headers.Authorization = 'Bearer ' + session.getToken();
        }

        return config;
      },

        responseError: function(rejection){
          if(rejection.status == 401){
             session.invalidate();
             $state.go('root.Login');
          }
          return $q.reject(rejection);
        }
    };

    return requestInterceptor;
}]);
