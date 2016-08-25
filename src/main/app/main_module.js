//Main module which gets required 'module = require('main_module')' in all custom written angular modules
require('angular');
require('angular-ui-bootstrap');
require('angular-animate');
require('angular-touch');
require('angular-ui-router');
require('angular-messages');
require('angular-cookies');
require('angular-jwt');
require('angular-gamepad.js');



// require('dronestream/dist/vendor/broadway/sylvester.js');

// require('dronestream/dist/vendor/broadway/glUtils.js');
// require('dronestream/dist/vendor/broadway/canvas.js');
// require('dronestream/nodecopter-stream.js');


window.jQuery = require('jquery');
window.$ = window.jQuery;
require('bootstrap');

require("bootstrap/dist/css/bootstrap.css");

var _module = angular.module('AngularSpringRestDemo', [ 'ngAnimate', 'ngTouch', 'ui.bootstrap', 'ui.router', 'ngMessages', 'ngCookies', 'angular-jwt','ngGamepad']);

_module.config(['$httpProvider', function($httpProvider) {
   $httpProvider.interceptors.push('myInterceptor');
}]);

module.exports = _module;
