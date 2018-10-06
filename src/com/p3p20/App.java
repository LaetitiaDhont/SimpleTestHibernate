package com.p3p20;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.p3p20.dao.UserDao;
import com.p3p20.model.User;
import com.p3p20.util.HibernateUtil;

public class App {

	/*
	 * une méthode addNewUser() prenant comme arguments un objet de type UserDao, un
	 * objet de type User et cinq objets de type String qui vont permettre d’ajouter
	 * les informations concernant l’utilisateur à la base de données la méthode
	 * main
	 * 
	 */

	public static void addNewUser(UserDao dao, User user, String firstName, String lastName, String year, String month,
			String date, String email) {

		// Add new user
		// On crée une instance de type User nommée user.

		user = new User();

		/*
		 * On donne à l’attribut firstName de l’instance user la valeur de l’argument
		 * firstName de la méthode. On donne à l’attribut lastName de l’instance user la
		 * valeur de l’argument lastName de la méthode.
		 */

		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			/*
			 * On donne à l’attribut dob de l’instance user la date recomposée à partir des
			 * arguments year, month et date de la méthode. On donne à l’attribut email de
			 * l’instance user la valeur de l’argument email de la méthode. On appelle la
			 * méthode addUser de la classe UserDao avec pour argument l’instance user. Elle
			 * permet d’ajouter l’utilisateur à la base de données.
			 */

			/*
			 * La méthode parse(), qui est une méthode de la classe SimpleDateFormat, permet
			 * de convertir les attributs de type String en date de type Date.
			 * 
			 */
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse("" + year + "-" + month + "-" + date + "");
			user.setDob(dob);

			/*
			 * La méthode parse() peut lever une exception qui sera prise en charge par le
			 * try/catch (ParseException). Un message d’erreur lié à cette erreur
			 * s’affichera.
			 * 
			 */

		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEmail("dwp20p3@simplon.com");
		dao.addUser(user);

		/*
		 * On affiche un message sur la console pour confirmer la réussite de
		 * l’opération précédente.
		 */

		System.out.println("User is added successfully into DB");
	}

	public static void main(String[] args) {

		/*
		 * Dans la méthode main, on crée une variable sf de type SessionFactory qui
		 * prend pour valeur une instance de sessionFactory. Elle va permettre de créer
		 * des instances de sessions.
		 */

		SessionFactory sf = HibernateUtil.getSessionFactory();

		/*
		 * On crée une variable session de type Session qui prend pour valeur une
		 * session en appelant la méthode openSession() sur la sessionFactory sf.
		 * 
		 */

		Session session = sf.openSession();

		/*
		 * On appelle la méthode beginTransaction() sur la session pour pouvoir envoyer
		 * un groupe de requêtes vers la base de données.
		 */

		session.beginTransaction();

		/*
		 * On crée une instance de la classe UserDao. On crée trois nouveaux
		 * utilisateurs avec la méthode addNewUser() en lui passant les arguments de la
		 * méthode.
		 */

		UserDao dao = new UserDao();

		// Add new user
		User user = new User();
		App.addNewUser(dao, user, "Athanasia", "Pierre", "2018", "06", "04", "dwp20p3@simplon.com");
		User user1 = new User();
		App.addNewUser(dao, user1, "Pierre", "Athanasia", "2018", "06", "08", "dwp20p3@simplon.co");
		User user2 = new User();
		App.addNewUser(dao, user2, "Pierre", "PierreKlk", "2018", "08", "09", "dwp20pfccv3@simplon.com");

		// Update user : dwp20p3 avec le bon mail
		/*
		 * Ensuite, on donne aux attributs email, userid, firstName, lastName et dob de
		 * l’instance user les valeurs des arguments correspondants.
		 */

		user.setEmail("dwp20p3@simplon.co");
		user.setUserid(1);
		user.setFirstName("Athanasia");
		user.setLastName("Pierre");

		// dob = date of birth

		Date dob;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-04");
			user.setDob(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * On met à jour l’email de l’utilisateur en appelant la méthode updateUser de
		 * l’objet dao en passant l’argument user en argument.
		 */

		dao.updateUser(user);

		// On affiche un message sur la console pour confirmer la réussite de l’update.

		System.out.println("User is updated successfully");

		/*
		 * On fait appel à la méthode deleteUser() pour supprimer le troisième
		 * utilisateur qui correspond à la valeur 3 de l’attribut userid.
		 */

		// Delete user by id

		dao.deleteUser(3);

		/*
		 * La méthode toString sera appelée dans la méthode main de la classe
		 * application lorsque l’on va afficher la liste des utilisateurs ou un
		 * utilisateur à partir de son ID. En effet, lorsque l’on imprime un objet (iter
		 * est un objet de type User et la méthode getUserById() retourne un objet de
		 * type User), la méthode System.out.println() fait appel à la méthode
		 * toString() de la classe de cet objet.
		 * 
		 */

		/*
		 * On fait une boucle for each pour parcourir l’arraylist user avec notre
		 * méthode getAllUsers();
		 */

		// Get all users
		
		for (User iter : dao.getAllUsers()) {
			System.out.println(iter);
		}

		/*
		 * for (int i = 0; i < dao.getAllUsers().size(); i++) {
		 * System.out.println(dao.getAllUsers().get(i)); }
		 */

		// Get user by id

		System.out.println(dao.getUserById(2));

		// commit & close
		session.getTransaction().commit();
		session.save(user);
		session.close();

	}

}
