package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Abstract parent class of student and faculty, contains the common information
 * between a student and a faculty member (a user)
 * 
 * @author Kunal Kapoor
 *
 */
public abstract class User {

	/** first name of the user. */
	private String firstName;
	/** last name of the user */
	private String lastName;
	/** id of the user */
	private String id;
	/** email of the user */
	private String email;
	/** user's password */
	private String password;

	/**
	 * Constructor for the user class.
	 * 
	 * @param firstName user's first name
	 * @param lastName  user's last name
	 * @param id        user's id number
	 * @param email     user's email
	 * @param password  user's password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Gets the first name of the user
	 * 
	 * @return firstName user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name of the user
	 * 
	 * @return lastName the user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the user's id number
	 * 
	 * @return id user's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets email of the user
	 * 
	 * @return email user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email
	 * 
	 * @param email the user's email
	 */
	public void setEmail(String email) {
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		int emailCorrectO = 0;
		int emailCorrectT = 0;
		int indexO = 0;
		int indexT = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.substring(i, i + 1).equals("@")) {
				emailCorrectO = 1;
				indexO = i;
			}
			if (email.substring(i, i + 1).equals(".")) {
				emailCorrectT = 1;
				indexT = i;
			}
		}
		if (emailCorrectO == 1 && emailCorrectT == 1 && indexO < indexT) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Invalid email");
		}
	}

	/**
	 * Gets the password of the current user
	 * 
	 * @return password as a string
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the user's password
	 * 
	 * @param password user's password
	 */
	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * sets the first name of the user
	 * 
	 * @param firstName user's first name
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the last name
	 * 
	 * @param lastName user's last name
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * sets the user's id number
	 * 
	 * @param id the user's id
	 */
	protected void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}