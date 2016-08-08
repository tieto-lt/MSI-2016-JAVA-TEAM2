var module = require('main_module');


function Controller($state, Session, AuthService) {

    var vm = this;

    vm.logout = logout;
    vm.isLogoutVisible = isLogoutVisible;
    vm.isNavigationVisible = isNavigationVisible;

    function isLogoutVisible() {
        return Session.isSessionActive();
    }

    function isNavigationVisible() {
      if($state.current.name != "root.Login"){
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
}


Controller.$inject = ['$state', 'Session', 'AuthService'];

module.component('logout', {
    controller: Controller,
    templateUrl: require('./logout.html')
});
