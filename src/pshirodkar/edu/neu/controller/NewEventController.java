package pshirodkar.edu.neu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pshirodkar.edu.neu.constants.ReferenceConstants;
import pshirodkar.edu.neu.model.EventBean;
import pshirodkar.edu.neu.service.GoogleCalendarEventService;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.util.ServiceException;

/**
 * The purpose of this servlet is to create an event in the calendar.
 * 
 * @author Prasad
 * 
 */
@Controller
public class NewEventController extends MainController {

	@Autowired
	private GoogleCalendarEventService googleCalendarEventService;
	
	public void setGoogleCalendarEventService(
			GoogleCalendarEventService googleCalendarEventService) {
		this.googleCalendarEventService = googleCalendarEventService;
	}
	
	@RequestMapping(value = "/NewEvent", method = RequestMethod.POST)
	public String createNewEvent(HttpServletRequest request) {

		EventBean eventBean = new EventBean();
		eventBean.setTitle(request.getParameter("eventTitle"));
		eventBean.setDateFrom(request.getParameter("dateFrom"));
		eventBean.setDateFromTime(request.getParameter("dateFromTime"));
		eventBean.setDateTo(request.getParameter("dateTo"));
		eventBean.setDateToTime(request.getParameter("dateToTime"));
		eventBean.setLocation(request.getParameter("eventLocation"));
		eventBean.setDescription(request.getParameter("eventDescription"));

		CalendarService calendarService = (CalendarService) request
				.getSession().getAttribute(
						ReferenceConstants.CALENDAR_SERVICE_INFO);

		CalendarEntry calendarEntry = (CalendarEntry) request.getSession()
				.getAttribute(ReferenceConstants.CALENDAR_SELECTED);
		
		try {
			googleCalendarEventService.addEventInCalendar(calendarService, calendarEntry.getId()
					.substring(calendarEntry.getId().lastIndexOf("/") + 1),
					eventBean);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return "forward:CalendarDetails.html";
	}
}