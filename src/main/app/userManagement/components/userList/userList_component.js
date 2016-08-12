var module = require('main_module');
require('userList.scss');

function Controller(UserServiceImpl, $uibModal, $scope) {
  //Convention to call controller instance 'vm'
  var vm = this;

  vm.user = {};

  vm.open = open;
  vm.close = close;
  vm.wait = wait;
  vm.executeUpdate = executeUpdate;

  $scope.items = ['item1', 'item2', 'item3'];


  vm.$onInit = function() {
    UserServiceImpl.all().then(function(response) {
      vm.users = response.data;
    });
  };

  function wait(ms) {
    var start = new Date().getTime();
    var end = start;
    while (end < start + ms) {
      end = new Date().getTime();
    }
  }

  function open(size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstanceCtrl',
      size: "lg",
      resolve: {
        items: function() {
          return $scope.items;
        }
      }
    });


  }

  function close() {
    $uibModalInstance.dismiss('cancel');
  }

  function executeUpdate(user, newRole) {
    vm.user = user;
    user.userRole = newRole;
    UserServiceImpl.put(vm.user).then(
      function() {
        console.log('Update success');

        open();
        // $uibModalInstance.dismiss('cancel');
        wait(1000);

      },
      function(err) {
        if (err.status === 400) {
          vm.errors = err.data;
        } else {
          console.log('Error', err);
        }
      });
  }

}

Controller.$inject = ['UserServiceImpl', '$uibModal', '$scope'];

module.component('userList', {
  controller: Controller,
  templateUrl: require('./userList.html')
});
