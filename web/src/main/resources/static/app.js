var app = angular.module('WebMusic', ['ngRoute', 'ngSanitize']);

//***** Service ************************************
app.factory('AppService', ['$http', '$q', function ($http, $q) {

	var API = "http://13.230.163.159:8090";
	var factory = {
	  init: init,
	  changeYear: changeYear,
	  searchWord: searchWord
	};
	return factory;

	function init(scope) {
	  $http.get(API + "/list/year", null).then(function (response) {
	    scope.yearList = response.data;
	  }, function (response) {
	    console.log('ERROR:' + response.data);
	  });
	}

	function changeYear(scope) {
	  $http.get(API + "/search/year/" + scope.year, null).then(function (response) {
	    scope.musicList = response.data;
	  }, function (response) {
	    console.log('ERROR:' + response.data);
	  });
	}

	function searchWord(scope) {
	  $http({
	    method: 'POST',
	    url: API + '/search/word',
	    headers: {'Content-Type': 'application/json; charset=UTF-8'},
	    data: {'word': scope.word}
	  }).then(function (response) {
	    scope.musicList = response.data;
	  }, function (response) {
	    console.log('ERROR:' + response.data);
	  });
	}

}])
// ***** Controller ************************************
.controller('AppController', ['$scope', 'AppService', function ($scope, AppService) {
	$scope.Math = window.Math;
    var audio = audiojs.createAll()[0];
	 AppService.init($scope);
	 
	 $scope.year = 1980;
	 // AppService.changeYear($scope);
	 $scope.changeYear = function changeYear() {
	   AppService.changeYear($scope);
	 };

	 $scope.word = "1980";
	 AppService.searchWord($scope);
	 $scope.searchWord = function searchWord() {
	   AppService.searchWord($scope);
	 };
	 
	 $scope.play = function play(mp3) {
         audio.load("http://13.230.163.159:8090/mp3/"+mp3);
         audio.play();
   };
}])
//***** Route Config ************************************
.config(['$routeProvider', function ($routeProvider) {
 $routeProvider.when('/app', {
  templateUrl : 'list',
  controller : 'AppController'
 });
 $routeProvider.otherwise({redirectTo: '/'});
}])
//***** onFinishRender ************************************
.directive('onFinishRender', function($timeout) {
 return {
  restrict : 'A',
  link : function(scope, element, attr) {
   if (scope.$last === true) {
    $timeout(function() {
    	
    });
   }
  }
 }
});

