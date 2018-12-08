<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>填写订单</title>
  <#include "/includes/head.ftl" />
<style>
	.tab-pane{
		padding-top:30px;
	}
	.panel-heading {
		padding: 5px 13px;
	}
	.panel-heading  h4{
		border-left: 2px solid #f35d5d;
		padding-left:15px;
		margin-left:-13px;
	}
  #order_process span.active{
  	  background: #fda6a3;
	  color: #fff;
  }
   #order_process{
   	width: 100%  !important;
   }
  #order_process span{
  	width: 25%  !important;
  	border:1px solid transparent;
  	border-color: #ddd;
  }
  .container , .form-group{
  	font-size: 12px;
  	margin-bottom: 0px;
  }
  .form-group .radio-inline{
  	padding-top: 0px;
  }
  .form-group .radio span {
  	padding: 10px 10px;
  }
  .form-group .radio{
  	margin-bottom: 10px;
  }
</style>
<#assign template="project"/>
</head>
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "index-toolbar.ftl" />
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "index-header.ftl" />
 <div class="container" style="margin-top: 35px;">
	  <div class="row">
		<div class="panel panel-default">
			<div class="panel-heading" id="doc-nav-top" ><h4>${result.periodicalName!''}</h4></div>
		  <div class="panel-body">
		 	 <!---项目右侧begin--->
			<div class="col-md-12  col-xs-12">
				<form class="form-horizontal" id="J_OrderForm"  action="${contextPath}/order/subscribe" method="post" >
				  <div class="form-group">
				    <div class="col-sm-10 col-xs-10">
				     <input type="hidden" name="prodId" value="${result.id}">
				     <input type="hidden" name="prodTotalQuantity" value="1">
				     <input type="hidden" name="prodTotalPrice" value="${result.rewardPrice}">
				      <p class="form-control-static"><code>${result.rewardPrice}</code></p>
				    </div>
				  </div>
                    <div class="media">
                        <div class="media-left">
                            <a href="${contextPath}/doc/${result.id}.jhtml" >
                                <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="/${imgPath}/${result.coverImgUrl!''}" data-holder-rendered="true" style="width: 180px;">
                            </a>
                        </div>
                        <div class="media-body">

						 </div>
                    </div>


				  <div class="form-group hide">
				    <label class="col-sm-2 col-xs-2  control-label text-right">配送费用：</label>
				    <div class="col-sm-10 col-xs-10">
				      <p class="form-control-static">无运费</p>
				    </div>
				  </div>
				  <!----发票信息begin----->
				   <div class="form-group hide">
				    <label for="invoiceFlag" class="col-sm-2 col-xs-2  control-label text-right">发票：</label>
				    <div class="col-sm-10 col-xs-10">
				      <div class="radio" id="invoiceFlag" >
					    <label class="radio-inline">
					      <input type="radio" name="invoiceFlag" checked="checked" value="0" > 不需要发票
					    </label>
					     <label class="radio-inline">
					      <input type="radio" name="invoiceFlag" value="1"> 需要发票
					    </label>
					    <span class="hide" id="_invoiceTitle_div">
					    	<span style='color: #f35d5d;' >*</span> 发票抬头    <input name="invoiceTitle" id="_invoiceTitle" type="text" maxlength="30" class="inp145" value="个人"/>
					    </span>
					  </div>
				    </div>
				  </div>
				    <!----发票信息end----->
				</form>
			    	<div class="text-center" >
			    	<botton type="button"  data-loading-text="订单提交中..."   class="btn btn-danger btn-lg" id="sub_order_btn" >立即支付</botton>
			    	</div>

			</div>
			<!---项目右侧end--->
		  </div>
		</div>
	  </div>
    </div>
   	<!------footer信息 begin----->
  	<#include "/includes/footer.ftl" />
  	<#include "/includes/footer-js.ftl" />
  	<!------footer信息 end----->
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->
 <script>
  	seajs.use("js/order/order_investment");
</script>
 </body>
</html>