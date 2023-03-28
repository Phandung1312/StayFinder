package com.finalproject.StayFinderApi.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.StayFinderApi.dto.AccountRespone;
import com.finalproject.StayFinderApi.dto.CommentRequest;
import com.finalproject.StayFinderApi.dto.CommentResponse;
import com.finalproject.StayFinderApi.entity.Account;
import com.finalproject.StayFinderApi.entity.Comment;
import com.finalproject.StayFinderApi.entity.Post;
import com.finalproject.StayFinderApi.repository.AccountRepository;
import com.finalproject.StayFinderApi.repository.CommentRepository;
import com.finalproject.StayFinderApi.repository.PostRepository;
import com.finalproject.StayFinderApi.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private PostRepository postRepo;

	@Override
	public List<CommentResponse> getCommentByPostId(long postId) {
		List<Comment> comments = commentRepo.findByPostId(postId);
		System.out.println(comments.size());
		Collections.sort(comments, new Comparator<Comment>() {
			@Override
			public int compare(Comment o1, Comment o2) {
				return o2.getCommentTime().compareTo(o1.getCommentTime());
			}
		});
		List<CommentResponse> commentResponses = comments.stream().map(i -> {
			AccountRespone accountRespone = new AccountRespone(i.getAccount().getUsername(), i.getAccount().getName(),
					i.getAccount().getAvatar());

			CommentResponse commentResp = new CommentResponse(i.getId(), i.getPost().getId(), accountRespone,
					i.getContent(), i.getCommentTime(), i.getImage());
			return commentResp;
		}).collect(Collectors.toList());

		return commentResponses;
	}

	@Override
	public boolean deleteCommentById(long id) {
		commentRepo.deleteById(id);
		return true;
	}

	@Override
	public CommentResponse addComment(CommentRequest commentRequest) {
		Optional<Post> postOptional = postRepo.findById(commentRequest.getPostId());
		Optional<Account> accountOptional = accountRepo.findById(commentRequest.getAccountId());
		if(postOptional.isPresent() && accountOptional.isPresent())
		{
			Comment comment = commentRepo.save(new Comment(0, postOptional.get(), accountOptional.get(), commentRequest.getContent(), new Date(), commentRequest.getImage()));
			
			return new CommentResponse(comment.getId(), commentRequest.getPostId(),new AccountRespone(accountOptional.get().getUsername(), accountOptional.get().getName(), accountOptional.get().getAvatar()) , comment.getContent(), comment.getCommentTime(), comment.getImage());
		}
		else
			throw new RuntimeException("Can't add comment");
	}

}