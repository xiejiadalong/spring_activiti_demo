/**
 * menuManerger
 */
app.controller('userManergerController', function($rootScope, $scope, $http) {

	//初始化
	var numberOfPages=5;  //默认每页5条
	init();
	function init() {
		getUserDatas(1,numberOfPages);
	}
	
	function getUserDatas(currentPage,numberOfPages){
		var parms={
				"currentPage":currentPage,
				"numberOfPages":numberOfPages
				
		};
		$http.post('/getUsers',parms).then(function successCallback(response) {
			$scope.userData = response.data;
			if(response.data.length>0){
				var totalPages =response.data[0].totalPages;
				var total_record=response.data[0].total_record;
				$scope.totalPages=totalPages;
				$scope.total_record=total_record;
				setPageData(currentPage,totalPages,numberOfPages);
			}
			
		}, function errorCallback(response) {
			console.log("error");
		});
	}
	
	//设置分页插件参数
	function setPageData(currentPage,totalPages,numberOfPages){
		 $('#pageLimit').bootstrapPaginator({
			    currentPage: currentPage,//当前的请求页面。
			    totalPages: totalPages,//一共多少页。
			    size:"normal",//应该是页眉的大小。
			    bootstrapMajorVersion: 3,//bootstrap的版本要求。
			    alignment:"left",
			    numberOfPages:numberOfPages,//一页列出多少数据。
			    itemTexts: function (type, page, current) {//如下的代码是将页眉显示的中文显示我们自定义的中文。
			        switch (type) {
			        case "first": return "首页";
			        case "prev": return "上一页";
			        case "next": return "下一页";
			        case "last": return "末页";
			        case "page": return page;
			        }
			    },
			    onPageClicked: function (event, originalEvent, type, page){
			    	getUserDatas(page,numberOfPages);
			    }
			});
	}
	//添加人员
	$scope.onAdd=function(){
		 $("#addUserModal").modal("show");
	}
	$scope.onDelete=function(user_id){
         console.log(user_id);
         if(!user_id){
        	 alert("无法删除！");
         }
         var user={};
		 user.user_id=user_id;
		 $http.post('/deleteUser',user).then(function successCallback(response) {
			 init();
			}, function errorCallback(response) {
				console.log("error");
			});
	}
	$scope.onCancel=function(){
		 $("#addUserModal").modal("hide");
	}
	$scope.onOk=function(){
		 var user={};
		 user.user_name=$scope.user_name;
		 user.id_number=$scope.id_number;
		 user.dev_position=$scope.dev_position;
		 user.level=$scope.level;
		 
		 $http.post('/addUser',user).then(function successCallback(response) {
			 init();
			 $("#addUserModal").modal("hide");
			}, function errorCallback(response) {
				console.log("error");
			});
		
	}
	
	
	

});