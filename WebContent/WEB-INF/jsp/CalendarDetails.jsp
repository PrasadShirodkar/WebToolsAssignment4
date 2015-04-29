<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<table align="center">
	<tr>
		<td colspan="3" align="center">
			<spring:message code="label.selectedCalendar" /> <b><c:out value="${sessionScope.calendarSelected.title.plainText}" /></b>
		</td>
	</tr>
	<tr>
		<td>
			<img src="<c:out value="${sessionScope.GOOGLE_MAPS}" />" />
		</td>
		<td width="4%"></td>
		<td>
			<table>
				<tr>
					<td>
						<table cellspacing="2">
							<tr>
								<th colspan="5">
									<spring:message code="label.calendarEvents" />
								</th>	
							</tr>
							<tr>
								<td>
									<b><spring:message code="label.marker" /></b>
								</td>
								<td>
									<b><spring:message code="label.eventTitle" /></b>
								</td>
								<td>
									<b><spring:message code="label.startDate" /> &amp; <spring:message code="label.time" /></b>
								</td>
								<td>
									<b><spring:message code="label.endDate" /> &amp; <spring:message code="label.time" /></b>
								</td>
								<td>
									<b><spring:message code="label.location" /></b>
								</td>
							</tr>
							<c:forEach var="event" items="${sessionScope.events}" varStatus="status">
							<tr>
								<td>
									<c:out value="${event.markerLabel}" />	
								</td>
								<td>
									<c:out value="${event.title}" />
								</td>
								<td>
									<c:out value="${event.startDateLabel}" />
								</td>
								<td>
									<c:out value="${event.endDateLabel}" />
								</td>
								<td>
									<c:out value="${event.location}" />
								</td>
							</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<form action="<%= request.getContextPath()%>/NewEvent.html" method="post">
							<table style="padding-top:5px;">
								<tr>
									<td>
										<spring:message code="label.eventTitle" />
									</td>
									<td colspan="5">
										<input type="text" name="eventTitle" size="60" />
									</td>
								</tr>
								<tr>
									<td>
										<spring:message code="label.eventDuration" />
									</td>
									<td>
										<input type="text" name="dateFrom" size="10" />
									</td>
									<td>
										<input type="text" name="dateFromTime" size="10" />
									</td>
									<td>
										<spring:message code="label.to" />
									</td>
									<td>
										<input type="text" name="dateTo" size="10" />
									</td>
									<td>
										<input type="text" name="dateToTime" size="10" />
									</td>
								</tr>
								<tr>
									<td valign="top">
										<spring:message code="label.eventLocation" />
									</td>
									<td colspan="5">
										<input type="text" name="eventLocation" size="60" />
									</td>
								</tr>
								<tr>
									<td valign="top">
										<spring:message code="label.eventDescription" />
									</td>
									<td colspan="5">
										<textarea rows="7" cols="45" name="eventDescription"></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="6">
										<input type="submit" value="Add Event" />
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>				
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<b><spring:message code="label.relatedImages" /></b>
		</td>
	</tr>
	<c:forEach var="event" items="${sessionScope.events}" varStatus="status">
	<tr style="padding-top: 10px;">
		<td colspan="3">
			<img src="<c:out value="${event.imageUrl}" />" title="<c:out value="${event.location}" />" />
		</td>
	</tr>
	</c:forEach>
</table>