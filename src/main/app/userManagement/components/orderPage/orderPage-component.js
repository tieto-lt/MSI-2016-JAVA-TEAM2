var module = require('main_module');
require('style.scss');

function Controller (OrderServiceImpl, Session, UserServiceImpl, $state)
{
  var vm = this;

  vm.order = {};
  vm.user = {};
  vm.map={};
  vm.objectMap=[{isEnabled:false},{isEnabled:false},{isEnabled:false}, {isEnabled:false}];

  vm.enableObject=enableObject;

  vm.create = create;
  vm.errors = [];

  vm.enterPressed = enterPressed;
  vm.isEnabled = isEnabled;

  vm.map = [];
  for(vm.i=0; vm.i<96; vm.i++)
  {
    vm.map.push({type:"unit", contains: ""});
  }

  function isEnabled(nr)
  {
    return vm.objectMap[nr].isEnabled;
  }

  function enableObject(param, nr){
    if(param===false)
    {
      vm.objectMap.splice(nr,1,{isEnabled:false});
    }
    else {
      if(!vm.objectMap[nr].isEnabled)
      {
      vm.objectMap.splice(nr,1,{isEnabled:true});
    }
    else {
      vm.objectMap[nr].isEnabled = param;
    }

    }

  }


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
    vm.order.orderObjects = vm.objectMap;
    console.log(vm.order);
    OrderServiceImpl.create(vm.order).then(
        function () {
            console.log(vm.order.name);
            $state.go('root.customerPage');
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

Controller.$inject = ['OrderServiceImpl', 'Session', 'UserServiceImpl', '$state'];

module.component('orderPage', {
    controller: Controller,
    templateUrl: require('./orderPage.html')
});
