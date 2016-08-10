var module = require('main_module');

function Controller($scope) {
  $scope.oneAtATime = true;

  $scope.status = {
    isCustomHeaderOpen: false,
    isFirstOpen: true,
    isFirstDisabled: false
  };
}

module.component('missionsPage', {
    controller: Controller,
    templateUrl: require('./missionsPage.html')
});
