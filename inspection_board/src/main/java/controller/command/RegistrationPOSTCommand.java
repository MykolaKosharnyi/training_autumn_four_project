package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import model.dao.jdbc.JDBCDaoFactory;
import model.entity.Enrollee;

public class RegistrationPOSTCommand implements Command {
	
	static Logger logger = Logger.getLogger(RegistrationPOSTCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String firstName = request.getParameter(ParameterContants.PARAM_FIRSTNAME);
		String secondName = request.getParameter(ParameterContants.PARAM_SECONDNAME);
		String email = request.getParameter(ParameterContants.PARAM_EMAIL);
		String phone = request.getParameter(ParameterContants.PARAM_PHONE);
		String password = request.getParameter(ParameterContants.PARAM_PASSWORD);
		String repeatPassword = request.getParameter(ParameterContants.PARAM_REPEAT_PASSWORD);
		
		if(!password.equals(repeatPassword)){
		    request.setAttribute("errorMessage", "Password and repeadPassword are different!!!");
			
		    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
		}
		
		
		Enrollee enrollee = new Enrollee();
		enrollee.setFirstName(firstName);
		enrollee.setSecondName(secondName);
		enrollee.setEmail(email);
		enrollee.setPhone(phone);
		enrollee.setPassword(password);

        //save in database  
		long idUser = new JDBCDaoFactory().createEnrolleeDao().create(enrollee);
		
		//log in user
		HttpSession session = request.getSession();
		session.setAttribute("userName", email);
	
		session.setAttribute("userId", idUser);
		// setting session to expiry in 30 mins
		session.setMaxInactiveInterval(30 * 60);

		Cookie userName = new Cookie("user", email);
		userName.setMaxAge(30 * 60);
		response.addCookie(userName);
		
		return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ENROLLEE_HOME_PAGE);
	}

}
