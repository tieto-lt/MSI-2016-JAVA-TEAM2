var module = require('main_module');


function Controller($state, Session, AuthService, $http, OperatorService) {

    var vm = this;

    vm.logout = logout;
    vm.accountInfo = accountInfo;
    vm.isLogoutVisible = isLogoutVisible;
    vm.isNavigationVisible = isNavigationVisible;
    vm.isItNewUser = isItNewUser;
    vm.isItHome = isItHome;
    vm.isOperator = isOperator;
    vm.isCustomer = isCustomer;
    vm.isAdmin = isAdmin;
    vm.goToOperatorPage = goToOperatorPage;
    vm.goToAdminPage = goToAdminPage;
    vm.goToCustomerPage = goToCustomerPage;
    vm.goToMissionsPage = goToMissionsPage;
    vm.goToUserList = goToUserList;
    vm.goHome = goHome;
    vm.goLogin = goLogin;
    vm.isVerified = false;
    vm.verifyButton = verifyButton;


    function verifyButton(){
      if(isOperator()){
        OperatorService.getOperator(Session.getSession().userId).then(
        function (response) {
            vm.isVerified = response.data.verified;
            console.log(vm.isVerified);
        },
        function (err) {
            vm.error = err.data.error_description;
        });
      }
    }

    verifyButton();


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

    function isItHome() {
      if($state.current.name == "root.homePage" ){
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

    function isAdmin(){

        if(Session.isSessionActive() && Session.getSession().authorities[0] == "ROLE_ADMIN"){
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
    function goLogin() {
        $state.go('root.login');
    }
    function goToCustomerPage() {
        $state.go('root.customerPage');
    }
    function goToAdminPage() {
        $state.go('root.adminPage');
    }

    function goToMissionsPage(){
        $state.go('root.missionsPage');
    }

    function goToUserList(){
        $state.go('root.userList');
    }
    function goHome(){
        $state.go('root.homePage');
    }

}


Controller.$inject = ['$state', 'Session', 'AuthService', '$http', 'OperatorService'];

module.component('logout', {
    controller: Controller,
    templateUrl: require('./logout.html')
});
