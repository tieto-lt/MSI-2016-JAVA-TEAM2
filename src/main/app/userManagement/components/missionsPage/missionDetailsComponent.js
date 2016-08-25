var module = require('main_module');

function Controller(missionService, $scope, Session, $filter, $sce) {
  var vm = this;

  vm.oneAtATime = true;
  vm.isOperator = isOperator;
  vm.isCompleted = isCompleted;
  vm.isRed=isRed;
  vm.isOrange=isOrange;
  vm.heading= vm.mission.name;


  vm.objectMap=[{isEnabled:false},{isEnabled:false},{isEnabled:false}, {isEnabled:false}];
  vm.map = [];
  for(vm.i=0; vm.i<96; vm.i++)
  {
    vm.map.push({type:"unit", contains: ""});
  }
   vm.$onInit = function() {
     if (vm.mission.name.length> 110) {
     vm.heading = vm.mission.name.substr(0, 105) + "...";
     }
   };

  function isRed(){
    return(vm.mission.status=="not completed"||vm.mission.status=="declined");
  }
  function isOrange(){
    return(vm.mission.status=="in progress"||vm.mission.status=="approved");
  }

  function isOperator(){
      return(Session.isSessionActive() && Session.getSession().authorities[0] == "ROLE_OPERATOR");
  }


  function isCompleted(){
      return (vm.mission.status=="done");
  }

  vm.status = {
    isCustomHeaderOpen: false,
    isFirstOpen: true,
    isFirstDisabled: false
  };


  $scope.$watch('status.open', function(newValue) {
    if (newValue) {
      vm.loadDetails();
    }
  });

  vm.loadDetails = function loadDetails(){

    // slides
    vm.navigationData = {
      startX: undefined,
      startY: undefined,
      endX: undefined,
      endY: undefined,
      battery: undefined
    };

    vm.noWrapSlides = false;
    vm.active = 0;
    var slides = vm.slides = [];
    var currIndex = 0;

    vm.addSlide = function(image) {
      slides.push({
        image: image,
        id: currIndex++
      });
    };

    vm.randomize = function() {
      var indexes = generateIndexesArray();
      assignNewIndexesToSlides(indexes);
    };

    $scope.trustSrc = function(src) {
      return $sce.trustAsResourceUrl(src);
    }

    missionService.retrieveMissionDetails(vm.mission.id).then(function(data){
      var images = data.data.images;
      var navigationData = data.data.navigationData;
      var videoBase64 = "https://www.youtube.com/embed/";
      videoBase64 += data.data.videoBase64;
      $scope.movie = {src:videoBase64, title:"Mission video"};
      if(vm.mission.status=="not completed"||vm.mission.status=="declined") {
        vm.missionDate = null;
      } else {
        vm.missionDate = $filter('date')(new Date(data.data.missionDate), 'dd-MM-yyyy HH:mm:ss');
      }
      if(navigationData){
        vm.navigationData.startX = navigationData[0].x.toFixed(8);
        vm.navigationData.startY = navigationData[0].y.toFixed(8);
        vm.navigationData.endX = navigationData[navigationData.length-1].x.toFixed(8);
        vm.navigationData.endY = navigationData[navigationData.length-1].y.toFixed(8);
        vm.navigationData.battery = navigationData[navigationData.length-1].battery;
        vm.prepareBar(vm.navigationData.battery);
      }

       for(i = 0; i<images.length; i++){
         vm.addSlide(images[i].imageBase64);
       }

    });

    // Randomize logic below

    function assignNewIndexesToSlides(indexes) {
      for (var i = 0, l = slides.length; i < l; i++) {
        slides[i].id = indexes.pop();
      }
    }

    function generateIndexesArray() {
      var indexes = [];
      for (var i = 0; i < currIndex; ++i) {
        indexes[i] = i;
      }
      return shuffle(indexes);
    }

    // http://stackoverflow.com/questions/962802#962890
    function shuffle(array) {
      var tmp, current, top = array.length;

      if (top) {
        while (--top) {
          current = Math.floor(Math.random() * (top + 1));
          tmp = array[current];
          array[current] = array[top];
          array[top] = tmp;
        }
      }

      return array;
    }

    vm.max = 200;


   //Progress bar

    vm.prepareBar = function(battery) {
      var value = battery;
      var type;

      if (value < 25) {
        type = 'danger';
      } else if (value < 50) {
        type = 'warning';
      } else if (value < 75) {
        type = 'info';
      } else {
        type = 'success';
      }
      vm.dynamic = value;
      vm.type = type;
    };



  };




}

Controller.$inject = ['MissionService', '$scope', 'Session', '$filter', '$sce'];

module.component('missionDetailsComponent', {
    controller: Controller,
    templateUrl: require('./missionDetailsComponent.html'),
    bindings: {
      mission: '='
    }
});
