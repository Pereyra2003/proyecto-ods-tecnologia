package com.vecinal.alertas.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {
        
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));
    }

    public String subirImagen(MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return null;
        }
        try {
            // Agregamos un bloque try-catch interno para capturar cualquier fallo de comunicación con Cloudinary
            Map<?, ?> resultado = cloudinary.uploader().upload(archivo.getBytes(), ObjectUtils.emptyMap());
            return resultado.get("secure_url").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si falla la subida, devuelve null para que el reporte se guarde al menos sin foto
        }
    }
}