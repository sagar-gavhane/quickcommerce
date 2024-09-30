package com.quickcommerce.service.impl;

import com.quickcommerce.dto.ProductDto;
import com.quickcommerce.entity.Brand;
import com.quickcommerce.entity.Category;
import com.quickcommerce.entity.Product;
import com.quickcommerce.repository.BrandRepository;
import com.quickcommerce.repository.CategoryRepository;
import com.quickcommerce.repository.ProductRepository;
import com.quickcommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> getProducts(Optional<Integer> categoryId, Optional<Integer> page) {
        List<Product> products = productRepository.findByIsActive(true, PageRequest.of(page.orElse(0), 25));
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        return productDtos;
    }

    @Override
    public ProductDto getProduct(Integer productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id (" + productId + ") passed"));

        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return productDto;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        // TODO: use product dto
        // TODO: validate product dto before creating product
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> {
            throw new IllegalArgumentException("Invalid category.id (" + product.getCategory().getId() + ") passed");
        });
        Brand brand = brandRepository.findById(product.getBrand().getId()).orElseThrow(() -> {
            throw new IllegalArgumentException("Invalid brand.id (" + product.getBrand().getId() + ") passed");
        });

        product.setCategory(category);
        product.setBrand(brand);
        product.setSlug(getProductSlug(product.getName()));

        Product addedProduct = productRepository.save(product);
        ProductDto result = modelMapper.map(addedProduct, ProductDto.class);
        return result;
    }

    @Override
    public String getProductSlug(String productName) {
        Pattern WHITESPACE = Pattern.compile("\\s");
        Pattern NON_ALPHANUMERIC = Pattern.compile("[^A-Za-z0-9-]");

        String slug = WHITESPACE.matcher(productName).replaceAll("-");
        slug = NON_ALPHANUMERIC.matcher(slug).replaceAll("");
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.toLowerCase();
        slug = slug + "-" + UUID.randomUUID().toString().substring(0, 8);

        return slug;
    }
}
