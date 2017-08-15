package com.massacre.codigotutoria.controllers;

import com.massacre.codigotutoria.utils.CodigoTutoriaConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by saurabh on 9/8/17.
 */
@Controller
@RequestMapping(value="/getimage")
public class ImageController {
    @Value("/images")
    public String staticpath;
    @RequestMapping(value ="/{imageType}/{imageId}", method= RequestMethod.GET,produces= MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String imageId, @PathVariable String imageType){
        System.out.println("Inside getImage");
        System.out.println("image_static_path: "+staticpath);
        String imgPath=staticpath+"/"+imageType+"."+imageId+".png";
        Path path= Paths.get(imgPath);
        byte[] read=null;
        try {
            read= Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }
}
