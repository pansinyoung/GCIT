lmsApp.config(["$routeProvider", function ($routeProvider) {
    return $routeProvider.when("/", {
        redirectTo: "/home"
    }).when("/home", {
        templateUrl: "welcome.html"
    }).when("/administrationBook", {
        templateUrl: "administrationBook.html"
    }).when("/administrationAuthor", {
        templateUrl: "adminAuthor.html"
    }).when("/administrationPublisher", {
        templateUrl: "adminPublisher.html"
    }).when("/administrationBorrower", {
        templateUrl: "adminBorrower.html"
    }).when("/administrationBranch", {
        templateUrl: "adminBranch.html"
    }).when("/administrationGenre", {
        templateUrl: "adminGenre.html"
    }).when("/administrationLoan", {
        templateUrl: "adminLoan.html"
    });
}]);