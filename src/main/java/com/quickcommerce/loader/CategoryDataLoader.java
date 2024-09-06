package com.quickcommerce.loader;

import com.quickcommerce.entity.Category;
import com.quickcommerce.entity.SubCategory;
import com.quickcommerce.repository.CategoryRepository;
import com.quickcommerce.repository.SubCategoryRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDataLoader implements ApplicationRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    private static String toKebabCase(String text) {
        return text
                .replace(" ", "-")
                .replace("&", "and")
                .replaceAll("[^a-zA-Z-]", "")
                .toLowerCase()
                .concat(".png");
    }

    private static List<CSVRecord> getCsvRecords(String fileName) throws IOException {
        File file = ResourceUtils.getFile("classpath:csv/" + fileName + ".csv");
        FileReader fileReader = new FileReader(file);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
        List<CSVRecord> csvRecords = csvParser.stream().skip(1).collect(Collectors.toList());
        return csvRecords;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        try {
            if (categoryRepository.count() > 0) {
                return;
            }

            categoryRepository.deleteAll();
            subCategoryRepository.deleteAll();
            categoryRepository.resetAutoIncrement();
            subCategoryRepository.resetAutoIncrement();

            List<CSVRecord> categoryCsvRecords = getCsvRecords("bulk-data-category");
            List<CSVRecord> subCategoryCsvRecords = getCsvRecords("bulk-data-sub-category");

            List<Category> categories = new ArrayList<>();

            for (CSVRecord record : categoryCsvRecords) {
                Category category = Category.builder()
                        .id(Integer.parseInt(record.get(0)))
                        .name(record.get(1))
                        .isFeatured(true)
                        .isActive(true)
                        .thumbnail("/category/thumbnails/" + toKebabCase(record.get(1)))
                        .build();

                List<SubCategory> subCategories = subCategoryCsvRecords.stream()
                        .filter(subCategory -> {
                            return Integer.parseInt(subCategory.get(2)) == Integer.parseInt(record.get(0));
                        })
                        .map(subCategory -> {
                            return SubCategory.builder()
                                    .isActive(true)
                                    .name(subCategory.get(1))
                                    .id(Integer.parseInt(subCategory.get(0)))
                                    .thumbnail("/sub-category/thumbnails/" + toKebabCase(subCategory.get(1)))
                                    .createdAt(Instant.now())
                                    .category(category)
                                    .build();
                        })
                        .collect(Collectors.toList());

                category.setSubCategory(subCategories);

                categories.add(category);
            }

            categoryRepository.saveAll(categories);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("FileNotFoundException Occurred");
        } catch (IOException ioException) {
            System.out.println("IOException Occurred");
        }
    }
}
