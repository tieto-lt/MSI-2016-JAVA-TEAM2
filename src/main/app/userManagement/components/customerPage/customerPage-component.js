var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('customerPage', {
    controller: Controller,
    templateUrl: require('./customerPage.html')
});
