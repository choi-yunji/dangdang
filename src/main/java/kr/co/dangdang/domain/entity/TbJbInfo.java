package kr.co.dangdang.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_JB_INFO")
@Entity
public class TbJbInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JB_ID", length = 20, nullable = false)
    private Long jbId;

    @Column(name = "JB_CONTENTS", length = 1000, nullable = false)
    private String jbContents;

    @Column(name = "JB_DELETE_YN", length = 1, nullable = false)
    private String jbDeleteYn;

    @Builder
    public TbJbInfo(
            String jbContents,
            String jbDeleteYn
    ){
        this.jbContents = jbContents;
        this.jbDeleteYn = jbDeleteYn;
    }
}
