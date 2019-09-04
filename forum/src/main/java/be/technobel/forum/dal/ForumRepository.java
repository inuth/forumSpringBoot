package be.technobel.forum.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.technobel.forum.entities.Forum;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Integer>{

}
