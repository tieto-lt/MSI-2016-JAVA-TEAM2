var module = require('main_module');

function Service ($http) {

    this.create = function(user) {
        return $http.post('/api/users', user);
    };


}

Service.$inject = ['$http'];
module.service('UserServiceImpl', Service);
