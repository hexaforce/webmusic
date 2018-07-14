'use strict';

(function($) {
	"use strict";
})(jQuery);

//########################################
//jPlayer
//########################################
function setup_jPlayer(){

	var	jquery_jplayer_1 = $("#jquery_jplayer_1");
	
	// Create click handlers for the different tracks
	$("#jp_container .track").click(function(e) {
		jquery_jplayer_1.jPlayer("setMedia", {
			title: $(this).attr("title"),
			mp3: $(this).attr("href")
		});
		jquery_jplayer_1.jPlayer("play");
		$(this).blur();
		return false;
	});
	
	$("#jquery_jplayer_1").jPlayer({
		swfPath: "/webjars/jplayer/2.9.2/dist/jplayer",
		supplied: "mp3",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		remainingDuration: true,
		toggleDuration: true,
		volume: 0.5
	});

	showVolumeControls();
	$(".jp-volume-controls").click(function(){showVolumeControls();});
	$(".jp-volume-bar").click(function(){showVolumeControls();});
	$(".jp-volume-bar-value").click(function(){showVolumeControls();});
	$(".jp-mute").click(function(){showVolumeControls();});
	$(".jp-volume-max").click(function(){showVolumeControls();});
	
}

function showVolumeControls(){
	console.log("showVolumeControls");
	$(".jp-volume-controls").show();
	$(".jp-volume-bar").show();
	$(".jp-volume-bar-value").show();
	$(".jp-mute").show();
	$(".jp-volume-max").show();
}

//########################################
//Angular
//########################################
var app = angular.module('WebMusic', ['ngRoute', 'ngSanitize', 'ngTouch']);

//Service
//****************************************
app.factory('x', ['$http', '$q', function ($http, $q) {

    var factory = {
  		  init:init,
      search: search
    };
    return factory;
    
    var opts = {
    		  lines: 13, // The number of lines to draw
    		  length: 38, // The length of each line
    		  width: 17, // The line thickness
    		  radius: 45, // The radius of the inner circle
    		  scale: 1, // Scales overall size of the spinner
    		  corners: 1, // Corner roundness (0..1)
    		  color: '#ffffff', // CSS color or array of colors
    		  fadeColor: 'transparent', // CSS color or array of colors
    		  speed: 1, // Rounds per second
    		  rotate: 0, // The rotation offset
    		  animation: 'spinner-line-fade-quick', // The CSS animation name for the lines
    		  direction: 1, // 1: clockwise, -1: counterclockwise
    		  zIndex: 2e9, // The z-index (defaults to 2000000000)
    		  className: 'spinner', // The CSS class to assign to the spinner
    		  top: '50%', // Top position relative to parent
    		  left: '50%', // Left position relative to parent
    		  shadow: '0 0 1px transparent', // Box-shadow for the lines
    		  position: 'absolute' // Element positioning
    		};
    
    function init(scope) {
    	
    	var spinner = new Spinner(opts).spin(document.getElementById('button-addon'));
    	
    	$http.get("/search-music-list/" + scope.year, null).then(function (response) {
        scope.data = response.data;
        
        spinner.spin();
        
      }, function (response) {
        console.log('ERROR:' + response.data);
      });
    }

    function search(scope) {

    	var spinner = new Spinner(opts).spin(document.getElementById('button-addon'));
    	
      var request = new Object();
      request["searchWord"] = scope.searchWord;
      
      $http.post("/search-music-list", request, null).then(function (response) {
        scope.data = response.data;

        spinner.spin();
        
      }, function (response) {
        console.log('ERROR:' + response.data);
      });
    }

  }
])
// Controller
// ****************************************
.controller('MusicList1980', ['$scope', 'x', function ($scope, x) {$scope.year = 1980;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1981', ['$scope', 'x', function ($scope, x) {$scope.year = 1981;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1982', ['$scope', 'x', function ($scope, x) {$scope.year = 1982;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1983', ['$scope', 'x', function ($scope, x) {$scope.year = 1983;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1984', ['$scope', 'x', function ($scope, x) {$scope.year = 1984;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1985', ['$scope', 'x', function ($scope, x) {$scope.year = 1985;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1986', ['$scope', 'x', function ($scope, x) {$scope.year = 1986;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1987', ['$scope', 'x', function ($scope, x) {$scope.year = 1987;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1988', ['$scope', 'x', function ($scope, x) {$scope.year = 1988;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1989', ['$scope', 'x', function ($scope, x) {$scope.year = 1989;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1990', ['$scope', 'x', function ($scope, x) {$scope.year = 1990;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1991', ['$scope', 'x', function ($scope, x) {$scope.year = 1991;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1992', ['$scope', 'x', function ($scope, x) {$scope.year = 1992;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1993', ['$scope', 'x', function ($scope, x) {$scope.year = 1993;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1994', ['$scope', 'x', function ($scope, x) {$scope.year = 1994;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1995', ['$scope', 'x', function ($scope, x) {$scope.year = 1995;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1996', ['$scope', 'x', function ($scope, x) {$scope.year = 1996;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1997', ['$scope', 'x', function ($scope, x) {$scope.year = 1997;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1998', ['$scope', 'x', function ($scope, x) {$scope.year = 1998;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList1999', ['$scope', 'x', function ($scope, x) {$scope.year = 1999;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2000', ['$scope', 'x', function ($scope, x) {$scope.year = 2000;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2001', ['$scope', 'x', function ($scope, x) {$scope.year = 2001;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2002', ['$scope', 'x', function ($scope, x) {$scope.year = 2002;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2003', ['$scope', 'x', function ($scope, x) {$scope.year = 2003;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2004', ['$scope', 'x', function ($scope, x) {$scope.year = 2004;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2005', ['$scope', 'x', function ($scope, x) {$scope.year = 2005;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2006', ['$scope', 'x', function ($scope, x) {$scope.year = 2006;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2007', ['$scope', 'x', function ($scope, x) {$scope.year = 2007;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2008', ['$scope', 'x', function ($scope, x) {$scope.year = 2008;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2009', ['$scope', 'x', function ($scope, x) {$scope.year = 2009;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2010', ['$scope', 'x', function ($scope, x) {$scope.year = 2010;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2011', ['$scope', 'x', function ($scope, x) {$scope.year = 2011;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2012', ['$scope', 'x', function ($scope, x) {$scope.year = 2012;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2013', ['$scope', 'x', function ($scope, x) {$scope.year = 2013;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2014', ['$scope', 'x', function ($scope, x) {$scope.year = 2014;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
.controller('MusicList2015', ['$scope', 'x', function ($scope, x) {$scope.year = 2015;x.init($scope);$scope.searchWord = "";$scope.search = function(){x.search($scope);};$scope.init = function(){x.init($scope);};}])
// Route Config
// ****************************************
.config(['$routeProvider', function ($routeProvider) {
  for (var i = 1980; i < 2016; i++) $routeProvider.when('/search-music-list/' + i, {
    templateUrl: 'music-list',
    controller: 'MusicList' + i
  });
  $routeProvider.otherwise({redirectTo: '/search-music-list/1980'});
}])
// onFinishRender
// ****************************************
.directive('onFinishRender', function($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element, attr) {
			if (scope.$last === true) {
				$timeout(function() {
					setup_jPlayer();
				});
			}
		}
	}
});