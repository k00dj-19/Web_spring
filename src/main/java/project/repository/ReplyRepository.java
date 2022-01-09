package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
}
