package kr.co.dangdang.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "TB_DB_INFO")
@Entity
public class TbDbInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOG_ID", length = 20, nullable = false)
    private Long dogId;

    @Column(name = "DOG_CONTENTS", length = 1000, nullable = false)
    private String dogContents;

    @Column(name = "DOG_DELETE_YN", length = 1, nullable = false)
    private String dogDeleteYn;

    @Builder
    public TbDbInfo(
            String dogContents,
            String dogDeleteYn
    ){
        this.dogContents = dogContents;
        this.dogDeleteYn = dogDeleteYn;
    }
}
