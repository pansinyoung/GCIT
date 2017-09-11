lmsApp.controller("listBookController", function ($scope, $http, $window, bookService, authorService, genreService, publisherService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function() {     
        bookService.readAllBooksService().then(function (data) {
            $scope.books = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchBook = function() {
        bookService.searchBookService($scope.searchString).then(function(data){
            $scope.books = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
        });
    };
    
    $scope.addBookClicked = function(){
        $scope.publishers = publisherService.readAllPublishersService();
        $scope.authors = authorService.readAllAuthorsService();
        $scope.genres = genreService.readAllGenresService();
        
        var modalInstance = $modal.open({
          templateUrl: 'addBookModal.html',
          controller: 'addBookModalCtrl',
            size: 'lg',
          resolve: {
            publishers: function () {
              return $scope.publishers;
            },
              authors: function(){
                  return $scope.authors;
              },
              genres: function(){
                  return $scope.genres;
              }
          }
        });

        modalInstance.result.then(function (newBook) {
            $scope.newBook = newBook;
            bookService.addUpdateBookService(newBook).then(function(){
                $window.alert('add successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.deleteBookClicked = function(){
        $scope.showDeleteButton = true;
        $scope.showUpdateButton = false;
    };
    
    $scope.deleteBookButtonClicked = function(bookId){
        bookService.deleteBookService({'bookId': bookId}).then(function(){
            $window.alert('delete successfully!');
            $scope.loadData();
            $scope.showDeleteButton = true;
      });
    };
    
    $scope.viewBookClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = false;
    };
    
    $scope.updateBookClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = true;
    };
    
    $scope.updateBookButtonClicked = function(bookId){
        $scope.bookToUpdate = bookService.selectBookByIdService(bookId);
        $scope.publishers = publisherService.readAllPublishersService();
        $scope.authors = authorService.readAllAuthorsService();
        $scope.genres = genreService.readAllGenresService();
        
        var anotherModalInstance = $modal.open({
          templateUrl: 'updateBookModal.html',
          controller: 'updateBookModalCtrl',
            size: 'lg',
          resolve: {
            publishers: function () {
              return $scope.publishers;
            },
              authors: function(){
                  return $scope.authors;
              },
              genres: function(){
                  return $scope.genres;
              },
              bookToUpdate: function(){
                  return $scope.bookToUpdate;
              }
          }
        });

        anotherModalInstance.result.then(function (bookToUpdate) {
            $scope.bookToUpdate = bookToUpdate;
            bookService.addUpdateBookService(bookToUpdate).then(function(){
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
    
    $scope.goToGenre = function(){
        $window.location.href = '#/administrationGenre';
    };
    
    $scope.goToLoan = function(){
        $window.location.href = '#/administrationLoan';
    };

});

lmsApp.controller('addBookModalCtrl', function ($log, $scope, $modalInstance, publishers, authors, genres) {

    $scope.publishers = publishers;
    $scope.authors = authors;
    $scope.genres = genres;

    $scope.ok = function () {
        $modalInstance.close($scope.newBook);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('updateBookModalCtrl', function ($log, $scope, $modalInstance, publishers, authors, genres, bookToUpdate) {

    $scope.bookToUpdate = bookToUpdate;
    $scope.publishers = publishers;
    $scope.authors = authors;
    $scope.genres = genres;

    $scope.ok = function () {
        $modalInstance.close($scope.bookToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});