package com.woostore.controller;

import com.woostore.entity.commerce.Product;
import com.woostore.services.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;

@RestController
public class ProductController {

    @Value("${image.urlPath}")
    String urlPath;

    @Value("${image.dirPath}")
    String dirPath;

    @Autowired
    ProductService productService;



    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") long id) {
        Product product = productService.findById(id);
        if(product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }




    @GetMapping("/product/image/{fileName:.+}")
    public ResponseEntity<?> getProductImage(@PathVariable("fileName") String filename) {
        Path pathFile = Paths.get(dirPath + filename);
        try {
            Resource resource = new UrlResource(pathFile.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/product/image")
    public ResponseEntity<?> addProductImage(@RequestParam("file") MultipartFile file) {
        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            String oldFilename = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(oldFilename);
            String filename = Integer.toString(LocalTime.now().hashCode(), 16) + Integer.toString(oldFilename.hashCode(), 16) + "." + ext;
            File targetFile = Files.createFile(Paths.get(dirPath + filename)).toFile();
            ImageIO.write(img, ext, targetFile);
            return ResponseEntity.ok(filename);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.status(202).build();
        }
        catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

}
