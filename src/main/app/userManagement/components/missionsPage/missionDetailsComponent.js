var module = require('main_module');

function Controller(missionService) {
  var vm = this;

  vm.oneAtATime = true;

  vm.status = {
    isCustomHeaderOpen: false,
    isFirstOpen: true,
    isFirstDisabled: false
  };


  vm.loadDetails = function loadDetails(element){

    // slides
    if(element.status && element.status.open){
      return;
    }
    vm.myInterval = 5000;
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

    missionService.retrieveMissionDetails(vm.mission.id).then(function(data){
      var images = data.data.images;
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

    vm.random = function() {
      var value = Math.floor(Math.random() * 100 + 1);
      var type;

      if (value < 25) {
        type = 'success';
      } else if (value < 50) {
        type = 'info';
      } else if (value < 75) {
        type = 'warning';
      } else {
        type = 'danger';
      }

      vm.showWarning = type === 'danger' || type === 'warning';

      vm.dynamic = value;
      vm.type = type;
    };

    vm.random();

    vm.randomStacked = function() {
      vm.stacked = [];
      var types = ['success', 'info', 'warning', 'danger'];

      for (var i = 0, n = Math.floor(Math.random() * 4 + 1); i < n; i++) {
          var index = Math.floor(Math.random() * 4);
          vm.stacked.push({
            value: Math.floor(Math.random() * 30 + 1),
            type: types[index]
          });
      }
    };

    vm.randomStacked();
  };


}

Controller.$inject = ['MissionService'];

module.component('missionDetailsComponent', {
    controller: Controller,
    templateUrl: require('./missionDetailsComponent.html'),
    bindings: {
      mission: '='
    }
});
