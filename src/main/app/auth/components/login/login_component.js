var module = require('main_module');
require('login.css');

function Controller($state, AuthService, $stateParams, Session, OperatorService) {

  var vm = this;
  vm.username = $stateParams.username;
  vm.password = undefined;

  vm.login = login;
  vm.error = undefined;
  vm.enterPressed = enterPressed;

  function login() {
    AuthService.login(vm.username, vm.password).then(
      function(response) {
        var role = Session.getSession().authorities[0];
        if (role == "ROLE_ADMIN") {
          vm.error = undefined;
          $state.go('root.adminPage');
        } else if (role == "ROLE_CUSTOMER") {
          vm.error = undefined;
          $state.go('root.customerPage');
        } else if (role == "ROLE_OPERATOR") {
          OperatorService.getOperator(Session.getSession().userId).then(
            function(response) {
              console.log(response.data);
              if (response.data.token) {
                vm.error = undefined;
                $state.go('root.operatorHomePage');
              } else {
                vm.error = undefined;
                $state.go('root.operatorPage');
              }
            });
        } else {
          vm.error = undefined;
          $state.go('root.itemList');
        }
      },
      function(err) {
        vm.error = err.data.error_description;
      });

  }

  function enterPressed(keyEvent) {
    if (keyEvent.which === 13)
      login();
  }
}


Controller.$inject = ['$state', 'AuthService', '$stateParams', 'Session', 'OperatorService'];
require('login.scss');
module.component('login', {
  controller: Controller,
  templateUrl: require('./login.html')
});
