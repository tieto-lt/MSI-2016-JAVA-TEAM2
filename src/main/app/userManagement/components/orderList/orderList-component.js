var module = require('main_module');

function Controller(OrderServiceImpl, $scope) {

    var vm = this;

    vm.order = {};


    vm.$onInit = function() {
      OrderServiceImpl.all().then(function(response){
        vm.orders = response.data;
      });
    };



}


Controller.$inject = ['OrderServiceImpl','$scope'];

module.component('orderList', {
    controller: Controller,
    templateUrl: require('./orderList.html')
});
