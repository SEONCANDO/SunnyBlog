package com.sunny.blog.reply;

import com.sunny.blog.board.Board;
import com.sunny.blog.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length=200)
    private String content;

    @ManyToOne
    @JoinColumn(name="board_Id")
    private Board board;

    @ManyToOne
    @JoinColumn(name="user_Id")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

}
