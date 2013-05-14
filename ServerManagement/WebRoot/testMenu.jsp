<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<link rel="stylesheet" media="screen" type="text/css"
	href="css/testMenu.css" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'testMenu.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<script language="JavaScript">
		startList = function() {
			if (document.all && document.getElementById) {
				navRoot = document.getElementById("nav");
				for (i = 0; i < navRoot.childNodes.length; i++) {
					node = navRoot.childNodes[i];
					if (node.nodeName == "LI") {
						node.onmouseover = function() {
							this.className += " over";
						}
						node.onmouseout = function() {
							this.className = this.className
									.replace(" over", "");
						}
					}
				}
			}
		}
		window.onload = startList;
	</script>
	<ul id="nav">
		<li><a href="#">Home</a>
		</li>
		<li><a href="#">About</a>
			<ul>
				<li><a href="#">History</a>
				</li>
				<li><a href="#">Team</a>
				</li>
				<li><a href="#">Offices</a>
				</li>
			</ul></li>
		<li><a href="#">Services</a>
			<ul>
				<li><a href="#">Web Design</a>
				</li>
				<li><a href="#">Internet Marketing</a>
				</li>
				<li><a href="#">Hosting</a>
				</li>
				<li><a href="#">Domain Names</a>
				</li>
				<li><a href="#">Broadband</a>
				</li>
			</ul></li>
		<li><a href="#">Contact Us</a>
			<ul>
				<li><a href="#">United Kingdom</a>
				</li>
				<li><a href="#">France</a>
				</li>
				<li><a href="#">USA</a>
				</li>
				<li><a href="#">Australia</a>
				</li>
			</ul></li>

	</ul>

</body>
</html>
