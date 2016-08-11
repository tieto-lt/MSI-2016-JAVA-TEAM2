var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('operatorHomePage', {
    controller: Controller,
    templateUrl: require('./operatorHomePage.html')
});
