import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class TradeTracker 
{
    public static void main(String[] args) 
    {
        // Create workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Trade History");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Entry Price");
        headerRow.createCell(1).setCellValue("Stop Loss Price");
        headerRow.createCell(2).setCellValue("Take Profit Price");
        headerRow.createCell(3).setCellValue("Risk to Reward Ratio");
        headerRow.createCell(4).setCellValue("Profit and Loss");
        headerRow.createCell(5).setCellValue("Before Trade Picture");
        headerRow.createCell(6).setCellValue("After Trade Picture");

        // Create sample trade data
        Object[][] trades = 
        {
                {100.0, 90.0, 120.0, 2.0, 200.0, "before_trade.png", "after_trade.png"},
                {50.0, 45.0, 60.0, 1.5, -100.0, "before_trade.png", "after_trade.png"},
                // Add more trade data here
        };

        // Populate trade data rows
        int rowCount = 1;
        for (Object[] trade : trades) 
        {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            for (Object field : trade) 
            {
                if (field instanceof Double) 
                {
                    row.createCell(columnCount++).setCellValue((Double) field);
                } else if (field instanceof String) 
                {
                    row.createCell(columnCount++).setCellValue((String) field);
                }
            }
        }

        // Auto-size columns
        for (int i = 0; i < 7; i++) 
        {
            sheet.autoSizeColumn(i);
        }

        // Save the workbook to a file
        try (FileOutputStream outputStream = new FileOutputStream("trade_history.xlsx")) 
        {
            workbook.write(outputStream);
            System.out.println("Trade history saved successfully.");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}