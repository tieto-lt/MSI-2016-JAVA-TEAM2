var module = require('main_module');
// require('newUser-style.css');

function Controller($state, UserServiceImpl) {
  var vm = this;

  vm.user = {};

  vm.create = create;
  vm.logout = logout;
  vm.errors = [];
  vm.enterPressed = enterPressed;



  function create() {
    console.log("creating new user");
    UserServiceImpl.create(vm.user).then(
        function () {
            console.log(vm.user.userName);
            $state.go('root.Login', { username: vm.user.userName});
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

  function enterPressed (keyEvent) {
      if (keyEvent.which === 13)
      create();
    }

  function logout() {
      console.log("loggin out");
      $state.go('root.Login', { username: vm.user.userName});

  }

}

Controller.$inject = ['$state', 'UserServiceImpl'];
module.component('newUser', {
    controller: Controller,
    templateUrl: require('./newUser.html')
});
module.directive("matchPassword", function(){
 return {
     require: "ngModel",
     scope: {
         otherModelValue: "=matchPassword"
     },
     link: function(scope, element, attributes, ngModel) {

         ngModel.$validators.matchPassword = function(modelValue) {
             return modelValue == scope.otherModelValue;
         };

         scope.$watch("otherModelValue", function() {
             ngModel.$validate();
         });
     }
 };
});
