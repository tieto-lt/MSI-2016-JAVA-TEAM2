var module = require('main_module');

function Controller($state, AuthService, $stateParams, Session) {

    var vm = this;
    vm.username = $stateParams.username;
    vm.password = undefined;

    vm.login = login;
    vm.error = undefined;

    function login() {
        AuthService.login(vm.username, vm.password).then(
            function (response) {
                var role = Session.getSession().authorities[0];
                if(role == "ROLE_ADMIN"){
                  vm.error = undefined;
                  $state.go('root.userList');
                }
                else if (role == "ROLE_CUSTOMER") {
                  vm.error = undefined;
                  $state.go('root.customerPage');
                }
                else if (role == "ROLE_OPERATOR"){
                  vm.error = undefined;
                  $state.go('root.operatorPage');
                } else {
                  vm.error = undefined;
                  $state.go('root.itemList');
                }
            },
            function (err) {
                vm.error = err.data.error_description;
            });


    }

    function createUser() {
        console.log("create new user");

    }

}


Controller.$inject = ['$state', 'AuthService', '$stateParams', 'Session'];
require('login.scss');
module.component('login', {
    controller: Controller,
    templateUrl: require('./login.html')
});
