var module = require('main_module');

function Service($http, $q) {

  // kol dar nera visu end point'u, tol nenaudoti


  this.validateOperator = function(operatorId) {
    return $http.get('/api/users/' + operatorId +'/operatorState', operatorId);
  };

}

Service.$inject = ['$http', '$q'];
module.service('OperatorService', Service);
