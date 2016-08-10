var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('homePage', {
    controller: Controller,
    templateUrl: require('./homePage.html')
});
