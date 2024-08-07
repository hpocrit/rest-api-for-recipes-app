package ru.kpfu.itis.gabdullina.MyRestApi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.CommentDto;
import ru.kpfu.itis.gabdullina.MyRestApi.mapper.CommentMapper;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Comment;
import ru.kpfu.itis.gabdullina.MyRestApi.repository.CommentsRepository;
import ru.kpfu.itis.gabdullina.MyRestApi.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> getAllCommentsByRecipeId(Long id) {
        List<Comment> comments = commentsRepository.findAll();
        return comments
                .stream()
                .filter(comment -> comment.getRecipeId().equals(id))
                .map(commentMapper::convertToDto)
                .toList();
    }

    @Override
    public void save(CommentDto commentDto) {
        commentsRepository.save(commentMapper.convertToModel(commentDto));
    }
}
