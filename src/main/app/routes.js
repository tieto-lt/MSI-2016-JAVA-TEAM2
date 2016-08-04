var angular = require('angular');
var module = angular.module('AngularSpringRestDemo');

module.config(function($stateProvider, $urlRouterProvider) {

  // For any unmatched url, redirect to /
  $urlRouterProvider.otherwise("/");
  //
  // Now set up the states
  $stateProvider
    .state('root', {
      template: "<main></main>",
    })
    .state('root.home', {
      url: '/',
      template: "<h4>This is home</h4>",
      data: {
        isPublic: true
      }
    })
    .state('root.Login', {
      url: "/login",
      template: "<login></login>",
      data: {
        isPublic: true
      },
      params: {
        username: undefined
      }
    })
    .state('root.itemList', {
      url: "/items",
      template: "<item-list></item-list>"
    })
    .state('root.itemNew', {
      url: "/items/new",
      template: "<item-new></item-new>"
    })
    .state('root.ItemDetails', {
      url: "/items/:id",
      template: "<item-details></item-details>"
    })
    .state('root.newUser', {
      url: "/newUser",
      template: "<new-user></new-user>",
      data: {
        isPublic: true
      }
    })
    .state('root.customerPage', {
      url: "/customerPage",
      template: "<customer-page></customer-page>",
      data: {
        isPublic: true
      }
    })
    .state('root.accountInformation', {
      url: "/accountInformation",
      template: "<account-information></account-information>",
      data: {
        isPublic: true
      }
    })
    .state('root.operatorPage', {
      url: "/operatorPage",
      template: "<operator-page></operator-page>",
      data: {
        isPublic: true
      }
    });
});

module.run(['$transitions', 'Session', '$state', function($transitions, Session, $state) {

  Session.initHttp();

  $transitions.onStart(
    {
      to: function (state) { return !state.data || !state.data.isPublic; }
    },
    function () {
      if (!Session.isSessionActive()) {
        return $state.target("root.Login");
      }
    });
}]);
