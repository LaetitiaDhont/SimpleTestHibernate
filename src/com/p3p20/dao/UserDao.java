package com.p3p20.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.p3p20.model.User;
import com.p3p20.util.HibernateUtil;

public class UserDao {

	// Data Access Object
	// Le DAO est simplement un pattern qui s�pare la partie applicative � celle
	// d'acc�s aux donn�es.

	/* Methode pour ajouter un utilisateur */

	public void addUser(User user) {

		// Une transaction est une op�ration quand on veut travailler sur une BDD.
		// Ces op�rations seront pr�sentes sur toutes les m�thodes de la class UserDao.

		Transaction trns = null;

		// La session est une connexion � la base de donn�es
		// Chaque session doit �tre ouverte avec la m�thode openSession() puis ferm�e
		// avec la m�thode close().

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			// La m�thode beginTransaction() permet d'ouvrir une transaction � la session
			// courante.

			trns = session.beginTransaction();

			// La m�thode save est utilis�e pour sauvegarder une entit� dans la base de
			// donn�es. Elle n'est pas obligatoirement dans une transaction.
			// Elle sauvegardera dans cette exemple une query de type INSERT.

			session.save(user);

			// .getTransaction().commit() met � jour les donn�es dans la base de donn�es et
			// ex�cute les query sauvegard�es via le .save. Une fois que le commit est
			// pass�, il n�est plus possible de faire un retour en arri�re. De plus le
			// commit ferme la transaction.

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (trns != null) {

				// La m�thode rollback() annule toutes les modifications apport�es apr�s la
				// donn�e SQLServerSavepoint objet a �t� d�fini.

				trns.rollback();
			}
			e.printStackTrace();
		}
	}

	/* M�thode pour supprimer un utilisateur */

	public void deleteUser(int userid) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			// ici, session.load() permet de r�cup�rer un objet, que l�on typecast en user,
			// depuis la base de donn�es.
			
			User user = (User) session.load(User.class, new Integer(userid));
			
			//ensuite .delete() permet de pr�parer une query de type delete.
			
			session.delete(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		}
	}

	/* Methode pour faire un update sur l� utilisateur */
	public void updateUser(User user) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		}
	}

	/* Recuperer tous les utilisateurs */
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			users = session.createQuery("from User").list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return users;
	}

	/* Recuperer des utilisateurs par Id */
	public User getUserById(int userid) {
		User user = null;
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			String queryString = "from User where id = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", userid);
			user = (User) query.uniqueResult();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return user;
	}
}
