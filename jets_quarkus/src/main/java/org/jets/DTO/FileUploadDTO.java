package org.jets.DTO;

import jakarta.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import java.io.InputStream;

public class FileUploadDTO {

    @FormParam("filename")
    @PartType("text/plain")
    public String filename;

    @FormParam("file")
    @PartType("application/octet-stream")
    public InputStream file;
}