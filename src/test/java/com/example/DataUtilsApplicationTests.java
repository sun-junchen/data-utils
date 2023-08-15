package com.example;

import com.example.entity.AccountDTO;
import com.example.entity.AccountVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
class DataUtilsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void contextLoads1() {
        AccountDTO accountDTO = new AccountDTO(1L,"test","123456","男","112@qq.com","user",new Date());
        AccountVO accountVO = accountDTO.asViewObject(AccountVO.class,v->{
            v.setGenderNum(Objects.equals(accountDTO.getGender(), "男") ? "1" : "0");
        });
        System.out.println(accountVO);
    }


    @Test
    void contextLoads2() {
        AccountDTO accountDTO = new AccountDTO(1L,"test","123456","男","112@qq.com","user",new Date());
        AccountDTO accountDTO2 = new AccountDTO(2L,"test2","123456","女","112@qq.com","admin",new Date());
        List<AccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(accountDTO);
        accountDTOList.add(accountDTO2);
        List<AccountVO> list = accountDTOList.stream().map(source -> source.asViewObject(AccountVO.class, v-> {
            v.setGenderNum(Objects.equals(source.getGender(), "男") ? "1" : "0");
        })).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

}
