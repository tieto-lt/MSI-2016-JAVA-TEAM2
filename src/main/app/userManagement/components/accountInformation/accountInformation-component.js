var module = require('main_module');


function Controller($state, Session, UserServiceImpl) {
    //Convention to call controller instance 'vm'
    var vm = this;
    vm.user = {};
    vm.errors = [];

   vm.$onInit = function(user) {
     console.log($state);
     console.log(Session);
     UserServiceImpl.getUser(2).then(
         function (response) {
           vm.user = response.data;
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

Controller.$inject = ['$state', 'Session', 'UserServiceImpl'];


module.component('accountInformation', {
    controller: Controller,
    templateUrl: require('./accountInformation.html')
});
