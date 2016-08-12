var module = require('main_module');

function Controller(Session, OperatorService, $state) {
  var vm = this;
  vm.operatorState = {};
  vm.error = undefined;

  vm.generateToken = generateToken;

  vm.$onInit = function() {
    OperatorService.getOperator(Session.getSession().userId).then(
    function (response) {
        vm.operatorState = response.data;
        if(vm.operatorState.token===null)
        {
          vm.operatorState.token = "Press the button to generate new token";
        }
    },
    function (err) {
        vm.error = err.data.error_description;
    });
  };


  function generateToken() {
      OperatorService.generateToken(Session.getSession().userId).then(
          function (response) {
              vm.error = undefined;
              vm.operatorState = response.data;
          },
          function (err) {
              vm.error = err.data.error_description;
          });
  }

}
Controller.$injcet = ['Session', 'OperatorService', '$state', '$route'];
module.component('operatorPage', {
    controller: Controller,
    templateUrl: require('./operatorPage.html')
});
