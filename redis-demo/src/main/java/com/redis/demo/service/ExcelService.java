package com.redis.demo.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface ExcelService {

    /**
     * 解析excel转换成集合
     * @param wb
     * @param
     */
    <T>List<T> parseExcel(Workbook wb, Class<T> clazz);
}
