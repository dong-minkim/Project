package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.model.Promotion.Promotion;
import com.dutmdcjf.backendserver.model.Promotion.PromotionList;
import com.dutmdcjf.backendserver.model.Promotion.PromotionMain;
import com.dutmdcjf.backendserver.model.Promotion.TabDefine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionService {
    private final String MAIN_FILE_PATH = "./excel/main.xlsx";
    private final String SERVICE_FILE_PATH = "./excel/service.xlsx";
    private final String IMAGE_URL = "Image URL";
    private final String ROLLING_IMAGE = "Rolling-Image";

    public PromotionMain getMainPromotion() throws Exception {
        FileInputStream fis = new FileInputStream(new File(MAIN_FILE_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Image URL
        Sheet imageSheet = workbook.getSheetAt(0);
        Row headerRow = imageSheet.getRow(0);

        String imageUrl = null;
        if (headerRow != null && headerRow.getCell(0).getStringCellValue().equals(IMAGE_URL)) {
            Row dataRow = imageSheet.getRow(1);
            if (dataRow != null) {
                imageUrl = dataRow.getCell(0).getStringCellValue();
            }
        }

        // Rolling Image URL
        List<String> rolling = new ArrayList<>();
        Sheet rollingSheet = workbook.getSheetAt(1);
        Row rollingRow = rollingSheet.getRow(0);

        if (headerRow != null && headerRow.getCell(0).getStringCellValue().equals(IMAGE_URL)) {
            int rowIndex = 1;
            while (true) {
                try {
                    Row dataRow = rollingSheet.getRow(rowIndex);
                    String value = dataRow.getCell(0).getStringCellValue();
                    if (!StringUtils.hasText(value)) {
                        break;
                    }
                    rolling.add(value);
                } catch (Exception e) {
                    // Row가 없거나, Cell 없으면 Exception이 발생한다.
                    break;
                }

                rowIndex += 1;
            }
        }

        return new PromotionMain(imageUrl, rolling);
    }

    public PromotionList getServicePromotion() throws Exception {
        FileInputStream fis = new FileInputStream(new File(SERVICE_FILE_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Excel Sheet Tab 관리
        Sheet imageSheet = workbook.getSheetAt(0);
        List<TabDefine> promotionList = new ArrayList<>();

        int rowIndex = 1;
        do {
            Row row = imageSheet.getRow(rowIndex);
            if (row == null) {
                break;
            }
            // 추가할 프로모션 있는지 확인
            String promotionTitle = null;
            String promotionDescription = null;
            String sheetCode = null;
            try {
                promotionTitle = row.getCell(0).getStringCellValue();
                if (!StringUtils.hasText(promotionTitle)) {
                    break;
                }

                // 이하 체크하지 않음
                promotionDescription = row.getCell(1).getStringCellValue();
                sheetCode = row.getCell(2).getStringCellValue();
            } catch (Exception e) {
                break;
            }

            TabDefine tabDefine = new TabDefine();
            Sheet childSheet = workbook.getSheet(sheetCode);

            List<Promotion> promotionProductList = new ArrayList<>();
            int childRowIndex = 1;
            do {
                Row childRow = childSheet.getRow(childRowIndex);
                if (childRow == null) {
                    break;
                }

                try {
                    String title = childRow.getCell(1).getStringCellValue();
                    if (!StringUtils.hasText(title)) {
                        break;
                    }

                    Integer cols = (int) childRow.getCell(0).getNumericCellValue();
                    String description = childRow.getCell(2).getStringCellValue();
                    String url = childRow.getCell(3).getStringCellValue();
                    Integer bigMenuCode = (int) childRow.getCell(4).getNumericCellValue();
                    Integer smallMenuCode = (int) childRow.getCell(5).getNumericCellValue();
                    String imageUrl = childRow.getCell(6).getStringCellValue();
                    promotionProductList.add(new Promotion(cols, title, description, url, bigMenuCode, smallMenuCode, imageUrl));
                } catch (Exception e) {
                    break;
                }

                childRowIndex += 1;
            } while (true);

            tabDefine.setTitle(promotionTitle);
            tabDefine.setDescription(promotionDescription);
            tabDefine.setCode(sheetCode);
            tabDefine.setList(promotionProductList);
            promotionList.add(tabDefine);

            rowIndex += 1;
        } while (true);

        return new PromotionList(promotionList);
    }
}