<html data-ng-app="editorApp">
<head>
<title>Editor platform</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Comfortaa">

<!-- Latest compiled and minified JavaScript -->
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"	type="text/javascript"></script>
<script	src="resources/js/bootstrap.min.js"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"	type="text/javascript"></script>
<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.28//angular-route.min.js"	type="text/javascript"></script>

<script type="text/javascript">
	angular.module("editorApp", [ "ngRoute" ])
			.config([ '$routeProvider', function($routeProvider) {
				$routeProvider
					.when("/details", {
						templateUrl : "resources/details.html"
						})				
					.when("/index", {
						templateUrl : "resources/index.html"
						});
			}])
			.controller(
					"editorCtrl",
					function($scope, $http, $location) {

						$scope.total = 0;
						$scope.orderByField = 'name';
			            $scope.reverseSort = false;
			            
			            
						$scope.getProjects = function() {
							$http.get('${pageContext.request.contextPath}/api/projects')
								.then(function(response) {
									console.log(response.data)
									$scope.projects = response.data;
									$scope.proj = {};
									$scope.proj.status = 0;
								});
							$location.path("/index");
						}
						
						$scope.getHoursList = function(){
							$http.get('${pageContext.request.contextPath}/api/hours')
								.then(function(response){
									console.log(response.data)
									$scope.hours = response.data;		
							});
							
						}	
						
						$scope.getHoursByProject = function(theId){
							var ret = 0;
							angular.forEach($scope.hours, function(hours) {
								if (hours[0] == theId){
									ret = hours[1];
									}
							});
							return ret;
						}	

						$scope.getProjectDetails = function(theId) {
							$http.get('${pageContext.request.contextPath}/api/details/' + theId)
								.then(function(response) {
									console.log(response.data)
									$scope.details = response.data;
							});
							$location.path("/details");
						}

						$scope.saveProject = function(proj) {
							console.log(proj)
							$http.post('${pageContext.request.contextPath}/api/projects', proj)
								.then(function(response) {
									console.log(response)
								});
							
						}
						
						$scope.updateProjectDetails = function(detail) {
							console.log(detail);
							$http.post('${pageContext.request.contextPath}/api/details', detail)
								.then(function(response) {
									console.log(response)
								});
						}
						
						$scope.deleteProject = function(theId) {
							console.log(theId);
							$http.delete('${pageContext.request.contextPath}/api/deleteProject/' + theId)
								.then(function(response){
									console.log(respone)
								});
						}
						
						$scope.deleteProjectDetails = function(theDetailId) {
							console.log(theDetailId);
							$http.delete('${pageContext.request.contextPath}/api/deleteDetails/' + theDetailId)
								.then(function(response){
									console.log(response)
								});
						} 
						
						$scope.closeOrOpenProject = function(proj) {
							proj.status=!proj.status;
							$http.post('${pageContext.request.contextPath}/api/closeOrOpen', proj)
								.then(function(response) {
									console.log(response)
								});
						}

						$scope.getTotal = function(item) {
							$scope.total += item.hours;
							return $scope.total;
						}
						
						$scope.incompleteCount = function() {
							var count = 0;
							angular.forEach($scope.projects,
									function(projects) {
										if (!projects.status) {
											count++
										}	
							});
							return count;
						}

						$scope.warningLevel = function() {
							return $scope.incompleteCount() < 3 ? "label-success"
									: "label-warning";
						}
						
						$scope.passId = function(theId) {
							console.log("The id= " + theId);
							$scope.tempId = theId;
						}						
					})
					
		.filter("closedProjects", function() {
			return function(projects, showComplete) {
				var resultArr = [];
				angular.forEach(projects, function(item) {

				if (item.status == 0 || showComplete == true) {
					resultArr.push(item);
				}
			});
			return resultArr;
		}
	});
	
</script>
	<style type="text/css">
		div {
			font-family: comfortaa;
		}
	</style>
</head>

<body data-ng-controller="editorCtrl">
	<div class="container" ng-init="getProjects(); getHoursList()">
		<br />
		<div class="well">
			<h4>
				<strong><a href='${pageContext.request.contextPath}'>EditorWeb
						demo</a></strong> <span class="label label-default" ng-class="warningLevel()">
					{{incompleteCount()}} </span>
			</h4>
		</div>
		<div class="checkbox-inline"></div>

		<div class="well">
	          <tr>
		          <div ng-view></div>
		      </tr>		          		
		</div>
	</div>
</body>
</html>