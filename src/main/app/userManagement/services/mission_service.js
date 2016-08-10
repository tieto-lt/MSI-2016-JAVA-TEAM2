module = require('main_module');

function Service ($http,Session) {

    function retrieveMissions() {
      return $http.get('/api/missions?onlyCompleted=true');
    }

    function retrieveMissionDetails(missionId){
      return $http.get('/api/missions/-1'); //TODO: Change to missionId after missions will be added
    }
}

Service.$inject = ['$http','Session'];
module.service('MissionService', Service);
