var module = require('main_module');
require('style.scss');

function Controller($state, $stateParams, ItemService) {
    var vm = this;
    var vid = document.getElementById("bgvid");
    var pauseButton = document.querySelector("#polina button");
    vm.detailsVisible = false;
    vm.showDetails = showDetails;

    function vidFade() {
        vid.classList.add("stopfade");
    }

    vid.addEventListener('ended', function() {
        // only functional if "loop" is removed
        vid.pause();
        // to capture IE10
        vidFade();
    });

    function showDetails() {
        vm.detailsVisible = !vm.detailsVisible;
        return vm.detailsVisible;
    }
}

module.component('homePage', {
    controller: Controller,
    templateUrl: require('./homePage.html')
});
