var module = require('main_module');

function Service($http, $q) {

  this.create = function(order) {
      return $http.post('/api/orders', order);
  };
}

Service.$inject = ['$http', '$q'];
module.service('OrderServiceImpl', Service);
