package zhijian.util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zhijian.dao.DefectFileNameDao;
import zhijian.dao.daoImp.DefectFileNameDaoImpl;
import zhijian.entity.DefectFileName;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 *
 * 名称：ExcelReader
 * 描述：读取excel数据
 *
 * @version 1.0
 * @author: Ma xiaoping
 * @datetime: 2024-05-20 11:31
 */


public class ExcelReader {
    public static void readAndStoreExcel(File directory) {
        boolean tag=false;
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        readAndStoreExcel(file);
                    }
                    if (file.isFile() && file.getName().endsWith(".xlsx")) {
                        String filename=file.getName();
                        DefectFileNameDao defectFileNameDao = new DefectFileNameDaoImpl();
                        if(defectFileNameDao.findFileName(filename)){
                            System.out.println("此文件已解析入库过无需重复入库===:" + filename);
                        }else {
                            DefectFileName defectFileName = new DefectFileName();
                            defectFileName.setFileName(filename);
                            defectFileNameDao.add(defectFileName);

                            tag=true;
                            List<List<String>> lists = readExcel(file.getAbsolutePath());
                            if(lists==null || lists.size()==0){
                                System.out.println("过滤掉空文件=======");
                            }else {
                                try {
                                    InputStream inputStream = Files.newInputStream(Paths.get(file.getPath()));

                                    DBconn.addDefectAnalyzeData(lists,inputStream);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    }
                }
            }
        }
        if(tag){
//            Utils.uploadJsonUpload();
        }
    }
    public static List<List<String>> readExcel(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try  {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            if(fileInputStream!=null) {

                Workbook workbook = new XSSFWorkbook(fileInputStream);
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    List<String> rowData = new ArrayList<>();
                    for (Cell cell : row) {
                        if (cell.getCellType() == NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                            rowData.add(cell.getLocalDateTimeCellValue().toString());
                        } else {
                            cell.setCellType(CellType.STRING);
                            rowData.add(cell.getStringCellValue());
                        }
                    }
                    records.add(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
