<!DOCTYPE html>
<html lang="en">
	<head>
		<title>EOP Admin</title>
		<#include "common/basecssjs.ftl"/>
		
		<script type="text/javascript">
			function resizeBody(){
				var ch = document.documentElement.clientHeight;
				ch = ch - $("#navbar").height() -  $("#footer").height()  ;
				$("#main-content").height(ch+"px");
				$("#content").css("height",$("#main-content").height());
				$("#content").css("width",$("#main-content").width()); 
				document.getElementById("content").style.visibility = "visible";
			} 
			
			function gotoPage(url) {
				document.getElementById("content").src=url;
			}

		</script>
	</head>

	<body class="no-skin" id="body" onload="resizeBody();" style="overflow-y: hidden" scroll="no">
		<!-- navbar start -->
			<#include "common/navbar.ftl"/>
		<!-- navbar end -->
		
		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar responsive ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>

				<ul class="nav nav-list">
					<li class="active">
						<a href="javascript:gotoPage('${host}/index.do?method=demo')">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> Dashboard </span>
						</a>

						<b class="arrow"></b>
					</li>
					
					<li>
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-file-o"></i>
							<span class="menu-text">
								Eop premission
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>
						<b class="arrow"></b>
						
						<ul class="submenu">
							<li class="">
								<a href="javascript:gotoPage('${host}/permission/appConfig.do')">
									<i class="menu-icon fa fa-caret-right"></i>
									App Config
								</a>

								<b class="arrow"></b>
							</li>
						</ul>
						
						<ul class="submenu">
							<li class="">
								<a href="javascript:gotoPage('${host}/app')">
									<i class="menu-icon fa fa-caret-right"></i>
									Service Config
								</a>

								<b class="arrow"></b>
							</li>
						</ul>
						
						<ul class="submenu">
							<li class="">
								<a href="javascript:gotoPage('${host}/app')">
									<i class="menu-icon fa fa-caret-right"></i>
									Service Limit
								</a>

								<b class="arrow"></b>
							</li>
						</ul>
						
					</li>
			
				</ul><!-- /.nav-list -->

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
			
			<!-- main content -->
			<div class="main-content" id="main-content">
				<iframe frameborder="0"  id="content" name="content" style="border: 0;overflow-x: hidden;visibility: hidden" src="${host}/index.do?method=demo" scrolling="auto" ></iframe>
			</div><!-- /.main-content -->
			<!-- main content -->


			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- footerjs start -->
		<#include "common/footerjs.ftl"/>
		<!-- footerjs end -->
	</body>
</html>
