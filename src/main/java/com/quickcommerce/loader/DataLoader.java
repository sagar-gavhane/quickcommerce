package com.quickcommerce.loader;

import com.quickcommerce.entity.Brand;
import com.quickcommerce.entity.Category;
import com.quickcommerce.entity.Product;
import com.quickcommerce.repository.BrandRepository;
import com.quickcommerce.repository.CategoryRepository;
import com.quickcommerce.repository.ProductRepository;
import com.quickcommerce.repository.SubCategoryRepository;
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
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

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
//    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        boolean flag = true;

        if (flag) return;

        try {
            List<CSVRecord> productCsvRecords = getCsvRecords("bulk-data-bb-products");
            List<Product> products = new ArrayList<>();
            Map<String, Brand> brandMap = new HashMap<>();
            Map<String, Category> categoryMap = new HashMap<>();

            for (CSVRecord record : productCsvRecords) {
                if (!record.isConsistent()) return;

                Brand brand;
                Category category;

                if (!brandMap.containsKey(record.get(4))) {
                    brand = Brand.builder()
                            .name(record.get(4))
                            .thumbnail("/brand/thumbnails/" + toKebabCase(record.get(4)))
                            .build();
                    brandMap.put(record.get(4), brand);
                } else {
                    brand = brandMap.get(record.get(4));
                }

                if (!categoryMap.containsKey(record.get(2))) {
                    category = Category.builder()
                            .name(record.get(2))
                            .isActive(true)
                            .thumbnail("/category/thumbnails/" + toKebabCase(record.get(2)))
                            .build();
                    categoryMap.put(record.get(2), category);
                } else {
                    category = categoryMap.get(record.get(2));
                }

                Float marketPrice = Float.parseFloat(record.get(6));
                Float salePrice = Float.parseFloat(record.get(5));
                Float offer = marketPrice - salePrice;

                Product product = Product.builder()
                        .name(record.get(1))
                        .description(record.get(9))
                        .offer(offer)
                        .marketPrice(marketPrice)
                        .salePrice(salePrice)
                        .sku(Integer.parseInt(record.get(0)))
                        .slug(toKebabCase(record.get(1)) + UUID.randomUUID().toString())
                        .brand(brand)
                        .category(category)
                        .build();

                products.add(product);
            }

            try {
                productRepository.saveAll(products);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

//            if (categoryRepository.count() > 0) {
//                return;
//            }
//
//            categoryRepository.deleteAll();
//            subCategoryRepository.deleteAll();
//            brandRepository.deleteAll();
//            categoryRepository.resetAutoIncrement();
//            subCategoryRepository.resetAutoIncrement();
//            brandRepository.resetAutoIncrement();
//
//            List<CSVRecord> categoryCsvRecords = getCsvRecords("bulk-data-category");
//            List<CSVRecord> subCategoryCsvRecords = getCsvRecords("bulk-data-sub-category");
//            List<CSVRecord> brandCsvRecords = getCsvRecords("bulk-data-brand");
//
//            List<Category> categories = new ArrayList<>();
//            List<Brand> brands = new ArrayList<>();
//
//            for (CSVRecord record : categoryCsvRecords) {
//                Category category = Category.builder()
//                        .id(Integer.parseInt(record.get(0)))
//                        .name(record.get(1))
//                        .isFeatured(true)
//                        .isActive(true)
//                        .thumbnail("/category/thumbnails/" + toKebabCase(record.get(1)))
//                        .build();
//
//                List<SubCategory> subCategories = subCategoryCsvRecords.stream()
//                        .filter(subCategory -> {
//                            return Integer.parseInt(subCategory.get(2)) == Integer.parseInt(record.get(0));
//                        })
//                        .map(subCategory -> {
//                            return SubCategory.builder()
//                                    .isActive(true)
//                                    .name(subCategory.get(1))
//                                    .id(Integer.parseInt(subCategory.get(0)))
//                                    .thumbnail("/sub-category/thumbnails/" + toKebabCase(subCategory.get(1)))
//                                    .createdAt(Instant.now())
//                                    .category(category)
//                                    .build();
//                        })
//                        .collect(Collectors.toList());
//
//                category.setSubCategory(subCategories);
//
//                categories.add(category);
//            }
//
//            categoryRepository.saveAll(categories);
//
//            for (CSVRecord brand : brandCsvRecords) {
//                brands.add(Brand.builder()
//                        .id(Integer.parseInt(brand.get(0)))
//                        .name(brand.get(1))
//                        .thumbnail("/brand/thumbnails/" + toKebabCase(brand.get(1)))
//                        .build());
//            }
//
//            brandRepository.saveAll(brands);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("FileNotFoundException Occurred" + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("IOException Occurred");
        }
    }
}
