var module = require('main_module');

function Controller (OrderServiceImpl, Session)
{
  var vm = this;

  vm.order = {};

  vm.create = create;
  vm.errors = [];
  vm.enterPressed = enterPressed;


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

Controller.$inject = ['OrderServiceImpl', 'Session'];

module.component('orderPage', {
    controller: Controller,
    templateUrl: require('./orderPage.html')
});
