package myServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;



/*
 * Christian Znidarsic
 * hikeQuoteGenerator Class
 * 
 * The hikeQuoteGenerator class extends HttpServlet. It receives HTTP 
 * requests from the client and uses the GetQuote class to update 
 * the QuoteRequest bean accordingly. It then passes control to main.jsp 
 * to send an HTTP response to the client.
 */

/**
 * Servlet implementation class hikeQuoteGenerator
 */
@WebServlet("/hikeQuoteGenerator")
public class hikeQuoteGenerator extends HttpServlet {
	public static final String QUOTEREQUEST = "quoteRequest";
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext();
		QuoteRequest quoteRequest = (QuoteRequest) session.getAttribute(QUOTEREQUEST);
		if (quoteRequest == null) {
			quoteRequest = new QuoteRequest();
			quoteRequest.setHikeNames(HikeType.getHikeNames());
			quoteRequest.setMaxYear(BookingDay.DEFAULT_MAX_YEAR);
			
			session.setAttribute(QUOTEREQUEST, quoteRequest);
		}
		else {
			// initialize the input variables
			String inputHike = request.getParameter("Hike");
			String inputDuration = request.getParameter("Duration");
			String inputYear = request.getParameter("Year");
			String inputMonth = request.getParameter("Month");
			String inputDay = request.getParameter("Day");
			String inputHikers = request.getParameter("Hikers");
			
			if (inputHike != null && inputDuration != null && inputYear != null && inputMonth != null && inputDay != null && inputHikers != null) {
        		if (!inputHike.matches("^[a-zA-Z\\s-]+$")) {
        			quoteRequest.setOutputMessage("The field \"Hike\" is formatted incorrectly. The Hike name can only contain letters, spaces and \"-\" characters.");
        		}
        		else if (!inputDuration.matches("^\\d{1,2}$")) {
        			quoteRequest.setOutputMessage("The field \"Duration\" is formatted incorrectly. Proper format is: \"##\" or \"#\"");
        		}
        		else if (!inputMonth.matches("^\\d{1,2}$")) {
        			quoteRequest.setOutputMessage("The field \"Month\" is formatted incorrectly. Proper format is: \"##\" or \"#\"");
        		}
        		else if (!inputDay.matches("^\\d{1,2}$")) {
        			quoteRequest.setOutputMessage("The field \"Day\" is formatted incorrectly. Proper format is: \"##\" or \"#\"");
        		}
        		else if (!inputYear.matches("^\\d{4}$")) {
        			quoteRequest.setOutputMessage("The field \"Year\" is formatted incorrectly. Proper format is: \"####\"");
        		}
        		else if (!inputHikers.matches("^\\d+$")) {
        			quoteRequest.setOutputMessage("The field \"Hikers\" is formatted incorrectly. The field \"Hikers\" can only contain numbers.");
        		}
        		else {
        			// update values in QuoteRequest object
        			quoteRequest.setHike(inputHike);
        			quoteRequest.setDuration(inputDuration);
        			quoteRequest.setYear(inputYear);
        			quoteRequest.setMonth(inputMonth);
        			quoteRequest.setDay(inputDay);
        			quoteRequest.setNumHikers(inputHikers);
        			quoteRequest.setOutputMessage(GetQuote.getQuote(Integer.valueOf(inputYear), Integer.valueOf(inputMonth), Integer.valueOf(inputDay), Integer.valueOf(inputDuration), inputHike, Integer.valueOf(inputHikers)));
        		}
			}
			else {
				quoteRequest.setOutputMessage("Some inputs were missing/formatted incorrectly.");
			}
			quoteRequest.setFirstAccess(false);
			
		}
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/main.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
