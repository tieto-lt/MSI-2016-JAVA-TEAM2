var module = require('main_module');

function Controller($state, $stateParams, ItemService) {
  
}

module.component('accountInformation', {
    controller: Controller,
    templateUrl: require('./accountInformation.html')
});
