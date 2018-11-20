package com.hang.common.service;

import com.hang.common.enums.JsonResultEnum;
import com.hang.common.exception.JsonException;
import org.springframework.stereotype.Service;

/**
 * 测试事务
 */
@Service
public class TestService {

    /*
    * 根据ID查询对应信息
    * */
    public void testResult(Integer age) throws Exception{
        if(age < 12) {
            throw new JsonException(JsonResultEnum.ADMIN_USER_NULL);
        }
    }

}
