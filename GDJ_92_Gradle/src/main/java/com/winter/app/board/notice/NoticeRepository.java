package com.winter.app.board.notice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winter.app.board.BoardVO;

public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
}
