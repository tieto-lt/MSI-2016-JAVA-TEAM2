var module = require('main_module');

function Controller(missionService) {
  var vm = this;

  vm.$onInit = function() {
    missionService.retrieveAll().then(function(response){
      vm.missions = response.data;
    });
};
}

Controller.$inject = ['MissionService'];

module.component('customerPage', {
    controller: Controller,
    templateUrl: require('./customerPage.html')
});
