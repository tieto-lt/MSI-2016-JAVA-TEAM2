var module = require('main_module');

function Controller($state, AuthService, $stateParams) {

    var vm = this;
    vm.username = $stateParams.username;
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

    function createUser() {
        console.log("create new user");

    }

}


Controller.$inject = ['$state', 'AuthService', '$stateParams'];
require('login.scss');
module.component('login', {
    controller: Controller,
    templateUrl: require('./login.html')
});
