package com.sunny.blog.board;

import com.sunny.blog.reply.Reply;
import com.sunny.blog.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Lob //대용량 데이터
    private String content;

    @Column
    private String writer;
    
    @ColumnDefault("0")
    private int count; //조회수

    // fetch = FetchType.EAGER: Board를 select하면 User 정보는 바로 갖고온다.
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One
    @JoinColumn(name="user_Id")
    private User user; // DB는 오브젝트 저장 불가. FK, 자바는 오브젝트 저장 가능

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    // reply 테이블의 board가 FK
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reply> reply;


    @Builder
    public Board(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

}
