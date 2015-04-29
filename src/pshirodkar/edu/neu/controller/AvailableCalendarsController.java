package pshirodkar.edu.neu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pshirodkar.edu.neu.constants.ReferenceConstants;
import pshirodkar.edu.neu.service.GoogleCalendarService;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;

/**
 * The purpose of this controller is to retrieve the list of calendars that the
 * authenticated user has owner access to.
 * 
 * @author Prasad
 * 
 */
@Controller
public class AvailableCalendarsController extends MainController {

	@Autowired
	private GoogleCalendarService googleCalendarService;
	
	public void setGoogleCalendarService(
			GoogleCalendarService googleCalendarService) {
		this.googleCalendarService = googleCalendarService;
	}

	@RequestMapping(value = "/AvailableCalendars")
	public ModelAndView displayAvailableCalendars(HttpServletRequest request,
			HttpSession session) {

		CalendarService calendarService = (CalendarService) session
				.getAttribute(ReferenceConstants.CALENDAR_SERVICE_INFO);
		try {
			CalendarFeed calendarFeed = googleCalendarService
					.fetchOwnCalendars(calendarService);

			return new ModelAndView("availableCalendars",
					ReferenceConstants.CALENDARS, calendarFeed.getEntries());

		} catch (ServiceException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}