module = require('main_module');

function Service ($http,Session) {

    this.retrieveAll = function retrieveAll(){
      return $http.get('/api/int/missions');
    };

    this.retrieveMissions = function retrieveMissions(){
      return $http.get('/api/int/missions?onlyCompleted=true');
    };

    this.retrieveMissionDetails = function retrieveMissionDetails(missionId){
      return $http.get('/api/int/missions/'+missionId);
    };
    this.isLiveOperators = function isLiveOperators(userId){
      return $http.get('/api/getOperators/'+ userId);
    };



}

Service.$inject = ['$http','Session'];
module.service('MissionService', Service);
