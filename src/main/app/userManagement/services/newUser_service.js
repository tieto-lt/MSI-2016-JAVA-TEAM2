var module = require('main_module');

function Service ($http) {

    this.create = function(user) {
        return $http.post('/newUser/', user);
    };


}

Service.$inject = ['$http', '$httpParamSerializer'];
module.service('UserServiceImpl', Service);
