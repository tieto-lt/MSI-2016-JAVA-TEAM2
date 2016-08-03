var module = require('main_module');

function Controller($state) {
  var vm = this;

  vm.username = undefined;
  vm.password = undefined;
  vm.name = undefined;
  vm.email = undefined;
  vm.phone = undefined;

  function createNewUser() {
      console.log("register!");
  }

  function logout() {
      $state.go('root.Login');
  }

}

module.component('newUser', {
    controller: Controller,
    templateUrl: require('./newUser.html')
});
