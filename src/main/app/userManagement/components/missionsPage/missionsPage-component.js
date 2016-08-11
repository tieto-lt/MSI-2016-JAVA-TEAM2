var module = require('main_module');
require('style.css');
function Controller() {

}

module.component('missionsPage', {
    controller: Controller,
    templateUrl: require('./missionsPage.html')
});
