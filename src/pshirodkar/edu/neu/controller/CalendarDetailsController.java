package pshirodkar.edu.neu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pshirodkar.edu.neu.constants.ReferenceConstants;
import pshirodkar.edu.neu.model.EventBean;
import pshirodkar.edu.neu.model.LoginBean;
import pshirodkar.edu.neu.service.GoogleCalendarEventService;
import pshirodkar.edu.neu.service.GoogleCalendarService;
import pshirodkar.edu.neu.service.GoogleMapsService;
import pshirodkar.edu.neu.service.PicasaPhotosService;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;

/**
 * The purpose of this controller is to display the details for the selected
 * calendar.
 * 
 * @author Prasad
 * 
 */
@Controller
public class CalendarDetailsController extends MainController {

	@Autowired
	private GoogleCalendarService googleCalendarService;

	@Autowired
	private GoogleCalendarEventService googleCalendarEventService;

	@Autowired
	private GoogleMapsService googleMapsService;

	@Autowired
	private PicasaPhotosService picasaPhotosService;

	public void setGoogleCalendarService(
			GoogleCalendarService googleCalendarService) {
		this.googleCalendarService = googleCalendarService;
	}

	public void setGoogleCalendarEventService(
			GoogleCalendarEventService googleCalendarEventService) {
		this.googleCalendarEventService = googleCalendarEventService;
	}

	public void setGoogleMapsService(GoogleMapsService googleMapsService) {
		this.googleMapsService = googleMapsService;
	}

	public void setPicasaPhotosService(PicasaPhotosService picasaPhotosService) {
		this.picasaPhotosService = picasaPhotosService;
	}

	@RequestMapping(value = "/CalendarDetails")
	public ModelAndView displayCalendarDetails(
			HttpServletRequest request, HttpSession session) {

		CalendarService calendarService = (CalendarService) session
				.getAttribute(ReferenceConstants.CALENDAR_SERVICE_INFO);
		
		String calendarIndex = request.getParameter("calendarIndex");
		
		try {
			CalendarFeed calendarFeed = googleCalendarService
					.fetchOwnCalendars(calendarService);

			CalendarEntry calendarEntry = null;
			if (calendarIndex != null) {
				
				int index = Integer.parseInt(calendarIndex);
				calendarEntry = calendarFeed.getEntries().get(
						index - 1);
				session.setAttribute(ReferenceConstants.CALENDAR_SELECTED,
						calendarEntry);
			} else if (session
					.getAttribute(ReferenceConstants.CALENDAR_SELECTED) != null) {
				calendarEntry = (CalendarEntry) session
						.getAttribute(ReferenceConstants.CALENDAR_SELECTED);
			} else {
				throw new IllegalArgumentException(
						"Neither Calendar index parameter nor session attribute found");
			}

			LoginBean loginBean = (LoginBean) session
					.getAttribute(ReferenceConstants.USER_INFO);

			// Fetch all the events for the calendar
			List<EventBean> eventList = googleCalendarEventService
					.fetchAllEventsInCalendar(
							calendarService,
							calendarEntry.getId().substring(
									calendarEntry.getId().lastIndexOf("/") + 1),
							loginBean.getUserName());

			session.setAttribute(ReferenceConstants.EVENTS, eventList);

			// Display the Google Maps for the calendar events
			session.setAttribute(ReferenceConstants.GOOGLE_MAPS,
					googleMapsService.constructMapUrl(eventList));

			// Display the photos of the calendar events
			picasaPhotosService.searchPhoto(loginBean.getUserName(),
					loginBean.getPassword(), eventList);

			return new ModelAndView("calendarDetails");

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
	}
}