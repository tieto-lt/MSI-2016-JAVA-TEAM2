var module = require('main_module');

function Controller(Session, OperatorService, $state) {
  var vm = this;
  vm.user = {};
  vm.error = undefined;

  vm.verifyOperator = verifyOperator;

  function verifyOperator() {
      OperatorService.verifyOperator(Session.getSession().userId).then(
          function (response) {
              vm.error = undefined;
              $state.go('root.operatorPage');
          },
          function (err) {
              vm.error = err.data.error_description;
          });
  }

}
Controller.$injcet = ['Session', 'OperatorService', '$state'];
module.component('operatorPage', {
    controller: Controller,
    templateUrl: require('./operatorPage.html')
});
