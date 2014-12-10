package de.yellow.medienverwaltung.business;

import java.util.List;

import de.yellow.medienverwaltung.database.dao.UserDao;
import de.yellow.medienverwaltung.database.entity.User;

/**
 * Verbindungselement zwischen Api (REST-Service) und Database-Schicht. Kann
 * Daten entweder nur durchreichen, oder in irgendeiner Form verarbeiten.
 * 
 * @author marius
 *
 */
public class UserService {

	private UserDao dao;

	public List<User> getAllUsers() {

		dao = new UserDao();

		List<User> users = dao.getAllUsers();
		// TODO: Do something useful with data
		return users;
	}

	public User getUserById(int id) {
		
		dao = new UserDao();
		
		User user = dao.getUserById(id);
		
		return user;
	}
	
	public User getUserByName(String name) {
		
		dao = new UserDao();
		
		User user = dao.getUserByName(name);
		
		return user;
	}
}