package com.leaf.xadmin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.entity.DictionaryInfo;
import com.leaf.xadmin.entity.DictionaryType;
import com.leaf.xadmin.service.IDictionaryInfoService;
import com.leaf.xadmin.service.IDictionaryTypeService;
import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.vo.ResponseResultVO;
import com.leaf.xadmin.vo.form.DictionaryInfoSubmitForm;
import com.leaf.xadmin.vo.form.DictionaryTypeSubmitForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leaf
 * <p>date: 2018-03-06 20:08</p>
 * <p>version: 1.0</p>
 */
@Api(value = "字典相关请求", description = "/dict")
@RestController
@RequestMapping("dict")
@CrossOrigin
@Slf4j
public class DictionaryController {
    @Autowired
    private IDictionaryInfoService dictionaryInfoService;
    @Autowired
    private IDictionaryTypeService dictionaryTypeService;

    @ApiOperation(value = "获取全部字典类型列表")
    @GetMapping(value = "getAllType")
    public ResponseResultVO getAllType(@RequestParam("current") int current,
                                       @RequestParam("size") int size) {
        Page<DictionaryType> page = new Page<>(current, size);
        return ResponseResultUtil.success(dictionaryTypeService.queryList(page));
    }

    @ApiOperation(value = "获取指定类型字典信息列表")
    @GetMapping(value = "getAllInfo")
    public ResponseResultVO getAllInfo(@RequestParam("current") int current,
                                       @RequestParam("size") int size,
                                       @RequestParam("type") long type) {
        Page<DictionaryInfo> page = new Page<>(current, size);
        return ResponseResultUtil.success(dictionaryInfoService.queryListByDictionaryType(page, type));
    }

    @ApiOperation(value = "添加新字典类型")
    @PutMapping("addOneType")
    public ResponseResultVO addOneType(@Valid DictionaryTypeSubmitForm dictionaryTypeSubmitForm) {
        DictionaryType dictionaryType = DictionaryType.builder().build();
        BeanUtils.copyProperties(dictionaryTypeSubmitForm, dictionaryType);
        return ResponseResultUtil.success(dictionaryTypeService.addOne(dictionaryType));
    }

    @ApiOperation(value = "添加新字典信息")
    @PutMapping("addOneInfo")
    public ResponseResultVO addOneInfo(@Valid DictionaryInfoSubmitForm dictionaryInfoSubmitForm) {
        DictionaryInfo dictionaryInfo = DictionaryInfo.builder().build();
        BeanUtils.copyProperties(dictionaryInfoSubmitForm, dictionaryInfo);
        return ResponseResultUtil.success(dictionaryInfoService.addOne(dictionaryInfo));
    }

    @ApiOperation(value = "更新字典类型")
    @PostMapping("updateOneType")
    public ResponseResultVO updateOneType(@Valid DictionaryTypeSubmitForm dictionaryTypeSubmitForm) {
        DictionaryType dictionaryType = DictionaryType.builder().build();
        BeanUtils.copyProperties(dictionaryTypeSubmitForm, dictionaryType);
        return ResponseResultUtil.success(dictionaryTypeService.updateOne(dictionaryType));
    }

    @ApiOperation(value = "更新字典信息")
    @PostMapping("updateOneInfo")
    public ResponseResultVO updateOneInfo(@Valid DictionaryInfoSubmitForm dictionaryInfoSubmitForm) {
        DictionaryInfo dictionaryInfo = DictionaryInfo.builder().build();
        BeanUtils.copyProperties(dictionaryInfoSubmitForm, dictionaryInfo);
        return ResponseResultUtil.success(dictionaryInfoService.updateOne(dictionaryInfo));
    }

    @ApiOperation(value = "删除字典类型")
    @DeleteMapping("deleteOneType")
    public ResponseResultVO deleteOneType(@RequestParam("id") Long id) {
        return ResponseResultUtil.success(dictionaryTypeService.deleteOne(id));
    }

    @ApiOperation(value = "删除字典信息")
    @DeleteMapping("deleteOneInfo")
    public ResponseResultVO deleteOneInfo(@RequestParam("id") Long id) {
        return ResponseResultUtil.success(dictionaryInfoService.deleteOne(id));
    }
}
