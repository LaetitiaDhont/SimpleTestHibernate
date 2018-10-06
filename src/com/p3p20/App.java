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
	 * une m�thode addNewUser() prenant comme arguments un objet de type UserDao, un
	 * objet de type User et cinq objets de type String qui vont permettre d�ajouter
	 * les informations concernant l�utilisateur � la base de donn�es la m�thode
	 * main
	 * 
	 */

	public static void addNewUser(UserDao dao, User user, String firstName, String lastName, String year, String month,
			String date, String email) {

		// Add new user
		// On cr�e une instance de type User nomm�e user.

		user = new User();

		/*
		 * On donne � l�attribut firstName de l�instance user la valeur de l�argument
		 * firstName de la m�thode. On donne � l�attribut lastName de l�instance user la
		 * valeur de l�argument lastName de la m�thode.
		 */

		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			/*
			 * On donne � l�attribut dob de l�instance user la date recompos�e � partir des
			 * arguments year, month et date de la m�thode. On donne � l�attribut email de
			 * l�instance user la valeur de l�argument email de la m�thode. On appelle la
			 * m�thode addUser de la classe UserDao avec pour argument l�instance user. Elle
			 * permet d�ajouter l�utilisateur � la base de donn�es.
			 */

			/*
			 * La m�thode parse(), qui est une m�thode de la classe SimpleDateFormat, permet
			 * de convertir les attributs de type String en date de type Date.
			 * 
			 */
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse("" + year + "-" + month + "-" + date + "");
			user.setDob(dob);

			/*
			 * La m�thode parse() peut lever une exception qui sera prise en charge par le
			 * try/catch (ParseException). Un message d�erreur li� � cette erreur
			 * s�affichera.
			 * 
			 */

		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEmail("dwp20p3@simplon.com");
		dao.addUser(user);

		/*
		 * On affiche un message sur la console pour confirmer la r�ussite de
		 * l�op�ration pr�c�dente.
		 */

		System.out.println("User is added successfully into DB");
	}

	public static void main(String[] args) {

		/*
		 * Dans la m�thode main, on cr�e une variable sf de type SessionFactory qui
		 * prend pour valeur une instance de sessionFactory. Elle va permettre de cr�er
		 * des instances de sessions.
		 */

		SessionFactory sf = HibernateUtil.getSessionFactory();

		/*
		 * On cr�e une variable session de type Session qui prend pour valeur une
		 * session en appelant la m�thode openSession() sur la sessionFactory sf.
		 * 
		 */

		Session session = sf.openSession();

		/*
		 * On appelle la m�thode beginTransaction() sur la session pour pouvoir envoyer
		 * un groupe de requ�tes vers la base de donn�es.
		 */

		session.beginTransaction();

		/*
		 * On cr�e une instance de la classe UserDao. On cr�e trois nouveaux
		 * utilisateurs avec la m�thode addNewUser() en lui passant les arguments de la
		 * m�thode.
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
		 * l�instance user les valeurs des arguments correspondants.
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
		 * On met � jour l�email de l�utilisateur en appelant la m�thode updateUser de
		 * l�objet dao en passant l�argument user en argument.
		 */

		dao.updateUser(user);

		// On affiche un message sur la console pour confirmer la r�ussite de l�update.

		System.out.println("User is updated successfully");

		/*
		 * On fait appel � la m�thode deleteUser() pour supprimer le troisi�me
		 * utilisateur qui correspond � la valeur 3 de l�attribut userid.
		 */

		// Delete user by id

		dao.deleteUser(3);

		/*
		 * La m�thode toString sera appel�e dans la m�thode main de la classe
		 * application lorsque l�on va afficher la liste des utilisateurs ou un
		 * utilisateur � partir de son ID. En effet, lorsque l�on imprime un objet (iter
		 * est un objet de type User et la m�thode getUserById() retourne un objet de
		 * type User), la m�thode System.out.println() fait appel � la m�thode
		 * toString() de la classe de cet objet.
		 * 
		 */

		/*
		 * On fait une boucle for each pour parcourir l�arraylist user avec notre
		 * m�thode getAllUsers();
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
