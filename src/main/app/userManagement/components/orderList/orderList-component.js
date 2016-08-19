var module = require('main_module');

function Controller(OrderServiceImpl, $scope) {

    var vm = this;

    vm.searchparam ={};
    vm.searchparam.status="not completed";
    vm.currentPage = 1;


    vm.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
    };

    vm.itemsPerPage = 15;

    vm.approve = function approve(order){
      order.status = 'approved';
      OrderServiceImpl.update(order);
    };

    vm.decline = function decline(order){
      order.status = 'declined';
      OrderServiceImpl.update(order);
    };

    vm.onChange = function change(){
      vm.filter();
    };

    vm.$onInit = function() {
      OrderServiceImpl.all().then(function(response){
        vm.orders = response.data;
        vm.filter();
      });
    };

     vm.filter = function filter(){
       vm.filteredOrders = vm.orders.filter(function(order){
         return vm.searchparam.status === order.status || vm.searchparam.status === '';
       });
       vm.totalItem = vm.filteredOrders.length;
       var begin = ((vm.currentPage - 1) * vm.itemsPerPage),
       end = begin + vm.itemsPerPage;
       vm.slicedOrders = vm.filteredOrders.slice(begin, end);
     };


}


Controller.$inject = ['OrderServiceImpl','$scope'];

module.component('orderList', {
    controller: Controller,
    templateUrl: require('./orderList.html')
});
