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
	// Le DAO est simplement un pattern qui sépare la partie applicative à celle
	// d'accès aux données.

	/* Methode pour ajouter un utilisateur */

	public void addUser(User user) {

		// Une transaction est une opération quand on veut travailler sur une BDD.
		// Ces opérations seront présentes sur toutes les méthodes de la class UserDao.

		Transaction trns = null;

		// La session est une connexion à la base de données
		// Chaque session doit être ouverte avec la méthode openSession() puis fermée
		// avec la méthode close().

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			// La méthode beginTransaction() permet d'ouvrir une transaction à la session
			// courante.

			trns = session.beginTransaction();

			// La méthode save est utilisée pour sauvegarder une entité dans la base de
			// données. Elle n'est pas obligatoirement dans une transaction.
			// Elle sauvegardera dans cette exemple une query de type INSERT.

			session.save(user);

			// .getTransaction().commit() met à jour les données dans la base de données et
			// exécute les query sauvegardées via le .save. Une fois que le commit est
			// passé, il n’est plus possible de faire un retour en arrière. De plus le
			// commit ferme la transaction.

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (trns != null) {

				// La méthode rollback() annule toutes les modifications apportées après la
				// donnée SQLServerSavepoint objet a été défini.

				trns.rollback();
			}
			e.printStackTrace();
		}
	}

	/* Méthode pour supprimer un utilisateur */

	public void deleteUser(int userid) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			// ici, session.load() permet de récupérer un objet, que l’on typecast en user,
			// depuis la base de données.
			
			User user = (User) session.load(User.class, new Integer(userid));
			
			//ensuite .delete() permet de préparer une query de type delete.
			
			session.delete(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		}
	}

	/* Methode pour faire un update sur l’ utilisateur */
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
