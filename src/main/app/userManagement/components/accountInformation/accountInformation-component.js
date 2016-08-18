var module = require('main_module');


function Controller($state, Session, UserServiceImpl, $scope) {
    //Convention to call controller instance 'vm'
    var vm = this;
    vm.user = {};
    vm.updatePass = {};
    vm.errors = [];
    vm.update = update;
    vm.updatePassword = updatePassword;
    vm.errors = [];

    $scope.alerts = [];

    $scope.addAlert = function(messageType, message) {
      $scope.alerts.push({type: messageType, msg: message});
    };

    $scope.closeAlert = function(index) {
      $scope.alerts.splice(index, 1);
    };

    function updatePassword() {
      UserServiceImpl.updatePassword(vm.updatePass).then(
          function () {
              $scope.addAlert('success', 'Password updated successfuly');
          },
          function (err) {
              if (err.status === 400) {
                  vm.errors = [err.data];
              } else {
                  console.log('Error', err);
              }
          }
      );
    }

    function update() {
      vm.user.password = "hackForUS13";
      UserServiceImpl.update(vm.user).then(
          function () {
              $scope.addAlert('success', 'Updated successfuly');
          },
          function (err) {
              if (err.status === 400) {
                  vm.errors = [err.data];
              } else {
                  console.log('Error', err);
              }
          }
      );
    }

   vm.$onInit = function(user) {
     UserServiceImpl.getUser(Session.getSession().userId).then(
         function (response) {
           vm.user = response.data;
           console.log(vm.user);
           console.log('getting user ok');
         },
         function (err) {
             if (err.status === 400) {
                 vm.errors = err.data;
             } else {
                 console.log('Error', err);
             }
         });
    };



}

Controller.$inject = ['$state', 'Session', 'UserServiceImpl', '$scope'];


module.component('accountInformation', {
    controller: Controller,
    templateUrl: require('./accountInformation.html')
});
