<header id="header-top"  role="banner" > 
 <div class="navbar navbar-inverse navbar-fixed-top"  id='header-navbar-top'> 
     <div class="full-container"  role="navigation" >
	  <ul class="navbar-menu pull-left hide" > 
	   <li class="dorpdown" > 
	    <div class="dt cw-icon ui-areamini-text-wrap" >
	     <span class="ui-areamini-text" data-id="15" style="padding: 5px 10px;" title="浙江" id="city_2">
	     <i class="fa fa-map-marker"></i> 浙江</span> 
	     <i class="fa fa-caret-down"></i>
	    </div> 
	   </li> 
	  </ul>
	  <ul class="navbar-menu pull-left" > 
	  	  <li class="dorpdown" > 
		  <p class="sn-back-home"><i class="fa fa-home"></i><a href="${contextPath}/"><@spring.message  code="label.system.index" /></a></p>
		  </li>
		   <li class="dorpdown hide" > 
			  <p id="login-info" class="sn-login-info" style="padding-left:150px;" >
				  个人中心
			  </p>
		  </li> 
	  </ul>
       <ul class="navbar-menu "  >
       	  <li class="sn-mybrand hide"><i class="fa fa-leaf"></i> 
       	 	<a target="_top" id="#" class="sn-mybrand-link" href="javascript:void(0);"></a> 
       	 </li>
       	 <li class="sn-separator"></li>
      	 <li class="sn-mobile hide"> <i class="fa fa-mobile"></i> <a title="" id="sn-mobile-link" target="_top" class="sn-mobile-link" href="javascript:void(0);"><@spring.message  code="label.system.client.phone" /></a> 
		     <div class="sn-qrcode hide" > 
		      <div class="sn-qrcode-content"></div> 
		      <p>扫一扫，定制我的App！</p> 
		      <b></b> 
		     </div>
     	</li>
     	
     	<li class="sn-sitemap">

          </li>
     	 <li class="sn-sitemap">
     	 	<a class="header-logo-invertocat" href="${contextPath}/userinfo.jhtml" aria-label="Homepage" >
     			个人中心
    		</a>  
       	
          </li>
           <li class="sn-sitemap">
     	 	   <a class="sn-login" href="${contextPath}/logout.jhtml" target="_top"><@spring.message  code="label.default.logout" /></a>
       		    <i class="fa fa-caret-down"></i>    	
          </li>
       </ul>
  
     </div> 
 </div>
</header>