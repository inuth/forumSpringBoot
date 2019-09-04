package be.technobel.forum.mappers;

import be.technobel.forum.entities.Topic;
import be.technobel.forum.forms.TopicForm;

public class TopicMapper {
	
	// But : transformer un objet Topic en TopicForm
	public static TopicForm entityToForm(Topic topic) {
		TopicForm tf = new TopicForm();
		tf.setId(topic.getId());
		tf.setTitle(topic.getTitle());
		tf.setDescription(topic.getDescription());
		return tf;
	}
	
	// But : transformer un objet TopicForm en Topic
	// à utiliser pour la création
	// Attention, ne pas le faire pour l'update car on "perd" le lien vers
	// le forum
	public static Topic formToEntity(TopicForm topicForm) {
		Topic t = new Topic();
		t.setId(topicForm.getId());
		t.setTitle(topicForm.getTitle());
		t.setDescription(topicForm.getDescription());
		return t;
	}
}
