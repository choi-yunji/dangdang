package kr.co.dangdang.domain.nativeRepository;

import kr.co.dangdang.domain.entity.TbReplyInfo;
import kr.co.dangdang.web.reply.dto.ReplyResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TbReplyInfoNativeRepository extends JpaRepository<TbReplyInfo, Long> {

    @Query(value="" +
            "SELECT  " +
            " REPLY_ID" +
            ",BOARD_TYPE" +
            ",REPLY " +
            ",CRE_ID " +
            ",CRE_DT " +
            ",DATEDIFF(NOW(),CRE_DT) AS DATE_DIFF " +
            ",TIMESTAMPDIFF(MINUTE , NOW(), CRE_DT) AS TIME_DIFF" +
            ",(SELECT USER_NICKNAME FROM TB_USER_INFO WHERE USER_ID = CRE_ID) AS USER_NICKNAME " +
            ",(SELECT IMAGE_ID FROM TB_USER_INFO WHERE USER_ID = CRE_ID) AS PROFILE_IMG_ID " +
            "FROM TB_REPLY_INFO " +
            "WHERE BOARD_ID = :boardId " +
            "AND BOARD_TYPE = :boardType " +
            "ORDER BY REPLY_ID DESC "
            , nativeQuery = true)
    List<Map<String, Object>> findAllByBoardIdAndBoardType(Long boardId, String boardType);

}
