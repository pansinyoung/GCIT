lmsApp.controller("listBookController", function ($scope, $http, $window, bookService, authorService, genreService, publisherService, Pagination, $filter, $modal, $log) {
   bookService.readAllBooksService().then(function (data) {
        $scope.books = data;
        $scope.pagination = Pagination.getNew(10);
        $scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
       })
    
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
              },
              newBook: function(){
                  return bookService.initBookService();
              }
          }
        });

        modalInstance.result.then(function (newBook) {
            $scope.newBook = newBook;
            $log.info(newBook);
            bookService.addUpdateBookService(newBook);
        }, function () {
//            bookService.addUpdateBookService(newBook);
        });
    
    };
});

lmsApp.controller('addBookModalCtrl', function ($log, $scope, $modalInstance, publishers, authors, genres, newBook) {

    $scope.newBook = newBook;
    $scope.publishers = publishers;
    $scope.authors = authors;
    $scope.genres = genres;

    $scope.ok = function () {
//        $scope.newBook.title = title;
//        $scope.newBook.publisher = newBook.publisher;
//        $scope.newBook.authors = newBook.authors;
//        $scope.newBook.genres = newBook.genres;
        $modalInstance.close(newBook);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});