var module = require('main_module');
require('style.scss');

function Controller($state, $stateParams, ItemService) {

    var vid = document.getElementById("bgvid");
    var pauseButton = document.querySelector("#polina button");

    function vidFade() {
      vid.classList.add("stopfade");
    }

    vid.addEventListener('ended', function()
    {
    // only functional if "loop" is removed
    vid.pause();
    // to capture IE10
    vidFade();
    });

}

module.component('homePage', {
    controller: Controller,
    templateUrl: require('./homePage.html')
});
