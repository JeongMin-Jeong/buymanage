package com.erp.buymanage.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class TblCategoryRepositoryTests {
    @Autowired
    TblCategoryRepository tblCategoryRepository;

    @Autowired
    TblCategoryRepositoryChild tblCategoryRepositoryChild;

    @Test
    public void insertCategoryDummy(){
        IntStream.range(1,5).forEach(i-> {
            TblCategory tblCategory = TblCategory.builder().cname("대분류"+i).build();
            tblCategoryRepository.save(tblCategory);
        });
    }

    @Test
    public void insertCategoryChildDummy(){
        IntStream.range(1,41).forEach(i-> {
            if(i >= 1 && i <= 10){
                TblCategoryChild tblCategoryChild = TblCategoryChild.builder().ccname("1-소분류"+i).pcid(1L).build();
                tblCategoryRepositoryChild.save(tblCategoryChild);
            }
            if(i >= 11 && i <= 20){
                TblCategoryChild tblCategoryChild = TblCategoryChild.builder().ccname("2-소분류"+i).pcid(2L).build();
                tblCategoryRepositoryChild.save(tblCategoryChild);
            }
            if(i >= 21 && i <= 30){
                TblCategoryChild tblCategoryChild = TblCategoryChild.builder().ccname("3-소분류"+i).pcid(3L).build();
                tblCategoryRepositoryChild.save(tblCategoryChild);
            }
            if(i >= 31 && i <= 41){
                TblCategoryChild tblCategoryChild = TblCategoryChild.builder().ccname("4-소분류"+i).pcid(4L).build();
                tblCategoryRepositoryChild.save(tblCategoryChild);
            }
        });
    }

    @Test
    public void testPageDefault(){

        Sort sort1 = Sort.by("cid").ascending();
        Pageable pageable = PageRequest.of(0,100, sort1);

        Page<TblCategory> result = tblCategoryRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("----------------------------");
        for(TblCategory tblCategory : result.getContent()){
            System.out.println(tblCategory);
        }
    }
}




