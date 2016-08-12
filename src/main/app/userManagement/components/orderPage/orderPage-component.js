var module = require('main_module');

function Controller (OrderServiceImpl, Session, UserServiceImpl)
{
  var vm = this;

  vm.order = {};
  vm.user = {};

  vm.create = create;
  vm.errors = [];
  vm.enterPressed = enterPressed;


  vm.$onInit = function(user) {
    UserServiceImpl.getUser(Session.getSession().userId).then(
        function (response) {
          vm.user = response.data;
          vm.order.email=vm.user.email;
          vm.order.phone=vm.user.phone;
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


  function create() {
    console.log("creating new order");
    vm.order.userId = Session.getSession().userId;
    OrderServiceImpl.create(vm.order).then(
        function () {
            console.log(vm.order.name);

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
}

Controller.$inject = ['OrderServiceImpl', 'Session', 'UserServiceImpl'];

module.component('orderPage', {
    controller: Controller,
    templateUrl: require('./orderPage.html')
});
