var module = require('main_module');

function Controller($state) {
  var vm = this;

  vm.user = {};

  cm.createUser = createUser;
  vm.errors = [];

  function createUser() {
    UserServiceImpl.create(vm.user).then(
        function () {
            $state.go('root.Login');
        },
        function (err) {
            if (err.status === 400) {
                vm.errors = err.data;
            } else {
                console.log('Error', err);
            }
        }
    );
  }


  function logout() {
      $state.go('root.Login');
  }

}

Controller.$inject = ['$state', 'UserServiceImpl'];
module.component('newUser', {
    controller: Controller,
    templateUrl: require('./newUser.html')
});
