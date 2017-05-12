package com.woostore.controller;

import com.woostore.entity.Product;
import com.woostore.services.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;

@Controller
@Path("/product")
@ConfigurationProperties(prefix = "image")
public class ProductController {

    String urlPath;
    String dirPath;

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    @Autowired
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        return Response.ok(productService.getProducts()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") long id) {
        Product product = productService.findById(id);
        if(product != null) {
            return Response.ok(product).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/image/{fileName}")
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response getProductImage(@PathParam("fileName") String filename) {
        File file = Paths.get(dirPath + filename).toFile();
        if (file.exists()) {
            Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
            responseBuilder.header("Content-Disposition", "attachment; filename=" + filename);
            return responseBuilder.build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        return Response.ok(productService.addProduct(product)).build();
    }

    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addProductImage(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition cdh) {
        try {
            BufferedImage img = ImageIO.read(fileInputStream);
            String oldFilename = cdh.getFileName();
            String ext = FilenameUtils.getExtension(oldFilename);
            String filename = Integer.toString(LocalTime.now().hashCode(), 16) + Integer.toString(oldFilename.hashCode(), 16) + "." + ext;
            File targetFile = Files.createFile(Paths.get(dirPath + filename)).toFile();
            ImageIO.write(img, ext, targetFile);
            return Response.ok(filename).build();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return Response.status(202).build();
        }
        catch (IOException e) {
            return Response.serverError().build();
        }
    }
}
