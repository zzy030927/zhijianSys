package zhijian.test; /**
 *
 * 名称：Demo
 * 描述：测试类
 *
 * @version 1.0
 * @author: Ma xiaoping
 * @datetime: 2024-04-29 15:46
 */

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Demo {

    public static void main(String[] args) {
        try {
            // 打开 Excel 文件
            FileInputStream file = new FileInputStream(new File("/Users/ASUS/Desktop/430017.csv")); // 替换为你的 Excel 文件路径

            // 创建工作簿
            Workbook workbook = WorkbookFactory.create(file);

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 遍历行
            for (Row row : sheet) {
                // 遍历单元格
                for (Cell cell : row) {
                    System.out.print("-----0----"+cell.getCellType());
                    // 根据单元格类型获取值
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print("-----1----"+cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {

                                shijian(cell.getDateCellValue().toString());
//                                zhuan01(cell.getDateCellValue().toString());
                                System.out.print("-----2----"+cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print("-----3----"+cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print("-----4----"+cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print("-----5----"+cell.getCellFormula() + "\t");
                            break;
                        case BLANK:
                            System.out.print("-----6----"+"<BLANK>\t");
                            break;
                        default:
                            System.out.print("-----7----"+"<UNKNOWN>\t");
                    }
                }
                System.out.println("-----9----"); // 换行
            }

            // 关闭文件流
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void shijian(String inputTimeString) {
//        String inputTimeString = "Wed Mar 27 10:59:30 CST 2024";

        // Define input and output date formats
        String inputPattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        String outputPattern = "yyyy-MM-dd HH:mm:ss";

        // Create SimpleDateFormat instances for input and output
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        try {
            // Parse the input time string
            Date date = inputFormat.parse(inputTimeString);

            // Format the parsed date into the desired output format
            String formattedDate = outputFormat.format(date);

            // Print the formatted date
            System.out.println("Formatted Date:----------------- " + formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void zhuan02(long timestamp) {

//        long timestamp = 1679943570; // Example timestamp (Wed Mar 27 10:59:30 CST 2024)

        // Create a Date object from the timestamp
        Date date = new Date(timestamp * 1000); // Convert seconds to milliseconds

        // Create a SimpleDateFormat object to specify the desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Set the time zone if needed
        sdf.setTimeZone(TimeZone.getTimeZone("CST"));

        // Format the date using the SimpleDateFormat object
        String formattedDate = sdf.format(date);

        // Print the formatted date
        System.out.println("---转年月日--------Formatted Date: " + formattedDate);





    }

    private static void zhuan01(String dateStrin) {
        String dateString = "Wed Mar 27 10:59:30 CST 2024";
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("CST"));

        try {
            Date date = sdf.parse(dateString);
            long timestamp = date.getTime() / 1000; // Convert milliseconds to seconds
            System.out.println("----转时间戳---------Timestamp: " + timestamp);

            zhuan02(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }






}





