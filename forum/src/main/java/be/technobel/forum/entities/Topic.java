package be.technobel.forum.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String title;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	// si le nom est écrit en lowerCamelCase, il va mettre des '_'
	// exemple : idForum => id_forum
	// attention, si on change le nom d'une colonne, il faut en tenir
	// compte dans le script d'insertion de données
	@JoinColumn(name = "idforum")
	private Forum forum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
}
