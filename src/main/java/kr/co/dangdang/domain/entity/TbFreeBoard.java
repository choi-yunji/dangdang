package kr.co.dangdang.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_FREE_BOARD")
@Entity
public class TbFreeBoard {
    @Id
    @Column(name = "FB_ID", length = 20, nullable = false)
    private String fbId;

    @Column(name = "FB_WRITER", length = 100, nullable = false)
    private String fbWriter;

    @Column(name = "FB_TITLE", length = 20, nullable = false)
    private String fbTitle;

    @Column(name = "FB_CONTENTS", length = 1000, nullable = false)
    private String fbContents;

    @Column(name = "INFO_YN", length = 1, nullable = false)
    private String inFoYn;

    @Column(name = "FB_REG_DT",  nullable = false)
    private LocalDateTime fbRegDt;
}
