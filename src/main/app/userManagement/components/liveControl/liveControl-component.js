var module = require('main_module');
//Adds NodecopterStream on window
require('./nodecopter-stream.js');

function Controller($state, Session) {
  var vm = this;
  vm.openConnection = openConnection;
  vm.connectionOpened =false;
  function openConnection()
  {
    if(!vm.connectionOpened){
    new NodecopterStream(document.getElementById("droneStream"), { userId: Session.getSession().userId });
    vm.connectionOpened = true;
    }
    
  }
}


Controller.$inject = ['$state', 'Session'];

module.component('liveControl', {
  controller: Controller,
  templateUrl: require('./liveControl.html')
});
