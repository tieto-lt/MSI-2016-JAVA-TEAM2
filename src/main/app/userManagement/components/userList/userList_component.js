var module = require('main_module');
require('userList.scss');

function Controller(UserServiceImpl, $scope) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.user = {};


    vm.executeUpdate = executeUpdate;

    $scope.alerts = [];

    $scope.addAlert = function(messageType, message) {
      $scope.alerts.push({type: messageType, msg: message});
    };

    $scope.closeAlert = function(index) {
      $scope.alerts.splice(index, 1);
    };

    vm.$onInit = function() {
      UserServiceImpl.all().then(function(response){
        vm.users = response.data;
      });
    };

    function executeUpdate(user,newRole) {
      vm.user = user;
      user.userRole = newRole;
        UserServiceImpl.put(vm.user).then(
            function () {
              console.log('Update success');
              $scope.addAlert('success', 'User\'s ' + user.userName + ' role has been changed to ' + newRole + '.');
            },
            function (err) {
                if (err.status === 400) {
                    vm.errors = err.data;
                } else {
                    console.log('Error', err);
                    $scope.addAlert('danger', 'User\'s ' + user.userName + ' role has been changed to ' + newRole + '.');
                }
            });
    }

}

Controller.$inject = ['UserServiceImpl', '$scope'];

module.component('userList', {
    controller: Controller,
    templateUrl: require('./userList.html')
});
