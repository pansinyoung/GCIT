lmsApp.controller("listGenreController", function ($scope, $http, $window, bookService, genreService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function() {     
        genreService.readAllGenresService().then(function (data) {
            $scope.genres = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchGenre = function() {
        genreService.searchGenreService($scope.searchString).then(function(data){
            $scope.genres = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
        });
    };
    
    $scope.addGenreClicked = function(){
        $scope.books = bookService.readAllBooksService();
        
        var modalInstance = $modal.open({
          templateUrl: 'addGenreModal.html',
          controller: 'addGenreModalCtrl',
            size: 'lg',
          resolve: {
            books: function () {
              return $scope.books;
            }
          }
        });

        modalInstance.result.then(function (newGenre) {
            $scope.newGenre = newGenre;
            genreService.addUpdateGenreService(newGenre).then(function(){
                $window.alert('add successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.deleteGenreClicked = function(){
        $scope.showDeleteButton = true;
        $scope.showUpdateButton = false;
    };
    
    $scope.deleteGenreButtonClicked = function(genre_id){
        genreService.deleteGenreService({'genre_id': genre_id}).then(function(){
            $window.alert('delete successfully!');
            $scope.loadData();
      });
    };
    
    $scope.viewGenreClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = false;
    };
    
    $scope.updateGenreClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = true;
    };
    
    $scope.updateGenreButtonClicked = function(genre_id){
        $scope.genreToUpdate = genreService.selectGenreByIdService(genre_id);
        $scope.books = bookService.readAllBooksService();

        var anotherModalInstance = $modal.open({
          templateUrl: 'updateGenreModal.html',
          controller: 'updateGenreModalCtrl',
            size: 'lg',
          resolve: {
            books: function () {
              return $scope.books;
            },
              genreToUpdate: function(){
                  return $scope.genreToUpdate;
              }
          }
        });

        anotherModalInstance.result.then(function (genreToUpdate) {
            $scope.genreToUpdate = genreToUpdate;
            genreService.addUpdateGenreService(genreToUpdate).then(function(){
                $window.alert('update successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.goToAuthor = function(){
        $window.location.href = "http://localhost:8080/lms/#/administrationAuthor";
    };
    
    $scope.goToPublisher = function(){
        $window.location.href = '#/administrationPublisher';
    };
    
    $scope.goToBranch = function(){
        $window.location.href = '#/administrationBranch';
    };
    
    $scope.goToBorrower = function(){
        $window.location.href = '#/administrationBorrower';
    };
    
    $scope.goToBook = function(){
        $window.location.href = '#/administrationBook';
    };
    
    $scope.goToLoan = function(){
        $window.location.href = '#/administrationLoan';
    };

});

lmsApp.controller('addGenreModalCtrl', function ($log, $scope, $modalInstance, books) {

    $scope.books = books;

    $scope.ok = function () {
        $modalInstance.close($scope.newGenre);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('updateGenreModalCtrl', function ($log, $scope, $modalInstance, books, genreToUpdate) {

    $scope.genreToUpdate = genreToUpdate;
    $scope.books = books;

    $scope.ok = function () {
        $modalInstance.close($scope.genreToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});