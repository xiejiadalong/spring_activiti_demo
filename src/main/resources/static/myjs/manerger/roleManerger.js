/**
 * roleManerger
 */
app.controller('roleManergerController', function($rootScope,$scope, $http) {
	//初始化
	var numberOfPages=5;  //默认每页5条
	initCss();
	init();
	function init() {
		getRoleDatas(1,numberOfPages);
	}
    function initCss(){
    	$("#pageLimit").css("cursor","pointer");
        $(".selectpicker").selectpicker({
    		noneSelectedText : '5'
    	}); 
        $('#slpk').on('changed.bs.select', function (e) {
        	var value = $('#slpk').val();
        	numberOfPages=parseInt(value);
        	init();
        });
    }

	function getRoleDatas(currentPage,numberOfPages){
		var parms={
				"currentPage":currentPage,
				"numberOfPages":numberOfPages
				
		};
		$http.post('/getRoles',parms).then(function successCallback(response) {
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
			    	getRoleDatas(page,numberOfPages);
			    }
			});
	}
	//添加人员
	$scope.onAdd=function(){
		 $("#addRoleModal").modal("show");
	}
	$scope.onDelete=function(role_id){
         console.log(role_id);
         if(!role_id){
        	 alert("无法删除！");
         }
         var role={};
         role.role_id=role_id;
		 $http.post('/deleteRole',role).then(function successCallback(response) {
			 init();
			}, function errorCallback(response) {
				console.log("error");
			});
	}
	$scope.onCancel=function(){
		 $("#addRoleModal").modal("hide");
	}
	$scope.onOk=function(){
		 var role={};
		 role.role_name=$scope.role_name;
		 role.role_status=$scope.role_status;
		 role.comment=$scope.comment;
		 $http.post('/addRole',role).then(function successCallback(response) {
			 init();
			 $("#addRoleModal").modal("hide");
			}, function errorCallback(response) {
				console.log("error");
			});
		
	}
});

