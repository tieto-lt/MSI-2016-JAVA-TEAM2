var module = require('main_module');
require('userList.scss');

function Controller(UserServiceImpl, $scope) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.user = {};
    vm.currentPage = 1;
    vm.itemsPerPage = 15;

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
        vm.filter();
      });
    };

    vm.filter = function filter(){
      vm.totalItem =  vm.users.length;
      var begin = ((vm.currentPage - 1) * vm.itemsPerPage),
      end = begin + vm.itemsPerPage;
      vm.slicedUsers = vm.users.slice(begin, end);
    };

    function executeUpdate(user,newRole) {
      vm.user = user;
      user.userRole = newRole;
        UserServiceImpl.put(vm.user).then(
            function () {
              $scope.addAlert('success', 'User\'s ' + user.userName + ' role has been changed to ' + newRole + '.');
            },
            function (err) {
                if (err.status === 400) {
                    $scope.addAlert('danger', 'Error: ' + err.data.message);

                } else {
                    console.log('Error', err);
                    $scope.addAlert('danger', 'Error: ' + err.data.message);
                }
            });
    }

}

Controller.$inject = ['UserServiceImpl', '$scope'];

module.component('userList', {
    controller: Controller,
    templateUrl: require('./userList.html')
});
