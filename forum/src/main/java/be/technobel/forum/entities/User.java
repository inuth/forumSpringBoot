package be.technobel.forum.entities;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String password;
	private LocalDate birthDate;
	
	public User() {}
	
	public User(Integer id, String login, String password, LocalDate birthDate) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.birthDate = birthDate;
	}

	public int getAge() {
//		LocalDate today = LocalDate.now();
//		int age = today.getYear() - birthDate.getYear();
//		
//		if(birthDate.getMonthValue() > today.getMonthValue())
//		{
//			age--;
//		}
//		else { 
//			if (birthDate.getMonthValue() == today.getMonthValue() && birthDate.getDayOfMonth() > today.getDayOfMonth()) {
//				age--;
//			}
//		}
//		return age;
		
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
