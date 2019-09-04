package be.technobel.forum.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// dit que la classe sera une table en DB
@Entity
public class Forum {
	// dit que le champ est la PK
	@Id
	// pour avoir une valeur auto-incrémentée
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	// le mappedBy est lié au NOM de la variable se trouvant
	// dans l'autre classe
	// ici : la classe Topic a un champ de type Forum dont le 
	// nom est "forum"
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "forum")
	private Set<Topic> topics = new HashSet<>();
	
	public Forum() {}
	
	public Forum(String title) {
		super();
		this.title = title;
	}

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

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(HashSet<Topic> topics) {
		this.topics = topics;
	}
	
	
}
