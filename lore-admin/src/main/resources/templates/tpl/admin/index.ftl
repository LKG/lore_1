<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>控制台</title>
  <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
<link rel="stylesheet" type="text/css" href="${contextPath}/css/navbar-left-menu.css?v=${ver!'1'}" />
  <#include "/includes/laypage-css.ftl" />
   <style>
   .main-panel .panel-heading{
      padding: 0px 15px;
   }
   .main-panel{
      margin-top: -20px;
   }
  .table td{white-space:nowrap;overflow:hidden; text-overflow:ellipsis; } 
  .table  {table-layout:fixed;}  
  .table th{vertical-align:middle !important;text-align: center; font-size:14px;}
  .Wdate{
	  height: 32px !important;
	  padding: 3px 1px !important; 
	  border: 1px solid #ccc !important; 
	}
  .form-search select{
   	  padding: 6px 3px  !important; 
   }
	.form-inline .form-group,.form-inline .form-control{
		width: 100px;
		display: inline;
	}
	@media (max-width: 900px){
		.panel-heading{
			padding: 10px 0px !important; 
		}
	}
	.laypage_main{
		clear: none !important;
	}
  </style>
     <#assign template="index"/>
     <#assign submenu="index"/> 
</head> 
<body class="page-header-fixed">
    <#include "/admin/navbar-header.ftl" />
    <div class="container-fluid main-container" >
     <div  class="main-container-inner">
     	  <#include "/admin/navbar-left-menu.ftl" />
     	  <div class="main-content"> 
     	  	<!-- .breadcrumb  begin -->
     	 	 <div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
				<li><i class="fa fa-dashboard"></i>控制台</li>
				</ul><!-- .breadcrumb -->
			 </div>
			<!-- .breadcrumb  end -->
			<div class="container-fluid" >
			  <div class="row" >
				  <div class="col-sm-6 col-md-4 col-lg-4 ">
				     <#include "/admin/panel.ftl" />
				  </div>
				  <div class="col-sm-6 col-md-4 col-lg-8 " id="container">
				    <div class="panel panel-primary">
				        <div class="panel-heading">
					    </div>
					    <div class="panel-body" >
       						<!------->
       						<div class="btn-group">
							  <a class="btn btn-default" href="javascript:void(0);" id="quick-upload-price" >
							   <span class="fa-stack fa-lg">
								  <i class="fa fa-square fa-stack-2x"></i>
								  <i class="fa fa-cloud-upload fa-stack-1x fa-inverse"></i>
								</span>
							  </a>
							  <a class="btn btn-default" href="javascript:void(0);" id="quick-upload-img">
								<span class="fa-stack fa-lg">
								  <i class="fa fa-circle fa-stack-2x"></i>
								  <i class="fa fa-book fa-stack-1x fa-inverse"></i>
								</span>
							  </a>
							  	  <a class="btn btn-default" href="${contextPath}/admin/periodical.jhtml">
								<span class="fa-stack fa-lg">
								  <i class="fa fa-circle fa-stack-2x"></i>
								  <i class="fa fa-flag fa-stack-1x fa-inverse"></i>
								</span>
							  </a>
							   <a class="btn btn-default" href="${contextPath}/admin/material/package.jhtml">
								<span class="fa-stack fa-lg">
								  <i class="fa fa-circle fa-stack-2x"></i>
								  <i class="fa fa-archive fa-stack-1x fa-inverse"></i>
								</span>
							  </a>
							     <a class="btn btn-default" href="${contextPath}/admin/material.jhtml">
								<span class="fa-stack fa-lg">
								  <i class="fa fa-circle fa-stack-2x"></i>
								  <i class="fa fa-flag fa-stack-1x fa-inverse"></i>
								</span>
							  </a>
							       <a class="btn btn-default" href="${contextPath}/admin/material/base.jhtml">
								<span class="fa-stack fa-lg">
								  <i class="fa fa-circle fa-stack-2x"></i>
								  <i class="fa fa-terminal fa-stack-1x fa-inverse"></i>
								</span>
							  </a>
							</div>
       							
								
       						<!------>
      					</div>
				     </div>
				     <#include "/admin/panel4.ftl" />
				  </div>
			  </div>
			</div>
     	  </div>
     </div>
    </div>
   	<!------footer信息 begin----->
  	<#include "/includes/footer-js.ftl" />
  	<!------footer信息 end----->
  	<#include "/includes/datePicker.ftl" />
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->
 <script>
  	seajs.use(["js/left-menu.js?v=${ver!'1'}","js/admin/index.js?v=${ver!'1'}"]);
</script>
 </body>
</html>