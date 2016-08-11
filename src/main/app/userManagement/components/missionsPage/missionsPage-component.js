var module = require('main_module');

function Controller(missionService) {
  var vm = this;
  missionService.retrieveMissions().then(function(missions){
    vm.missions = missions.data;
  });
}

Controller.$inject = ['MissionService'];

module.component('missionsPage', {
    controller: Controller,
    templateUrl: require('./missionsPage.html')
});
