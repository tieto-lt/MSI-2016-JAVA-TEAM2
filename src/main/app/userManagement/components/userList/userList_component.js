var module = require('main_module');

function Controller(UserServiceImpl) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.user = {};


    vm.executeUpdate = executeUpdate;

    vm.$onInit = function() {
      UserServiceImpl.all().then(function(response){
        vm.users = response.data;
      });
    };

    function executeUpdate(user,newRole) {
      vm.user = user;
    user.userRole = newRole;
        UserServiceImpl.put(vm.user).then(
            function () { console.log('Update success'); },
            function (err) {
                if (err.status === 400) {
                    vm.errors = err.data;
                } else {
                    console.log('Error', err);
                }
            });
    }

}

Controller.$inject = ['UserServiceImpl'];

module.component('userList', {
    controller: Controller,
    templateUrl: require('./userList.html')
});
