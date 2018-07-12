'use strict';

//########################################
// jPlayer
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
		swfPath: "/js/vendor",
		supplied: "mp3",
		wmode: "window",
		useStateClassSkin: true,
		autoBlur: false,
		smoothPlayBar: true,
		keyEnabled: true,
		remainingDuration: true,
		toggleDuration: true,
		volume: 0.1
	});
}

//########################################
// Angular
//########################################
var app = angular.module('MusicServer', ['ngRoute', 'ngSanitize']);

// Service
// ****************************************
app.factory('SongListService', ['$http', '$q', function ($http, $q) {

      var factory = {
    		  init:init,
        search: search
      };
      return factory;

      function init(scope) {
        $http.get("/search-music-list/" + scope.year, null).then(function (response) {
          scope.data = response.data;
        }, function (response) {
          console.log('ERROR:' + response.data);
        });
      }

      function search(scope) {
        var request = new Object();
        request["searchWord"] = scope.searchWord;
        $http.post("/search-music-list", request, null).then(function (response) {
          scope.data = response.data;
        }, function (response) {
          console.log('ERROR:' + response.data);
        });
      }

    }
  ])
  // Controller
  // ****************************************
  .controller('SongList1980Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1980;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1981Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1981;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1982Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1982;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1983Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1983;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1984Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1984;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1985Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1985;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1986Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1986;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1987Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1987;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1988Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1988;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1989Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1989;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1990Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1990;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1991Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1991;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1992Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1992;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1993Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1993;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1994Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1994;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1995Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1995;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1996Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1996;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1997Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1997;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1998Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1998;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList1999Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 1999;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2000Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2000;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2001Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2001;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2002Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2002;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2003Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2003;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2004Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2004;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2005Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2005;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2006Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2006;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2007Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2007;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2008Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2008;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2009Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2009;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2010Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2010;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2011Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2011;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2012Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2012;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2013Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2013;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.search = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2014Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2014;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.init = function(){SongListService.init($scope);};
    $scope.search = function search() {
      SongListService.search($scope);
    };
  }]).controller('SongList2015Controller', ['$scope', 'SongListService', function ($scope, SongListService) {
    $scope.year = 2015;
    SongListService.init($scope);
    $scope.searchWord = "";
    $scope.init = function(){SongListService.init($scope);};$scope.search = function(){SongListService.search($scope);};
  }])
  // Route Config
  // ****************************************
  .config(['$routeProvider', function ($routeProvider) {
    for (var i = 1980; i < 2016; i++) $routeProvider.when('/search-music-list/' + i, {
      templateUrl: 'music-list',
      controller: 'SongList' + i + 'Controller'
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

