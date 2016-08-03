var module = require('main_module');

function Controller($state, $stateParams, ItemService) {

}

module.component('registrationForm', {
    controller: Controller,
    templateUrl: require('./registrationForm.html')
});
