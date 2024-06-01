package com.hugo83.board_back.entity;

// import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long Bno;

	@Column(name = "Title", length = 200)
	private String title;

	@Column(name="Content")
	private String content;

	@CreatedDate
	@Column(name="CreateDate", updatable = false)
	private LocalDateTime createDate;

	@LastModifiedDate
	@Column(name="ModifyDate")
	private LocalDateTime modifyDate;

	// @OneToMany(mappedBy = "Board", cascade = CascadeType.REMOVE)
	// private List<Reply> replyList;
}
