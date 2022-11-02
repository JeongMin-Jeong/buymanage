package com.erp.buymanage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long pno; // 자동번호부여
    private String pcode; // 품목코드
    private String pname; // 품목이름
    private String ptype1; // 대분류
    private String ptype2; // 중분류
    private String ptype3; // 일반검색
    private String pcontent; // 설명
    private String petc; // 비고
    private LocalDateTime regdate, moddate; // 등록일, 수정일

    //화면에 이미지들도 같이 수집을 해야 함 리스트 이용해서 수집
    //빌더로 인스턴스 생성시 초기화할 값을 정할수 있다.
    @Builder.Default
    private List<ProductImageDTO> imageDTOList = new ArrayList<>();
}
