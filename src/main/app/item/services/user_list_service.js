var module = require('main_module');

function Service($http, $q) {

  this.get = function(id) {
    return $http.get('/userList').then(
      function(response) {
        return response.data.filter(function(user) {
          return user.id === id;
        })[0];
      }
    );
  };

  this.all = function() {
    return $http.get('/userList');
  };

  this.update = function(user) {
    var d = $q.defer();
    d.resolve(user);
    return d.promise;
  };

  this.create = function(user) {
    var d = $q.defer();
    d.resolve(user);
    return d.promise;
  };

  this.remove = function(id) {
    var d = $q.defer();
    d.resolve(id);
    return d.promise;
  };
}

Service.$inject = ['$http', '$q'];
module.service('UserService', Service);
