module = angular.module('app', []);

module.controller('NewUserController', function($scope) {
  $scope.save = function() {
    if ($scope.userForm.$valid) {
      alert('User saved');
      $scope.reset();
    } else {
      alert("There are invalid fields");
    }
  };

  $scope.reset = function() {
    $scope.user = { name: '', email: '' };
  };

});
