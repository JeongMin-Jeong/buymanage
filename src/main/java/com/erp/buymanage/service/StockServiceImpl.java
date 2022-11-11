package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.StockDTO;
import com.erp.buymanage.entity.QStock;
import com.erp.buymanage.entity.Stock;
import com.erp.buymanage.repository.StockRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor    //의존성 자동 주입
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Long register(StockDTO dto){

        int sin = dto.getSin();
        String scode = dto.getScode();
        long sno = 0L;

        log.info("DTO---------------------");
        log.info(dto);
        dto.setStock(dto.getSin());
        Stock entity = dtoToEntity(dto);
        Stock history = stockRepository.findByScode(scode);
        List<Stock> stockList = stockRepository.findAll();
        List<String> list = new ArrayList();

        for (Stock stockEntity : stockList ) {
            if(!stockEntity.getScode().equals(scode)) {
                list.add(stockEntity.getScode());
            } else if (stockEntity.getScode().equals(dto.getScode())){
                list.add(dto.getScode());
                stockRepository.updateSin(sin,scode);
                sno = history.getSno();
                System.out.println("확인 필요한 코드 Update==========================" + sno);
                break;
            }
        }
        if (!list.contains(scode)){
            stockRepository.save(entity);
            sno = entity.getSno();
            System.out.println("확인 필요한 코드 Insert==========================" + sno);
        }

        log.info(entity);

        return sno;
    }

    @Override
    public PageResultDTO<StockDTO, Stock> getList(StockPageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("sno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO); //검색 조건 처리

        Page<Stock> result = stockRepository.findAll(booleanBuilder, pageable);

        Function<Stock, StockDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public StockDTO read(Long sno) {

        Optional<Stock> result = stockRepository.findById(sno);

        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    public void modify(StockDTO dto) {

        Optional<Stock> result = stockRepository.findById(dto.getSno());

        if(result.isPresent()){

            Stock entity = result.get();

            entity.changeCode(dto.getScode());
            entity.changeName(dto.getSname());
            entity.changeCate1(dto.getScate1());
            entity.changeCate2(dto.getScate2());
            entity.changeNote(dto.getSnote());

            stockRepository.save(entity);
        }
    }

    @Override
    public void remove(Long sno) {

        stockRepository.deleteById(sno);

    }

    private BooleanBuilder getSearch(StockPageRequestDTO requestDTO) {   //Querydsl  처리

        String type1 = requestDTO.getType1();
        String type2 = requestDTO.getType2();
        String type3 = requestDTO.getType3();
        if(type1 == ""){type1 = null;}
        if(type2 == ""){type2 = null;}
        if(type3 == ""){type3 = null;}

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QStock qStock = QStock.stock1;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qStock.sno.gt(0L);   //sno > 0 조건만 생성

        booleanBuilder.and(expression);

        if(type1 == "" && type2 == "" && type3 == "") {

            return booleanBuilder;

        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type2 != null && type3 != null) {
            if (type3.contains("k")) {
                conditionBuilder.and(qStock.scode.contains(keyword));
            }
            if (type3.equals("l")) {
                conditionBuilder.and(qStock.sname.contains(keyword));
            }
            conditionBuilder.and(qStock.scate2.eq(type2));
        } else if (type2 == null && type3 != null) {
            if (type3.contains("k")) {
                conditionBuilder.and(qStock.scode.contains(keyword));
            }
            if (type3.equals("l")) {
                conditionBuilder.and(qStock.sname.contains(keyword));
            }
        } else if (type2 != null && type3 == null) {
            conditionBuilder.and(qStock.scate2.eq(type2));
        } else if (type2 == null && type3 == null){

        }


        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    @Override
    public void outModify(StockDTO dto) {
        Optional<Stock> result = stockRepository.findById(dto.getSno());

        if (result.isPresent()) {
            Stock entity = result.get();
            entity.changeOut(dto.getSout());
            entity.changeStock2(dto.getSout());
            stockRepository.save(entity);
        }
    }

    @Transactional
    @Override
    public Object getStock(HttpServletResponse response) {

        List<Stock> stockList = stockRepository.findAll(Sort.by(Sort.Direction.DESC, "sno"));
        createExcelDownloadResponse(response, stockList);

        return null;
    }

    @Transactional
    @Override
    public void createExcelDownloadResponse(HttpServletResponse response, List<Stock> stockList) {

        try{
            Workbook workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("자재목록");

            //숫자 포맷은 아래 numberCellStyle을 적용시킬 것이다다
            CellStyle numberCellStyle = workbook.createCellStyle();
            numberCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

            //파일명
            final String fileName = "자재목록";

            //헤더
            final String[] header = {"품목코드", "품목이름", "품목분류1", "품목분류2", "입고수량", "출고수량", "재고수량", "공급단가", "재고금액"};
            Row row = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(header[i]);
            }

            //바디
            for (int i = 0; i < stockList.size(); i++) {
                row = sheet.createRow(i + 1);  //헤더 이후로 데이터가 출력되어야하니 +1

                Stock stock = stockList.get(i);

                Cell cell = null;
                cell = row.createCell(0);
                cell.setCellValue(stock.getScode());

                cell = row.createCell(1);
                cell.setCellValue(stock.getSname());

                cell = row.createCell(2);
                cell.setCellValue(stock.getScate1());

                cell = row.createCell(3);
                cell.setCellValue(stock.getScate2());

                cell = row.createCell(4);
                cell.setCellStyle(numberCellStyle);      //숫자포맷 적용
                cell.setCellValue(stock.getSin());

                cell = row.createCell(5);
                cell.setCellStyle(numberCellStyle);      //숫자포맷 적용
                cell.setCellValue(stock.getSout());

                cell = row.createCell(6);
                cell.setCellStyle(numberCellStyle);      //숫자포맷 적용
                cell.setCellValue(stock.getStock());

                cell = row.createCell(7);
                cell.setCellStyle(numberCellStyle);      //숫자포맷 적용
                cell.setCellValue(stock.getSreturn());

                cell = row.createCell(8);
                cell.setCellStyle(numberCellStyle);      //숫자포맷 적용
                cell.setCellValue(stock.getSreturn()*stock.getStock());
            }


            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8")+".xlsx");
            //파일명은 URLEncoder로 감싸주는게 좋다!

            workbook.write(response.getOutputStream());
            workbook.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}