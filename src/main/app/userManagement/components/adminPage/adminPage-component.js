var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('adminPage', {
    controller: Controller,
    templateUrl: require('./adminPage.html')
});
