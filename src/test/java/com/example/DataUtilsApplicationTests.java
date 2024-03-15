package com.example;

import com.example.entity.AccountDTO;
import com.example.entity.AccountVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class DataUtilsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void contextLoads1() {
        AccountDTO accountDTO = new AccountDTO(1L,"test","123456","男","112@qq.com","user",new Date(),1);
        AccountVO accountVO = accountDTO.asTargetObject(AccountVO.class,v->{
            v.setGenderNum(Objects.equals(accountDTO.getGender(), "男") ? "1" : "0");
        });
        System.out.println(accountVO);
    }


    @Test
    void contextLoads2() {
        AccountDTO accountDTO = new AccountDTO(1L,"test","123456","男","112@qq.com","user",new Date(),0);
        AccountDTO accountDTO2 = new AccountDTO(2L,"test2","123456","女","112@qq.com","admin",new Date(),1);
        List<AccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(accountDTO);
        accountDTOList.add(accountDTO2);
        List<AccountVO> list = accountDTOList.stream().map(source -> source.asTargetObject(AccountVO.class, v-> {
            v.setGenderNum(Objects.equals(source.getGender(), "男") ? "1" : "0");
        })).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    @Test
    void contextLoads3() {
        AccountDTO accountDTO = new AccountDTO(1L,"test","123456","男","112@qq.com","user",new Date(),1);
        AccountDTO accountDTO2 = new AccountDTO(2L,"test2","123456","女","112@qq.com","admin",new Date(),0);
        Set<AccountDTO> accountDTOSet = new HashSet<>();
        accountDTOSet.add(accountDTO);
        accountDTOSet.add(accountDTO2);
        Set<AccountVO> set = accountDTOSet.stream().map(source -> source.asTargetObject(AccountVO.class, v-> {
            v.setGenderNum(Objects.equals(source.getGender(), "男") ? "1" : "0");
        })).collect(Collectors.toSet());
        set.forEach(System.out::println);
    }


}
