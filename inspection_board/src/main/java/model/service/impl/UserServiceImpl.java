package model.service.impl;

import java.util.List;
import java.util.Optional;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;
import model.service.UserService;

public class UserServiceImpl implements UserService {
	private DaoFactory daoFactory = DaoFactory.getInstance();

	private UserServiceImpl() {
	}

	private static class Holder {
		static final UserServiceImpl INSTANCE = new UserServiceImpl();
	}

	public static UserServiceImpl getInstance() {
		return Holder.INSTANCE;
	}

	public long create(User enrollee) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			long result = dao.create(enrollee);
			connection.commit();
			return result;
		}
	}

	public Optional<User> find(long id) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			return dao.find(id);
		}
	}

	public List<User> findAll() {
		try (DaoConnection connection = daoFactory.getConnection()) {
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			return dao.findAll();
		}
	}

	public void update(User enrollee) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			dao.update(enrollee);
			connection.commit();
		}
	}

	public void delete(long id) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			dao.delete(id);
			connection.commit();
		}
	}

	public Optional<User> findByEmail(String email) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			return dao.findByEmail(email);
		}
	}

	public boolean checkLogin(String email, String password) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			UserDao dao = daoFactory.createEnrolleeDao(connection);
			return dao.checkLogin(email, password);
		}
	}

}
