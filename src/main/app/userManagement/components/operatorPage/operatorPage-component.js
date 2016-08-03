var module = require('main_module');

function Controller($state, $stateParams, ItemService) {
  var vm = this;
  vm.username = undefined;
  vm.password = undefined;

  vm.login = login;
  vm.error = undefined;

  function login() {
      AuthService.login(vm.username, vm.password).then(
          function (response) {
              vm.error = undefined;
              $state.go('root.itemList');
          },
          function (err) {
              vm.error = err.data.error_description;
          });
  }

}

module.component('operatorPage', {
    controller: Controller,
    templateUrl: require('./operatorPage.html')
});
