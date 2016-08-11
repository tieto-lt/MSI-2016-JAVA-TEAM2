module = require('main_module');

function Service ($http,Session) {

    this.retrieveMissions = function retrieveMissions(){
      return $http.get('/api/int/missions?onlyCompleted=true');
    };

    this.retrieveMissionDetails = function retrieveMissionDetails(missionId){
      return $http.get('/api/int/missions/'+missionId);
    };

}

Service.$inject = ['$http','Session'];
module.service('MissionService', Service);
