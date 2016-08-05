var module = require('main_module');

function Controller($state, Session) {

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
        Session.invalidate();
        $state.go('root.Login');
    }
}


Controller.$inject = ['$state', 'Session'];

module.component('logout', {
    controller: Controller,
    templateUrl: require('./logout.html')
});
