package yeinyeonha.SMooD.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yeinyeonha.SMooD.domain.Post;
import yeinyeonha.SMooD.domain.User;
import yeinyeonha.SMooD.dto.PostRequestDto;
import yeinyeonha.SMooD.dto.PostResponseDto;
import yeinyeonha.SMooD.repository.PostRepository;
import yeinyeonha.SMooD.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    //게시글 생성
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, Long userId) {
        User user = userRepository.findById(userId).get();
        Post post = postRepository.save(PostRequestDto.toEntiy(postRequestDto, user));
        return new PostResponseDto(post);
    }
    //게시글 수정
    @Transactional
    public PostResponseDto updatePost(PostRequestDto postRequestDto, Long postId) {
        Post post = postRepository.findById(postId).get();
        if (postRequestDto.getCategory() != null) post.updateCategory(postRequestDto.getCategory());
        if (postRequestDto.getStore() != null) post.updateStore(postRequestDto.getStore());
        if (postRequestDto.getTitle() != null) post.updateTitle(postRequestDto.getTitle());
        if (postRequestDto.getContents() != null) post.updateContents(postRequestDto.getContents());
        return new PostResponseDto(post);
    }
    //게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllPosts() {
        List<Post> post = postRepository.findAll();
        return post.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
    //게시글 단건 조회
    @Transactional(readOnly = true)
    public PostResponseDto findPostBypostId(Long postId) {
        Post post = postRepository.findById(postId).get();
        return new PostResponseDto((post));
    }
}