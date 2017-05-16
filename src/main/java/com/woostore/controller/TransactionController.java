package com.woostore.controller;

import com.woostore.entity.commerce.Transaction;
import com.woostore.services.TransactionService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    @Value("${image.urlPath}")
    String urlPath;

    @Value("${image.dirPath}")
    String dirPath;

    @Value("${image.receiptPath}")
    String receiptPath;

    @Autowired
    TransactionService transactionService;

    @GetMapping("transactions")
    @PreAuthorize("hasRole('STAFF')")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions(null);
    }

    @GetMapping("transactions/pending")
    @PreAuthorize("hasRole('STAFF')")
    public List<Transaction> getTransactionsPending() {
        return transactionService.getTransactionsPending(null);
    }

    @GetMapping("transactions/paid")
    @PreAuthorize("hasRole('STAFF')")
    public List<Transaction> getTransactionsPaid() {
        return transactionService.getTransactionsPaid(null);
    }

    @GetMapping("transactions/{timestamp}")
    @PreAuthorize("hasRole('STAFF')")
    public List<Transaction> getTransactions(@PathVariable("timestamp") String timestamp) {
        Date date = Date.from( Instant.ofEpochSecond( Long.parseLong(timestamp) ) );
        System.out.println(date);
        return transactionService.getTransactions(date);
    }

    @GetMapping("transactions/pending/{timestamp}")
    @PreAuthorize("hasRole('STAFF')")
    public List<Transaction> getTransactionsPending(@PathVariable("timestamp") String timestamp) {
        Date date = Date.from( Instant.ofEpochSecond( Long.parseLong(timestamp) ) );
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return transactionService.getTransactionsPending(date);
    }

    @GetMapping("transactions/paid/{timestamp}")
    @PreAuthorize("hasRole('STAFF')")
    public List<Transaction> getTransactionsPaid(@PathVariable("timestamp") String timestamp) {
        Date date = Date.from( Instant.ofEpochSecond( Long.parseLong(timestamp) ) );
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return transactionService.getTransactionsPaid(date);
    }

    @PostMapping("transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable("id") long id) {
        return transactionService.findById(id);
    }

    @GetMapping("/transaction/owner/{id}")
    public List<Transaction> getTransactionByOwnerId(@PathVariable("id") long id) {
        return transactionService.findAllByOwnerId(id);
    }

    @GetMapping("/transaction/payment/image/{fileName:.+}")
    public ResponseEntity<?> getProductImage(@PathVariable("fileName") String filename) {
        Path pathFile = Paths.get(dirPath + receiptPath + filename);
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


    @PostMapping("/transaction/payment/image")
    public ResponseEntity<?> addReceiptImage(@RequestParam("file") MultipartFile file) {
        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            String oldFilename = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(oldFilename);
            String filename = Integer.toString(LocalTime.now().hashCode(), 16) + Integer.toString(oldFilename.hashCode(), 16) + "." + ext;
            File targetFile = Files.createFile(Paths.get(dirPath + receiptPath + filename)).toFile();
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
