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
@Table(name = "TB_FB_REPLY")
@Entity
public class TbFbReply {
    @Id
    @Column(name = "FBL_ID", length = 20, nullable = false)
    private String fblId;

    @Column(name = "FB_ID", length = 20, nullable = false)
    private String fbId;

    @Column(name = "FBL_WRITER", length = 100, nullable = false)
    private String fblWriter;

    @Column(name = "FBL_LIKE", length = 20, nullable = false)
    private String fblLike;

    @Column(name = "FBL_REG_DT",  nullable = false)
    private LocalDateTime fblRegDt;
}
