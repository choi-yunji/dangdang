package kr.co.dangdang.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_SB_INFO")
@Entity
public class TbSbInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SB_ID", length = 20, nullable = false)
    private Long sbId;

    @Column(name = "SB_CONTENTS", length = 1000, nullable = false)
    private String sbContents;

    @Column(name = "SB_DELETE_YN", length = 1, nullable = false)
    private String sbDeleteYn;

    @Builder
    public TbSbInfo(
            String sbContents,
            String sbDeleteYn
    ){
        this.sbContents = sbContents;
        this.sbDeleteYn = sbDeleteYn;
    }
}
