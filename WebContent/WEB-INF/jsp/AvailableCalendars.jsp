<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div align="center">
	<h1><spring:message code="label.welcome" /> ${USER_INFO.userName}</h1>
	<h2><spring:message code="label.availableCalendars" /></h2>
	<h2><spring:message code="label.scheduleDetails" /></h2>
	
	<table>
		<c:forEach var="calendar" items="${calendars}" varStatus="status">	
		<tr>
			<td>
				<a href="<%= request.getContextPath()%>/CalendarDetails.html?calendarIndex=<c:out value="${status.count}"/>">
					<c:out value="${calendar.title.plainText}" />
				</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>