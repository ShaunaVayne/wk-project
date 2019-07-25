package com.redis.demo.api;

import com.alibaba.fastjson.JSON;
import com.redis.demo.bean.GroupClass;
import com.redis.demo.service.ExcelService;
import com.redis.demo.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/10 15:40
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@RestController
@Slf4j
public class ExcelApi {

    @Autowired
    private ExcelService excelService;

    @PostMapping(value = "/parseExcel")
    public List<GroupClass> parseExcel(MultipartFile mf) {
        String filename = mf.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        Workbook wb = null;
        try(InputStream is = mf.getInputStream()) {
            switch (suffix){
                case ".xls":
                    wb = new HSSFWorkbook(is);
                    break;
                case ".xlsx":
                    wb = new XSSFWorkbook(is);
                    break;
                default:
                    wb = null;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(wb, "表格为空");
        List<GroupClass> groupClassList = excelService.parseExcel(wb, GroupClass.class);
        //班号只能是数字加字母, 不论大小写
        String regex_classCode = "^[0-9a-zA-Z]+$";
        //上课时间为日期, 精确到天
        String regex_classTime = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$";
        //时间段为具体的时间
        String regex_classPeriod = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9])-([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";
        List<GroupClass> regexList = groupClassList.stream().filter(e -> {
            return !e.getClassCode().matches(regex_classCode) || !e.getClassTime().matches(regex_classTime) || !e.getClassPeriod().matches(regex_classPeriod);
        }).collect(Collectors.toList());
        log.info("{}:返回结果:{}", "", JSON.toJSONString(groupClassList));
        log.info("{}:返回结果:{}", "", JSON.toJSONString(regexList));
        if(ListUtil.isNotBlankList(regexList))
            return null;
        return groupClassList;
    }
}
