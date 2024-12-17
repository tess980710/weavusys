//package com.weavusys.hrd.service;
//
//import com.weavusys.hrd.entity.Employee;
//import com.weavusys.hrd.repo.EmployeeRepository;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ExcelService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public void downloadExcel(){
//        List<Employee> employees = employeeRepository.findAll();
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("weavus-group");
//
//        Row header = sheet.createRow(0);
//        header.createCell(0).setCellValue("이름");
//        header.createCell(1).setCellValue("계약형태");
//        header.createCell(2).setCellValue("입사일");
//        header.createCell(3).setCellValue("전환날짜");
//        header.createCell(4).setCellValue("퇴사일");
//        header.createCell(5).setCellValue("퇴직금 총액");
//
//        int rowNum =1;
//        for(Employee employee : employees){
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(employee.getName());
//            row.createCell(1).setCellValue(employee.getEmployeeType().name());
//            row.createCell(2).setCellValue(employee.getExitDate());
//            row.createCell(3).setCellValue(employee.getConversionDate());
//            row.createCell(4).setCellValue(employee.getExitDate());
//
//        }
//    }
//}
