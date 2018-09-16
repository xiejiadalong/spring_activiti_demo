/**
 * userManerger
 */
app.controller('menuManergerController', function($rootScope,$scope, $http) {
	
	getTree();
	$scope.onAdd=function(){
		var checkdData=$('#tree').data('treeview').getChecked();
		getPermissions();
		console.log(checkdData);
		var lenght=checkdData.length;
		if(checkdData.length==0){
			$scope.status= "1";
			$scope.menu_type= "1";
		    $scope.p_id= "0";
			$scope.level= "1";
			$scope.p_name= "首级菜单";
			$("#addMenuModal").modal("show");
		}else
	    if(lenght>=2){
			alert("只能勾选一个父节点！");
		}else{
			var data=checkdData[0];
			$scope.status= "1";
			$scope.menu_type= "1";
		    $scope.p_id= data.menu_id;
		    var level= parseInt(data.level)+1;
		    if(level>3){
		    	alert("只能添加到三级菜单");
		    	return;
		    }
			$scope.level= level+"";
			$scope.p_name= data.menu_name;
			$("#addMenuModal").modal("show");
			
		}
	}
	
	function getPermissions(){
		var parms = {
		};
		$http.post('/getAllPermissions', parms).then(function successCallback(response) {
			var permissionata=response.data;
			$("#slpermission").html("");
			for(var i=0;i<permissionata.length;i++){
				var permission_id=permissionata[i].permission_id;
				var permissiom_name=permissionata[i].permissiom_name;
				$("#slpermission").append("<option value="+permission_id+">"+permissiom_name+"</option>");
			} 
		}, function errorCallback(response) {
			console.log("error");
		});
	}
	
	$scope.onDelete=function(){
		var checkdData=$('#tree').data('treeview').getChecked();
		var lenght=checkdData.length;
		
		if(checkdData.length==0){
			alert("请选择要删除的菜单！");
		}else if(lenght>=2){
			alert("只能勾选一个菜单！");
		}else{
			 var data=checkdData[0];
			 var parms={};
			 parms.menu_id=data.menu_id;
			 $http.post('/deleteMenu',parms).then(function successCallback(response) {
				 getTree();
			  }, function errorCallback(response) {
					console.log("error");
			 });
		}
	}
	$scope.onCancel=function(){
		 $("#addMenuModal").modal("hide");
	}
	$scope.onOk=function(){
		 var parms={};
		 parms.status= $scope.status;
		 parms.menu_type= $scope.menu_type;
		 parms.p_id= $scope.p_id;
		 parms.url= $scope.url;
		 parms.level= $scope.level;
		 parms.menu_name=$scope.menu_name;
		 parms.permission_id = $('#slpermission').val();
		 console.log(parms);
		 $http.post('/addMenu',parms).then(function successCallback(response) {
			 $("#addMenuModal").modal("hide");
			 getTree();
			}, function errorCallback(response) {
				console.log("error");
			});
	}
	function getTree() {
		var parms={
				"menu_id":"0"
		};
		$http.post('/createTreeMenu',parms).then(function successCallback(response) {
			var treeData = response.data;
			$('#tree').treeview({
				data:treeData,
			    highlightSelected : false,
//			    icon:"glyphicon glyphicon-stop",
			    multiSelect : false,
			    showCheckbox : true,
//			    onNodeSelected:onNodeSelected
			    }).treeview('collapseAll', {// 节点展开
			    silent : true
			    });
		}, function errorCallback(response) {
			console.log("error");
		});
	}
	function onNodeSelected(event, data){

	}
});