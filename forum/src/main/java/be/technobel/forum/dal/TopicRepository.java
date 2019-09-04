package be.technobel.forum.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.technobel.forum.entities.Topic;

// CrudRepository
// PagingAndSortingRepository
// JpaRepository
@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
	
}
