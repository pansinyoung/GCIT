lmsApp.controller("listPublisherController", function ($scope, $http, $window, bookService, authorService, genreService, publisherService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function() {     
        publisherService.readAllPublishersService().then(function (data) {
            $scope.publishers = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.publishers.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchPublisher = function() {
        publisherService.searchPublisherService($scope.searchString).then(function(data){
            $scope.publishers = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.publishers.length / $scope.pagination.perPage);
        });
    };
    
    $scope.addPublisherClicked = function(){
        $scope.books = bookService.readAllBooksService();        
        var modalInstance = $modal.open({
          templateUrl: 'addPublisherModal.html',
          controller: 'addPublisherModalCtrl',
            size: 'lg',
          resolve: {
            books: function () {
              return $scope.books;
            }
          }
        });

        modalInstance.result.then(function (newPublisher) {
            $scope.newPublisher = newPublisher;
            publisherService.addUpdatePublisherService(newPublisher).then(function(){
                $window.alert('add successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.deletePublisherClicked = function(){
        $scope.showDeleteButton = true;
        $scope.showUpdateButton = false;
    };
    
    $scope.deletePublisherButtonClicked = function(publisherId){
        publisherService.deletePublisherService({'publisherId': publisherId}).then(function(){
            $window.alert('delete successfully!');
            $scope.loadData();
            $scope.showDeleteButton = true;
      });
    };
    
    $scope.viewPublisherClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = false;
    };
    
    $scope.updatePublisherClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = true;
    };
    
    $scope.updatePublisherButtonClicked = function(publisherId){
        $scope.publisherToUpdate = publisherService.selectPublisherByIdService(publisherId);
        $scope.books = bookService.readAllBooksService();
        
        var anotherModalInstance = $modal.open({
          templateUrl: 'updatePublisherModal.html',
          controller: 'updatePublisherModalCtrl',
            size: 'lg',
          resolve: {
            books: function () {
                return $scope.books;
            },
            publisherToUpdate: function(){
                return $scope.publisherToUpdate;
            }
          }
        });

        anotherModalInstance.result.then(function (publisherToUpdate) {
            $scope.publisherToUpdate = publisherToUpdate;
            publisherService.addUpdatePublisherService(publisherToUpdate).then(function(){
                $window.alert('update successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.goToAuthor = function(){
        $window.location.href = "http://localhost:8080/lms/#/administrationAuthor";
    };
    
    $scope.goToBook = function(){
        $window.location.href = '#/administrationBook';
    };
    
    $scope.goToBranch = function(){
        $window.location.href = '#/administrationBranch';
    };
    
    $scope.goToBorrower = function(){
        $window.location.href = '#/administrationBorrower';
    };
    
    $scope.goToGenre = function(){
        $window.location.href = '#/administrationGenre';
    };
    
    $scope.goToLoan = function(){
        $window.location.href = '#/administrationLoan';
    };

});

lmsApp.controller('addPublisherModalCtrl', function ($log, $scope, $modalInstance, books) {

    $scope.books = books;

    $scope.ok = function () {
        $modalInstance.close($scope.newPublisher);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('updatePublisherModalCtrl', function ($log, $scope, $modalInstance, books, publisherToUpdate) {

    $scope.publisherToUpdate = publisherToUpdate;
    $scope.books = books;

    $scope.ok = function () {
        $modalInstance.close($scope.publisherToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});