lmsApp.controller("listAuthorController", function ($scope, $http, $window, bookService, authorService, genreService, publisherService, Pagination, $filter, $modal, $log, $route) {
     $scope.loadData = function() {     
        authorService.readAllAuthorsService().then(function (data) {
            $scope.authors = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.authors.length / $scope.pagination.perPage);
        })
     };
    
    $scope.loadData();
    
    $scope.searchAuthor = function() {
        authorService.searchAuthorService($scope.searchString).then(function(data){
            $scope.authors = data;
            $scope.pagination = Pagination.getNew(10);
            $scope.pagination.numPages = Math.ceil($scope.authors.length / $scope.pagination.perPage);
        });
    };
    
    $scope.addAuthorClicked = function(){
        $scope.books = bookService.readAllBooksService();
        
        var modalInstance = $modal.open({
          templateUrl: 'addAuthorModal.html',
          controller: 'addAuthorModalCtrl',
            size: 'lg',
          resolve: {
            books: function () {
              return $scope.books;
            }
          }
        });

        modalInstance.result.then(function (newAuthor) {
            $scope.newAuthor = newAuthor;
            authorService.addUpdateAuthorService(newAuthor).then(function(){
                $window.alert('add successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.deleteAuthorClicked = function(){
        $scope.showDeleteButton = true;
        $scope.showUpdateButton = false;
    };
    
    $scope.deleteAuthorButtonClicked = function(authorId){
        authorService.deleteAuthorService({'authorId': authorId}).then(function(){
            $window.alert('delete successfully!');
            $scope.loadData();
            $scope.showDeleteButton = true;
      });
    };
    
    $scope.viewAuthorClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = false;
    };
    
    $scope.updateAuthorClicked = function(){
        $scope.showDeleteButton = false;
        $scope.showUpdateButton = true;
    };
    
    $scope.updateAuthorButtonClicked = function(authorId){
        $scope.authorToUpdate = authorService.selectAuthorByIdService(authorId);
        $scope.books = bookService.readAllBooksService();
        
        var anotherModalInstance = $modal.open({
          templateUrl: 'updateAuthorModal.html',
          controller: 'updateAuthorModalCtrl',
            size: 'lg',
          resolve: {
            publishers: function () {
              return $scope.publishers;
            },
              books: function(){
                  return $scope.books;
              },
              authorToUpdate: function(){
                  return $scope.authorToUpdate;
              }
          }
        });

        anotherModalInstance.result.then(function (authorToUpdate) {
            $scope.authorToUpdate = authorToUpdate;
            authorService.addUpdateAuthorService(authorToUpdate).then(function(){
                $window.alert('update successfully!');
                $scope.loadData();
            });
        });
        
        
    };
    
    $scope.goToBook = function(){
        $window.location.href = '#/administrationBook';
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

lmsApp.controller('addAuthorModalCtrl', function ($log, $scope, $modalInstance, books) {

//    $scope.newBook = newBook;
    $scope.books = books;

    $scope.ok = function () {
//        $scope.newBook.title = title;
//        $scope.newBook.publisher = newBook.publisher;
//        $scope.newBook.authors = newBook.authors;
//        $scope.newBook.genres = newBook.genres;
        $modalInstance.close($scope.newAuthor);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});

lmsApp.controller('updateAuthorModalCtrl', function ($log, $scope, $modalInstance, books, authorToUpdate) {

//    $scope.newBook = newBook;
    $scope.authorToUpdate = authorToUpdate;
    $scope.books = books;
    
    $scope.ok = function () {
//        $scope.newBook.title = title;
//        $scope.newBook.publisher = newBook.publisher;
//        $scope.newBook.authors = newBook.authors;
//        $scope.newBook.genres = newBook.genres;
        $modalInstance.close($scope.authorToUpdate);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});