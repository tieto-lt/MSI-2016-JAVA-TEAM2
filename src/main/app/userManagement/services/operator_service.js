var module = require('main_module');

function Service($http, $q, $cookies) {

  this.verifyOperator = function(operatorId) {
    return $http.post('/api/users/' + operatorId + '/operatorState', operatorId);
  };

}

Service.$inject = ['$http', '$q', '$cookies'];
module.service('OperatorService', Service);
