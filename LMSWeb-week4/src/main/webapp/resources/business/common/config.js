lmsApp.config(["$routeProvider", function ($routeProvider) {
    return $routeProvider.when("/", {
        redirectTo: "/home"
    }).when("/home", {
        templateUrl: "welcome.html"
    }).when("/administrationBook", {
        templateUrl: "administrationBook.html"
    });
}]);