var module = require('main_module');

function Service($http, $q) {

  this.validateOperator = function(operatorId) {
    return $http.get('/api/users/' + operatorId +'/operatorState', operatorId);
  };

}

Service.$inject = ['$http', '$q'];
module.service('OperatorService', Service);
