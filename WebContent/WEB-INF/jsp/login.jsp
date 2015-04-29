<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Login - Social Calendar MVC Application with Google
				Calendar and Google Maps</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />		
	</head>
	<body>
		<div class="loginDiv" style="background-color:#eBeefa;">
			<h2><spring:message code="label.assignmentDescription" /></h2>
			<table align="center">
				<tr>
					<td colspan="2" style="text-align:center;">
						<spring:message code="label.signin" /><br>
					</td>
				</tr>
				<tr>
					<td>
						<img alt="Google" src="https://www.google.com/accounts/google_transparent.gif">
					</td>
					<td style="font-weight:bold; font-size: 19;">
						<spring:message code="label.account" />
					</td>
				</tr>
			</table>
			
			<form:form method="post" action="login.html">
				<table class="center" border="0">
					<tr>
						<td>
							<form:label for="userName" path="userName">
								<spring:message code="label.userName" />
							</form:label>							
						</td>
						<td>
							<form:input path="userName" maxlength="40" size="30" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							<form:errors path="userName" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<form:label for="password" path="password">
								<spring:message code="label.password" />
							</form:label>
						</td>
						<td>
							<form:password path="password" maxlength="30" size="30" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							<form:errors path="password" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="center">
							<input type="submit" value='<spring:message code="button.login" />'/>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>