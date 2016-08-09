var module = require('main_module');

function Controller(Session, OperatorService) {
  var vm = this;
  vm.user = {};


  vm.error = undefined;

  // kol dar nera visu end point'u, tol nenaudoti

  vm.validateOperator = validateOperator;

  function validateOperator() {
      OperatorService.validateOperator(Session.getSession().userId).then(
          function (response) {
              vm.error = undefined;
              $state.go('root.operatorPage');
          },
          function (err) {
              vm.error = err.data.error_description;
          });
  }

}
Controller.$injcet = ['Session', 'OperatorService'];
module.component('operatorPage', {
    controller: Controller,
    templateUrl: require('./operatorPage.html')
});
