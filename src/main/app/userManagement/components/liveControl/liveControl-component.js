var module = require('main_module');
//Adds NodecopterStream on window
require('./nodecopter-stream.js');


function Controller($state, Session, $document, $gamepad, $scope) {

  var vm = this;
  vm.openConnection = openConnection;
  var webSocket;
  vm.connectionOpened = false;
  vm.batteryPercentage = undefined;
  var inAir = false;
  vm.currentSpeed = 0.8;
  function openConnection()
  {
  if(!vm.connectionOpened){
   vm.connectionOpened = true;
    new NodecopterStream(document.getElementById("droneStream"), { userId: Session.getSession().userId });
    webSocket = new WebSocket('ws://localhost:8080/ws/control/'+Session.getSession().userId);
    webSocket.onmessage = function (event){
      var batteryPercentage = JSON.parse(event.data).droneState.demo.batteryPercentage;
      vm.batteryPercentage = batteryPercentage;
      $scope.$apply();
      console.log(batteryPercentage);
    };
    }
  }

  vm.sendCommandForDrone = function sendCommandForDrone(command, speed) {
    console.log(command,speed);
    if (webSocket) {
      commandObj = {
        payloadType: "DirectCommand",
        commandType: command,
        speed: speed
      };
      webSocket.send(JSON.stringify(commandObj));
    }
  };

  $document.bind("keyup", function(event) {
      vm.onKeyUp(event);
  });
  $document.bind("keydown", function(event) {
      vm.onKeyDown(event);
  });
  $scope.$on('gamepad:updated',function(event,gamepad){

    if(gamepad.LS.Y > 0) {
       vm.sendCommandForDrone("right", Math.abs(-gamepad.LS.Y));
    } else if(gamepad.LS.Y < 0)  {
      vm.sendCommandForDrone("left", Math.abs(gamepad.LS.Y));
    } else if(gamepad.RS.Y < 0) {
       vm.sendCommandForDrone("front", Math.abs(gamepad.RS.Y));
    } else if(gamepad.RS.Y > 0){
       vm.sendCommandForDrone("back", Math.abs(-gamepad.RS.Y));
    } else if(gamepad.LS.X > 0) {
       vm.sendCommandForDrone("clockwise", Math.abs(-gamepad.LS.X));
    } else if (gamepad.LS.X < 0) {
       vm.sendCommandForDrone("counterClockwise", Math.abs(gamepad.LS.X));
    } else if (gamepad.buttons.B === 1) {
      if(inAir) {
        vm.sendCommandForDrone("land", undefined);
        inAir = false;
      } else {
        vm.sendCommandForDrone("takeoff", undefined);
        inAir = true;
      }
    } else if (gamepad.buttons.R2 === 1) {
      vm.sendCommandForDrone("up", vm.currentSpeed);
    } else if (gamepad.buttons.L2 === 1) {
      vm.sendCommandForDrone("down", vm.currentSpeed);
    } else if (gamepad.buttons.L1 === 1) {
      vm.sendCommandForDrone("horizontalCamera", undefined);
    } else if (gamepad.buttons.R1 === 1) {
      vm.sendCommandForDrone("verticalCamera", undefined);
    } else if (gamepad.buttons.X === 1) {
      vm.sendCommandForDrone("takePicture",undefined);
    } else if (gamepad.LS.Y === 0 && gamepad.LS.X === 0 && gamepad.RS.Y === 0 &&
      gamepad.RS.X === 0 && gamepad.buttons.X === 0 && gamepad.buttons.L1 === 0 &&
      gamepad.buttons.R1 === 0 && gamepad.buttons.R2 === 0  && gamepad.buttons.L2 === 0
      && gamepad.buttons.B === 0) {
       vm.sendCommandForDrone("stop", undefined);
    }
  });


  vm.KEYBOARD = {
    PLUS : 187,
    MINUS : 189,
    UP : 38,
    DOWN : 40,
    RIGHT : 39,
    LEFT : 37,
    W_KEY : 87,
    S_KEY : 83,
    D_KEY : 68,
    A_KEY : 65,
    PAGE_UP : 33,
    PAGE_DOWN : 34,
    ESC : 27,
    ENTER : 13,
    SHIFT : 16
  };

  vm.onKeyUp = function onKeyUp(e) {
   vm.keyDowned = false;
   switch (e.which) {
     case vm.KEYBOARD.UP:
     case vm.KEYBOARD.KEYBOARD:
     case vm.KEYBOARD.RIGHT:
     case vm.KEYBOARD.W_KEY:
     case vm.KEYBOARD.LEFT:
     case vm.KEYBOARD.S_KEY:
     case vm.KEYBOARD.A_KEY:
     case vm.KEYBOARD.D_KEY:
       vm.sendCommandForDrone("stop", undefined);
       e.preventDefault();
       return false;
   }
 };



 vm.setCurrentSpeed = function setCurrentSpeed(increasedSpeed){
   vm.currentSpeed = increasedSpeed;
 };

 vm.onKeyDown =  function onKeyDown(e) {
   if (vm.keyDowned) {
     e.preventDefault();
     return;
   }
   vm.keyDowned = true;
   speedStep = 0.1;
   switch (e.which) {
     case vm.KEYBOARD.PLUS:
       vm.setCurrentSpeed(vm.currentSpeed() + speedStep);
       break;
     case vm.KEYBOARD.MINUS:
       vm.setCurrentSpeed(vm.currentSpeed() - speedStep);
       break;
     case vm.KEYBOARD.UP:
       vm.sendCommandForDrone("up", vm.currentSpeed);
       break;
     case vm.KEYBOARD.DOWN:
       vm.sendCommandForDrone("down", vm.currentSpeed);
       break;
     case vm.KEYBOARD.RIGHT:
       vm.sendCommandForDrone("clockwise", vm.currentSpeed);
       break;
     case vm.KEYBOARD.LEFT:
       vm.sendCommandForDrone("counterClockwise", vm.currentSpeed);
       break;
     case vm.KEYBOARD.W_KEY:
       vm.sendCommandForDrone("front", vm.currentSpeed);
       break;
     case vm.KEYBOARD.S_KEY:
       vm.sendCommandForDrone("back", vm.currentSpeed);
       break;
     case vm.KEYBOARD.A_KEY:
       vm.sendCommandForDrone("left", vm.currentSpeed);
       break;
     case vm.KEYBOARD.D_KEY:
       vm.sendCommandForDrone("right", vm.currentSpeed);
       break;
     case vm.KEYBOARD.ENTER:
       vm.sendCommandForDrone("takeoff", undefined);
       break;
     case vm.KEYBOARD.ESC:
       vm.sendCommandForDrone("land", undefined);
       break;
     case vm.KEYBOARD.PAGE_UP:
       vm.sendCommandForDrone("horizontalCamera", undefined);
       break;
     case vm.KEYBOARD.PAGE_DOWN:
       vm.sendCommandForDrone("verticalCamera", undefined);
       break;
     case vm.KEYBOARD.SHIFT:
       vm.sendCommandForDrone("takePicture",undefined);
       break;
   }
   e.preventDefault();
   return false;
 };


}

function populateData(){

}


Controller.$inject = ['$state', 'Session','$document','$gamepad','$scope'];

module.component('liveControl', {
  controller: Controller,
  templateUrl: require('./liveControl.html')
});
