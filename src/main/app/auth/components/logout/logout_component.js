var module = require('main_module');


function Controller($state, Session, AuthService, $http) {

    var vm = this;

    vm.logout = logout;
    vm.accountInfo = accountInfo;
    vm.isLogoutVisible = isLogoutVisible;
    vm.isNavigationVisible = isNavigationVisible;
    vm.isItNewUser = isItNewUser;
    vm.isOperator = isOperator;
    vm.isCustomer = isCustomer;
    vm.goToOperatorPage = goToOperatorPage;
    vm.goToMissionsPage = goToMissionsPage;
    vm.goToOrdersPage = goToOrdersPage;
    vm.goToCustomerPage = goToCustomerPage;

    function isLogoutVisible() {
        return Session.isSessionActive();
    }

    function isNavigationVisible() {
      if($state.current.name != "root.Login" ){
          return true;
      } else {
          return false;
      }
    }

    function isItNewUser() {
      if($state.current.name != "root.newUser" ){
          return true;
      } else {
          return false;
      }
    }


    function logout() {
        AuthService.logout().then(
            function() {
                $http.defaults.headers.common.Authorization=undefined;
                Session.invalidate();
                $state.go('root.Login');
            });
    }

    function accountInfo() {
        $state.go('root.accountInformation');
    }

    function isOperator(){

        if(Session.isSessionActive() && Session.getSession().authorities[0] == "ROLE_OPERATOR"){
          return true;
        } else {
          return false;
        }
    }

    function isCustomer(){

        if(Session.isSessionActive() && Session.getSession().authorities[0] == "ROLE_CUSTOMER"){
          return true;
        } else {
          return false;
        }
    }

    function goToOperatorPage() {
        $state.go('root.operatorPage');
    }

    function goToMissionsPage(){
        $state.go('root.missionsPage');
    }

    function goToOrdersPage(){
        $state.go('root.customerPage');
    }
    function goToCustomerPage(){
        $state.go('root.customerPage');
    }

}


Controller.$inject = ['$state', 'Session', 'AuthService', '$http'];

module.component('logout', {
    controller: Controller,
    templateUrl: require('./logout.html')
});
