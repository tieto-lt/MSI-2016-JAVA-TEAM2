angular.module('ui.bootstrap').controller('AccordionDemoCtrl', function ($scope) {
  $scope.oneAtATime = true;

  $scope.status = {
    isCustomHeaderOpen: false,
    isFirstOpen: true,
    isFirstDisabled: false
  };
});
