var module = require('main_module');

function Controller(UserService) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.users = [];

    vm.$onInit = function() {
      UserService.all().then(function(response){
        vm.users = response.data;
      });
    };
}

Controller.$inject = ['UserService'];

module.component('userList', {
    controller: Controller,
    templateUrl: require('./userList.html')
});
