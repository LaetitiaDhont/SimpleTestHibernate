/* le nom de package ou on a cr�� notre classe*/
package com.p3p20.model;

/* Biblioth�que pour qu�on puisse utiliser le type de donn�es Date*/
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

	/** Ici, on override (red�finit) la m�thode toString() qui existe d�j� par
	* h�ritage pour qu�elle retourne ce que l�on souhaite.
	*/

	@Override

	/** La m�thode toString est d�finie dans la classe Object ; en cons�quence toutes
	* les classes Java en h�ritent. Initialement, elle renvoie le nom de la classe
	*de l'objet concern� suivi de l'adresse de cet objet.
	*/
	
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + "]";
	}
}
