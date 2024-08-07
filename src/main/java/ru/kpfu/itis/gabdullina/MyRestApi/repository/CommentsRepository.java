package ru.kpfu.itis.gabdullina.MyRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Comment;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Recipe;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {



}
