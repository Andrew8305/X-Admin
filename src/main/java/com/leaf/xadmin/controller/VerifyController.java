package com.leaf.xadmin.controller;

import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.utils.verify.VerifyPictureUtil;
import com.leaf.xadmin.vo.ResponseResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author leaf
 * <p>date: 2018-02-08 18:46</p>
 * <p>version: 1.0</p>
 */
@Api(value = "验证请求相关", description = "/verify")
@RestController
@RequestMapping("verify")
@CrossOrigin
@Slf4j
public class VerifyController {
    private static final ThreadLocal<String> VERIFY_CODE_THREAD_LOCAL = new ThreadLocal<>();

    @Autowired
    private VerifyPictureUtil verifyPictureUtil;

    @ApiOperation(value = "生成Base64验证码")
    @PostMapping(value = "generate")
    public ResponseResultVO generate() {
        // 绘制验证图片
        Map<String, Object> resultMap = verifyPictureUtil.draw();
        String code = (String) resultMap.get(VerifyPictureUtil.VERIFY_CODE_VALUE);
        if (code != null && !code.isEmpty()) {
            VERIFY_CODE_THREAD_LOCAL.set(code);
        }

        // 返回Base64编码请求
        return ResponseResultUtil.success(resultMap.get(VerifyPictureUtil.VERIFY_ENCODE_RESULT));
    }

    @ApiOperation(value = "校验验证码合法性")
    @PostMapping(value = "valid")
    public ResponseResultVO valid(@RequestParam("code") String code) {
        String attribute = VERIFY_CODE_THREAD_LOCAL.get();
        if (attribute != null && !attribute.isEmpty() && attribute.equalsIgnoreCase(code)) {
            VERIFY_CODE_THREAD_LOCAL.remove();
            return ResponseResultUtil.success(Boolean.TRUE);
        }
        return ResponseResultUtil.fail(Boolean.FALSE);
    }
}
