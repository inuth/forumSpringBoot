package be.technobel.forum.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicForm {
	private Integer id;
	@NotNull(message = "title obligatoire")
	@Size(min = 3, max = 100, message = "taille entre 3 et 100")
	private String title;
	private String description;
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
	
	
}
