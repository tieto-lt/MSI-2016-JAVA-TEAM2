var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('newUser', {
    controller: Controller,
    templateUrl: require('./newUser.html')
});
