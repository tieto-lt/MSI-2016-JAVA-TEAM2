var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('customerHomePage', {
    controller: Controller,
    templateUrl: require('./customerHomePage.html')
});
