
//管理员页面
var app = angular.module('manergerApp', []);


app.controller('userCtrl', function($rootScope,$scope, $http) {

	 ////权限控制
	$scope.roles={};
	function getUserDetals()
	{
		 $http.get('/getUserDetals').then(function successCallback(response) {
			 $scope.roles=response.data.roles;
			 var roles=$scope.roles;
			 var b=isContainAction(roles,'manerger');
			 if(b==false)
			 {
				 //没有权限
				 alert("你没有权限，请及时申请");
				 window.location="index.html";
			 }			 
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
	getUserDetals();
	//判断是否包含某种权限
	var isContainAction=function(roles,name)
	{
		for(var i=0;i<roles.length;i++)
		{
			var role=roles[i];
			var actions=role.actions;
			for(var j=0;j<actions.length;j++)
			{
			      var action = actions[j];
			      if(action==name)
			    	  {
			    	  return true;
			    	  }
			}
		}
		return false;
	}
	
    $scope.userData="";
    $scope.userList="";
    $scope.warm="";
    $scope.pageNum=1;
    $scope.pageSize=6;
    $scope.pageList=[];
    $scope.serchName="";
    $rootScope.curentUser={};
    
    $scope.showRoleModel=function(user)
	{
    	 $rootScope.curentUser=user;
		 $("#userRoleModal").modal("show");
		 $rootScope.$broadcast('nameChanged', user);
	}
    
   
	
	
	
	function getPageList(pages)
	{
		$scope.pageList=[];
		for(var i=0;i<pages;i++)
		{
			$scope.pageList.push(i+1);
		}
	}
	
	function getUsers(pageNum)
	{
		
		$scope.pageNum=pageNum;
		if($scope.serchName==""){
		$http.get('/getUsers?pageNum='+ $scope.pageNum+'&pageSize='+$scope.pageSize).then(function successCallback(response) {
			 $scope.warm="";
			$scope.userData = response.data;
			$scope.userList=$scope.userData.data;
			getPageList($scope.userData.pageCount);
		 }, function errorCallback(response) {
			 $scope.warm="获取信息失败！！";
	     });
		}
		else
		{
			$http.get('/getUserByName?pageNum='+ $scope.pageNum+'&pageSize='+$scope.pageSize+"&name="+$scope.serchName).then(function successCallback(response) {
				 $scope.warm="";
				$scope.userData = response.data;
				$scope.userList=$scope.userData.data;
				getPageList($scope.userData.pageCount);
			 }, function errorCallback(response) {
				 $scope.warm="获取信息失败！！";
		     });
		}
	}
	

	
	getUsers(1);
	
	$scope.selectPage=function(page)
	{
		getUsers(page);
	}
	$scope.nextPage=function()
	{  
		if($scope.pageNum>=$scope.pageCount)
		{$scope.pageNum=$scope.pageCount}
		else
			{
			$scope.pageNum+=1;
			}
		getUsers($scope.pageNum);
	}
	$scope.prePage=function()
	{  
		if($scope.pageNum<=1)
		{$scope.pageNum=1}
		else
			{
			$scope.pageNum-=1;
			}
		getUsers($scope.pageNum);
	}
	
});

app.controller('addCtrl', function($scope, $http) {
	$scope.sexs=[{name:'男',value:1},{name:'女',value:0}];
	$scope.user={};
	
	
	
	$scope.onsubmit=function()
	{
		//console.log($scope.user);
		//参数判断
		var user=$scope.user;
		if(user.username==null||user.password==null||user.sex==null)
		{
			alert("不能为空");
			return;
		}
		if(isNaN(user.age)||user.age>200||user.age<0)
		{
			alert("年龄不正确");
			return;
		}
		
		$http.post("/addUser",$scope.user).then(function successCallback(response) {
		          console.log(response.data);
		          $("#userModal").modal("hide");
		          $scope.user={};
			 }, function errorCallback(response) {
				 console.log("error");
		      });
	}
	
	
	
});

app.filter("change",function()
	{
	     return function(input,parm)
	      {
	    	 var out;
             if(parm=='sex')
             {
            	 if(input==1)
                 {
            		 out='男';
                 }
            	 else
            	 {
            		 out='女';
            	 }
            	 return out;
             }
             
             if(parm=='state')
             {
            	 if(input==1)
                 {
            		 out='可用';
                 }
            	 else
            	 {
            		 out='不可用';
            	 }
            	 return out;
             }
	    	 
	    	 return input;
	    	 
	      }
	
	}	
);

////////////////role 模块//

app.controller('roleController',function($scope, $http) {
	
	$scope.roleData={};
	
	function getRoleData()
	{
		$http.get('/getRoles').then(function successCallback(response) {
	          console.log(response.data);
	          $scope.roleData=response.data;
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
    
    
	getRoleData();
	
	
});


////
app.controller('userRoleCtrl',function($rootScope,$scope, $http) {
	
	$scope.user;
	$scope.roles;
	$scope.selected = [] ; 
	
	$scope.data={};
	$scope.onsubmit=function()
	{
		 $scope.data.ids=$scope.selected;
		 $scope.data.user_id=$scope.user.user_id;
		 $http.post('/updateUserRole',$scope.data).then(function successCallback(response) {
			 console.log("ok");
			 alert("修改成功");
			 $("#userRoleModal").modal("hide");
		 }, function errorCallback(response) {
			 console.log("error");
			 alert("修改失败");
	     });
		 
	}
	
	// 监听事件
    $rootScope.$on('nameChanged', function(event, data){
    	$scope.selected = [] ;
    	$scope.user=data;
    	getRoleData($scope.user.user_id);
    	getRoles();
    })
    function  initSelect(myroles)
	{
		for(var i=0;i<myroles.length;i++)
		{
//			 console.log(myroles[i].role_id);
			 $scope.selected.push(myroles[i].role_id);
		}
	}
    
    $scope.isChecked = function(id){  
        return $scope.selected.indexOf(id) >= 0 ;  
    };
    
    function getRoleData(id)
	{
    	
		 $http.get('/getRolesByUser?userid='+id).then(function successCallback(response) {
	          console.log("data",response.data);
	          $scope.myroles=response.data;
	          initSelect($scope.myroles);
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
    
    function getRoles()
	{
    	
		 $http.get('/getRoles').then(function successCallback(response) {
	          $scope.roles=response.data;
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
    
    $scope.updateSelection = function($event,id){  
        var checkbox = $event.target ;  
        var checked = checkbox.checked ;  
        if(checked){  
            $scope.selected.push(id) ;  
        }else{  
            var idx = $scope.selected.indexOf(id) ;  
            $scope.selected.splice(idx,1) ;  
        }  
//        console.log($scope.selected);
    }
	
	
});


////////
app.controller('goodsController', function($scope, $http) {
	
	$scope.sorts={};
	$scope.kinds={};
	$scope.specs={};
	function getSorts()
	{
		 $http.get('/getSort').then(function successCallback(response) {
//	          console.log("data",response.data);
	          $scope.sorts=response.data;
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
	
	function getKinds(sortid)
	{
		 $http.get('/getKind?sortid='+sortid).then(function successCallback(response) {
//	          console.log("data",response.data);
			 $scope.kinds=response.data;
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
	function getSpecs(kindid)
	{
		 $http.get('/getSpec?kindid='+kindid).then(function successCallback(response) {
//	          console.log("data",response.data);
			 $scope.specs=response.data;
		 }, function errorCallback(response) {
			 console.log("error");
	     });
	}
	

	$scope.getKind=function(sort_id)
	{
		$scope.specs={};
		getKinds(sort_id);
		
	}
	
	$scope.getSpec=function(kindid)
	{
		getSpecs(kindid);
	}
	getSorts();
	
	
}
);
