package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

public class LoginCommand implements Command {

	private static final String PARAM_NAME_EMAIL = "email";
	private static final String PARAM_NAME_PASSWORD = "password";
	static Logger logger = Logger.getLogger(LoginCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;

		String login = request.getParameter(PARAM_NAME_EMAIL);
		logger.info("login:" + login);

		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		logger.info("password:" + pass);

		JDBCDaoFactory factory = new JDBCDaoFactory();
		if (factory.createEnrolleeDao().checkLogin(login, pass)) {

			
			logger.info("user is ckecking");
			if ("admin".equals(login)) {

				
				page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
			} else {
				
				Enrollee enrollee = factory.createEnrolleeDao().findByEmail(login);
				String name = enrollee.getFirstName();
				long idUser = enrollee.getId();

				HttpSession session = request.getSession();
				logger.info("user's name is" + name);
				session.setAttribute("userName", name);
				
				logger.info("user's id is" + idUser);
				session.setAttribute("userId", idUser);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);

				Cookie userName = new Cookie("user", login);
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				
				try {
					page = "/home"/*ConfigurationManager.getInstance().getProperty(ConfigurationManager.ENROLLEE_HOME_PAGE)*/;	
				} catch(Exception ex) {
					request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
					page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
					logger.error(MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
				}
			}
		
		} else {

			try {
				request.setAttribute("errorMessage", "E-mail or password is incorect!");
				page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);	
			} catch(Exception ex) {
				request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
				page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
				logger.error(MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
			}
		}
		

		return page;
	}

}
