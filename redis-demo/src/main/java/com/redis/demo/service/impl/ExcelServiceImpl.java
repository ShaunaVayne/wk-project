package com.redis.demo.service.impl;

import com.redis.demo.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/7/11 9:50
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {

    /**
     * 解析excel转换成集合
     *
     * @param wb
     * @param clazz
     */
    @Override
    public <T> List<T> parseExcel(Workbook wb, Class<T> clazz) {
        Sheet sheet = wb.getSheetAt(0);//得到第一个sheet
        int firstRowNum = sheet.getFirstRowNum() + 1;//第一行是列名,不读
        int lastRowNum = sheet.getLastRowNum();//最后一行
        int colNum = sheet.getRow(0).getPhysicalNumberOfCells();
        List<T> list = new ArrayList<>();
        for(int index = firstRowNum; index <= lastRowNum;index++) {
            Row row = sheet.getRow(index);
            int j = 0;
            String[] data = new String[colNum];
            while (j < colNum) {
                data[j] = row.getCell(j).toString();
                j++;
            }
            try {
                Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
                Constructor<T> constructor = Arrays.stream(constructors).filter(e -> {
                    return e.getParameterCount() > 0;
                }).findFirst().get();
                T t = constructor.newInstance(data);
                list.add(t);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
