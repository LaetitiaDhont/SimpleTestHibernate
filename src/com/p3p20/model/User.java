/* le nom de package ou on a créé notre classe*/
package com.p3p20.model;

/* Bibliothèque pour qu’on puisse utiliser le type de données Date*/
import java.util.Date;

/**
 * Class User
 */
public class User {

	// Les attributs de mon utilisateur

	private int userid;
	private String firstName;
	private String lastName;
	private Date dob;
	private String email;

	/**
	 * Les getters /setters pour les attributs de cette classe
	 */

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/** Ici, on override (redéfinit) la méthode toString() qui existe déjà par
	* héritage pour qu’elle retourne ce que l’on souhaite.
	*/

	@Override

	/** La méthode toString est définie dans la classe Object ; en conséquence toutes
	* les classes Java en héritent. Initialement, elle renvoie le nom de la classe
	*de l'objet concerné suivi de l'adresse de cet objet.
	*/
	
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + "]";
	}
}
